package security.permission;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

import security.dao.SessionManager;
import security.dao.UserDAO;
import security.dao.UserRoleDAO;
import security.entity.User;
import security.entity.UserRole;
import security.oauth2.authcode.OAuth2CodeSettings;
import security.oauth2.authcode.RevokeServlet;
import security.oauth2.flow.OAuth2Client;
import security.oauth2.flow.OAuth2Settings;
import security.rest.exceptions.CustomWebApplicationException;

@SuppressWarnings("unused")
@WebServlet(value = { "/auth", "/logout", "/session" }, name = "auth-servlet")
public class AuthenticationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(AuthenticationServlet.class.getName());

	public void init() throws ServletException {
		//init
	}

	public void destroy() {
		//destroy
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			if (login(username, password)) {
				req.getSession().setAttribute("username", username);
				UserRoleDAO dao = new UserRoleDAO(SessionManager.getInstance().getEntityManager());
				List<UserRole> userRoles = dao.findByLogin(username, Integer.MAX_VALUE, 0);
				String rolesID = AuthorizationFilter.EVERYONE_ID;
				for (UserRole userRole : userRoles) {
					rolesID += "," + userRole.getRole().getId();
				}

				req.getSession().setAttribute("roles", rolesID);

				User user = this.getUserByName(username);

				if (user != null) {
					Gson gson = new Gson();
					JsonElement json = gson.toJsonTree(user);
					resp.setHeader("Content-Type", "application/json");
					resp.getOutputStream().print(json.toString());
				} else {
					resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
				}
			} else {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (Exception e) {
			throw new CustomWebApplicationException(e);
		}
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String uri = req.getRequestURI().substring(req.getContextPath().length());
			if ("/logout".equals(uri)) {
				logout(req, resp);
			} else if ("/session".equals(uri)) {
				session(req, resp);
			} else {
				resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			}
		} catch (Exception e) {
			throw new CustomWebApplicationException(e);
		}
	}

	private boolean login(String username, String password) {
		logger.log(Level.INFO, "login");
		// return authenticateLocal(username, password);
		// return authenticateDataBase(username, password);
		return authenticateOAuth2(username, password);
	}

	private boolean authenticateLocal(String username, String password) {
		boolean result = "admin".equals(username) && "admin".equals(password);
		if (result)
			AuthenticationServlet.createUserIfNotExists(username, password, null);
		return result;
	}

	private boolean authenticateOAuth2(String username, String password) {
		OAuth2Client client = new OAuth2Client(OAuth2Settings.TOKEN_URI, OAuth2Settings.REVOKE_URI,
				OAuth2Settings.CLIENT_ID, OAuth2Settings.CLIENT_SECRET);
		String token = null;
		try {
			token = client.authenticate(username, password);
			createUserIfNotExists(username, username, null);
			logger.log(Level.INFO, token);
		} catch (Exception e) {
			e.printStackTrace();
			logger.log(Level.SEVERE, e.getMessage());
		}
		return (token != null);
	}

	public static void createUserIfNotExists(String name, String username, String pictureURL) {
		// cria usuario, senao existir
		SessionManager session = SessionManager.getInstance();

		UserDAO userDao = new UserDAO(session.getEntityManager());
		List<User> users = userDao.findByAttribute("login", username);
		if (users.isEmpty()) {
			session.begin();
			logger.log(Level.INFO, "Creating user: " + username);
			User userEntity = new User();
			userEntity.setLogin(username);
			userEntity.setName(name);
			userEntity.setPicture(pictureURL);
			userDao.save(userEntity);
			session.commit();
		}
	}

	private boolean authenticateDataBase(String username, String password) {
		UserDAO dao = new UserDAO(SessionManager.getInstance().getEntityManager());
		for (User user : dao.findAll()) {
			if (username.equals(user.getLogin()))
				return true;
		}
		return false;
	}

	private ClientConfig configureClientWithSSL() {
		TrustManager[] certs = new TrustManager[] { new X509TrustManager() {

			@Override
			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}

			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}

			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
			}
		} };

		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance("TLS");
			ctx.init(null, certs, new SecureRandom());
		} catch (java.security.GeneralSecurityException ex) {
			// NoCommand
		}

		HttpsURLConnection.setDefaultSSLSocketFactory(ctx.getSocketFactory());
		ClientConfig config = new DefaultClientConfig();
		try {
			config.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
					new HTTPSProperties(new HostnameVerifier() {
						@Override
						public boolean verify(String hostname, SSLSession session) {
							return true;
						}
					}, ctx));
		} catch (Exception e) {
			// NoCommand
		}

		return config;
	}

	private Client createClientWithSSL() {
		return Client.create(configureClientWithSSL());
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) {
		Object accessToken = request.getSession().getAttribute("accessToken");
		request.getSession().invalidate();
		if (accessToken != null) {
			try {
				OAuth2CodeSettings settings = (OAuth2CodeSettings) request.getSession().getAttribute("settings");
				RevokeServlet.revoke(settings, accessToken.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void session(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object username = req.getSession().getAttribute("username");
		if (username != null) {
			User user = this.getUserByName(username.toString());
			if (user != null) {
				Gson gson = new Gson();
				JsonElement json = gson.toJsonTree(user);
				resp.setHeader("Content-Type", "application/json");
				resp.getOutputStream().print(json.toString());
			} else {
				resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
			}
		} else {
			resp.setStatus(HttpServletResponse.SC_FORBIDDEN);
		}
	}

	private User getUserByName(String username) {
		UserDAO userDao = new UserDAO(SessionManager.getInstance().getEntityManager());
		List<User> users = userDao.findByAttribute("login", username.toString());
		if (!users.isEmpty()) {
			return users.get(0);
		}

		return null;
	}

}
