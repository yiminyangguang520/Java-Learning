package com.lee.model;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author min
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {

  private String nickName;
  private String loginBy;
  private String token;
}
