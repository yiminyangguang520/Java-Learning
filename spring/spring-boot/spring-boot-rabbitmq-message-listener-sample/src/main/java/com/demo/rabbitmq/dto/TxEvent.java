package com.demo.rabbitmq.dto;

import java.io.Serializable;

/**
 * @author litz-a
 */
public class TxEvent implements Serializable {

  private static final long serialVersionUID = 1L;

  private String cardId;

  private boolean isDebit;

  private Float amount;

  private String txRefNum;

  public String getCardId() {
    return cardId;
  }

  public void setCardId(String cardId) {
    this.cardId = cardId;
  }

  public boolean isDebit() {
    return isDebit;
  }

  public void setDebit(boolean isDebit) {
    this.isDebit = isDebit;
  }

  public Float getAmount() {
    return amount;
  }

  public void setAmount(Float amount) {
    this.amount = amount;
  }

  public String getTxRefNum() {
    return txRefNum;
  }

  public void setTxRefNum(String txRefNum) {
    this.txRefNum = txRefNum;
  }

}
