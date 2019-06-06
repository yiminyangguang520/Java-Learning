package com.windmt.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/**
 * @author yibo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "accounts")
public class Account {

    @Id
    private String id;

    private String customerId;

    private Double amount;

}
