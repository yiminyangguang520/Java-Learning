package com.javasampleapproach.webflux;

import com.javasampleapproach.webflux.model.Customer;
import java.util.HashMap;
import java.util.Map;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@WebFluxTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SpringWebFluxTestApplicationTests {

  @Autowired
  private WebTestClient webClient;

  private static Map<String, Customer> customerMap = new HashMap<>();

  @BeforeClass
  public static void setup() throws Exception {
    customerMap.put("Jack", new Customer(1, "Jack", "Smith", 20));
    customerMap.put("Peter", new Customer(2, "Peter", "Johnson", 25));
    customerMap.put("Mary", new Customer(3, "Mary", "Taylor", 27));
    customerMap.put("Amy", new Customer(3, "Amy", "Taylor", 24));
  }

  @Test
  public void tc_1_getAllCustomer() throws Exception {

    webClient.get().uri("/api/customer").accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Customer.class)
        .hasSize(2)
        .contains(customerMap.get("Jack"), customerMap.get("Peter"));
  }

  @Test
  public void tc_2_getCustomer() throws Exception {

    webClient.get().uri("/api/customer/{id}", 2).accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBody(Customer.class)
        .isEqualTo(customerMap.get("Peter"));
  }

  @Test
  public void tc_3_postCustomer() throws Exception {

    webClient.post().uri("/api/customer/post")
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(customerMap.get("Mary")))
        .exchange()
        .expectStatus().isCreated()
        .expectBody(String.class)
        .isEqualTo("Post Successfully!");
  }

  @Test
  public void tc_4_putCustomer() throws Exception {

    webClient.put().uri("/api/customer/put/{id}", 3)
        .contentType(MediaType.APPLICATION_JSON)
        .body(BodyInserters.fromValue(customerMap.get("Amy")))
        .exchange()
        .expectStatus().isCreated()
        .expectBody(Customer.class)
        .isEqualTo(customerMap.get("Amy"));

  }

  @Test
  public void tc_5_deleteCustomer() throws Exception {

    webClient.delete().uri("/api/customer/delete/{id}", 1)
        .exchange()
        .expectStatus().isAccepted()
        .expectBody(String.class)
        .isEqualTo("Delete Succesfully!");
  }

  @Test
  public void tc_6_finalCheckByGetAllCustomer() throws Exception {

    webClient.get().uri("/api/customer").accept(MediaType.APPLICATION_JSON)
        .exchange()
        .expectStatus().isOk()
        .expectBodyList(Customer.class)
        .hasSize(2)
        .contains(customerMap.get("Peter"), customerMap.get("Amy"));
  }
}
