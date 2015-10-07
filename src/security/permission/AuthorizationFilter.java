package security.permission;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import security.dao.PermissionDAO;
import security.dao.RoleDAO;
import security.dao.SessionManager;
import security.dao.UserDAO;
import security.dao.UserRoleDAO;
import security.entity.Permission;
import security.entity.Role;
import security.entity.User;
import security.entity.UserRole;

@WebFilter(urlPatterns = { "/*" }, filterName = "authorization-filter")
public class AuthorizationFilter implements Filter {

	public static final long serialVersionUID = -1l;
	private static final Logger logger = Logger.getLogger(AuthorizationFilter.class.getName());
	private static final int REFRESH_PERMISSIONS_TIME = 180;

	public static final String ADMIN_ID = "00000000-0000-0000-0000-000000000000";
	public static final String EVERYONE_ID = "11111111-1111-1111-1111-111111111111";
	public static final String LOGGED_ID = "22222222-2222-2222-2222-222222222222";

	private List<Permission> permissions;
	private long lastRefresh = 0;

	public void init(FilterConfig config) throws ServletException {
		//init
	}

	public void refreshPermissionsIfNeeded() {
		if (System.currentTimeMillis() - lastRefresh > (1000 * REFRESH_PERMISSIONS_TIME)) {
			synchronized (this) {
				if (System.currentTimeMillis() - lastRefresh > (1000 * REFRESH_PERMISSIONS_TIME)) {
					refreshPermissions();
				}
			}
		}
	}

	public synchronized void refreshPermissions() {

		PermissionDAO dao = new PermissionDAO(SessionManager.getInstance().getEntityManager());

		try {
			permissions = dao.findAll();

			if (permissions.isEmpty()) {
				initialPermission();
				permissions = dao.findAll();
			}

			lastRefresh = System.currentTimeMillis();

		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new RuntimeException(e);
		}

	}

	private Permission buildPermission(Role role, String path, String verb, String exclude) {

		Permission permission = new Permission();
		permission.setRole(role);
		permission.setPath(path);
		permission.setVerb(verb);
		permission.setPriority(0);
		permission.setEnabled(true);
		permission.setExclude(exclude);

		return permission;

	}

	private void initialPermission() {
		logger.log(Level.INFO, "creatingDefaultPermission");

		SessionManager session = SessionManager.getInstance();
		session.begin();

		EntityManager em = session.getEntityManager();

		UserDAO userDAO = new UserDAO(em);
		RoleDAO roleDAO = new RoleDAO(em);
		UserRoleDAO userRoleDAO = new UserRoleDAO(em);
		PermissionDAO permissionDAO = new PermissionDAO(em);

		User userAdmin = new User();
		userAdmin.setName("Administrator");
		userAdmin.setLogin("admin");
		userDAO.save(userAdmin);

		Role roleAdmin = new Role();
		roleAdmin.setId(ADMIN_ID);
		roleAdmin.setName("Administrators");

		Role roleEveryOne = new Role();
		roleEveryOne.setId(EVERYONE_ID);
		roleEveryOne.setName("Every One");

		Role roleLogged = new Role();
		roleLogged.setId(LOGGED_ID);
		roleLogged.setName("Logged");

		List<Role> roles = new ArrayList<>();
		roles.add(roleAdmin);
		roles.add(roleEveryOne);
		roles.add(roleLogged);

		for (Role role : roles)
			roleDAO.save(role);

		UserRole role = new UserRole();
		role.setUser(userAdmin);
		role.setRole(roleAdmin);

		userRoleDAO.save(role);

		permissionDAO.save(
				buildPermission(roleEveryOne, "(.)*", "ALL", "/api/rest/(.)*|/views/admin/(.)*|/views/logged/(.)*"));

		permissionDAO.save(
				buildPermission(roleLogged, "/views/logged/(.)*|/api/rest/(.)*", "ALL", "/api/rest/security/(.)*"));

		permissionDAO.save(buildPermission(roleAdmin, "/views/admin/(.)*|/api/rest/security/(.)*", "ALL", null));

		session.commit();

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		if (permissions == null)
			refreshPermissions();

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		String username = httpRequest.getSession().getAttribute("username") == null ? "anonymous"
				: httpRequest.getSession().getAttribute("username").toString();
		String roles = httpRequest.getSession().getAttribute("roles") == null ? EVERYONE_ID
				: httpRequest.getSession().getAttribute("roles").toString();

		try {
			if (filter(httpRequest, httpResponse, username, roles)) {
				chain.doFilter(request, response);
			} else {
				httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		} finally {
			SessionManager.clearInstance();
		}

	}

	private boolean filter(HttpServletRequest request, HttpServletResponse response, String username, String roles) {

		boolean allowed = false;

		String verb = request.getMethod();
		String path = request.getRequestURI().substring(request.getContextPath().length());

		if (path.equals("/auth"))
			refreshPermissionsIfNeeded();

		if (!username.equals("anonymous")) {
			roles += LOGGED_ID;
		}

		for (Permission permission : permissions) {

			if (permission.getEnabled() && roles.contains(permission.getRole().getId())) {
				if (verb.equalsIgnoreCase(permission.getVerb()) || permission.getVerb().equalsIgnoreCase("ALL")) {
					if ((path.matches(permission.getPath())
							&& !path.matches(String.valueOf(permission.getExclude())))) {
						allowed = true;
						break;
					}
				}
			}

		}

		return allowed;
	}

	public void destroy() {
		//destroy	
	}

}