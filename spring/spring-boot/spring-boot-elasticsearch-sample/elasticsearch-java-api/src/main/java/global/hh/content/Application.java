package global.hh.content;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@RestController
@EnableAutoConfiguration
@EnableDiscoveryClient
@RequestMapping("/")
@Slf4j
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
//        log.info("Start SpringApplication Successes !");
    }

    @Bean
    public TransportClient transportClient(){
        Settings settings = Settings.builder()
                .put("client.transport.ignore_cluster_name", false)//Set to true to ignore cluster name validation of connected nodes.Defaults to false
                .put("client.transport.ping_timeout", 5 , TimeUnit.SECONDS) //The time to wait for a ping response from a node. Defaults to 5s.
                .put("client.transport.nodes_sampler_interval", 5 ,TimeUnit.SECONDS)//How often to sample / ping the nodes listed and connected. Defaults to 5s.
                .put("cluster.name", "bruce-lee-application") //set cluster name
                .put("client.transport.sniff", true) //The Transport client comes with a cluster sniffing feature which allows it to dynamically add new hosts and remove old ones
                .build();

        TransportClient transportClient = null;
        try {
            transportClient = new PreBuiltTransportClient(settings)
                    //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
                    //.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300))
                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("127.0.0.1"), 9300));
        } catch (UnknownHostException e) {
//            log.error("Create TransportClient Failure! {}",e.getMessage());
        }
       /* client.close();*/
        return transportClient;
    }

    @RequestMapping("/")
    public String index() {
        return "Hello World";
    }

}