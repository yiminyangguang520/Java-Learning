package com.algerfan.domain;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bruce
 */
@Data
@Document(collection = "user")
public class User {

  @Id
  private String id;

  @NotBlank
  private String name;

  @Range(min = 10, max = 100)
  private int age;
}
