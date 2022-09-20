#WSO2

Acesse o WSO2 como admin teste

https://localhost:9443

Crie um Service Provider
Main > Service Providers > Add

Depois edite-o
Expand a seção 

Inbound Authentication Configuration > OAuth/OpenID Connect Configuration > Configure


OAuth Version	    OAuth-2.0
Callback Url        http://localhost:8080/jerseyoauth2client/oauth2callback	
Allowed Grant Types	Code Implicit Password Client CredentialRefresh Token SAML IWA-NTLM
Access Token Url	https://localhost:9443/oauth2/token
Authorize Url	    https://localhost:9443/oauth2/authorize



#GOOGLE

Crie uma aplicação no google

https://console.developers.google.com

Editar
API & auth > Credentials

Ex:

Client ID	        968123716109-kl3r1kh7d135g8du3ia1sn5ttodl6u2f.apps.googleusercontent.com
Email address       968123716109-kl3r1kh7d135g8du3ia1sn5ttodl6u2f@developer.gserviceaccount.com
Client secret       uyy7JodulZE5HCXoPyy6inp3
Redirect URIs       http://localhost:8080/jerseyoauth2client/oauth2callback
JavaScript origins	http://localhost:8080/


#FACEBOOK

Crie uma aplicação em
https://developers.facebook.com/apps

My Apps > Settings

Basic
AppId     1626293370948828
AppSecret b2ed9ec163c201c2589ff652a2e8356c

Advanced
Valid OAuth redirect URIs http://localhost:8080/jerseyoauth2client/oauth2callback


#LINKEDIN

https://developer.linkedin.com/docs/oauth2

Create App

https://www.linkedin.com/developer/apps?newapp=

Api
https://developer.linkedin.com/docs/signin-with-linkedin


# Maven

mvn clean install
mvn tomcat7:run


# Documentação

http://repo1.maven.org/maven2/org/glassfish/jersey/jersey-documentation/2.5.1/jersey-documentation-2.5.1-user-guide.pdf
Página 179

https://jersey.java.net/documentation/latest/security.html

https://oracleus.activeevents.com/2014/connect/fileDownload/session/A2A421F5AEAD596B6E4F0593A58E8492/CON4990_Candido%20da%20Silva-JavaOne%202014%20-%20Securing%20RESTful%20resources%20with%20OAuth2.pdf

Exemplo
https://github.com/jivesoftware/jive-sdk-java-jersey/blob/master/jive-sdk/src/main/java/com/jivesoftware/sdk/service/oauth/facebook/FacebookOAuth2Service.java
