package com.ns.gwttoken.entity;


import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author litz-a
 */
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

  @Id
  private Long subjectId;
  private String subjectName;
}
