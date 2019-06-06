# Spring Cloud Gateway整合Swagger聚合微服务系统API文档(非Zuul)

    这个文档适用之前写的时候，使用仓库代码应该没啥问题。新版本spring修正了一些东西。最近有空的时候再跟进spring版本再更新一下，可以参考思路】最近在学习SpringBoot2和Spring Cloud.Finchley版，网上资料也是少的可怜，大部分还是通过一些github或者码云上的一些开源框架来学习，途中出现的一些bug也只能自己看看源码尝试解决。最近使用Spring Cloud Gateway替换Zuul的时候发现Swagger并不支持以WebFlux为底层的Gateway，无法集成，运行报错。下面分享我愚钝的解决思路，和关键代码，若有改进之处，望大佬指点，详细代码可以下载源码查看。

贴上源码https://gitee.com/sywd/sw

    首先是子项目Spring Boot项目正常集成Swagger。在业务项目Admin中添加Swagger依赖包（使用Eureka为注册中心，文章未展示多余部分）。
    
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.2</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.2</version>
    </dependency>
    
    添加测试Controller。
    
    @RestController
    @RequestMapping("/test")
    @Api("测试")
    public class TestController {
     
        @ApiOperation(value = "计算+", notes = "加法")
        @ApiImplicitParams({
                @ApiImplicitParam(name = "a", paramType = "path", value = "数字a", required = true, dataType = "Long"),
                @ApiImplicitParam(name = "b", paramType = "path", value = "数字b", required = true, dataType = "Long")
        })
        @GetMapping("/{a}/{b}")
        public Integer get(@PathVariable Integer a, @PathVariable Integer b) {
            return a + b;
        }
    }
    
    配置Swagger使API注解生效。
    
    @Configuration
    @EnableSwagger2
    public class SwaggerConfig {
        @Bean
        public Docket createRestApi() {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                    .paths(PathSelectors.any())
                    .build();
        }
        private ApiInfo apiInfo() {
            return new ApiInfoBuilder()
                    .title("Swagger API")
                    .description("test")
                    .termsOfServiceUrl("")
                    .contact(new Contact("wd", "", ""))
                    .version("2.0")
                    .build();
        }
    }

