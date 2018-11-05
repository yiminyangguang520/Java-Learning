package com.lee.mybatis.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class Account {

  private Integer id;

  private String accountName;

  private String accountNum;

  private Integer accountType;

  private String accountPurpose;

  private String depositBank;

  @JsonIgnore
  private Integer accountState;

  @JsonIgnore
  private Date createDate;

  @JsonIgnore
  private Date updateDate;

  @JsonIgnore
  private Integer deleted;

  private Integer customerId;

  @Getter
  @Setter
  private List<AccountBill> accountBillList;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName == null ? null : accountName.trim();
  }

  public String getAccountNum() {
    return accountNum;
  }

  public void setAccountNum(String accountNum) {
    this.accountNum = accountNum == null ? null : accountNum.trim();
  }

  public Integer getAccountType() {
    return accountType;
  }

  public void setAccountType(Integer accountType) {
    this.accountType = accountType;
  }

  public String getAccountPurpose() {
    return accountPurpose;
  }

  public void setAccountPurpose(String accountPurpose) {
    this.accountPurpose = accountPurpose == null ? null : accountPurpose.trim();
  }

  public String getDepositBank() {
    return depositBank;
  }

  public void setDepositBank(String depositBank) {
    this.depositBank = depositBank == null ? null : depositBank.trim();
  }

  public Integer getAccountState() {
    return accountState;
  }

  public void setAccountState(Integer accountState) {
    this.accountState = accountState;
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

  public Integer getDeleted() {
    return deleted;
  }

  public void setDeleted(Integer deleted) {
    this.deleted = deleted;
  }

  public Integer getCustomerId() {
    return customerId;
  }

  public void setCustomerId(Integer customerId) {
    this.customerId = customerId;
  }
}