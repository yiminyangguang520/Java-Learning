package com.logicbig.example.web;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * AbstractSecurityWebApplicationInitializer实现了WebApplicationInitializer，因此
 * Spring会发现它，并用它在Web容器中注册DelegatingFilterProxy
 * @author litz-a
 */
public class SpringSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

}