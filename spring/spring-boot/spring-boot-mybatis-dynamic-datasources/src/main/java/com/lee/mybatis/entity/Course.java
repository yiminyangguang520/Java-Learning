package com.lee.mybatis.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author bruce
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(of = {"id", "name", "lessonPeriod", "score"})
@ToString
public class Course implements Serializable {

  private Integer id;

  private String name;

  private Double lessonPeriod;

  private Double score;
}
