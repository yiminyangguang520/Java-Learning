package cn.merryyou.soo.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * Created on 2017/12/26.
 *
 * @author zlf
 * @since 1.0
 */
@EnableOAuth2Sso
@SpringBootApplication
public class SsoClient2Application {

  public static void main(String[] args) {
    SpringApplication.run(SsoClient2Application.class, args);
  }
}