此时启动Admin项目后应该能正常访问admin-ip:admin:port/swagger-ui.html。下面是网关gateway部分。

    建立网关项目gateway，添加核心依赖包
    
    <dependency>
        <groupId>org.springframework.cloud</groupId>
        <artifactId>spring-cloud-starter-gateway</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis-reactive</artifactId>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger-ui</artifactId>
        <version>2.9.2</version>
    </dependency>
    <dependency>
        <groupId>io.springfox</groupId>
        <artifactId>springfox-swagger2</artifactId>
        <version>2.9.2</version>
    </dependency>
    
    添加gateway路由配置
    
    server:
      port: 3333
     
    spring:
      application:
        name: wd-gateway
      cloud:
          gateway:
            locator:
              enabled: true
            routes:
            - id: wd-admin
              uri: lb://wd-admin
              predicates:
              - Path=/admin/**
              filters:
              - SwaggerHeaderFilter
              - StripPrefix=1
     
    eureka:
      instance:
        prefer-ip-address: true
      client:
        service-url:
          defaultZone: http://localhost:8060/eureka/


因为Swagger暂不支持webflux项目，所以Gateway里不能配置SwaggerConfig，也就是说Gateway无法提供自身API。但我想一般也不会在网关项目代码里写业务API代码吧。。所以这里的集成只是基于基于WebMvc的微服务项目。

    配置SwaggerProvider，获取Api-doc，即SwaggerResources。
    
    @Component
    @Primary
    @AllArgsConstructor
    public class SwaggerProvider implements SwaggerResourcesProvider {
        public static final String API_URI = "/v2/api-docs";
        private final RouteLocator routeLocator;
        private final GatewayProperties gatewayProperties;


​     
​        @Override
​        public List<SwaggerResource> get() {
​            List<SwaggerResource> resources = new ArrayList<>();
​            List<String> routes = new ArrayList<>();
​            //取出gateway的route
​            routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
​            //结合配置的route-路径(Path)，和route过滤，只获取有效的route节点
​            gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
​                    .forEach(routeDefinition -> routeDefinition.getPredicates().stream()
​                            .filter(predicateDefinition -> ("Path").equalsIgnoreCase(predicateDefinition.getName()))
​                            .forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
​                                    predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
​                                            .replace("/**", API_URI)))));
​            return resources;
​        }
​     
        private SwaggerResource swaggerResource(String name, String location) {
            SwaggerResource swaggerResource = new SwaggerResource();
            swaggerResource.setName(name);
            swaggerResource.setLocation(location);
            swaggerResource.setSwaggerVersion("2.0");
            return swaggerResource;
        }
    }

因为Gateway里没有配置SwaggerConfig，而运行Swagger-ui又需要依赖一些接口，所以我的想法是自己建立相应的swagger-resource端点。

    @RestController
    @RequestMapping("/swagger-resources")
    public class SwaggerHandler {
        @Autowired(required = false)
        private SecurityConfiguration securityConfiguration;
        @Autowired(required = false)
        private UiConfiguration uiConfiguration;
        private final SwaggerResourcesProvider swaggerResources;
     
        @Autowired
        public SwaggerHandler(SwaggerResourcesProvider swaggerResources) {
            this.swaggerResources = swaggerResources;
        }


​     
​        @GetMapping("/configuration/security")
​        public Mono<ResponseEntity<SecurityConfiguration>> securityConfiguration() {
​            return Mono.just(new ResponseEntity<>(
​                    Optional.ofNullable(securityConfiguration).orElse(SecurityConfigurationBuilder.builder().build()), HttpStatus.OK));
​        }
​     
        @GetMapping("/configuration/ui")
        public Mono<ResponseEntity<UiConfiguration>> uiConfiguration() {
            return Mono.just(new ResponseEntity<>(
                    Optional.ofNullable(uiConfiguration).orElse(UiConfigurationBuilder.builder().build()), HttpStatus.OK));
        }
     
        @GetMapping("")
        public Mono<ResponseEntity> swaggerResources() {
            return Mono.just((new ResponseEntity<>(swaggerResources.get(), HttpStatus.OK)));
        }
    }
    
    另外，我发现在路由为admin/test/{a}/{b}，在swagger会显示为test/{a}/{b}，缺少了admin这个路由节点。断点源码时发现在Swagger中会根据X-Forwarded-Prefix这个Header来获取BasePath，将它添加至接口路径与host中间，这样才能正常做接口测试，而Gateway在做转发的时候并没有这个Header添加进Request，所以发生接口调试的404错误。解决思路是在Gateway里加一个过滤器来添加这个header。
    
    @Component
    public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {
        private static final String HEADER_NAME = "X-Forwarded-Prefix";
     
        @Override
        public GatewayFilter apply(Object config) {
            return (exchange, chain) -> {
                ServerHttpRequest request = exchange.getRequest();
                String path = request.getURI().getPath();
                if (!StringUtils.endsWithIgnoreCase(path, SwaggerProvider.API_URI)) {
                    return chain.filter(exchange);
                }
                String basePath = path.substring(0, path.lastIndexOf(SwaggerProvider.API_URI));
                ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
                ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
                return chain.filter(newExchange);
            };
        }
    }
    
    在配置文件中为admin节点添加过滤器生效
    
          routes:
            - id: wd-admin
              uri: lb://wd-admin
              predicates:
              - Path=/admin/**
              filters:
              - SwaggerHeaderFilter
              - StripPrefix=1

这时启动Gateway，访问gateway-ip:gateway-port/swagger-ui.html时，即可正常使用swagger。大家可以多加几个API服务试试效果

最后附上效果图

---------------------
![img](https://img-blog.csdn.net/20180718194312888?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3R0em9tbWVk/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)



![img](https://img-blog.csdn.net/20180718194359909?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3R0em9tbWVk/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

原文：https://blog.csdn.net/ttzommed/article/details/81103609 

# Spring Cloud Gateway 聚合swagger文档

关于pigX：[全网最新的微服务脚手架，Spring Cloud Finchley、oAuth2的最佳实践](https://link.juejin.im?target=https%3A%2F%2Fgitee.com%2Flog4j%2Fpig%2Fwikis%2Fpigx%3Fsort_id%3D467134)

​	在微服务架构下，通常每个微服务 都会使用Swagger来管理我们的接口文档，当微服务越来越多，接口查找管理无形中要浪费我们不少时间，毕竟懒是程序员的美德。

​	由于swagger2暂时不支持webflux 走了很多坑，完成这个效果感谢 @dreamlu @世言。

## 文档聚合效果

通过访问网关的 host:port/swagger-ui.html，即可实现: [pig聚合文档效果预览传送门](https://link.juejin.im?target=http%3A%2F%2Fpreview.pig4cloud.com%2F%23%2Fmyiframe%2FurlPath%3Fsrc%3Dhttp%253A%252F%252F139.224.200.249%253A9999%252Fswagger-ui.html%26name%3D%25E6%258E%25A5%25E5%258F%25A3%25E6%2596%2587%25E6%25A1%25A3)

通过右上角的Select a spec 选择服务模块来查看swagger文档



![img](https://user-gold-cdn.xitu.io/2018/7/20/164b4d92a0d1db79?imageView2/0/w/1280/h/960/ignore-error/1)



## Pig的Zuul 核心实现

获取到zuul配置的路由信息，主要到**SwaggerResource**

```
/**
* 参考jhipster
* GatewaySwaggerResourcesProvider
*/
@Component
@Primary
public class RegistrySwaggerResourcesProvider implements SwaggerResourcesProvider {
    private final RouteLocator routeLocator;
    public RegistrySwaggerResourcesProvider(RouteLocator routeLocator) {
        this.routeLocator = routeLocator;
    }
    
    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        List<Route> routes = routeLocator.getRoutes();
        routes.forEach(route -> {
            //授权不维护到swagger
            if (!StringUtils.contains(route.getId(), ServiceNameConstant.AUTH_SERVICE)){
                resources.add(swaggerResource(route.getId(), route.getFullPath().replace("**", "v2/api-docs")));
            }
        });
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion("2.0");
        return swaggerResource;
    }
}
复制代码
```

## PigX的Spring Cloud Gateway 实现

### 注入路由到**SwaggerResource**

```
@Component
@Primary
@AllArgsConstructor
public class SwaggerProvider implements SwaggerResourcesProvider {
	public static final String API_URI = "/v2/api-docs";
	private final RouteLocator routeLocator;
	private final GatewayProperties gatewayProperties;


