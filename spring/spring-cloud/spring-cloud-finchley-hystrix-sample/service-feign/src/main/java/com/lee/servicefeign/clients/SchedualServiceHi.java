package com.lee.servicefeign.clients;

import com.lee.servicefeign.clients.impl.SchedualServiceHiHystricImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author min
 **/
@FeignClient(value = "service-client", fallback = SchedualServiceHiHystricImpl.class)
public interface SchedualServiceHi {

  /**
   * sayHiFromClientOne
   * @param name
   * @return
   */
  @RequestMapping(value = "/hi", method = RequestMethod.GET)
  String sayHiFromClientOne(@RequestParam(value = "name") String name);
}

