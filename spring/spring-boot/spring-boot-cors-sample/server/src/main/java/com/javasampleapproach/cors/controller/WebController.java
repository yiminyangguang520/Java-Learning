package com.javasampleapproach.cors.controller;

import com.javasampleapproach.cors.model.Customer;
import com.javasampleapproach.cors.service.CustomerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * In the code above, CORS is not enabled for getCart() method. getCustomers() and getData() have different CORS configuration.
 *
 * – origins: specifies the URI that can be accessed by resource. “*” means that all origins are allowed. If undefined, all origins are allowed.
 *
 * – allowCredentials: defines the value for Access-Control-Allow-Credentials response header. If value is true, response to the request can be exposed to the page. The credentials are cookies, authorization headers or TLS client certificates. The default value is true.
 *
 * – maxAge: defines maximum age (in seconds) for cache to be alive for a pre-flight request. By default, its value is 1800 seconds.
 *
 * We also have some attributes:
 * – methods: specifies methods (GET, POST,…) to allow when accessing the resource. If we don’t use this attribute, it takes the value of @RequestMapping method by default. If we specify methods attribute value in @CrossOrigin annotation, default method will be overridden.
 *
 * – allowedHeaders: defines the values for Access-Control-Allow-Headers response header. We don’t need to list headers if it is one of Cache-Control, Content-Language, Expires, Last-Modified, or Pragma. By default all requested headers are allowed.
 *
 * – exposedHeaders: values for Access-Control-Expose-Headers response header. Server uses it to tell the browser about its whitelist headers. By default, an empty exposed header list is used.
 *
 *
 *
 *
 * Spring will combine attributes from both to merge CORS configuration:
 * – /customers,其中8080和9000为客户端端口号,可分别修改tomcat端口号为8080和9000后启动tomcat;而server端口号为8081:
 * @CrossOrigin(origins = { "http://localhost:8080" , "http://localhost:9000" }, maxAge = 6000)
 *
 * – /data:
 * @CrossOrigin(origins = { "http://localhost:9000" }, maxAge = 3000)

 * @author min
 */
@CrossOrigin(origins = {"http://localhost:9000"}, maxAge = 3000)
@RestController
public class WebController {

  @Autowired
  private CustomerService service;

  @CrossOrigin(origins = {"http://localhost:8080"}, maxAge = 6000)
  @RequestMapping("customers")
  public List<Customer> getCustomers() {

    List<Customer> list = service.getCustomers();
    return list;
  }

  @RequestMapping("data")
  public List<Customer> getData() {

    List<Customer> list = service.getCustomers();
    list.forEach(item -> item.setName(item.getName().toUpperCase()));

    return list;
  }
}
