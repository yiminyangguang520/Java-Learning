package pl.piomin.services.customer.client;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import pl.piomin.services.customer.model.Account;

/**
 * @author min
 */
@FeignClient(name = "account-service")
public interface AccountClient {

  /**
   * findByCustomer
   * @param customerId
   * @return
   */
  @GetMapping("/customer/{customerId}")
  List<Account> findByCustomer(@PathVariable("customerId") Long customerId);
}
