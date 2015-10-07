package security.rest;

/**
 * REST.ftl - Publicando metodos de negocio via REST
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 **/

import security.business.RoleBusiness;
import security.business.UserBusiness;
import security.business.UserRoleBusiness;
import security.dao.SessionManager;
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


@Path("/User")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserREST implements RESTService<User> {

  private SessionManager session;
  private UserBusiness business;
  private UserRoleBusiness userRoleBusiness;
  private RoleBusiness roleBusiness;
  
  @Context 
  private HttpServletRequest request;

  public UserREST() {
    this.session = SessionManager.getInstance();
    this.business = new UserBusiness(session);
    this.userRoleBusiness = new UserRoleBusiness(session);
    this.roleBusiness = new RoleBusiness(session);
  }
  
  @POST
  public Response post(User entity) {
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
  public Response put(User entity) {
    try {
	    session.begin();
	    User updatedEntity = business.update(entity);
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
  public Response putWithId(User entity) {
    try {
	    session.begin();
	    User updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  @DELETE
  public Response delete(User entity) {  
		try {
			session.begin();
			User updatedEntity = business.update(entity);
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
			User user = this.business.findById(id);
			entity.setUser(user);
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
  @Path("/{id}/Roles")
  public List<Role> listRoles(@PathParam("id") java.lang.String id, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset) {
    return this.business.listRoles(id, limit, offset);
  }
  
  @POST
  @Path("/{id}/Roles")
  public Response postRole(Role entity, @PathParam("id") java.lang.String id) {
		try {
			session.begin();
			UserRole newUserRole = new UserRole();

			User instance = this.business.findById(id);


			newUserRole.setRole(entity);
			newUserRole.setUser(instance);
			
			this.userRoleBusiness.save(newUserRole);
			session.commit();
			this.userRoleBusiness.refresh(newUserRole);
			return Response.ok(newUserRole.getUser()).build();
		} catch(Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);	
		}
  }   
  
  @DELETE
  @Path("/{instanceId}/Roles/{relationId}")
  public Response deleteRole(@PathParam("instanceId") java.lang.String instanceId, @PathParam("relationId") java.lang.String relationId) {
		try {
			session.begin();
			if (this.business.deleteRole(instanceId, relationId) > 0) {
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
  	
  public List<User> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.list(limit, offset);

  }
  @GET
  @Path("/findByRole/{roleid}")	
  public List<User> findByRole(@PathParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByRole(roleid, limit, offset);

  }
	
  @GET
  @Path("/findByRole")	
  public List<User> findByRoleParams(@QueryParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByRole(roleid, limit, offset);	
  }
}
