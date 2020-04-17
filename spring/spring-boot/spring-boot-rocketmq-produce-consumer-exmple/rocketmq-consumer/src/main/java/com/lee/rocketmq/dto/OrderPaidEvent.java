package com.lee.rocketmq.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPaidEvent implements Serializable {

  private String orderId;

  private BigDecimal paidMoney;
}
