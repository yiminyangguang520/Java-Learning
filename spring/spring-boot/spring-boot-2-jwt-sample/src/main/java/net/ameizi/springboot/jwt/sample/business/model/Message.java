package net.ameizi.springboot.jwt.sample.business.model;

import lombok.Data;

@Data
public class Message {

  private String message;

  private String code;

  public Message() {
  }

  public Message(String code, String message) {
    this.code = code;
    this.message = message;
  }

  public Message(String message) {
    this.message = message;
  }

}
