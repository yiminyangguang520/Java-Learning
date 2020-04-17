package com.javasampleapproach.infinispan.controller;

import com.javasampleapproach.infinispan.model.Customer;
import com.javasampleapproach.infinispan.repository.CustomerRepo;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
public class CacheController {


  @Autowired
  private CustomerRepo custRepo;

  @RequestMapping("/findCustById")
  public Customer findCustById(@RequestParam("id") long id) {
    return custRepo.findByCode(id);
  }

  @RequestMapping("/putCust")
  public String putCustomer(@RequestParam("custNumber") int custNumber) {
    Random r = new Random();
    for (int i = 0; i < custNumber; i++) {
      custRepo.putCustomerToCache(r.nextLong());
    }
    return "Done";
  }

  @RequestMapping("/evictAll")
  public String evictAll() {
    custRepo.evictAllEntries();
    return "Done";
  }

  @RequestMapping("/evictEntry")
  public String evictEntry(@RequestParam("id") long id) {
    custRepo.evictEntry(id);
    return "Done";
  }
}
