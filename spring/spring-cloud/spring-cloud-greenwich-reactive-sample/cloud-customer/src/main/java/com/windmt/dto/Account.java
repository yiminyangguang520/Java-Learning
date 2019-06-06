package com.windmt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * @author yibo
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Account {

    private String id;

    private String customerId;

    private Double amount;

}
