package com.javasampleapproach.formvalidation.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

/**
 * @author min
 */
public class RequestInfo {

  @Email(message = "invalid email")
  private String email;

  @Size(min = 5, max = 255, message = "Please enter between {min} and {max} characters.")
  private String text;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }

  @Override
  public String toString() {
    return "RequestInfo [email=" + email + ", text=" + text + "]";
  }

}
