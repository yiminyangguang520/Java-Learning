package com.glodon.sso.core.user;

import java.io.Serializable;
import lombok.Data;

/**
 * @author litz-a
 */
@Data
public class SsoUser implements Serializable {
  private int userid;
  private String username;
}
