package com.brahalla.cerberus.model.json.response;

import com.brahalla.cerberus.model.base.ModelBase;

/**
 * @author litz-a
 */
public class AuthenticationResponse extends ModelBase {

  private static final long serialVersionUID = -6624726180748515507L;
  private String token;

  public AuthenticationResponse() {
    super();
  }

  public AuthenticationResponse(String token) {
    this.setToken(token);
  }

  public String getToken() {
    return this.token;
  }

  public void setToken(String token) {
    this.token = token;
  }

}
