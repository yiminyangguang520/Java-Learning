package com.xingfly.xfauth.domain;

import com.xingfly.xfauth.domain.base.BaseEntity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Authority extends BaseEntity {

  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String value;
}
