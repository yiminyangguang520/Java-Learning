+++
date = 2018-07-21
publishDate = 2018-07-25
title = "Setup and Customize a Login Page With Reactive Spring Security."
description = "Spring Security provides a intuitive and concise API for managing Authentication aspects within your app."
toc = true
categories = ["appsec","security","reactive"]
tags = ["functional","java","spring","web","forms","demo"]
+++

# Customized WebFlux Form Authentication

This demonstration examines Spring Security WebFlux's Authentication mechanisms. We will look at authentication with HTML forms using Mustache, User Authentication, and customized form-based login / logout configurations.

## The ServerHttpSecurity Configuration

[SecurityWebFilterChain](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/server/SecurityWebFilterChain.html) is the governing chain of [WebFilter]'s that allows us to lock down reactive WebFlux applications.
With [@EnableWebFluxSecurity](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/annotation/web/reactive/EnableWebFluxSecurity.html) turned on, we can build this object by issuing commands to the [ServerHttpSecurity](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/web/server/ServerHttpSecurity.html) DSL object.

SecurityConfiguration.java:

```java
    @EnableWebFluxSecurity
    @Slf4j
    @Configuration
    public class SecurityConfiguration {

        @Bean
        public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
            return http
                    .authorizeExchange()
        // ...
```

First, by calling `authorizeExchange()` method to expose [AuthorizeExchangeSpec](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/web/server/ServerHttpSecurity.AuthorizeExchangeSpec.html) lets us proceed with authentication details.
With this, we can apply a matcher and permission model to our endpoints.  For this example, we want to open several endpoints to everyone. This is where `permitAll()` can be applied to a multi-argument `pathMatchers()` expression.

SecurityConfiguration.java:

```java
                    .pathMatchers("/login",
                            "/bye",
                            "/favicon.ico",
                            "/images/**")
                        .permitAll()
```

For this example, we want every endpoint thats not publicly available to require a logged-in user. Wire in a [PathMatcher](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/util/PathMatcher.html) for all other URL's, and apply the `authenticated()` operator to whatever matches.

SecurityConfiguration.java:

```java
                    .pathMatchers("/**")
                        .authenticated()
                        .and()
```

### CSRF Configuration

