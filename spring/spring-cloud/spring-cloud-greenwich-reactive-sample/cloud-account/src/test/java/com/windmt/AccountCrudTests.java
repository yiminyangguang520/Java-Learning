package com.windmt;

import com.windmt.domain.Account;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

/**
 * @author: yibo
 **/
public class AccountCrudTests {

    private WebTestClient client = WebTestClient.bindToServer().baseUrl("http://localhost:8100").build();

    @Test
    public void testSaveAccount() {
        Account account = getAccount();
        client.post().uri("/account")
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(account), Account.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("id").isNotEmpty()
                .jsonPath("customerId").isEqualTo(account.getCustomerId())
                .jsonPath("amount").isEqualTo(account.getAmount());
    }


    private Account getAccount() {
        return new Account("5ae16fc940f1688225034c0c", "5ae15fa640f1687f200d8941", 100.0D);
    }
}
