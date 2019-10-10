package com.ns.gwttoken.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author litz-a
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {

  @Id
  private String email;
  private String password;
  private String role;
}
