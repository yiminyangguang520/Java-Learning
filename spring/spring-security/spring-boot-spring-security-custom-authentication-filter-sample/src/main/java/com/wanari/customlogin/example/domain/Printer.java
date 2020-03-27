package com.wanari.customlogin.example.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

/**
 * @author bruce
 * */
@Getter
@Setter
@Entity(name = "printer")
@GenericGenerator(name = "jpa-uuid", strategy = "uuid")
public class Printer implements Serializable {

  @Id
  @GeneratedValue(generator = "jpa-uuid")
  public String id;

  public String name;

  public Boolean isDetonated;
}
