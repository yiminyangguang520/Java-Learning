package net.ameizi.springboot.jwt.sample.business.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
@Entity
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private int id;
  private String userName;
  private String loginName;
  private String password;
  private String roles;
  private String email;
  private String location;
  private String signature;
  @JsonIgnore
  private Date createAt;
  @JsonIgnore
  private Date updateAt;

}
