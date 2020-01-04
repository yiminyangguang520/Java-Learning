package org.ostenant.springboot.learning.examples.config;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicRefererConfigBean;
import com.weibo.api.motan.config.springsupport.ProtocolConfigBean;
import com.weibo.api.motan.config.springsupport.RegistryConfigBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MotanConfiguration {

    @Bean
    public AnnotationBean motanAnnotationBean() {
        AnnotationBean motanAnnotationBean = new AnnotationBean();
        motanAnnotationBean.setPackage("org.ostenant.springboot.learning.examples");
        return motanAnnotationBean;
    }

    @Bean(name = "registry")
    public RegistryConfigBean registryConfig() {
        RegistryConfigBean config = new RegistryConfigBean();
        config.setRegProtocol("zookeeper");
        config.setAddress("192.168.127.132:2181,192.168.127.132:2182,192.168.127.132:2183");
        config.setConnectTimeout(2000);
        config.setRequestTimeout(500);
        return config;
    }

    @Bean(name = "motanProtocol")
    public ProtocolConfigBean protocolConfig() {
        ProtocolConfigBean config = new ProtocolConfigBean();
        config.setDefault(true);
        config.setName("motan");
        config.setHaStrategy("failover");
        config.setLoadbalance("roundrobin");
        config.setMaxClientConnection(10);
        config.setMinClientConnection(2);
        return config;
    }


    @Bean(name = "motanBasicReferer1")
    public BasicRefererConfigBean basicRefererConfig1() {
        BasicRefererConfigBean config = new BasicRefererConfigBean();
        config.setId("motanBasicReferer1");
        config.setRequestTimeout(200);
        config.setAccessLog(false);
        config.setRetries(3);
        config.setGroup("g1");
        config.setModule("m1");
        config.setApplication("");
        config.setProtocol("motanProtocol");
        config.setRegistry("registry");
        config.setThrowException(false);
        config.setCheck(true);
        return config;
    }

    @Bean(name = "motanBasicReferer2")
    public BasicRefererConfigBean basicRefererConfig2() {
        BasicRefererConfigBean config = new BasicRefererConfigBean();
        config.setId("motanBasicReferer2");
        config.setRequestTimeout(200);
        config.setAccessLog(false);
        config.setRetries(3);
        config.setGroup("g2");
        config.setModule("m2");
        config.setApplication("");
        config.setProtocol("motanProtocol");
        config.setRegistry("registry");
        config.setThrowException(false);
        config.setCheck(true);
        return config;
    }

}
