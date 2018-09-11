package org.newtutorials.elasticsearch.client;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 *
 * @author dani
 * @date 5/7/2017
 */
public class ElasticSearchClientBuilder {

  public static TransportClient createElasticSearchClient(String host, int port, String clusterName) throws UnknownHostException {
    Settings settings = Settings.builder()
        .put("cluster.name", clusterName)
        .put("client.transport.sniff", true)
        .put("client.transport.ping_timeout", 20, TimeUnit.SECONDS)
        .build();
    return new PreBuiltTransportClient(settings)
        .addTransportAddress(new InetSocketTransportAddress(Inet4Address.getByName(host), port));
  }
}
