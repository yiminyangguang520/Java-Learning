package com.packtpub.springsecurity.web.configuration;

import org.apache.catalina.connector.Connector;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration class will force all HTTP traffic to redirect to HTTPS.
 *
 * @author mickknutson
 */
@Configuration
public class CustomTomcatEmbeddedServletContainerFactory implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

  /**
   * Create an HTTP redirect connector NOTE: This only works with HTTP/1.1
   *
   * @param factory the web server factory to customize
   */
  @Override
  public void customize(TomcatServletWebServerFactory factory) {
    Connector httpConnector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
    httpConnector.setScheme("http");
    httpConnector.setPort(8080);
    httpConnector.setSecure(false);
    httpConnector.setRedirectPort(8443);
    factory.addAdditionalTomcatConnectors(httpConnector);
  }

}
