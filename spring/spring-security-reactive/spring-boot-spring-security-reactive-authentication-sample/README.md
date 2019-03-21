---
date = 2018-07-14
publishDate = 2018-07-14
title = "Setup and customize Authentication against a WebFlux Application"
description = "Spring Security provides a intuitive and concise API for managing Authentication aspects within your app."
toc = true
categories = ["appsec","security","reactive"]
tags = ["functional","java","spring","web","demo"]
---

# Configuring Authentication against a WebFlux app

This demonstration examines Spring Security WebFlux's Authentication mechanisms. We will look at Authentication request escalation, as well as user-domain customizations.

## Authentication flow-control

How do we determine when a request must provide an authentication context? Spring does this with help from an [AuthenticationEntryPoint](https://docs.spring.io/spring-security/site/docs/5.0.0.M3/api/org/springframework/security/web/server/AuthenticationEntryPoint.html)
that identifies un-authenticated requests and returns with a response to the user to perform some authentication action.

Configure [ServerHttpSecurity](http://foo-bar) to use HTTP-BASIC by calling it's `httpBasic()` method. This will enable HTTP-Basic within WebFlux while exposing [HttpBasicSpec](http://foo-bar)'s lower level components such as the [ReactiveAuthenticationManager](http://foo-bar) for customization. For now, we are interested in overriding the default [HttpBasicServerAuthenticationEntryPoint](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/server/authentication/HttpBasicServerAuthenticationEntryPoint.html) it provides. This entry-point escalates authentication by sending `WWW-Authenticate` headers with status 401, triggering the HTTP-Basic login interaction.

We can customize the HTTP-BASIC flow by configuring exception handling on the [ServerHttpSecurity](http://foo-bar). Override the provided [HttpBasicServerAuthenticationEntryPoint](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/web/server/authentication/HttpBasicServerAuthenticationEntryPoint.html) with an [RedirectServerAuthenticationEntryPoint](http://foo-bar) that redirects users to the "/custom-login" view.

NOTE: I do not condon the muddling of authentication flows; this is just an example:

SecurityConfiguration.java:

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
                .authorizeExchange()
                ...
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(new RedirectServerAuthenticationEntryPoint("/form-login"))
                .and()
                .httpBasic()
                .and()
                .build();
    }

### Access Restriction Customization

[AuthenticationEntryPoint](https://docs.spring.io/spring-security/site/docs/5.0.0.M3/api/org/springframework/security/web/server/AuthenticationEntryPoint.html) is activated when an un-authenticated request raises an [AccessDeniedException](http://flow-control). The exception is caught within [ExceptionTranslationWebFilter](http://ExceptionTranslationWebFilter) and determines whether to block access, or escalate to authentication. The later is accomplished by invoking [AuthenticationEntryPoint](https://docs.spring.io/spring-security/site/docs/5.0.0.M3/api/org/springframework/security/web/server/AuthenticationEntryPoint.html)'s `commence()` method to initiate an authentication flow.

Change the rejection behaviour by providing an [ServerAccessDeniedHandler](http://ServerAcessDeniedHandler) to [ExceptionHandlingSpec](http://foo-bar)'s `accessDeniedHandler()` method.

SecurityXConfiguration.java:

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        return http
        ...
        .and()
        .exceptionHandling()
        .accessDeniedHandler(new HttpStatusServerAccessDeniedHandler(HttpStatus.BAD_REQUEST))
        .and()
        ...

Now, with any access restriction, the client will see HTTP 400 instead of 403.

## Authenticating Users

[ReactiveAuthenticationManager](https://docs.spring.io/spring-security/site/docs/5.0.x/api/org/springframework/security/authentication/ReactiveAuthenticationManager.html)
does the job of facilitating user and credential validation within the application. We can also use this component to escalate and complete the authentication process for a given flow.

NOTE: Spring provides an integration component [ReactiveAuthenticationManagerAdapter](https://docs.spring.io/spring-security/site/docs/current/api/org/springframework/security/authentication/ReactiveAuthenticationManagerAdapter.html)
for hoisting your existing, classic AuthenticationManager implementations into the reactive world.

### Customizing the User

The [UserDetailsRepositoryReactiveAuthenticationManager](https://docs.spring.io/spring-security/site/docs/5.0.3.RELEASE/api/org/springframework/security/authentication/UserDetailsRepositoryReactiveAuthenticationManager.html)
bean is provided automatically if there are no other configured [ReactiveAuthenticationManager](http://ReactiveAuthenticationManager) `@Bean` definitions. This authentication manager defers principal/credential operations to a [ReactiveUserDetailsService](https://docs.spring.io/spring-security/site/docs/5.1.0.M1/api/org/springframework/security/core/userdetails/ReactiveUserDetailsService.html) implementation.

Spring comes with ready-made implemenations for storing and looking up users in the [MapReactiveUserDetailsService](http://MapReactiveUserDetailsService). We'll complete this section by making 2 uses of this bean - one MapReactive, the other our own - to illustrate simplicity in overriding and levering this component..

First, the custom User domain object with UserDetails as prescribed by the `UserDetailsService` interface:

ExampleUser.java:

    @Data
    @Slf4j
    public class ExampleUser implements UserDetails {

        private final Account account;
        Collection<GrantedAuthority> authorities;

        public ExampleUser(Account account, String[] roles) {
            this.authorities = Arrays.asList(roles)
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            this.account = account;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return this.authorities;
        }

        @Override
        public String getPassword() {
            return account.getPassword();
        }

        @Override
        public String getUsername() {
            return account.getUsername();
        }

        @Override
        public boolean isAccountNonExpired() {
            return account.isActive();
        }

        @Override
        public boolean isAccountNonLocked() {
            return account.isActive();
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return account.isActive();
        }

        @Override
        public boolean isEnabled() {
            return account.isActive();
        }

        @Data
        public static class Account {

            private String username;
            private String password;
            private boolean active;

            public Account(String username, String password, boolean active) {
                this.username = username;
                this.password = password;
                this.active = active;
            }

        }
    }

We will also need a way to find our users. This demo will use a pre-programmed `List()` of users to hold any UserDetails we want to expose throughout the app. We provide a few convenience methods to setting up the object. Of significant import is the [PasswordEncoder](https://docs.spring.io/spring-security/site/docs/4.2.4.RELEASE/apidocs/org/springframework/security/crypto/password/PasswordEncoder.html) that is used to encrypt (defaults to bcrypt) plaintext.

UserDetailServiceBean.java:

    @Configuration
    public class UserDetailServiceBeans {

        private static final PasswordEncoder pw = PasswordEncoderFactories.createDelegatingPasswordEncoder();

        private static UserDetails user(String u, String... roles) {
            return new ExampleUser(new ExampleUser.Account(u, pw.encode("password"), true),
                    roles);
        }

        private static final Collection<UserDetails> users = new    ArrayList<>(
            Arrays.asList(
                    user("thor", "ROLE_ADMIN"),
                    user("loki", "ROLE_USER"),
                    user("zeus", "ROLE_ADMIN", "ROLE_USER")
            ));
    //...

Now, with users available, we can wire in a [UserDetailService](http://UserDetailService). Lets start with the easy-to-use [MapReactiveUserDetailService](http://mapReactiveUserDetailService). We'll bind it to a spring profile `"map-reactive"` for use case demonstration.

UserDetailServiceBean.java:

    @Bean
    @Profile("map-reactive")
    public MapReactiveUserDetailsService mapReactiveUserDetailsService() {
        return new MapReactiveUserDetailsService(users);
    }

What if I wanted to implement my own [ReactiveUserDetailService](http://ReactiveUserDetailService)? This can be accomplished! simply wire in an own implementation of [ReactiveUserDetailService](http://ReactiveUserDetailService) as a bean. We'll bind it ot the spring profile `"custom"` for use case demonstration.

UserDetailServiceBeans.java:

    @Component
    @Profile("custom")
    class ExampleUserDetailService implements ReactiveUserDetailsService {

        @Override
        public Mono<UserDetails> findByUsername(String username) {
            Optional<UserDetails> maybeUser = users.stream().filter(u -> u.getUsername().equalsIgnoreCase(username)).findFirst();
            return maybeUser.map(Mono::just).orElse(Mono.empty());
        }
    }

This way we can call our services and be sure that the [AuthenticationPrincipal](http://AuthenticationPrincipal) referenced in every request is our own `ExampleUser`.

## Recap

Spring Security offers a rich and complete set of solutions for implementing authenticaiton and authorization schemes into our WebFlux application. The ReativeAuthenticationManager and ReactiveAuthorizationManagers do much of the work at the WebFilter level.  ServerHttpSecurity helps us to wire all this together using a fluent API.

What we want to do next is provide a oAuth2 solutions to our applications. We will tackle that in the next article for Spring Security Webflux.
