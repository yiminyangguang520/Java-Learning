package com.lee.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuRoleExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public MenuRoleExample() {
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
      addCriterion("role_menu.id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("role_menu.id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("role_menu.id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("role_menu.id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("role_menu.id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("role_menu.id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("role_menu.id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("role_menu.id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("role_menu.id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("role_menu.id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("role_menu.id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("role_menu.id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andRoleIdIsNull() {
      addCriterion("role_menu.role_id is null");
      return (Criteria) this;
    }

    public Criteria andRoleIdIsNotNull() {
      addCriterion("role_menu.role_id is not null");
      return (Criteria) this;
    }

    public Criteria andRoleIdEqualTo(Integer value) {
      addCriterion("role_menu.role_id =", value, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdNotEqualTo(Integer value) {
      addCriterion("role_menu.role_id <>", value, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdGreaterThan(Integer value) {
      addCriterion("role_menu.role_id >", value, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("role_menu.role_id >=", value, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdLessThan(Integer value) {
      addCriterion("role_menu.role_id <", value, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdLessThanOrEqualTo(Integer value) {
      addCriterion("role_menu.role_id <=", value, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdIn(List<Integer> values) {
      addCriterion("role_menu.role_id in", values, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdNotIn(List<Integer> values) {
      addCriterion("role_menu.role_id not in", values, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdBetween(Integer value1, Integer value2) {
      addCriterion("role_menu.role_id between", value1, value2, "roleId");
      return (Criteria) this;
    }

    public Criteria andRoleIdNotBetween(Integer value1, Integer value2) {
      addCriterion("role_menu.role_id not between", value1, value2, "roleId");
      return (Criteria) this;
    }

    public Criteria andMenuIdIsNull() {
      addCriterion("role_menu.menu_id is null");
      return (Criteria) this;
    }

    public Criteria andMenuIdIsNotNull() {
      addCriterion("role_menu.menu_id is not null");
      return (Criteria) this;
    }

    public Criteria andMenuIdEqualTo(Integer value) {
      addCriterion("role_menu.menu_id =", value, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdNotEqualTo(Integer value) {
      addCriterion("role_menu.menu_id <>", value, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdGreaterThan(Integer value) {
      addCriterion("role_menu.menu_id >", value, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("role_menu.menu_id >=", value, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdLessThan(Integer value) {
      addCriterion("role_menu.menu_id <", value, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdLessThanOrEqualTo(Integer value) {
      addCriterion("role_menu.menu_id <=", value, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdIn(List<Integer> values) {
      addCriterion("role_menu.menu_id in", values, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdNotIn(List<Integer> values) {
      addCriterion("role_menu.menu_id not in", values, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdBetween(Integer value1, Integer value2) {
      addCriterion("role_menu.menu_id between", value1, value2, "menuId");
      return (Criteria) this;
    }

    public Criteria andMenuIdNotBetween(Integer value1, Integer value2) {
      addCriterion("role_menu.menu_id not between", value1, value2, "menuId");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIsNull() {
      addCriterion("role_menu.last_update_time is null");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIsNotNull() {
      addCriterion("role_menu.last_update_time is not null");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeEqualTo(Date value) {
      addCriterion("role_menu.last_update_time =", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotEqualTo(Date value) {
      addCriterion("role_menu.last_update_time <>", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeGreaterThan(Date value) {
      addCriterion("role_menu.last_update_time >", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
      addCriterion("role_menu.last_update_time >=", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeLessThan(Date value) {
      addCriterion("role_menu.last_update_time <", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
      addCriterion("role_menu.last_update_time <=", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIn(List<Date> values) {
      addCriterion("role_menu.last_update_time in", values, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotIn(List<Date> values) {
      addCriterion("role_menu.last_update_time not in", values, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
      addCriterion("role_menu.last_update_time between", value1, value2, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
      addCriterion("role_menu.last_update_time not between", value1, value2, "lastUpdateTime");
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