package security.rest;

/**
 * REST.ftl - Publicando metodos de negocio via REST
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 **/

import security.business.UserRoleBusiness;
import security.dao.SessionManager;
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


@Path("/UserRole")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class UserRoleREST implements RESTService<UserRole> {

  private SessionManager session;
  private UserRoleBusiness business;
  
  @Context 
  private HttpServletRequest request;

  public UserRoleREST() {
    this.session = SessionManager.getInstance();
    this.business = new UserRoleBusiness(session);
  }
  
  @POST
  public Response post(UserRole entity) {
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
  public Response put(UserRole entity) {
    try {
	    session.begin();
	    UserRole updatedEntity = business.update(entity);
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
  public Response putWithId(UserRole entity) {
    try {
	    session.begin();
	    UserRole updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  @DELETE
  public Response delete(UserRole entity) {  
		try {
			session.begin();
			UserRole updatedEntity = business.update(entity);
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
  	
  public List<UserRole> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.list(limit, offset);

  }
  @GET
  @Path("/findByUser/{user}")	
  public List<UserRole> findByUser(@PathParam("user")User user, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByUser(user, limit, offset);

  }
  @GET
  @Path("/findByEmail/{email}")	
  public List<UserRole> findByEmail(@PathParam("email")java.lang.String email, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByEmail(email, limit, offset);

  }
  @GET
  @Path("/findByLogin/{login}")	
  public List<UserRole> findByLogin(@PathParam("login")java.lang.String login, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByLogin(login, limit, offset);

  }
  @GET
  @Path("/findByRole/{roleid}")	
  public List<UserRole> findByRole(@PathParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByRole(roleid, limit, offset);

  }
	
  @GET
  @Path("/findByUser")	
  public List<UserRole> findByUserParams(@QueryParam("user")User user, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByUser(user, limit, offset);	
  }
  @GET
  @Path("/findByEmail")	
  public List<UserRole> findByEmailParams(@QueryParam("email")java.lang.String email, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByEmail(email, limit, offset);	
  }
  @GET
  @Path("/findByLogin")	
  public List<UserRole> findByLoginParams(@QueryParam("login")java.lang.String login, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByLogin(login, limit, offset);	
  }
  @GET
  @Path("/findByRole")	
  public List<UserRole> findByRoleParams(@QueryParam("roleid")java.lang.String roleid, @DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.findByRole(roleid, limit, offset);	
  }
}
