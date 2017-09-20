//package com.glodon.es.config;
//
//import java.net.InetAddress;
//import org.elasticsearch.client.transport.TransportClient;
//import org.elasticsearch.common.settings.Settings;
//import org.elasticsearch.common.transport.InetSocketTransportAddress;
//import org.elasticsearch.transport.client.PreBuiltTransportClient;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.DisposableBean;
//import org.springframework.beans.factory.FactoryBean;
//import org.springframework.beans.factory.InitializingBean;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Author litz-a
// * @Date 2017/9/12 14:26
// */
//@Configuration
//public class ElasticsearchConfig implements FactoryBean<TransportClient>, InitializingBean, DisposableBean {
//
//  private static final Logger logger = LoggerFactory.getLogger(ElasticsearchConfig.class);
//  //由于项目从2.2.4配置的升级到 5.4.1版本 原配置文件不想动还是指定原来配置参数
//  @Value("${spring.data.elasticsearch.cluster-nodes}")
//  private String clusterNodes ;
//
//  @Value("${spring.data.elasticsearch.cluster-name}")
//  private String clusterName;
//
//  private TransportClient client;
//
//  @Override
//  public void destroy() throws Exception {
//    try {
//      logger.info("Closing elasticSearch client");
//      if (client != null) {
//        client.close();
//      }
//    } catch (final Exception e) {
//      logger.error("Error closing ElasticSearch client: ", e);
//    }
//  }
//
//  @Override
//  public TransportClient getObject() throws Exception {
//    return client;
//  }
//
//  @Override
//  public Class<TransportClient> getObjectType() {
//    return TransportClient.class;
//  }
//
//  @Override
//  public boolean isSingleton() {
//    return false;
//  }
//
//  @Override
//  public void afterPropertiesSet() throws Exception {
//    buildClient();
//  }
//
//  protected void buildClient()  {
//    try {
//      PreBuiltTransportClient preBuiltTransportClient = new PreBuiltTransportClient(settings());
//      if (!"".equals(clusterNodes)){
//        for (String nodes:clusterNodes.split(",")) {
//          String InetSocket [] = nodes.split(":");
//          String  Address = InetSocket[0];
//          Integer  port = Integer.valueOf(InetSocket[1]);
//          preBuiltTransportClient.addTransportAddress(new
//              InetSocketTransportAddress(InetAddress.getByName(Address),port ));
//        }
//        client = preBuiltTransportClient;
//      }
//    } catch (Exception e) {
//      logger.error(e.getMessage());
//    }
//  }
//  /**
//   * 初始化默认的client
//   * 客户端允许嗅其余的集群,它将数据节点添加到列表的机器使用。在这种情况下要注意,将使用的IP地址的其他节点开始(“publish”地址)。启用它,设置client.transport.sniff为 true:
//   */
//  private Settings settings(){
//    Settings settings = Settings.builder()
//        .put("cluster.name",clusterName)
//        .put("client.transport.sniff",true).build();
//    client = new PreBuiltTransportClient(settings);
//    return settings;
//  }
//}
