package com.xingfly.xfauth.domain.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Data;

/**
 * Created by SuperS on 2017/9/25.
 *
 * @author SuperS
 */
@MappedSuperclass
@Data
public class BaseEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  @Column(updatable = false, nullable = false)
  private Instant createTime;

  @Column(nullable = false)
  @JsonIgnore
  private Instant updateTime;

  @PrePersist
  public void prePersist() {
    createTime = updateTime = Instant.now();
  }

  @PreUpdate
  public void preUpdate() {
    updateTime = Instant.now();
  }
}
