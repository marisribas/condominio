package uml.Servicos.rest;


import java.util.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.persistence.*;

import uml.Servicos.rest.util.*;

import uml.Servicos.dao.*;
import uml.Servicos.entity.*;
import uml.Servicos.business.*;
import javax.servlet.http.HttpServletRequest;

import uml.Servicos.rest.exceptions.*;


/**
 * Publicando metodos de negocio via REST
 * @generated
 **/
@Path("/PrestadoresServicos")
@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class PrestadoresServicosREST implements RESTService<PrestadoresServicos> {
  /**
   * @generated
   */
  private SessionManager session;
  /**
   * @generated
   */  
  private PrestadoresServicosBusiness business;
  /**
   * @generated
   */  
  @Context 
  private HttpServletRequest request;

  /**
   * @generated
   */
  public PrestadoresServicosREST() {
    this.session = SessionManager.getInstance();
    this.business = new PrestadoresServicosBusiness(session);
  }
  
  /**
   * @generated
   */  
  @POST
  public Response post(PrestadoresServicos entity) {
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

  /**
   * @generated
   */
  @PUT
  public Response put(PrestadoresServicos entity) {
    try {
	    session.begin();
	    PrestadoresServicos updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  /**
   * @generated
   */  
  @PUT
  @Path("/{id}")
  public Response putWithId(PrestadoresServicos entity) {
    try {
	    session.begin();
	    PrestadoresServicos updatedEntity = business.update(entity);
	    session.commit();
	    return Response.ok(updatedEntity).build();
    }
    
    catch(Exception exception){
	    session.rollBack();
        throw new CustomWebApplicationException(exception);
    }  
  }
  
  /**
   * @generated
   */  
  @DELETE
  public Response delete(PrestadoresServicos entity) {  
		try {
			session.begin();
			PrestadoresServicos updatedEntity = business.update(entity);
			business.delete(updatedEntity);
			session.commit();
			return Response.ok().build();
		}

		catch (Exception exception) {
			session.rollBack();
			throw new CustomWebApplicationException(exception);
		}    
  } 
   
  /**
   * @generated
   */    
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
  
  
  


  
  /**
   * NamedQuery list
   * @generated
   */
  @GET
  	
  public GenericEntity<List<PrestadoresServicos>> list(@DefaultValue("100") @QueryParam("limit") int limit, @DefaultValue("0") @QueryParam("offset") int offset){
      return new GenericEntity<List<PrestadoresServicos>>(business.list(limit, offset)){};

  }
	
}
