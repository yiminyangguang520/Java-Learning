package com.thoughtmechanix.licenses.services;


import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DiscoveryService {

  @Autowired
  RestTemplate restTemplate;

  @Autowired
  private DiscoveryClient discoveryClient;

  public List getEurekaServices() {
    List<String> services = new ArrayList<>();

    discoveryClient.getServices()
        .forEach(serviceName -> discoveryClient.getInstances(serviceName)
            .forEach(instance -> services.add(String.format("%s:%s", serviceName, instance.getUri()))));

    return services;
  }


}
