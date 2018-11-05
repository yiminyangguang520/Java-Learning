package com.lee.authlogin.auth;

/**
 * @author litz-a
 */
public class JwtAuthenticationFilter {
  //1.reads JWT authentication token from the Authorization header of all the requests
  //2.validates the token
  //3.loads the user details associated with that token.
  //4.Sets the user details in Spring Security’s SecurityContext. Spring Security uses the user details to perform
  // authorization checks. We can also access the user details stored in the SecurityContext in our controllers to
  // perform our business logic.
}
