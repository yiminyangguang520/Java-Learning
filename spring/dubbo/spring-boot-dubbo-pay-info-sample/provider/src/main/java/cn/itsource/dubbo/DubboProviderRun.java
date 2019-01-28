package cn.itsource.dubbo;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * @author litz-a
 */
@SpringBootApplication
public class DubboProviderRun {

  public static void main(String[] args) {
    new SpringApplicationBuilder(DubboProviderRun.class)
        .web(WebApplicationType.NONE)
        .run(args);
  }

}