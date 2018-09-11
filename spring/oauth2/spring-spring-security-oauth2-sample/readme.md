# Reference
http://websystique.com/spring-security/secure-spring-rest-api-using-oauth2/

# Requests
GET
http://localhost:8080/SpringSecurityOAuth2Example/user/?access_token=[TOKEN]

POST TOKEN
http://localhost:8080/SpringSecurityOAuth2Example/oauth/token?grant_type=password&username=bill&password=abc123

Note: You must add an authorization header with client credentials [my-trusted-client/secret].

POST REFRESH TOKEN
http://localhost:8080/SpringSecurityOAuth2Example/oauth/token?grant_type=refresh_token&refresh_token=[REFRESH_TOKEN]