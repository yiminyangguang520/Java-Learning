package com.lee.mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class AccountBillExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public AccountBillExample() {
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

    protected void addCriterionForJDBCDate(String condition, Date value, String property) {
      if (value == null) {
        throw new RuntimeException("Value for " + property + " cannot be null");
      }
      addCriterion(condition, new java.sql.Date(value.getTime()), property);
    }

    protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
      if (values == null || values.size() == 0) {
        throw new RuntimeException("Value list for " + property + " cannot be null or empty");
      }
      List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
      Iterator<Date> iter = values.iterator();
      while (iter.hasNext()) {
        dateList.add(new java.sql.Date(iter.next().getTime()));
      }
      addCriterion(condition, dateList, property);
    }

    protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
      if (value1 == null || value2 == null) {
        throw new RuntimeException("Between values for " + property + " cannot be null");
      }
      addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
    }

    public Criteria andIdIsNull() {
      addCriterion("account_bill.id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("account_bill.id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("account_bill.id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("account_bill.id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("account_bill.id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("account_bill.id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("account_bill.id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("account_bill.id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("account_bill.id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("account_bill.id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("account_bill.id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("account_bill.id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateIsNull() {
      addCriterion("account_bill.statistics_date is null");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateIsNotNull() {
      addCriterion("account_bill.statistics_date is not null");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateEqualTo(Date value) {
      addCriterionForJDBCDate("account_bill.statistics_date =", value, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateNotEqualTo(Date value) {
      addCriterionForJDBCDate("account_bill.statistics_date <>", value, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateGreaterThan(Date value) {
      addCriterionForJDBCDate("account_bill.statistics_date >", value, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateGreaterThanOrEqualTo(Date value) {
      addCriterionForJDBCDate("account_bill.statistics_date >=", value, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateLessThan(Date value) {
      addCriterionForJDBCDate("account_bill.statistics_date <", value, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateLessThanOrEqualTo(Date value) {
      addCriterionForJDBCDate("account_bill.statistics_date <=", value, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateIn(List<Date> values) {
      addCriterionForJDBCDate("account_bill.statistics_date in", values, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateNotIn(List<Date> values) {
      addCriterionForJDBCDate("account_bill.statistics_date not in", values, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateBetween(Date value1, Date value2) {
      addCriterionForJDBCDate("account_bill.statistics_date between", value1, value2, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andStatisticsDateNotBetween(Date value1, Date value2) {
      addCriterionForJDBCDate("account_bill.statistics_date not between", value1, value2, "statisticsDate");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyIsNull() {
      addCriterion("account_bill.borrow_money is null");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyIsNotNull() {
      addCriterion("account_bill.borrow_money is not null");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyEqualTo(BigDecimal value) {
      addCriterion("account_bill.borrow_money =", value, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyNotEqualTo(BigDecimal value) {
      addCriterion("account_bill.borrow_money <>", value, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyGreaterThan(BigDecimal value) {
      addCriterion("account_bill.borrow_money >", value, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyGreaterThanOrEqualTo(BigDecimal value) {
      addCriterion("account_bill.borrow_money >=", value, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyLessThan(BigDecimal value) {
      addCriterion("account_bill.borrow_money <", value, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyLessThanOrEqualTo(BigDecimal value) {
      addCriterion("account_bill.borrow_money <=", value, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyIn(List<BigDecimal> values) {
      addCriterion("account_bill.borrow_money in", values, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyNotIn(List<BigDecimal> values) {
      addCriterion("account_bill.borrow_money not in", values, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyBetween(BigDecimal value1, BigDecimal value2) {
      addCriterion("account_bill.borrow_money between", value1, value2, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andBorrowMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
      addCriterion("account_bill.borrow_money not between", value1, value2, "borrowMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyIsNull() {
      addCriterion("account_bill.loan_money is null");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyIsNotNull() {
      addCriterion("account_bill.loan_money is not null");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyEqualTo(BigDecimal value) {
      addCriterion("account_bill.loan_money =", value, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyNotEqualTo(BigDecimal value) {
      addCriterion("account_bill.loan_money <>", value, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyGreaterThan(BigDecimal value) {
      addCriterion("account_bill.loan_money >", value, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyGreaterThanOrEqualTo(BigDecimal value) {
      addCriterion("account_bill.loan_money >=", value, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyLessThan(BigDecimal value) {
      addCriterion("account_bill.loan_money <", value, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyLessThanOrEqualTo(BigDecimal value) {
      addCriterion("account_bill.loan_money <=", value, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyIn(List<BigDecimal> values) {
      addCriterion("account_bill.loan_money in", values, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyNotIn(List<BigDecimal> values) {
      addCriterion("account_bill.loan_money not in", values, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyBetween(BigDecimal value1, BigDecimal value2) {
      addCriterion("account_bill.loan_money between", value1, value2, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andLoanMoneyNotBetween(BigDecimal value1, BigDecimal value2) {
      addCriterion("account_bill.loan_money not between", value1, value2, "loanMoney");
      return (Criteria) this;
    }

    public Criteria andCreateDateIsNull() {
      addCriterion("account_bill.create_date is null");
      return (Criteria) this;
    }

    public Criteria andCreateDateIsNotNull() {
      addCriterion("account_bill.create_date is not null");
      return (Criteria) this;
    }

    public Criteria andCreateDateEqualTo(Date value) {
      addCriterion("account_bill.create_date =", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotEqualTo(Date value) {
      addCriterion("account_bill.create_date <>", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateGreaterThan(Date value) {
      addCriterion("account_bill.create_date >", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateGreaterThanOrEqualTo(Date value) {
      addCriterion("account_bill.create_date >=", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateLessThan(Date value) {
      addCriterion("account_bill.create_date <", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateLessThanOrEqualTo(Date value) {
      addCriterion("account_bill.create_date <=", value, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateIn(List<Date> values) {
      addCriterion("account_bill.create_date in", values, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotIn(List<Date> values) {
      addCriterion("account_bill.create_date not in", values, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateBetween(Date value1, Date value2) {
      addCriterion("account_bill.create_date between", value1, value2, "createDate");
      return (Criteria) this;
    }

    public Criteria andCreateDateNotBetween(Date value1, Date value2) {
      addCriterion("account_bill.create_date not between", value1, value2, "createDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateIsNull() {
      addCriterion("account_bill.update_date is null");
      return (Criteria) this;
    }

    public Criteria andUpdateDateIsNotNull() {
      addCriterion("account_bill.update_date is not null");
      return (Criteria) this;
    }

    public Criteria andUpdateDateEqualTo(Date value) {
      addCriterion("account_bill.update_date =", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateNotEqualTo(Date value) {
      addCriterion("account_bill.update_date <>", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateGreaterThan(Date value) {
      addCriterion("account_bill.update_date >", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateGreaterThanOrEqualTo(Date value) {
      addCriterion("account_bill.update_date >=", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateLessThan(Date value) {
      addCriterion("account_bill.update_date <", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateLessThanOrEqualTo(Date value) {
      addCriterion("account_bill.update_date <=", value, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateIn(List<Date> values) {
      addCriterion("account_bill.update_date in", values, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateNotIn(List<Date> values) {
      addCriterion("account_bill.update_date not in", values, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateBetween(Date value1, Date value2) {
      addCriterion("account_bill.update_date between", value1, value2, "updateDate");
      return (Criteria) this;
    }

    public Criteria andUpdateDateNotBetween(Date value1, Date value2) {
      addCriterion("account_bill.update_date not between", value1, value2, "updateDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateIsNull() {
      addCriterion("account_bill.deleted_date is null");
      return (Criteria) this;
    }

    public Criteria andDeletedDateIsNotNull() {
      addCriterion("account_bill.deleted_date is not null");
      return (Criteria) this;
    }

    public Criteria andDeletedDateEqualTo(Date value) {
      addCriterion("account_bill.deleted_date =", value, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateNotEqualTo(Date value) {
      addCriterion("account_bill.deleted_date <>", value, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateGreaterThan(Date value) {
      addCriterion("account_bill.deleted_date >", value, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateGreaterThanOrEqualTo(Date value) {
      addCriterion("account_bill.deleted_date >=", value, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateLessThan(Date value) {
      addCriterion("account_bill.deleted_date <", value, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateLessThanOrEqualTo(Date value) {
      addCriterion("account_bill.deleted_date <=", value, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateIn(List<Date> values) {
      addCriterion("account_bill.deleted_date in", values, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateNotIn(List<Date> values) {
      addCriterion("account_bill.deleted_date not in", values, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateBetween(Date value1, Date value2) {
      addCriterion("account_bill.deleted_date between", value1, value2, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedDateNotBetween(Date value1, Date value2) {
      addCriterion("account_bill.deleted_date not between", value1, value2, "deletedDate");
      return (Criteria) this;
    }

    public Criteria andDeletedIsNull() {
      addCriterion("account_bill.deleted is null");
      return (Criteria) this;
    }

    public Criteria andDeletedIsNotNull() {
      addCriterion("account_bill.deleted is not null");
      return (Criteria) this;
    }

    public Criteria andDeletedEqualTo(Byte value) {
      addCriterion("account_bill.deleted =", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedNotEqualTo(Byte value) {
      addCriterion("account_bill.deleted <>", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedGreaterThan(Byte value) {
      addCriterion("account_bill.deleted >", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedGreaterThanOrEqualTo(Byte value) {
      addCriterion("account_bill.deleted >=", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedLessThan(Byte value) {
      addCriterion("account_bill.deleted <", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedLessThanOrEqualTo(Byte value) {
      addCriterion("account_bill.deleted <=", value, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedIn(List<Byte> values) {
      addCriterion("account_bill.deleted in", values, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedNotIn(List<Byte> values) {
      addCriterion("account_bill.deleted not in", values, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedBetween(Byte value1, Byte value2) {
      addCriterion("account_bill.deleted between", value1, value2, "deleted");
      return (Criteria) this;
    }

    public Criteria andDeletedNotBetween(Byte value1, Byte value2) {
      addCriterion("account_bill.deleted not between", value1, value2, "deleted");
      return (Criteria) this;
    }

    public Criteria andAccountIdIsNull() {
      addCriterion("account_bill.account_id is null");
      return (Criteria) this;
    }

    public Criteria andAccountIdIsNotNull() {
      addCriterion("account_bill.account_id is not null");
      return (Criteria) this;
    }

    public Criteria andAccountIdEqualTo(Integer value) {
      addCriterion("account_bill.account_id =", value, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdNotEqualTo(Integer value) {
      addCriterion("account_bill.account_id <>", value, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdGreaterThan(Integer value) {
      addCriterion("account_bill.account_id >", value, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("account_bill.account_id >=", value, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdLessThan(Integer value) {
      addCriterion("account_bill.account_id <", value, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdLessThanOrEqualTo(Integer value) {
      addCriterion("account_bill.account_id <=", value, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdIn(List<Integer> values) {
      addCriterion("account_bill.account_id in", values, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdNotIn(List<Integer> values) {
      addCriterion("account_bill.account_id not in", values, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdBetween(Integer value1, Integer value2) {
      addCriterion("account_bill.account_id between", value1, value2, "accountId");
      return (Criteria) this;
    }

    public Criteria andAccountIdNotBetween(Integer value1, Integer value2) {
      addCriterion("account_bill.account_id not between", value1, value2, "accountId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdIsNull() {
      addCriterion("account_bill.customer_id is null");
      return (Criteria) this;
    }

    public Criteria andCustomerIdIsNotNull() {
      addCriterion("account_bill.customer_id is not null");
      return (Criteria) this;
    }

    public Criteria andCustomerIdEqualTo(Integer value) {
      addCriterion("account_bill.customer_id =", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdNotEqualTo(Integer value) {
      addCriterion("account_bill.customer_id <>", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdGreaterThan(Integer value) {
      addCriterion("account_bill.customer_id >", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("account_bill.customer_id >=", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdLessThan(Integer value) {
      addCriterion("account_bill.customer_id <", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdLessThanOrEqualTo(Integer value) {
      addCriterion("account_bill.customer_id <=", value, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdIn(List<Integer> values) {
      addCriterion("account_bill.customer_id in", values, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdNotIn(List<Integer> values) {
      addCriterion("account_bill.customer_id not in", values, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdBetween(Integer value1, Integer value2) {
      addCriterion("account_bill.customer_id between", value1, value2, "customerId");
      return (Criteria) this;
    }

    public Criteria andCustomerIdNotBetween(Integer value1, Integer value2) {
      addCriterion("account_bill.customer_id not between", value1, value2, "customerId");
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