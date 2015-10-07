package security.oauth2.authcode;

import org.glassfish.jersey.client.oauth2.ClientIdentifier;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow;
import org.glassfish.jersey.client.oauth2.OAuth2CodeGrantFlow.Builder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@WebServlet("/oauth2")
public class AuthServlet extends HttpServlet implements Serializable {
	private static final long serialVersionUID = 1L;
	public static Map<String, Object> map = new HashMap<>();
	private OAuth2CodeSettings settings;

	private OAuth2CodeGrantFlow buildGenericFlow(ClientIdentifier clientIdentifier) {
		Builder<?> builder = OAuth2ClientSupport.authorizationCodeGrantFlowBuilder(clientIdentifier,
				settings.AUTHORIZATION_URI, settings.TOKEN_URI);
		final OAuth2CodeGrantFlow flow = builder.scope(settings.SCOPE).redirectUri(settings.AUTHORIZATION_CALLBACK_URI)
				.build();

		return flow;
	}

	private OAuth2CodeGrantFlow buildFacebookFlow(ClientIdentifier clientIdentifier) {
		final OAuth2CodeGrantFlow flow = OAuth2ClientSupport
				.facebookFlowBuilder(clientIdentifier, settings.AUTHORIZATION_CALLBACK_URI).build();
		return flow;
	}

	private OAuth2CodeGrantFlow buildFlow(ClientIdentifier clientIdentifier) {
		OAuth2CodeGrantFlow flow = null;

		String idp = settings.getResourceName();
		switch (idp) {
		case "facebook":
			flow = buildFacebookFlow(clientIdentifier);
			break;
		default:
			flow = buildGenericFlow(clientIdentifier);
		}
		return flow;
	}

	private String authorize(HttpServletRequest request) {

		ClientIdentifier clientIdentifier = new ClientIdentifier(settings.CLIENT_KEY, settings.CLIENT_SECRET);

		final OAuth2CodeGrantFlow flow = buildFlow(clientIdentifier);

		request.getSession().invalidate();
		//request.getSession().setAttribute("OAuth2CodeGrantFlow", flow);
		String flowId = UUID.randomUUID().toString();
		request.getSession().setAttribute("flowId", flowId);
		map.put(flowId, flow);
		request.getSession().setAttribute("settings", settings);

		String authHomeUri = request.getRequestURI();
		request.getSession().setAttribute("authHomeUri", authHomeUri);

		return flow.start();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//
		String idp = request.getParameter("idp") == null ? "wso2" : request.getParameter("idp");
		this.settings = new OAuth2CodeSettings(idp);

		String authorizationUri = authorize(request);
		response.setStatus(303);
		response.sendRedirect(authorizationUri);
	}
}
