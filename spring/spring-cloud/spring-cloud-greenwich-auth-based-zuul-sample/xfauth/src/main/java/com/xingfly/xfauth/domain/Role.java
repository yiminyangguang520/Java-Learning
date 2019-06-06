package com.xingfly.xfauth.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.xingfly.xfauth.domain.base.BaseEntity;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.BatchSize;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class Role extends BaseEntity {

  private static final long serialVersionUID = 1L;
  @Id
  @GeneratedValue
  private Long id;
  private String name;
  private String value;

  @JsonIgnore
  @ManyToMany(targetEntity = Authority.class, fetch = FetchType.EAGER)
  @BatchSize(size = 20)
  private Set<Authority> authorities = new HashSet<>();

}
