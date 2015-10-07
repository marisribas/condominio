package security.oauth2.flow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class OAuth2Settings {
	public static String CLIENT_ID;
	public static String CLIENT_SECRET;

	public static String SERVER_URL;
	public static Integer SERVER_PORT;

	public static String ADMIN_USERNAME;
	public static String ADMIN_PASSWORD;

	public static String TOKEN_URI;
	public static String REVOKE_URI;

	public static String SETTINGS_FILE_NAME = "oauth2.properties";

	static {
		try {
			String fileName = null;
			URL resourcefileName = OAuth2Settings.class.getResource("/META-INF/" + SETTINGS_FILE_NAME);
			if (resourcefileName != null)
				fileName = resourcefileName.getFile();
			else {
				File file = new File(SETTINGS_FILE_NAME);
				fileName = file.getAbsolutePath();
			}
			loadConfiguration(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void loadConfiguration(String fileName) throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		File file = new File(fileName);
		properties.load(new FileInputStream(file));

		CLIENT_ID = properties.getProperty("CLIENT_ID");
		CLIENT_SECRET = properties.getProperty("CLIENT_SECRET");
		SERVER_URL = properties.getProperty("SERVER_URL");
		SERVER_PORT = Integer.parseInt(properties.getProperty("SERVER_PORT"));
		ADMIN_USERNAME = properties.getProperty("ADMIN_USERNAME");
		ADMIN_PASSWORD = properties.getProperty("ADMIN_PASSWORD");
		TOKEN_URI = properties.getProperty("TOKEN_URI");
		REVOKE_URI = properties.getProperty("REVOKE_URI");
	}

}