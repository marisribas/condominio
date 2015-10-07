package security.oauth2.authcode;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OAuth2CodeSettings implements Serializable {
	private static final long serialVersionUID = 1L;

	public String CLIENT_KEY;
	public String AUTHORIZATION_CALLBACK_URI;
	public String AUTHORIZATION_URI;

	public String CLIENT_SECRET;
	public String TOKEN_URI;
	public String PROFILE_URI;
	public String SCOPE;
	public String REVOKE_URI;
	public String GRANT_TYPE;

	private final Logger logger = Logger.getLogger(OAuth2CodeSettings.class.getName());
	private String resourceName;

	public OAuth2CodeSettings(String resourceName) {
		try {

			InputStream resource = OAuth2CodeSettings.class
					.getResourceAsStream("/META-INF/" + resourceName + ".properties");

			Properties properties = new Properties();
			properties.load(resource);

			// 1)
			CLIENT_KEY = properties.getProperty("CLIENT_KEY");
			AUTHORIZATION_CALLBACK_URI = properties.getProperty("AUTHORIZATION_CALLBACK_URI");
			AUTHORIZATION_URI = properties.getProperty("AUTHORIZATION_URI");
			SCOPE = properties.getProperty("SCOPE");
			GRANT_TYPE = properties.getProperty("GRANT_TYPE");

			// 2)
			CLIENT_SECRET = properties.getProperty("CLIENT_SECRET");
			TOKEN_URI = properties.getProperty("TOKEN_URI");
			PROFILE_URI = properties.getProperty("PROFILE_URI");

			REVOKE_URI = properties.getProperty("REVOKE_URI");

			this.resourceName = resourceName;

		} catch (IOException exception) {
			exception.printStackTrace();
			logger.log(Level.SEVERE, exception.getMessage());
		}
	}

	public String getResourceName() {
		return resourceName;
	}

}
