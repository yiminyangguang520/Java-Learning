In this series of tutorial, we will be integrating social login with spring boot application using [spring security 5](https://spring.io/projects/spring-security)  provided features. In this particular tutorial, we will be adding  google oauth login and custom registration support in a spring boot app  and in coming articles we will be integrating other social platfom such  as facebook, twitter and Github with it.  

For a better understanding of spring security OAuth2 internals, We  will be building this application with a very basic OAUTH2 integration  with default configurations provided by spring security using [oauth2Login() element](https://docs.spring.io/spring-security/site/docs/5.0.5.RELEASE/reference/html/oauth2login-advanced.html)  and then customize it to a greater extent by adding custom login page,  custom redirect-uri and UserService, token endpoint etc to extract user  details such as name, email and profile image and use this information  to generate JWT token out of it to authorize user for future server  calls. We will also build an option available for a new user  registration using email and password along with google oauth.

In my previous articles, we have discussed a lot about OAuth2 and  it's principles and hence I will encourage you to go through those  tutorials - [Spring Security OAuth2](https://www.devglan.com/spring-security/spring-oauth2-role-based-authorization) first to understand the basics of OAuth2 and then continue here.

## What is OAuth2

[OAuth2](https://oauth.net/2/) is an  authorization framework that enables applications to obtain limited  access to user accounts on an HTTP service, such as Facebook, GitHub,  and Google. It works by delegating user authentication to the service  that hosts the user account, and authorizing third-party applications to  access the user account. OAuth 2 provides authorization flows for web  and desktop applications, and mobile devices.

## Project Structure

First of all, we will generate the project with spring  initializer(start.spring.io) as below and then add other required  dependencies.

![spring-security-google-oauth-project-structure](https://i.imgur.com/QI6qMs6.png)

### Maven Depenedencies

Apart from the default dependencies that were included during project initialization, we also require below dependencies.

**pom.xml**

```
<dependency>
	<groupId>org.springframework.security</groupId>
		<artifactId>spring-security-oauth2-client</artifactId>
</dependency>
<dependency>
	<groupId>org.springframework.security</groupId>
	<artifactId>spring-security-oauth2-jose</artifactId>
</dependency>
<dependency>
	<groupId>io.jsonwebtoken</groupId>
	<artifactId>jjwt</artifactId>
	<version>0.9.0</version>
</dependency>
```

## Spring Security OAuth2 Config

Let us first use the default support provided by spring security 5  for OAuth2 and make our google OAuth sign in process successful as it  requires bare minimum configurations. Doing so, we will not get confused  after overriding the default implementation and build the final  project.

Below is the security config that we require to get started with the OAuth in spring security. **oauth2Login()**  in the below configuration enables all default configurations and makes  all the resources protected. Only parameters it requires is the  client-id and client-secret which we will be generating on google  console in next section.

```
package com.devglan.springbootgoogleoauth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2Login();
    }

}
```

Now let us implement a test page - index.html. As we have protected  all the resources, whenever we try to access index.html, we will be  redirected to Google OAuth page and after successful authentication the  index.html page will be served by spring boot. 

**index.html**

```
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>Dashboard</title>
    <meta name="description" content=""/>
    <meta name="viewport" content="width=device-width"/>
    <base href="/"/>
    <link rel="stylesheet" type="text/css" href="/webjars/bootstrap/css/bootstrap.min.css"/>
    <script type="text/javascript" src="/webjars/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h1>Welcome !</h1>
</div>
</body>
</html>
```

## Google OAuth2 App Registration

Let us register our app on [google developer console](https://console.developers.google.com/projectselector/apis/credentials) to get the client-id and cient-secret. As by default spring security 5 redirects user to **{baseUrl}/{action}/oauth2/code/google** post authentication, we have added 

```
http://localhost:8080/login/oauth2/code/google
```

 as authorised redirect URI on google console.



Once we have the client-id and client-secret, let us configure the  same in our application.properties. Below entries in the  application.properties is enough to run our application with default  configuration.

**application.properties**

```
spring.security.oauth2.client.registration.google.clientId=150677252870-78tlj6v2mm653alhodqr9t5br5fu5bs0.apps.googleusercontent.com
spring.security.oauth2.client.registration.google.clientSecret=ZptsDAPoVjn7DzVYbzSDNt1v

spring.datasource.url=jdbc:mysql://localhost:3306/test?useSSL=false
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
```

## Testing Spring Security 5 OAuth2 Default Configuration

Whatever we have implemented above is enough to get started with a  spring boot OAuth2 application. Spring boot has really made OAuth2  implementation very simple in a spring MVC app. Now, we can run  SpringBootGoogleOauthApplication.java as a java application to test the  application.

**1.** Hit localhost:8080/login or localhost:8080 and you will be forwarded to google sign in.

**2.** Now you can login to google(provider) and authorise this app.

**3.** Now you can see the index.html page rendered in your browser.

## Spring Security Google OAuth2 Internals

The authentication process has been done now and the spring boot  configuration looks very simple and straight forward but there are many  things that happened in the background. Let us discuss each of them now.

When a request is made to localhost:8080 to access secured resource,  spring security does not find any authenticated object in the context  and redirects to 

```
http://localhost:8080/oauth2/authorization/google
```

 for authentication against oauth2Login() element. This GET request is intercepted by [OAuth2AuthorizationRequestRedirectFilter](https://github.com/spring-projects/spring-security/blob/master/oauth2/oauth2-client/src/main/java/org/springframework/security/oauth2/client/web/OAuth2AuthorizationRequestRedirectFilter.java)



Now the request is redirected to 

```
accounts.google.com/o/oauth2/v2/auth?response_type=code&client_id=150677252870-78tlj6v2mm653alhodqr9t5br5fu5bs0.apps.googleusercontent.com&scope=openid+profile+email&state=QFWkpSxvN-zs5gGoMCnFGDJDTYF1HZg1FC_5l31H0qg%3D&redirect_uri=http%3A%2F%2Flocalhost%3A8080%2Flogin%2Foauth2%2Fcode%2Fgoogle.
```

  If you inspect the URL, the url contains the client-id, client-secret  and redirect-uri that we configured in the application.properties.



If the redirect_uri that we have configured in application.proprties  does not match with the one that we have configured in google console,  then you may see an error as **Error: redirect_uri_mismatch**

After a successful authentication with google and authorization of  our app, the user will be redirected to '/login/oauth2/code/google with  the authentication code as request param and this request will be  intercepted by [OAuth2LoginAuthenticationFilter](https://stackoverflow.com/questions/49179087/oauth2loginauthenticationfilter-spring-security). With this auth code, a POST request will be made to 

```
https://www.googleapis.com/oauth2/v4/token
```

  to access the user info such as email, picture, name, family_name. Once  the response is received by our app, following authentication object  will be set in spring security context with ROLE as user and the user  will served the secured page index.html page against **"/"**



You can visit my another article for a [better understanding of Spring Security filter chain and customization](https://www.devglan.com/).

Below is a sample response from Google.

```
Principal: Name: [112360792925347143122], Granted Authorities: [ROLE_USER], User Attributes: [at_hash=TCTreSX3yj6ZB_T3TKTjRg, sub=112360792925347143122, email_verified=true, iss=https://accounts.google.com, given_name=Dhiraj, locale=en-GB, picture=https://lh4.googleusercontent.com/-jf-AkErib08/AAAAAAAAAAI/AAAAAAAAAwc/huSoFQWOXOk/s96-c/photo.jpg, aud=[150677252870-78tlj6v2mm653alhodqr9t5br5fu5bs0.apps.googleusercontent.com], azp=150677252870-78tlj6v2mm653alhodqr9t5br5fu5bs0.apps.googleusercontent.com, name=Dhiraj Ray, exp=2019-01-12T19:16:14Z, family_name=Ray, iat=2019-01-12T18:16:14Z, email=ssss@gmail.com]; Credentials: [PROTECTED]; Authenticated: true; Details: null; Granted Authorities: ROLE_USER
```

## Customizing Spring Security oauth2Login()

### Custom Redirection Endpoint

The Redirection Endpoint is used by the Authorization Server for  returning the Authorization Response to the client. As we discussed  above, we are using default redirection endpoint - 

```
http://localhost:8080/login/oauth2/code/google
```

  till now. We can customize it to any other URL of our  choice(/oauth2/callback/). Below is the spring security configuration  for it. Make sure to update this property in google console and  application.properties.



```
spring.security.oauth2.client.registration.google.redirect-uri=http://localhost:8080/oauth2/callback/google
protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*");
    }
```

### Custmomizing userInfoEndpoint()

Our custom user info endpoint will make request to the provider user  info endpoint and retrieve the user info such as name, email, image etc.  To do so, we need to implement OAuth2UserService. Spring boot provides  two different implementation for this. We will be implementing [OidcUserService for OpenID Connect (Google) providers](https://docs.spring.io/autorepo/docs/spring-security/5.0.x-SNAPSHOT/api/index.html?org/springframework/security/oauth2/client/oidc/userinfo/OidcUserService.html).

​     

 For Facebook, we need to implement DefaultOAuth2UserService which we  will implement in next article. The implenentaion will fetch the user  info and creates entry in our local database. And we have already  configured our MySql DB support for this app. Below is the  implementation.

A call to `super()` will amke the REST call to fetch the  userinfo from the external provider(Google). After a success response,  the user entry will be created in our local system. 

**CustomOidcUserService.java**

```
package com.devglan.springbootgoogleoauth.oauth2;

import com.devglan.springbootgoogleoauth.dao.UserRepository;
import com.devglan.springbootgoogleoauth.dto.GoogleOAuth2UserInfo;
import com.devglan.springbootgoogleoauth.model.User;
import com.devglan.springbootgoogleoauth.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CustomOidcUserService extends OidcUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        OidcUser oidcUser = super.loadUser(userRequest);
        Map attributes = oidcUser.getAttributes();
        GoogleOAuth2UserInfo userInfo = new GoogleOAuth2UserInfo();
        userInfo.setEmail((String) attributes.get("email"));
        userInfo.setId((String) attributes.get("sub"));
        userInfo.setImageUrl((String) attributes.get("picture"));
        userInfo.setName((String) attributes.get("name"));
        updateUser(userInfo);
        return oidcUser;
    }

    private void updateUser(GoogleOAuth2UserInfo userInfo) {
        User user = userRepository.findByEmail(userInfo.getEmail());
        if(user == null) {
            user = new User();
        }
        user.setEmail(userInfo.getEmail());
        user.setImageUrl(userInfo.getImageUrl());
        user.setName(userInfo.getName());
        user.setUserType(UserType.google);
		userRepository.save(user);
    }
}
```

Below is our updated security config now.

```
protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2Login()
                .redirectionEndpoint()
                .baseUri("/oauth2/callback/*")
                .and()
                .userInfoEndpoint()
                .oidcUserService(oidcUserService);
    }
```

**UserRepository.java**

```
package com.devglan.springbootgoogleoauth.dao;

import com.devglan.springbootgoogleoauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository {

    User findByEmail(String email);
}
```

**User.java**

```
@Entity
@Table(name = "User")
public classUser {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String imageUrl;
    @Column
    private UserType userType;
	
```

### Customizing Authorization Endpoint

oauth2Login().authorizationEndpoint() allows configuring the  Authorization Endpoint. It is used by the client to obtain authorization  from the resource owner via user-agent redirection.

```
oauth2Login()
.authorizationEndpoint()
                .baseUri("/oauth2/authorize")
                .authorizationRequestRepository(customAuthorizationRequestRepository());
				
@Bean
public AuthorizationRequestRepository customAuthorizationRequestRepository() {
	return new HttpSessionOAuth2AuthorizationRequestRepository();
}
```

At this point of time, if you try to run this application and follow above steps, you can see a new user entry in the DB.

### Customizing Login Endpoint

By default, the OAuth 2.0 Login Page is auto-generated by the  DefaultLoginPageGeneratingFilter. To override the default login page,  configure oauth2Login().loginPage() and to start authentication with  Google then you can redirect user to **/oauth2/authorize/google**. Here, /oauth2/authorize is the authorization endpoint that is configured above. You can visit this article for a [Spring Security OAuth2 User Registration](https://www.devglan.com/spring-security/spring-security-oauth2-user-registration)

```
@Override
protected void configure(HttpSecurity http) throws Exception {
	http
		.oauth2Login()
			.loginPage("/login/oauth2")
			...
			.authorizationEndpoint()
				.baseUri("/oauth2/authorize")
				....
}
```

## Generating Custom JWT Token

To generate a custom [JWT (Json Web Token) token](https://jwt.io/),  we require to overide the default AuthenticationSuccessHandler in  spring security with our custom success handler. So far, our login  process is done and now let us define our success handler. Inside the  success handler we will be generating our JWT token.

While generating the JWT token, we will be re-using the features that we had developed in our previous article - [Spring Boot JWT Auth](https://www.devglan.com/spring-security/spring-boot-jwt-auth).

**CustomAuthenticationSuccessHandler.java**

```
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    private String homeUrl = "http://localhost:8080/";

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (response.isCommitted()) {
            return;
        }
        DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
        Map attributes = oidcUser.getAttributes();
        String email = (String) attributes.get("email");
        User user = userRepository.findByEmail(email);
        String token = JwtTokenUtil.generateToken(user);
        String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
                .queryParam("auth_token", token)
                .build().toUriString();
        getRedirectStrategy().sendRedirect(request, response, redirectionUrl);
    }

}
```

Below is the implementation for generating JWT token. The  implementation of JWT authentication in spring security is very much  similar to this - . In next article, we will build the complete implementation of custom login with email and password and JWT token validation.

```
@Component
public class JwtTokenUtil implements Serializable {

    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "devglan123r";

    public static String generateToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuer("https://devglan.com")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .compact();
    }

}
```

## Conclusion

In this article, we implemented spring boot security google oauth and  integrated JWT with it.Also, we looked into different ways we can  customize the default behaviour of spring security 5 OAuth  implementation. In the next article, we will be overriding the default  login page and add support for custom login with email and password alng  with JWT token validation.