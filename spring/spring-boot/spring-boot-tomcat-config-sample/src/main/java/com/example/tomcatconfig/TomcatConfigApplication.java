package com.example.tomcatconfig;

import org.apache.catalina.connector.Connector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;

/**
 * @author litz-a
 */
@SpringBootApplication
public class TomcatConfigApplication implements WebServerFactoryCustomizer<TomcatServletWebServerFactory> {

  @Value("${server.additional-ports}")
  String ports;

  public static void main(String[] args) {
    SpringApplication.run(TomcatConfigApplication.class, args);
  }

  /**
   * Customize the specified
   *
   * @param factory the web server factory to customize
   */
  @Override
  public void customize(TomcatServletWebServerFactory factory) {
    if (ports != null) {
      String[] portsArray = ports.split(",");
      for (String portStr : portsArray) {
        int port = Integer.parseInt(portStr);
        // Tomcat中,一个Connecter监听一个端口,指定协议为HTTP/1.1
        Connector httpConnector = new Connector("HTTP/1.1");
        httpConnector.setPort(port);
        factory.addAdditionalTomcatConnectors(httpConnector);
      }
    }
  }
}

