package com.lee.mybatis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.math.BigDecimal;
import java.util.Date;

public class AccountBill {

  private Integer id;

  private Date statisticsDate;

  private BigDecimal borrowMoney;

  private BigDecimal loanMoney;

  @JsonIgnore
  private Date createDate;

  @JsonIgnore
  private Date updateDate;

  @JsonIgnore
  private Date deletedDate;

  @JsonIgnore
  private Integer deleted;

  private Integer accountId;

  @JsonIgnore
  private Integer customerId;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Date getStatisticsDate() {
    return statisticsDate;
  }

  public void setStatisticsDate(Date statisticsDate) {
    this.statisticsDate = statisticsDate;
  }

  public BigDecimal getBorrowMoney() {
    return borrowMoney;
  }

  public void setBorrowMoney(BigDecimal borrowMoney) {
    this.borrowMoney = borrowMoney;
  }

  public BigDecimal getLoanMoney() {
    return loanMoney;
  }

  public void setLoanMoney(BigDecimal loanMoney) {
    this.loanMoney = loanMoney;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Date getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(Date updateDate) {
    this.updateDate = updateDate;
  }

  public Date getDeletedDate() {
    return deletedDate;
  }

  public void setDeletedDate(Date deletedDate) {
    this.deletedDate = deletedDate;
  }

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }
}