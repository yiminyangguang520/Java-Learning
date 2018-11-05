package com.lee.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public AccountExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  protected abstract static class GeneratedCriteria {

    protected List<Criterion> criteria;

    protected GeneratedCriteria() {
      super();
      criteria = new ArrayList<Criterion>();
    }

    public boolean isValid() {
      return criteria.size() > 0;
    }

    public List<Criterion> getAllCriteria() {
      return criteria;
    }

    public List<Criterion> getCriteria() {
      return criteria;
    }

    protected void addCriterion(String condition) {
      if (condition == null) {
        throw new RuntimeException("Value for condition cannot be null");
      }
      criteria.add(new Criterion(condition));
    }

    protected void addCriterion(String condition, Object value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value));
    }

    protected void addCriterion(String condition, Object value1, Object value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      criteria.add(new Criterion(condition, value1, value2));
    }

    public Criteria andIdIsNull() {
      addCriterion("account.id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("account.id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("account.id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("account.id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("account.id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("account.id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("account.id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("account.id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("account.id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("account.id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("account.id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("account.id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andAccountNameIsNull() {
      addCriterion("account.account_name is null");
      return (Criteria) this;
    }

    public Criteria andAccountNameIsNotNull() {
      addCriterion("account.account_name is not null");
      return (Criteria) this;
    }

    public Criteria andAccountNameEqualTo(String value) {
      addCriterion("account.account_name =", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameNotEqualTo(String value) {
      addCriterion("account.account_name <>", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameGreaterThan(String value) {
      addCriterion("account.account_name >", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
      addCriterion("account.account_name >=", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameLessThan(String value) {
      addCriterion("account.account_name <", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameLessThanOrEqualTo(String value) {
      addCriterion("account.account_name <=", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameLike(String value) {
      addCriterion("account.account_name like", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameNotLike(String value) {
      addCriterion("account.account_name not like", value, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameIn(List<String> values) {
      addCriterion("account.account_name in", values, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameNotIn(List<String> values) {
      addCriterion("account.account_name not in", values, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameBetween(String value1, String value2) {
      addCriterion("account.account_name between", value1, value2, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNameNotBetween(String value1, String value2) {
      addCriterion("account.account_name not between", value1, value2, "accountName");
      return (Criteria) this;
    }

    public Criteria andAccountNumIsNull() {
      addCriterion("account.account_num is null");
      return (Criteria) this;
    }

    public Criteria andAccountNumIsNotNull() {
      addCriterion("account.account_num is not null");
      return (Criteria) this;
    }

    public Criteria andAccountNumEqualTo(String value) {
      addCriterion("account.account_num =", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumNotEqualTo(String value) {
      addCriterion("account.account_num <>", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumGreaterThan(String value) {
      addCriterion("account.account_num >", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumGreaterThanOrEqualTo(String value) {
      addCriterion("account.account_num >=", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumLessThan(String value) {
      addCriterion("account.account_num <", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumLessThanOrEqualTo(String value) {
      addCriterion("account.account_num <=", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumLike(String value) {
      addCriterion("account.account_num like", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumNotLike(String value) {
      addCriterion("account.account_num not like", value, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumIn(List<String> values) {
      addCriterion("account.account_num in", values, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumNotIn(List<String> values) {
      addCriterion("account.account_num not in", values, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumBetween(String value1, String value2) {
      addCriterion("account.account_num between", value1, value2, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountNumNotBetween(String value1, String value2) {
      addCriterion("account.account_num not between", value1, value2, "accountNum");
      return (Criteria) this;
    }

    public Criteria andAccountTypeIsNull() {
      addCriterion("account.account_type is null");
      return (Criteria) this;
    }

    public Criteria andAccountTypeIsNotNull() {
      addCriterion("account.account_type is not null");
      return (Criteria) this;
    }

    public Criteria andAccountTypeEqualTo(Byte value) {
      addCriterion("account.account_type =", value, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeNotEqualTo(Byte value) {
      addCriterion("account.account_type <>", value, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeGreaterThan(Byte value) {
      addCriterion("account.account_type >", value, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeGreaterThanOrEqualTo(Byte value) {
      addCriterion("account.account_type >=", value, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeLessThan(Byte value) {
      addCriterion("account.account_type <", value, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeLessThanOrEqualTo(Byte value) {
      addCriterion("account.account_type <=", value, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeIn(List<Byte> values) {
      addCriterion("account.account_type in", values, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeNotIn(List<Byte> values) {
      addCriterion("account.account_type not in", values, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeBetween(Byte value1, Byte value2) {
      addCriterion("account.account_type between", value1, value2, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountTypeNotBetween(Byte value1, Byte value2) {
      addCriterion("account.account_type not between", value1, value2, "accountType");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeIsNull() {
      addCriterion("account.account_purpose is null");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeIsNotNull() {
      addCriterion("account.account_purpose is not null");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeEqualTo(String value) {
      addCriterion("account.account_purpose =", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeNotEqualTo(String value) {
      addCriterion("account.account_purpose <>", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeGreaterThan(String value) {
      addCriterion("account.account_purpose >", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeGreaterThanOrEqualTo(String value) {
      addCriterion("account.account_purpose >=", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeLessThan(String value) {
      addCriterion("account.account_purpose <", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeLessThanOrEqualTo(String value) {
      addCriterion("account.account_purpose <=", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeLike(String value) {
      addCriterion("account.account_purpose like", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeNotLike(String value) {
      addCriterion("account.account_purpose not like", value, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeIn(List<String> values) {
      addCriterion("account.account_purpose in", values, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeNotIn(List<String> values) {
      addCriterion("account.account_purpose not in", values, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeBetween(String value1, String value2) {
      addCriterion("account.account_purpose between", value1, value2, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andAccountPurposeNotBetween(String value1, String value2) {
      addCriterion("account.account_purpose not between", value1, value2, "accountPurpose");
      return (Criteria) this;
    }

    public Criteria andDepositBankIsNull() {
      addCriterion("account.deposit_bank is null");
      return (Criteria) this;
    }

    public Criteria andDepositBankIsNotNull() {
      addCriterion("account.deposit_bank is not null");
      return (Criteria) this;
    }

    public Criteria andDepositBankEqualTo(String value) {
      addCriterion("account.deposit_bank =", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankNotEqualTo(String value) {
      addCriterion("account.deposit_bank <>", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankGreaterThan(String value) {
      addCriterion("account.deposit_bank >", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankGreaterThanOrEqualTo(String value) {
      addCriterion("account.deposit_bank >=", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankLessThan(String value) {
      addCriterion("account.deposit_bank <", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankLessThanOrEqualTo(String value) {
      addCriterion("account.deposit_bank <=", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankLike(String value) {
      addCriterion("account.deposit_bank like", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankNotLike(String value) {
      addCriterion("account.deposit_bank not like", value, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankIn(List<String> values) {
      addCriterion("account.deposit_bank in", values, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankNotIn(List<String> values) {
      addCriterion("account.deposit_bank not in", values, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankBetween(String value1, String value2) {
      addCriterion("account.deposit_bank between", value1, value2, "depositBank");
      return (Criteria) this;
    }

    public Criteria andDepositBankNotBetween(String value1, String value2) {
      addCriterion("account.deposit_bank not between", value1, value2, "depositBank");
      return (Criteria) this;
    }

    public Criteria andAccountStateIsNull() {
      addCriterion("account.account_state is null");
      return (Criteria) this;
    }

    public Criteria andAccountStateIsNotNull() {
      addCriterion("account.account_state is not null");
      return (Criteria) this;
    }

    public Criteria andAccountStateEqualTo(Byte value) {
      addCriterion("account.account_state =", value, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateNotEqualTo(Byte value) {
      addCriterion("account.account_state <>", value, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateGreaterThan(Byte value) {
      addCriterion("account.account_state >", value, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateGreaterThanOrEqualTo(Byte value) {
      addCriterion("account.account_state >=", value, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateLessThan(Byte value) {
      addCriterion("account.account_state <", value, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateLessThanOrEqualTo(Byte value) {
      addCriterion("account.account_state <=", value, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateIn(List<Byte> values) {
      addCriterion("account.account_state in", values, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateNotIn(List<Byte> values) {
      addCriterion("account.account_state not in", values, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateBetween(Byte value1, Byte value2) {
      addCriterion("account.account_state between", value1, value2, "accountState");
      return (Criteria) this;
    }

    public Criteria andAccountStateNotBetween(Byte value1, Byte value2) {
      addCriterion("account.account_state not between", value1, value2, "accountState");
      return (Criteria) this;
    }

    public Criteria andCreateDateIsNull() {
      addCriterion("account.create_date is null");
      return (Criteria) this;
    }

    public Criteria andCreateDateIsNotNull() {
      addCriterion("account.create_date is not null");
      return (Criteria) this;
    }

    public Criteria andCreateDateEqualTo(Date value) {
      addCriterion("account.create_date =", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotEqualTo(Date value) {
      addCriterion("account.create_date <>", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateGreaterThan(Date value) {
      addCriterion("account.create_date >", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
      addCriterion("account.create_date >=", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateLessThan(Date value) {
      addCriterion("account.create_date <", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateLessThanOrEqualTo(Date value) {
      addCriterion("account.create_date <=", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateIn(List<Date> values) {
      addCriterion("account.create_date in", values, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotIn(List<Date> values) {
      addCriterion("account.create_date not in", values, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateBetween(Date value1, Date value2) {
      addCriterion("account.create_date between", value1, value2, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotBetween(Date value1, Date value2) {
      addCriterion("account.create_date not between", value1, value2, "createDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateIsNull() {
      addCriterion("account.update_date is null");
      return (Criteria) this;
    }

    public Criteria andUpdateDateIsNotNull() {
      addCriterion("account.update_date is not null");
      return (Criteria) this;
    }

    public Criteria andUpdateDateEqualTo(Date value) {
      addCriterion("account.update_date =", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateNotEqualTo(Date value) {
      addCriterion("account.update_date <>", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateGreaterThan(Date value) {
      addCriterion("account.update_date >", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
      addCriterion("account.update_date >=", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateLessThan(Date value) {
      addCriterion("account.update_date <", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
      addCriterion("account.update_date <=", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateIn(List<Date> values) {
      addCriterion("account.update_date in", values, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateNotIn(List<Date> values) {
      addCriterion("account.update_date not in", values, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateBetween(Date value1, Date value2) {
      addCriterion("account.update_date between", value1, value2, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
      addCriterion("account.update_date not between", value1, value2, "updateDate");
      return (Criteria) this;
    }

    public Criteria andDeletedIsNull() {
      addCriterion("account.deleted is null");
      return (Criteria) this;
    }

    public Criteria andDeletedIsNotNull() {
      addCriterion("account.deleted is not null");
      return (Criteria) this;
    }

    public Criteria andDeletedEqualTo(Byte value) {
      addCriterion("account.deleted =", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedNotEqualTo(Byte value) {
      addCriterion("account.deleted <>", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedGreaterThan(Byte value) {
      addCriterion("account.deleted >", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedGreaterThanOrEqualTo(Byte value) {
      addCriterion("account.deleted >=", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedLessThan(Byte value) {
      addCriterion("account.deleted <", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedLessThanOrEqualTo(Byte value) {
      addCriterion("account.deleted <=", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedIn(List<Byte> values) {
      addCriterion("account.deleted in", values, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedNotIn(List<Byte> values) {
      addCriterion("account.deleted not in", values, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedBetween(Byte value1, Byte value2) {
      addCriterion("account.deleted between", value1, value2, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedNotBetween(Byte value1, Byte value2) {
      addCriterion("account.deleted not between", value1, value2, "deleted");
      return (Criteria) this;
    }

    public Criteria andCustomerIdIsNull() {
      addCriterion("account.customer_id is null");
      return (Criteria) this;
    }

    public Criteria andCustomerIdIsNotNull() {
      addCriterion("account.customer_id is not null");
      return (Criteria) this;
    }

    public Criteria andCustomerIdEqualTo(Integer value) {
      addCriterion("account.customer_id =", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdNotEqualTo(Integer value) {
      addCriterion("account.customer_id <>", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdGreaterThan(Integer value) {
      addCriterion("account.customer_id >", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("account.customer_id >=", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdLessThan(Integer value) {
      addCriterion("account.customer_id <", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
      addCriterion("account.customer_id <=", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdIn(List<Integer> values) {
      addCriterion("account.customer_id in", values, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdNotIn(List<Integer> values) {
      addCriterion("account.customer_id not in", values, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
      addCriterion("account.customer_id between", value1, value2, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
      addCriterion("account.customer_id not between", value1, value2, "customerId");
      return (Criteria) this;
    }
  }

  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  public static class Criterion {

    private String condition;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private String typeHandler;

    public String getCondition() {
      return condition;
    }

    public Object getValue() {
      return value;
    }

    public Object getSecondValue() {
      return secondValue;
    }

    public boolean isNoValue() {
      return noValue;
    }

    public boolean isSingleValue() {
      return singleValue;
    }

    public boolean isBetweenValue() {
      return betweenValue;
    }

    public boolean isListValue() {
      return listValue;
    }

    public String getTypeHandler() {
      return typeHandler;
    }

    protected Criterion(String condition) {
      super();
      this.condition = condition;
      this.typeHandler = null;
      this.noValue = true;
    }

    protected Criterion(String condition, Object value, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.typeHandler = typeHandler;
      if (value instanceof List<?>) {
        this.listValue = true;
      } else {
        this.singleValue = true;
      }
    }

    protected Criterion(String condition, Object value) {
      this(condition, value, null);
    }

    protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
      super();
      this.condition = condition;
      this.value = value;
      this.secondValue = secondValue;
      this.typeHandler = typeHandler;
      this.betweenValue = true;
    }

    protected Criterion(String condition, Object value, Object secondValue) {
      this(condition, value, secondValue, null);
    }
  }
}