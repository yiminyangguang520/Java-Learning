package com.ns.gwttoken.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author min
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {

  @Id
  private Long studentId;
  private String name;
}
