package security.oauth2.authcode;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@WebServlet("/revoke")
public class RevokeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static String revoke(OAuth2CodeSettings settings, String accessToken)
			throws ClientProtocolException, IOException {

		HttpPost httpMethod = new HttpPost(settings.REVOKE_URI);

		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		parameters.add(new BasicNameValuePair("token", accessToken));
		parameters.add(new BasicNameValuePair("token_type_hint", "access_token"));

		httpMethod.setEntity(new UrlEncodedFormEntity(parameters));

		String encoding = Base64.getEncoder()
				.encodeToString((settings.CLIENT_KEY + ":" + settings.CLIENT_SECRET).getBytes());
		httpMethod.setHeader("Authorization", "Basic " + encoding);

		CloseableHttpClient httpClient = HttpClientBuilder.create().build();
		HttpResponse httResponse = httpClient.execute(httpMethod);
		if (httResponse.getStatusLine().getStatusCode() != 200) {
			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			return responseHandler.handleResponse(httResponse);
		}
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String accessToken = request.getSession().getAttribute("accessToken").toString();
		OAuth2CodeSettings settings = (OAuth2CodeSettings) request.getSession().getAttribute("settings");
		String authHomeUri = request.getSession().getAttribute("authHomeUri").toString();

		authHomeUri += "?idp=" + settings.getResourceName();

		String message = revoke(settings, accessToken);
		if (message == null) {
			// cleanUp session
			request.getSession().invalidate();
			// Redirect
			response.sendRedirect(authHomeUri);
		} else {
			response.getOutputStream().print(message);
		}

	}
}
