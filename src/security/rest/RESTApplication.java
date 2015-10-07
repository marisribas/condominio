package security.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;


/**
 * Realiza disponibiliza os pacotes rest.
 * 
 * @author Techne
 * @version 1.0
 * @since 2015-09-02
 *
 */

@ApplicationPath("/api/rest/security")
public class RESTApplication extends ResourceConfig {

	public RESTApplication() {
		packages("security.rest");
	}

}