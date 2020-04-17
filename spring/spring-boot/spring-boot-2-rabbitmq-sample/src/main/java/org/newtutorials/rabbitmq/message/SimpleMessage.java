package org.newtutorials.rabbitmq.message;

import java.io.Serializable;

/**
 * Created by dani on 5/22/2017.
 *
 * @author min
 */
public class SimpleMessage implements Serializable {

  private Long messageId;
  private String theMessage;
  private Integer step;

  public SimpleMessage() {
  }

  public SimpleMessage(Long messageId, String theMessage, Integer step) {
    this.messageId = messageId;
    this.theMessage = theMessage;
    this.step = step;
  }

  public Long getMessageId() {
    return messageId;
  }

  public void setMessageId(Long messageId) {
    this.messageId = messageId;
  }

  public String getTheMessage() {
    return theMessage;
  }

  public void setTheMessage(String theMessage) {
    this.theMessage = theMessage;
  }

  public Integer getStep() {
    return step;
  }

  public void setStep(Integer step) {
    this.step = step;
  }

  @Override
  public String toString() {
    return "SimpleMessage{" +
        "messageId=" + messageId +
        ", theMessage='" + theMessage + '\'' +
        ", step=" + step +
        '}';
  }
}
