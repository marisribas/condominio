package security.oauth2.flow;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;

public class OAuth2Client {
	private String grantType = "password";
	private String clientId;
	private String clientSecret;
	private String tokenUri;
	private String revokeUri;

	public OAuth2Client(String tokenUri, String revokeUri, String clientId, String clientSecret) {
		this.tokenUri = tokenUri;
		this.revokeUri = revokeUri;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public String authenticate(String username, String password) throws Exception {

		System.out.println("TOKEN_URI:" + tokenUri);
		HttpPost httpMethod = new HttpPost(tokenUri);
		ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("grant_type", grantType));
		parameters.add(new BasicNameValuePair("username", username));
		parameters.add(new BasicNameValuePair("password", password));

		httpMethod.setEntity(new UrlEncodedFormEntity(parameters));

		String encoding = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
		httpMethod.setHeader("Authorization", "Basic " + encoding);

		CloseableHttpClient httpClient = HttpClientFactory.createHttpClient_AcceptsUntrustedCerts();

		ResponseHandler<String> handler = new BasicResponseHandler();
		String responseBody = httpClient.execute(httpMethod, handler);
		return responseBody;
	}

	public String logout(String accessToken) throws ClientProtocolException, IOException, KeyManagementException,
			NoSuchAlgorithmException, KeyStoreException {

		HttpPost httpMethod = new HttpPost(revokeUri);

		String encoding = Base64.getEncoder().encodeToString((clientId + ":" + clientSecret).getBytes());
		httpMethod.setHeader("Authorization", "Bearer " + encoding);

		CloseableHttpClient httpClient = HttpClientFactory.createHttpClient_AcceptsUntrustedCerts();

		ResponseHandler<String> handler = new BasicResponseHandler();
		String responseBody = httpClient.execute(httpMethod, handler);
		return responseBody;
	}

}