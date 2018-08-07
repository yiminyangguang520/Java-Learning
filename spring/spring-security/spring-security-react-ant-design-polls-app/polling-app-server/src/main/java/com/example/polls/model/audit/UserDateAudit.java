package com.example.polls.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

/**
 *
 * @author rajeevkumarsingh
 * @date 19/08/17
 */

@MappedSuperclass
@JsonIgnoreProperties(
    value = {"createdBy", "updatedBy"},
    allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {

  @CreatedBy
  private Long createdBy;

  @LastModifiedBy
  private Long updatedBy;

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Long getUpdatedBy() {
    return updatedBy;
  }

  public void setUpdatedBy(Long updatedBy) {
    this.updatedBy = updatedBy;
  }
}
