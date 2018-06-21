package org.shiro.security.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @author yuanpb
 * @version 创建时间：2018年5月2日 上午10:03:31 类说明：cors 跨域
 */
@Configuration
public class CorsConfig {


  @Value("${cors.urls}")
  private String urls;

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    // 对响应头进行CORS授权
    CustomCorsRegistration corsRegistration = new CustomCorsRegistration("*");
/*		List<String> allowedOrigins = new ArrayList<>();
		allowedOrigins.add("http://10.0.0.46:8888");
		String[] objects = allowedOrigins.toArray(new String[allowedOrigins.size()]);*/

    corsRegistration
        // 允许向该服务器提交请求的URI，*表示全部允许
        .allowedOrigins("*")
        // 允许提交请求的方法，*表示全部允许
        .allowedMethods(HttpMethod.DELETE.name(), HttpMethod.GET.name(), HttpMethod.HEAD.name(),
            HttpMethod.POST.name(), HttpMethod.PUT.name())
        // 允许的头信息,*标识全部允许
        .allowedHeaders("*")
        // 暴露的头信息
        .exposedHeaders(HttpHeaders.SET_COOKIE)
        // 允许Cookie跨域，在做登录校验的时候有用
        .allowCredentials(true)
        // 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
        .maxAge(1800);
    // 注册CORS过滤器
    UrlBasedCorsConfigurationSource configurationSource = new UrlBasedCorsConfigurationSource();
    // 第一个参数表示过滤的url,*表示过滤所有
    configurationSource.registerCorsConfiguration("*", corsRegistration.getCorsConfiguration());
    CorsFilter corsFilter = new CorsFilter(configurationSource);

    return new FilterRegistrationBean(corsFilter);
  }

}
