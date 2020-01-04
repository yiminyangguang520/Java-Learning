package org.ostenant.springboot.learning.examples.config;

import com.weibo.api.motan.config.springsupport.AnnotationBean;
import com.weibo.api.motan.config.springsupport.BasicServiceConfigBean;
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
        config.setId("motanProtocol");
        config.setName("motan");
        config.setMaxContentLength(1048576);
        config.setMaxServerConnection(80000);
        config.setMaxWorkerThread(800);
        config.setMinWorkerThread(20);
        return config;
    }

    @Bean(name = "motanBasicService")
    public BasicServiceConfigBean baseServiceConfig() {
        BasicServiceConfigBean config = new BasicServiceConfigBean();
        config.setId("motanBasicService");
        config.setExport("motanProtocol:50020");
        config.setGroup("g2");
        config.setModule("m2");
        config.setAccessLog(false);
        config.setShareChannel(true);
        config.setApplication("motanService");
        config.setRegistry("registry");
        return config;
    }


}
