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
import security.dao.SessionManager;
import security.entity.Permission;
import security.rest.exceptions.CustomWebApplicationException;
import security.rest.util.RESTService;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


@Path("/Permission")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class PermissionREST implements RESTService<Permission> {

  private SessionManager session;
  private PermissionBusiness business;
  
  @Context 
  private HttpServletRequest request;

  public PermissionREST() {
    this.session = SessionManager.getInstance();
    this.business = new PermissionBusiness(session);
  }
  
  @POST
  public Response post(Permission entity) {
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
  public Response put(Permission entity) {
    try {
	    session.begin();
	    Permission updatedEntity = business.update(entity);
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
  public Response putWithId(Permission entity) {
    try {
	    session.begin();
	    Permission updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  @DELETE
  public Response delete(Permission entity) {  
		try {
			session.begin();
			Permission updatedEntity = business.update(entity);
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
  	
  public List<Permission> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return business.list(limit, offset);

  }
	
}
