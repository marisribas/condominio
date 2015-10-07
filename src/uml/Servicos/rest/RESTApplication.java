package uml.Servicos.rest;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;


/**
 * Realiza disponibiliza os pacotes rest.
 * @generated
 */

@ApplicationPath("/api/rest/uml/Servicos")
public class RESTApplication extends ResourceConfig {

    /**
     * @generated
     */
	public RESTApplication() {
		packages("uml.Servicos.rest");
	}

}