	@Override
	public List<SwaggerResource> get() {
		List<SwaggerResource> resources = new ArrayList<>();
		List<String> routes = new ArrayList<>();
		routeLocator.getRoutes().subscribe(route -> routes.add(route.getId()));
		gatewayProperties.getRoutes().stream().filter(routeDefinition -> routes.contains(routeDefinition.getId()))
			.forEach(routeDefinition -> routeDefinition.getPredicates().stream()
				.filter(predicateDefinition -> "Path".equalsIgnoreCase(predicateDefinition.getName()))
				.filter(predicateDefinition -> !"pigx-auth".equalsIgnoreCase(routeDefinition.getId()))
				.forEach(predicateDefinition -> resources.add(swaggerResource(routeDefinition.getId(),
					predicateDefinition.getArgs().get(NameUtils.GENERATED_NAME_PREFIX + "0")
						.replace("/**", API_URI)))));
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion("2.0");
		return swaggerResource;
	}
}

复制代码
```

## 提供swagger 对外接口配置



```
@Slf4j
@Configuration
@AllArgsConstructor
public class RouterFunctionConfiguration {
	private final SwaggerResourceHandler swaggerResourceHandler;
	private final SwaggerSecurityHandler swaggerSecurityHandler;
	private final SwaggerUiHandler swaggerUiHandler;

