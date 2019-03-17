package cn.msgcode.consumer;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author litz-a
 */
@RestController
public class RemoteService {

  @Resource
  private RestTemplate restTemplate;

  @RequestMapping("test")
  public String test() {
    return restTemplate.postForObject("http://nacos-provider/config/get", null, String.class);
  }
}