Another component for configuring SecurityWebFilterChain is the [CsrfSpec](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/web/server/ServerHttpSecurity.CsrfSpec.html) enabled by calling `csrf()` method.  This lets us configure [CSRF](https://www.owasp.org/index.php/Cross-Site_Request_Forgery_(CSRF)_Prevention_Cheat_Sheet) tokens and request matchers, or exclude CSRF entirely.

In this demo we will use the default options, so `'_csrf'` becomes our parameter namd, and `'X-CSRF-TOKEN'` is our header.

SecurityConfiguration.java:

```JAVA
                    .csrf()
                        //.csrfTokenRepository(customCsrfTokenRepository)
                        //.requireCsrfProtectionMatcher(customCsrfMatcher)
                        .and()
```

Note: Currently supported token repository exists are:

|Class| Function |
|-----|----------|
|[WebSessionServerCsrfTokenRepository](WebSessionServerCsrfTokenRepository)| Store CSRF token in a Web Session |
|[CookieServerCsrfTokenRepository](https://docs.spring.io/spring-security/site/docs/5.1.0.BUILD-SNAPSHOT/api/org/springframework/security/web/server/csrf/CookieServerCsrfTokenRepository.html)| Store CSRF token in custom cookie |

Later, we will customize how CSRF tokens get included to our web page through custom filtering.

### Customizing Login/logout

Spring Security provides login/logout pages on demand whenever one is not already configured. This is provided by the [LoginPageGeneratingWebFilter](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/server/ui/LoginPageGeneratingWebFilter.html) and [LogoutPageGeneratingWebFilter](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/server/ui/LogoutPageGeneratingWebFilter.html) that get wired in if no login/logout page was specified.

To override this, expose [FormLoginSpec](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/config/web/server/ServerHttpSecurity.FormLoginSpec.html) by calling `HttpServerSecurity's` `formLogin()` method. We can then issue the path the our custom login page and declare form-login success/error Handlers. We want to redirect successes to the home page by using [RedirectServerAuthenticationSuccessHandler](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/server/authentication/RedirectServerAuthenticationSuccessHandler.html).  Then For logout successes, we'll send the user to the "/bye" endpoint by configuring the [RedirectServerLogoutSuccessHandler](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/server/authentication/logout/RedirectServerLogoutSuccessHandler.html) in a separate method since it's contructor doesnt support parameters.

SecurityConfiguration.java:

```java
                    .and()
                    .formLogin()
                        .loginPage("/login")
                        .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/"))
                    .and()
                    .logout()
                        .logoutUrl("/logout")
                        .logoutSuccessHandler(logoutSuccessHandler("/bye"))
                    .and()
                    .build();
        }

        public ServerLogoutSuccessHandler logoutSuccessHandler(String uri) {
            RedirectServerLogoutSuccessHandler successHandler = new RedirectServerLogoutSuccessHandler();
            successHandler.setLogoutSuccessUrl(URI.create(uri));
            return successHandler;
        }

    }
```

Both handlers do similar things - namely redirect on success. Expelicitly constructing the `logoutSuccessHandler` since it's constructor only allows no-args.

Next, we will look at how user/pass pairs are authenticated. This is done by a subclass of [ReactiveAuthenticationManager](https://docs.spring.io/spring-security/site/docs/5.1.0.BUILD-SNAPSHOT/api/org/springframework/security/authentication/ReactiveAuthenticationManager.html).

## Authenticating Users

[UserDetailsRepositoryReactiveAuthenticationManager](https://docs.spring.io/spring-security/site/docs/5.0.3.RELEASE/api/org/springframework/security/authentication/UserDetailsRepositoryReactiveAuthenticationManager.html)
bean is provided automatically if there are no other configured [ReactiveAuthenticationManager](http://ReactiveAuthenticationManager) `@Bean` definitions. This authentication manager defers principal/credential operations to a [ReactiveUserDetailsService](https://docs.spring.io/spring-security/site/docs/5.1.0.M1/api/org/springframework/security/core/userdetails/ReactiveUserDetailsService.html) implementation.

Spring comes with ready-made implemenations for storing and looking up users in the [MapReactiveUserDetailsService](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/MapReactiveUserDetailsService.html). We'll complete this section using the map reactive implementation, and by having our users come from the handly [User](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/core/userdetails/User.html) object since no other details are necessary for [customizing the user](https://www.sudoinit5.com/post/spring-reactive-authentication/#customizing-the-user).

UserDetailBeans.java:

```java
    @Configuration
    public class UserDetailServiceBeans {

        @Bean
        public MapReactiveUserDetailsService mapReactiveUserDetailsService() {
            return new MapReactiveUserDetailsService(users);
        }

        private static final PasswordEncoder pw =     PasswordEncoderFactories.createDelegatingPasswordEncoder();

        private static UserDetails user(String u, String... roles) {

            return User
                    .withUsername(u)
                    .passwordEncoder(pw::encode)
                    .password("pw")
                    .authorities(roles)
                    .build();
        }

        private static final Collection<UserDetails> users = new ArrayList<>(
                Arrays.asList(
                        user("thor", "ROLE_USER"),
                        user("loki", "ROLE_USER"),
                        user("odin", "ROLE_ADMIN", "ROLE_USER")
                ));
    }
```

## View Configuration with Mustache

To enable [Mustache](https://mustache.github.io/) Views, we need to wire in an appropriate [ViewResolver](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/ViewResolverRegistry.html) so view names are rendered with the Mustache template View.

First, include the `spring-boot-starter-mustache` dependency in your pom.xml.

pom.xml:

```xml
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mustache</artifactId>
    </dependency>
```

Now, create a [WebFluxConfigurer]() to enable Mustache View rendering.

WebConfig.java:

```html
    @Configuration
    class WebConfig implements WebFluxConfigurer {

        private final MustacheViewResolver resolver;

        // The resolver is provided by MustacheAutoConfiguration class
        WebConfig(MustacheViewResolver resolver) {
            this.resolver = resolver;
        }

        // order matters; cache will find first and render.
        @Override
        public void configureViewResolvers(ViewResolverRegistry registry) {
            registry.viewResolver(resolver);
        }

    }
```

This configuration will populate a [MustacheViewResolver](https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/web/reactive/result/view/MustacheViewResolver.html())
into the [ViewResolverRegistry](https://docs.spring.io/spring/docs/current/javadoc-api/org/springframework/web/reactive/config/ViewResolverRegistry.html).

### Mustache Web Content

Mustache lets us use includes for re-usable content. We will create 3 common fragements for our views.

frag/header.html:

```html
    <!doctype html>
    <html lang="en">
    <body>
```

frag/footer.html:

```html
    </body>
    </html>
```

frag/logout.html:

```html
    <form class="form-inline" action="/logout" method="post">
        <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
        <button class="btn btn-lg btn-primary btn-block" type="submit">Escape!</button>
    </form>
```

Next we can define the meat of our content. We just need a login, game, and logout page.

game.html:

```html
{{>frag/header}}
<h1>West of House</h1>
<div>Hello, {{user.username}}. You are standing in an open field west of a white house, with a boarded front door.<br/>
    There is a small mailbox here.</div>
<br/>
&gt;<image src="https://rawgit.com/marios-code-path/reactive-authentication/master/form-login/src/main/resources/images/cursor.gif" />
{{>frag/logout}}
{{>frag/footer}}
```

login-form.html:

```html
    {{>frag/header}}
    <h1>Welcome to Sp0rk. This version created 21-JUL-2018</h1>
    <div id="main-content" class="container">
        <div class="row">
            <div class="col-md-6">
                <form class="form-inline" action="/login" method="post">
                    <div class="form-group">
                        <label for="username">What is your name?</label>
                        <input type="text" name="username" id="username" class="form-control" placeholder="My name is...">
                    </div>
                    <div class="form-group">
                        <label for="password">What is the passcode?</label>
                        <input type="password" name="password" id="password" class="form-control" placeholder="Passphrase">
                    </div>
                    <br/>
                    <input type="hidden" name="_csrf" value="{{_csrf.token}}" />
                    <button class="btn btn-lg btn-primary btn-block" type="submit">Who R U</button>
                </form>
            </div>
        </div>
    </div>
    {{>frag/footer}}
```

bye.html:

```html
    {{>frag/header}}
    <h1>Leaving the Great Underground Empire</h1>
    <div>
        See you later, dungeon-master!<br/>
    </div>
    <br/>
    {{>frag/footer}}
```

### Configuring Mustache Behaviour

To let Mustache view resolver know where to find our templates, how to handle view objects, we set the specific options for this.  Using `expose-request-attributes` allows us to access our request's view attributes from within the template.

application.properties

```properties
    spring.mustache.prefix=classpath:/templates/mustache/
    spring.mustache.suffix=.html
    spring.mustache.expose-request-attributes=true
```

## Routing to views

We need to wire up our views with routing logic, so lets add this with the functional style [RouterFunction](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/server/RouterFunction.html).

We need a way to display icons, so first we will wire in a 'favicon.ico' route, and send it to a [ClassPathResource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/ClassPathResource.html) resource for classpath resolution. Alternately, we could imply that the file exists on the local FileSystem by using a [FileSystemResource](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/core/io/FileSystemResource.html).

WebRoutes.java:

```java
    @Component
    public class WebRoutes {

        @Bean
        RouterFunction<?> iconResources() {
            return RouterFunctions
                    .resources("/favicon.**", new ClassPathResource("images/favicon.ico"));
        }
```

Next we will have a route to the login page.  Since we already overrode the default location in `ServerHttpSecurity`, we must provide the route to our new login page. Also included in this example is our logout landing page. it can be any URL you select, for this example we have a single page to tell the user 'goodbye'.

WebRoutes.java:

```java
    @Bean
    RouterFunction<?> viewRoutes() {
        return RouterFunctions
                .route(RequestPredicates.GET("/login"),
                        req -> ServerResponse
                                .ok()
                                .render("login-form",
                                        req.exchange().getAttributes())
                )
                .andRoute(RequestPredicates.GET("/bye"),
                        req -> ServerResponse.ok().render("bye")
                )
```

## Data Model for Views

The last route will require some information about the user logged in. We can construct the model for our mustache template by incluing a `Map<String, Object>` as the second argument to the `render()` method.

To get to the logged-in user, we get the principal from the [ServerRequest](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/reactive/function/server/ServerRequest.html) object, cast it to it's value type, and inject it into request attributes Map under the 'user' key. The attributes map (model object) is then passed in to the `render()` function.

WebRoutes.java:

```java
                .andRoute(RequestPredicates.GET("/"),
                        req -> req.principal()
                                .ofType(Authentication.class)
                                .flatMap(auth -> {
                                    User user = User.class.cast(auth.getPrincipal());
                                    req.exchange()
                                            .getAttributes()
                                            .putAll(Collections.singletonMap("user", user));
                                    return ServerResponse.ok().render("game",
                                            req.exchange().getAttributes());
                                })
                )
```

## Application execution Entry

This simple app requires no additional configuration beyond [EnableWebFlux](http://enable-web-flux-annotation) and [SpringBootApplication](http://spring-boot-applkication) annotations.

App.java:

```java
@SpringBootApplication
@EnableWebFlux
public class FormLoginApp {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(FormLoginApp.class);
        app.run();
    }
}
```

Execute the application by running it in your IDE or by executing the following comand in a bash window.

```bash
    mvn spring-boot:run
```

# Conclusion

This simple web was made to demonstratekey concepts in obtaining successful authentication from a user that is browser-bound. We looked at wiring up CSRF, Mustache views, login/logout customization, and Routing/Filtering in the WebFlux environment.