	@Bean
	public RouterFunction routerFunction() {
		return RouterFunctions.route(
			.andRoute(RequestPredicates.GET("/swagger-resources")
				.and(RequestPredicates.accept(MediaType.ALL)), swaggerResourceHandler)
			.andRoute(RequestPredicates.GET("/swagger-resources/configuration/ui")
				.and(RequestPredicates.accept(MediaType.ALL)), swaggerUiHandler)
			.andRoute(RequestPredicates.GET("/swagger-resources/configuration/security")
				.and(RequestPredicates.accept(MediaType.ALL)), swaggerSecurityHandler);

	}
}
复制代码
```

## 业务handler 的实现

```
	@Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		return ServerResponse.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(BodyInserters.fromObject(swaggerResources.get()));
	}
	
    @Override
	public Mono<ServerResponse> handle(ServerRequest request) {
		return ServerResponse.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(BodyInserters.fromObject(
				Optional.ofNullable(securityConfiguration)
					.orElse(SecurityConfigurationBuilder.builder().build())));
	}
	
    @Override
    public Mono<ServerResponse> handle(ServerRequest request) {
        return ServerResponse.status(HttpStatus.OK)
			.contentType(MediaType.APPLICATION_JSON_UTF8)
			.body(BodyInserters.fromObject(
				Optional.ofNullable(uiConfiguration)
					.orElse(UiConfigurationBuilder.builder().build())));
	}
复制代码
```

### swagger路径转换

通过以上配置，可以实现文档的参考和展示了，但是使用swagger 的 **try it out** 功能发现路径是路由切割后的路径比如：

swagger 文档中的路径为： 主机名：端口：映射路径   少了一个 **服务路由前缀**，是因为展示handler 经过了 **StripPrefixGatewayFilterFactory** 这个过滤器的处理，原有的 路由前缀被过滤掉了！

### 方案1，通过swagger 的host 配置手动维护一个前缀

```
return new Docket(DocumentationType.SWAGGER_2)
    .apiInfo(apiInfo())
    .host("主机名：端口：服务前缀")  //注意这里的主机名：端口是网关的地址和端口
    .select()
    .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
    .paths(PathSelectors.any())
    .build()
    .globalOperationParameters(parameterList);
复制代码
```

### 方案2，增加X-Forwarded-Prefix

swagger 在拼装URL 数据时候，会增加X-Forwarder-Prefix 请求头里面的信息为前缀



![img](https://user-gold-cdn.xitu.io/2018/7/20/164b4d92a1282631?imageView2/0/w/1280/h/960/ignore-error/1)

![img](https://user-gold-cdn.xitu.io/2018/7/20/164b4d92a11dc763?imageView2/0/w/1280/h/960/ignore-error/1)

通过如上分析，知道应该在哪里下手了吧，在 网关上追加一个请求头即可



```
@Component
public class SwaggerHeaderFilter extends AbstractGatewayFilterFactory {
	private static final String HEADER_NAME = "X-Forwarded-Prefix";

	@Override
	public GatewayFilter apply(Object config) {
		return (exchange, chain) -> {
			ServerHttpRequest request = exchange.getRequest();
			String path = request.getURI().getPath();
			if (!StringUtils.endsWithIgnoreCase(path, SwaggerProvider.API_URI)) {
				return chain.filter(exchange);
			}

			String basePath = path.substring(0, path.lastIndexOf(SwaggerProvider.API_URI));


			ServerHttpRequest newRequest = request.mutate().header(HEADER_NAME, basePath).build();
			ServerWebExchange newExchange = exchange.mutate().request(newRequest).build();
			return chain.filter(newExchange);
		};
	}
}
复制代码
```

## 总结

- 相对zuul的实现，核心逻辑都是一样，获取到配置路由信息，重写swaggerresource
- gateway的配置稍微麻烦，资源的提供handler，swagger url 重写的细节
- 我的知识星球：[《微服务最前沿》 免费的微服务资讯分享](https://link.juejin.im?target=http%3A%2F%2Fobq1lvsd9.bkt.clouddn.com%2F20180720070346.png)
- 源码获取：[基于Spring Cloud Finchley.RELEASE、oAuth2 实现的权限系统](https://link.juejin.im?target=https%3A%2F%2Fgitee.com%2Flog4j%2Fpig%2Fwikis%2Fpigx%3Fsort_id%3D467134)

链接：https://juejin.im/post/5b511d016fb9a04fda4e13cb

## 