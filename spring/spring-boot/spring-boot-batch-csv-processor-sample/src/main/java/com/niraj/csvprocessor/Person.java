package com.niraj.csvprocessor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author min
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

  @NotNull
  private String lastName;

  @NotNull
  private String firstName;

  @NotNull
  @Max(value = 100)
  private Integer age;

  private String ageGroup;


}
