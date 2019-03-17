package pl.piomin.services.order.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import pl.piomin.services.order.model.Product;

/**
 * @author litz-a
 */
@FeignClient(name = "product-service")
public interface ProductClient {

  @PostMapping("/ids")
  List<Product> findByIds(List<Long> ids);

}
