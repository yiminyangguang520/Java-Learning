package com.windmt;

import com.windmt.domain.Customer;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @author: yibo
 **/
public class CustomerCrudTests {


    private WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8200").build();

    @Test
    public void testSaveCustomer() {
        client.post().uri("/customer")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(getCustomer()), Customer.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("name").isEqualTo("Jack")
                .jsonPath("mobile").isEqualTo("12345678901")
                .jsonPath("id").isNotEmpty(); // 5ae15fa640f1687f200d8941
    }

    @Test
    public void testGetCustomer() {
        Customer customer = getCustomer();
        client.get().uri("/customer/" + customer.getId())
                .exchange()
                .expectBody(Customer.class)
                .isEqualTo(customer);
    }

    @Test
    public void testUpdateCustomer() {
        Customer customer = getCustomer();
        customer.setMobile("98765432101");
        client.put().uri("/customer/" + customer.getId())
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(customer), Customer.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody().jsonPath("mobile").isEqualTo("98765432101");
    }

    @Test
    public void testDeleteCustomer() {
        Customer customer = getCustomer();
        client.get().uri("/customer/" + customer.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("name").isEqualTo("Jack")
                .jsonPath("id").isNotEmpty();

        client.delete().uri("/customer/" + customer.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody().isEmpty();

        client.get().uri("/customer/" + customer.getId())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .isEmpty();
    }

    @Test
    public void testCustomerList() {
        client.get().uri("/customer")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$[0].name").isEqualTo("Jack")
                .jsonPath("$[0].mobile").isEqualTo("12345678901");
    }

    private Customer getCustomer() {
        return new Customer("5ae15fa640f1687f200d8941", "Jack", "12345678901");
    }

}
