- **@RestController** was introduced in Spring MVC 4 and is a convenience annotation that itself is annotated with `@Controller` and `@ResponseBody` annotation. This eliminates the need of annotating your controller methods with `@ResponseBody` individually.
- **@RequestMapping** annotation is used to map URLs such as `/users` onto an entire class or a particular handler method. A class-level annotation like `/users` maps a specific request path onto a controller, with additional method-level annotations further narrowing the mapping for a specific HTTP request method like “GET”, “PUT”, “POST” or “DELETE” etc.
- **RequestMethod** is an enumeration of possible HTTP request methods like GET, PUT, POST, DELETE, etc.
- **@PathVariable** annotation indicates that a method parameter should be bound to a URI template variable. To process the `@PathVariable` annotation, Spring MVC needs to find the matching URI template variable by name. You can specify it in the annotation or, if the URI template variable name matches the method argument name, you can omit that detail.
- **@RequestBody** annotation indicates a method parameter should be bound to the body of the HTTP web request. Behind the scene, a `HttpMessageConverter` is responsible for converting from the HTTP request message to an object and converting from an object to the HTTP response body.
- **@ResponseBody** annotation indicates a method return value should be bound to the HTTP response body. By annotating your class with `@RestController`, you no longer need to add this annotation to every method individually.
- **ResponseEntity** extends from `HttpEntity` which allows you to add a `HttpStatus` code directly to the response. The `ResponseEntity` represents the entire HTTP response. You can add header information, status codes and add content to the body.
- **HttpHeaders** represents HTTP request and response headers. This class has some convenience methods for setting popular header types like *Content-Type*, *Access-Control-Allow-Headers*, etc.

## CorsFilter

A common problem while developing single page applications or applications that use AJAX is **Cross Origin Resource Sharing** or **CORS**. For security reasons, most browsers block requests originating from another domain outside the domain from which the request/resource originated.

**Problem**: When accessing a REST API, you might receive one of the following errors:

```
XMLHttpRequest cannot load http://example.com/login. 
No 'Access-Control-Allow-Origin' header is present on the requested resource. 
Origin 'null' is therefore not allowed access.
```

```
XMLHttpRequest cannot load http://example.com. Origin http://localhost:8080 is not allowed by Access-Control-Allow-Origin.
```

**Solution**: Cross-Origin Resource Sharing. We can add HTTP response headers to control inter-domain communication. Using Spring MVC 4 the easiest way is to add a filter. Using the following headers, you can granularly control CORS:

- **Access-Control-Allow-Origin** (required) indicates which origin site(s) is/are allowed. This header can only contain a single domain, or it can contain a ‘*’ to allow requests from any origin. If – for security reasons – you need to support multiple domains, you can inspect from where the request is coming and allow the domain based on a condition.
- **Access-Control-Allow-Methods** (required) – Comma-delimited list of the supported HTTP methods.
- **Access-Control-Allow-Header** (required if the request has an *Access-Control-RequestHeaders header*) – Comma-delimited list of the supported request headers.
- **Access-Control-Allow-Credentials** (optional) – By default, cookies are not included in CORS requests. You can include cookies by setting this header to *true*.
- **Access-Control-Max-Age** (optional) – The value of this header allows the response to be cached for a specified number of seconds.

## Spring MVC Configuration

Next, we need to configure Spring MVC 4. We used Java Configuration. We’ll briefly explain the different configurations.

- **@EnableWebMvc** imports the Spring MVC configuration from `WebMvcConfigurationSupport` if it is used in conjunction with `@Configuration`.
- **@ComponentScan** configures component scanning directives for use with `@Configuration`. You need to register which packages it can scan to find your Spring components.

## Configure Spring MVC with Java Config

The `@Configuration` annotation indicates that this class declares one or more `@Bean` methods and may be processed by the spring container to generate bean definitions and service requests for those beans at runtime.

The `@EnableWebMvc` enables support for the `@Controller` annotation that uses `@RequestMapping` to map incomming requests to specific methods.

The `@ComponentScan` will instruct spring which packages it may scan to discover spring annotated beans.

The `InternalResourceViewResolver` will prefix and suffix the return value of the controller to construct the real path of the view file.

## Configuring the Dispatcher Servlet

The `DispatcherServlet` acts like a front-controller and is used to dispatch the requests to the appropriate controller methods. We configure it by extending the `AbstractAnnotationConfigDispatcherServletInitializer` class which is a base class for the `WebApplicationInitializer`, this will configure the servlet context programatically. We need to tell it where the location of our Spring MVC Java Configuration file is located. We do this by registering the class – of our java configuration – in the `getServletConfigClasses` method.