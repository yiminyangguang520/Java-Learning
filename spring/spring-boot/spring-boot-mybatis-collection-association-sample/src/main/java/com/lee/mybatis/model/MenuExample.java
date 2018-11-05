package com.lee.mybatis.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public MenuExample() {
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
      addCriterion("menu.id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("menu.id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("menu.id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("menu.id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("menu.id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("menu.id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("menu.id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("menu.id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("menu.id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("menu.id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("menu.id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("menu.id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andValueIsNull() {
      addCriterion("menu.value is null");
      return (Criteria) this;
    }

    public Criteria andValueIsNotNull() {
      addCriterion("menu.value is not null");
      return (Criteria) this;
    }

    public Criteria andValueEqualTo(String value) {
      addCriterion("menu.value =", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotEqualTo(String value) {
      addCriterion("menu.value <>", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueGreaterThan(String value) {
      addCriterion("menu.value >", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueGreaterThanOrEqualTo(String value) {
      addCriterion("menu.value >=", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueLessThan(String value) {
      addCriterion("menu.value <", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueLessThanOrEqualTo(String value) {
      addCriterion("menu.value <=", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueLike(String value) {
      addCriterion("menu.value like", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotLike(String value) {
      addCriterion("menu.value not like", value, "value");
      return (Criteria) this;
    }

    public Criteria andValueIn(List<String> values) {
      addCriterion("menu.value in", values, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotIn(List<String> values) {
      addCriterion("menu.value not in", values, "value");
      return (Criteria) this;
    }

    public Criteria andValueBetween(String value1, String value2) {
      addCriterion("menu.value between", value1, value2, "value");
      return (Criteria) this;
    }

    public Criteria andValueNotBetween(String value1, String value2) {
      addCriterion("menu.value not between", value1, value2, "value");
      return (Criteria) this;
    }

    public Criteria andDisplayValueIsNull() {
      addCriterion("menu.display_value is null");
      return (Criteria) this;
    }

    public Criteria andDisplayValueIsNotNull() {
      addCriterion("menu.display_value is not null");
      return (Criteria) this;
    }

    public Criteria andDisplayValueEqualTo(String value) {
      addCriterion("menu.display_value =", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueNotEqualTo(String value) {
      addCriterion("menu.display_value <>", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueGreaterThan(String value) {
      addCriterion("menu.display_value >", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueGreaterThanOrEqualTo(String value) {
      addCriterion("menu.display_value >=", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueLessThan(String value) {
      addCriterion("menu.display_value <", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueLessThanOrEqualTo(String value) {
      addCriterion("menu.display_value <=", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueLike(String value) {
      addCriterion("menu.display_value like", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueNotLike(String value) {
      addCriterion("menu.display_value not like", value, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueIn(List<String> values) {
      addCriterion("menu.display_value in", values, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueNotIn(List<String> values) {
      addCriterion("menu.display_value not in", values, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueBetween(String value1, String value2) {
      addCriterion("menu.display_value between", value1, value2, "displayValue");
      return (Criteria) this;
    }

    public Criteria andDisplayValueNotBetween(String value1, String value2) {
      addCriterion("menu.display_value not between", value1, value2, "displayValue");
      return (Criteria) this;
    }

    public Criteria andUrlIsNull() {
      addCriterion("menu.url is null");
      return (Criteria) this;
    }

    public Criteria andUrlIsNotNull() {
      addCriterion("menu.url is not null");
      return (Criteria) this;
    }

    public Criteria andUrlEqualTo(String value) {
      addCriterion("menu.url =", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotEqualTo(String value) {
      addCriterion("menu.url <>", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThan(String value) {
      addCriterion("menu.url >", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlGreaterThanOrEqualTo(String value) {
      addCriterion("menu.url >=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThan(String value) {
      addCriterion("menu.url <", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLessThanOrEqualTo(String value) {
      addCriterion("menu.url <=", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlLike(String value) {
      addCriterion("menu.url like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotLike(String value) {
      addCriterion("menu.url not like", value, "url");
      return (Criteria) this;
    }

    public Criteria andUrlIn(List<String> values) {
      addCriterion("menu.url in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotIn(List<String> values) {
      addCriterion("menu.url not in", values, "url");
      return (Criteria) this;
    }

    public Criteria andUrlBetween(String value1, String value2) {
      addCriterion("menu.url between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andUrlNotBetween(String value1, String value2) {
      addCriterion("menu.url not between", value1, value2, "url");
      return (Criteria) this;
    }

    public Criteria andCategoryIsNull() {
      addCriterion("menu.category is null");
      return (Criteria) this;
    }

    public Criteria andCategoryIsNotNull() {
      addCriterion("menu.category is not null");
      return (Criteria) this;
    }

    public Criteria andCategoryEqualTo(Integer value) {
      addCriterion("menu.category =", value, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryNotEqualTo(Integer value) {
      addCriterion("menu.category <>", value, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryGreaterThan(Integer value) {
      addCriterion("menu.category >", value, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryGreaterThanOrEqualTo(Integer value) {
      addCriterion("menu.category >=", value, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryLessThan(Integer value) {
      addCriterion("menu.category <", value, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryLessThanOrEqualTo(Integer value) {
      addCriterion("menu.category <=", value, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryIn(List<Integer> values) {
      addCriterion("menu.category in", values, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryNotIn(List<Integer> values) {
      addCriterion("menu.category not in", values, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryBetween(Integer value1, Integer value2) {
      addCriterion("menu.category between", value1, value2, "category");
      return (Criteria) this;
    }

    public Criteria andCategoryNotBetween(Integer value1, Integer value2) {
      addCriterion("menu.category not between", value1, value2, "category");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNull() {
      addCriterion("menu.description is null");
      return (Criteria) this;
    }

    public Criteria andDescriptionIsNotNull() {
      addCriterion("menu.description is not null");
      return (Criteria) this;
    }

    public Criteria andDescriptionEqualTo(String value) {
      addCriterion("menu.description =", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotEqualTo(String value) {
      addCriterion("menu.description <>", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThan(String value) {
      addCriterion("menu.description >", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionGreaterThanOrEqualTo(String value) {
      addCriterion("menu.description >=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThan(String value) {
      addCriterion("menu.description <", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLessThanOrEqualTo(String value) {
      addCriterion("menu.description <=", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionLike(String value) {
      addCriterion("menu.description like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotLike(String value) {
      addCriterion("menu.description not like", value, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionIn(List<String> values) {
      addCriterion("menu.description in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotIn(List<String> values) {
      addCriterion("menu.description not in", values, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionBetween(String value1, String value2) {
      addCriterion("menu.description between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andDescriptionNotBetween(String value1, String value2) {
      addCriterion("menu.description not between", value1, value2, "description");
      return (Criteria) this;
    }

    public Criteria andIsActiveIsNull() {
      addCriterion("menu.is_active is null");
      return (Criteria) this;
    }

    public Criteria andIsActiveIsNotNull() {
      addCriterion("menu.is_active is not null");
      return (Criteria) this;
    }

    public Criteria andIsActiveEqualTo(Byte value) {
      addCriterion("menu.is_active =", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveNotEqualTo(Byte value) {
      addCriterion("menu.is_active <>", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveGreaterThan(Byte value) {
      addCriterion("menu.is_active >", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveGreaterThanOrEqualTo(Byte value) {
      addCriterion("menu.is_active >=", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveLessThan(Byte value) {
      addCriterion("menu.is_active <", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveLessThanOrEqualTo(Byte value) {
      addCriterion("menu.is_active <=", value, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveIn(List<Byte> values) {
      addCriterion("menu.is_active in", values, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveNotIn(List<Byte> values) {
      addCriterion("menu.is_active not in", values, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveBetween(Byte value1, Byte value2) {
      addCriterion("menu.is_active between", value1, value2, "isActive");
      return (Criteria) this;
    }

    public Criteria andIsActiveNotBetween(Byte value1, Byte value2) {
      addCriterion("menu.is_active not between", value1, value2, "isActive");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIsNull() {
      addCriterion("menu.last_update_time is null");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIsNotNull() {
      addCriterion("menu.last_update_time is not null");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeEqualTo(Date value) {
      addCriterion("menu.last_update_time =", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotEqualTo(Date value) {
      addCriterion("menu.last_update_time <>", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeGreaterThan(Date value) {
      addCriterion("menu.last_update_time >", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeGreaterThanOrEqualTo(Date value) {
      addCriterion("menu.last_update_time >=", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeLessThan(Date value) {
      addCriterion("menu.last_update_time <", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeLessThanOrEqualTo(Date value) {
      addCriterion("menu.last_update_time <=", value, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeIn(List<Date> values) {
      addCriterion("menu.last_update_time in", values, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotIn(List<Date> values) {
      addCriterion("menu.last_update_time not in", values, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeBetween(Date value1, Date value2) {
      addCriterion("menu.last_update_time between", value1, value2, "lastUpdateTime");
      return (Criteria) this;
    }

    public Criteria andLastUpdateTimeNotBetween(Date value1, Date value2) {
      addCriterion("menu.last_update_time not between", value1, value2, "lastUpdateTime");
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