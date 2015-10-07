package security.rest;

/**
 * REST.ftl - Publicando metodos de negocio via REST
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 **/

import security.business.PermissionBusiness;
import security.business.RoleBusiness;
import security.business.UserBusiness;
import security.business.UserRoleBusiness;
import security.dao.SessionManager;
import security.entity.Permission;
import security.entity.Role;
import security.entity.User;
import security.entity.UserRole;
import security.rest.exceptions.CustomWebApplicationException;
import security.rest.util.RESTService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/Role")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class RoleREST implements RESTService<Role> {

  private SessionManager session;
  private RoleBusiness business;
  private UserRoleBusiness userRoleBusiness;
  private PermissionBusiness permissionBusiness;
  private UserBusiness userBusiness;
  
  @Context 
  private HttpServletRequest request;

  public RoleREST() {
    this.session = SessionManager.getInstance();
    this.business = new RoleBusiness(session);
    this.userRoleBusiness = new UserRoleBusiness(session);
    this.permissionBusiness = new PermissionBusiness(session);
    this.userBusiness = new UserBusiness(session);
  }
  
  @POST
  public Response post(Role entity) {
    try {
	    session.begin();
	    business.save(entity);
	    session.commit();
	    business.refresh(entity);
	    return Response.ok(entity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }
  }

  @PUT
  public Response put(Role entity) {
    try {
	    session.begin();
	    Role updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  @PUT
  @Path("/{id}")
  public Response putWithId(Role entity) {
    try {
	    session.begin();
	    Role updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  @DELETE
  public Response delete(Role entity) {  
		try {
			session.begin();
			Role updatedEntity = business.update(entity);
			business.delete(updatedEntity);
			session.commit();
			return Response.ok().build();
		}

		catch (Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);
		}    
  } 
    
  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") java.lang.String id) {  
		try {
			session.begin();
			if (business.deleteById(id) > 0) {
				session.commit();
				return Response.ok().build();
			} else {
				return Response.status(404).build();
			}
		}

		catch (Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);
		}    
  }
  
  
  
  @GET
  @Path("/{id}/UserRoles")
  public List<UserRole> findUserRoles(@PathParam("id") java.lang.String id, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return this.business.findUserRoles(id, limit, offset);
  }
  
  @DELETE
  @Path("/{id}/UserRoles/{userRoleid}")
  public Response deleteUserRole(@PathParam("userRoleid") java.lang.String id) {
		try {
			session.begin();
			if (this.userRoleBusiness.deleteById(id) > 0) {
				session.commit();
				return Response.ok().build();
			} else {
				session.rollBack();
				return Response.status(404).build();
			}
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }
  
  @PUT
  @Path("/{id}/UserRoles/{userRoleid}")
  public Response putUserRole(UserRole entity, @PathParam("userRoleid") java.lang.String id) {
		try {
			session.begin();
			UserRole updatedEntity = this.userRoleBusiness.update(entity);
			session.commit();
			return Response.ok(updatedEntity).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }  
  
  @POST
  @Path("/{id}/UserRoles")
  public Response postUserRole(UserRole entity, @PathParam("id") java.lang.String id) {
		try {
			session.begin();
			Role role = this.business.findById(id);
			entity.setRole(role);
			this.userRoleBusiness.save(entity);
			session.commit();
			this.userRoleBusiness.refresh(entity);
			return Response.ok(entity).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }   
  
  @GET
  @Path("/{id}/Permissions")
  public List<Permission> findPermissions(@PathParam("id") java.lang.String id, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return this.business.findPermissions(id, limit, offset);
  }
  
  @DELETE
  @Path("/{id}/Permissions/{permissionid}")
  public Response deletePermission(@PathParam("permissionid") java.lang.String id) {
		try {
			session.begin();
			if (this.permissionBusiness.deleteById(id) > 0) {
				session.commit();
				return Response.ok().build();
			} else {
				session.rollBack();
				return Response.status(404).build();
			}
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }
  
  @PUT
  @Path("/{id}/Permissions/{permissionid}")
  public Response putPermission(Permission entity, @PathParam("permissionid") java.lang.String id) {
		try {
			session.begin();
			Permission updatedEntity = this.permissionBusiness.update(entity);
			session.commit();
			return Response.ok(updatedEntity).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }  
  
  @POST
  @Path("/{id}/Permissions")
  public Response postPermission(Permission entity, @PathParam("id") java.lang.String id) {
		try {
			session.begin();
			Role role = this.business.findById(id);
			entity.setRole(role);
			this.permissionBusiness.save(entity);
			session.commit();
			this.permissionBusiness.refresh(entity);
			return Response.ok(entity).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }   
  


  @GET
  @Path("/{id}/Users")
  public List<User> listUsers(@PathParam("id") java.lang.String id, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return this.business.listUsers(id, limit, offset);
  }
  
  @POST
  @Path("/{id}/Users")
  public Response postUser(User entity, @PathParam("id") java.lang.String id) {
		try {
			session.begin();
			UserRole newUserRole = new UserRole();

			Role instance = this.business.findById(id);


			newUserRole.setUser(entity);
			newUserRole.setRole(instance);
			
			this.userRoleBusiness.save(newUserRole);
			session.commit();
			this.userRoleBusiness.refresh(newUserRole);
			return Response.ok(newUserRole.getRole()).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }   
  
  @DELETE
  @Path("/{instanceId}/Users/{relationId}")
  public Response deleteUser(@PathParam("instanceId") java.lang.String instanceId, @PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			if (this.business.deleteUser(instanceId, relationId) > 0) {
				session.commit();
				return Response.ok().build();
			} else {
				session.rollBack();
				return Response.status(404).build();
			}
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }  
  
  
  @GET
  	
  public List<Role> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.list(limit, offset);

  }
	
}
