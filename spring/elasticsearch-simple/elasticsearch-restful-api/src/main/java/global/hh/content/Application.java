package global.hh.content;

import com.didispace.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableDiscoveryClient
@Slf4j
@EnableSwagger2Doc
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        log.info("Start SpringApplication Successes !");
    }

    @Bean
    public RestClient restClient(){
        RestClientBuilder builder = RestClient.builder(
             /*   new HttpHost("localhost", 9200, "http"),*/
                new HttpHost("127.0.0.1", 9200, "http")
        );
        //Header[] defaultHeaders = new Header[]{new BasicHeader("header", "value")};
        //builder.setDefaultHeaders(defaultHeaders);//Set the default headers that need to be sent with each request, to prevent having to specify them with each single request
        builder.setMaxRetryTimeoutMillis(10000);//Set the timeout that should be honoured in case multiple attempts are made for the same request. The default value is 30 seconds,
        builder.setFailureListener(new RestClient.FailureListener() {
            @Override
            public void onFailure(HttpHost host) {
//                log.error("Create RestClient Failure! {}",host.toHostString());
            }
        });
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                return requestConfigBuilder.setSocketTimeout(10000);
            }
        });
        RestClient restClient =  builder.build();
      /*  restClient.close();*/
        return restClient;
    }

    @Bean
    public RestHighLevelClient restHighLevelClient(RestClient restClient){
        RestHighLevelClient restHighLevelClient = new RestHighLevelClient(restClient);
        return restHighLevelClient;
    }

    @ApiIgnore
    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

}