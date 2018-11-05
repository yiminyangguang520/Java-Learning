package com.lee.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoleExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public RoleExample() {
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
      addCriterion("role.id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("role.id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("role.id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("role.id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("role.id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("role.id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("role.id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("role.id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("role.id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("role.id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("role.id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("role.id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andNameIsNull() {
      addCriterion("role.NAME is null");
      return (Criteria) this;
    }

    public Criteria andNameIsNotNull() {
      addCriterion("role.NAME is not null");
      return (Criteria) this;
    }

    public Criteria andNameEqualTo(String value) {
      addCriterion("role.NAME =", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotEqualTo(String value) {
      addCriterion("role.NAME <>", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThan(String value) {
      addCriterion("role.NAME >", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameGreaterThanOrEqualTo(String value) {
      addCriterion("role.NAME >=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThan(String value) {
      addCriterion("role.NAME <", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLessThanOrEqualTo(String value) {
      addCriterion("role.NAME <=", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameLike(String value) {
      addCriterion("role.NAME like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotLike(String value) {
      addCriterion("role.NAME not like", value, "name");
      return (Criteria) this;
    }

    public Criteria andNameIn(List<String> values) {
      addCriterion("role.NAME in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotIn(List<String> values) {
      addCriterion("role.NAME not in", values, "name");
      return (Criteria) this;
    }

    public Criteria andNameBetween(String value1, String value2) {
      addCriterion("role.NAME between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andNameNotBetween(String value1, String value2) {
      addCriterion("role.NAME not between", value1, value2, "name");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNull() {
      addCriterion("role.description is null");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNotNull() {
      addCriterion("role.description is not null");
      return (Criteria) this;
    }

    public Criteria andDescriptionEqualTo(String value) {
      addCriterion("role.description =", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotEqualTo(String value) {
      addCriterion("role.description <>", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThan(String value) {
      addCriterion("role.description >", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
      addCriterion("role.description >=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThan(String value) {
      addCriterion("role.description <", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThanOrEqualTo(String value) {
      addCriterion("role.description <=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLike(String value) {
      addCriterion("role.description like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotLike(String value) {
      addCriterion("role.description not like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionIn(List<String> values) {
      addCriterion("role.description in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotIn(List<String> values) {
      addCriterion("role.description not in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionBetween(String value1, String value2) {
      addCriterion("role.description between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotBetween(String value1, String value2) {
      addCriterion("role.description not between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andIsActiveIsNull() {
      addCriterion("role.is_active is null");
      return (Criteria) this;
    }

    public Criteria andIsActiveIsNotNull() {
      addCriterion("role.is_active is not null");
      return (Criteria) this;
    }

    public Criteria andIsActiveEqualTo(Byte value) {
      addCriterion("role.is_active =", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveNotEqualTo(Byte value) {
      addCriterion("role.is_active <>", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveGreaterThan(Byte value) {
      addCriterion("role.is_active >", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveGreaterThanOrEqualTo(Byte value) {
      addCriterion("role.is_active >=", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveLessThan(Byte value) {
      addCriterion("role.is_active <", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveLessThanOrEqualTo(Byte value) {
      addCriterion("role.is_active <=", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveIn(List<Byte> values) {
      addCriterion("role.is_active in", values, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveNotIn(List<Byte> values) {
      addCriterion("role.is_active not in", values, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveBetween(Byte value1, Byte value2) {
      addCriterion("role.is_active between", value1, value2, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveNotBetween(Byte value1, Byte value2) {
      addCriterion("role.is_active not between", value1, value2, "isActive");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIsNull() {
      addCriterion("role.last_update_time is null");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIsNotNull() {
      addCriterion("role.last_update_time is not null");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeEqualTo(Date value) {
      addCriterion("role.last_update_time =", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotEqualTo(Date value) {
      addCriterion("role.last_update_time <>", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeGreaterThan(Date value) {
      addCriterion("role.last_update_time >", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
      addCriterion("role.last_update_time >=", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeLessThan(Date value) {
      addCriterion("role.last_update_time <", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
      addCriterion("role.last_update_time <=", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIn(List<Date> values) {
      addCriterion("role.last_update_time in", values, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotIn(List<Date> values) {
      addCriterion("role.last_update_time not in", values, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
      addCriterion("role.last_update_time between", value1, value2, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
      addCriterion("role.last_update_time not between", value1, value2, "lastUpdateTime");
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