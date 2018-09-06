package cn.merryyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * Created on 2017/12/26.
 *
 * @author zlf
 * @since 1.0
 */
@EnableResourceServer
@SpringBootApplication
public class SsoServerApplication extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(SsoServerApplication.class, args);
  }
}
