package com.pocketdigi.dal.po;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfigPOExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public ConfigPOExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  public String getOrderByClause() {
    return orderByClause;
  }

  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  public boolean isDistinct() {
    return distinct;
  }

  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
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
    criteria.andIsDeletedEqualTo("N");
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
      addCriterion("id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIsDeletedIsNull() {
      addCriterion("is_deleted is null");
      return (Criteria) this;
    }

    public Criteria andIsDeletedIsNotNull() {
      addCriterion("is_deleted is not null");
      return (Criteria) this;
    }

    public Criteria andIsDeletedEqualTo(String value) {
      addCriterion("is_deleted =", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedNotEqualTo(String value) {
      addCriterion("is_deleted <>", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedGreaterThan(String value) {
      addCriterion("is_deleted >", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedGreaterThanOrEqualTo(String value) {
      addCriterion("is_deleted >=", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedLessThan(String value) {
      addCriterion("is_deleted <", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedLessThanOrEqualTo(String value) {
      addCriterion("is_deleted <=", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedLike(String value) {
      addCriterion("is_deleted like", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedNotLike(String value) {
      addCriterion("is_deleted not like", value, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedIn(List<String> values) {
      addCriterion("is_deleted in", values, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedNotIn(List<String> values) {
      addCriterion("is_deleted not in", values, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedBetween(String value1, String value2) {
      addCriterion("is_deleted between", value1, value2, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andIsDeletedNotBetween(String value1, String value2) {
      addCriterion("is_deleted not between", value1, value2, "isDeleted");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIsNull() {
      addCriterion("gmt_create is null");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIsNotNull() {
      addCriterion("gmt_create is not null");
      return (Criteria) this;
    }

    public Criteria andGmtCreateEqualTo(Date value) {
      addCriterion("gmt_create =", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotEqualTo(Date value) {
      addCriterion("gmt_create <>", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateGreaterThan(Date value) {
      addCriterion("gmt_create >", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateGreaterThanOrEqualTo(Date value) {
      addCriterion("gmt_create >=", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateLessThan(Date value) {
      addCriterion("gmt_create <", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateLessThanOrEqualTo(Date value) {
      addCriterion("gmt_create <=", value, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateIn(List<Date> values) {
      addCriterion("gmt_create in", values, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotIn(List<Date> values) {
      addCriterion("gmt_create not in", values, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateBetween(Date value1, Date value2) {
      addCriterion("gmt_create between", value1, value2, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtCreateNotBetween(Date value1, Date value2) {
      addCriterion("gmt_create not between", value1, value2, "gmtCreate");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedIsNull() {
      addCriterion("gmt_modified is null");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedIsNotNull() {
      addCriterion("gmt_modified is not null");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedEqualTo(Date value) {
      addCriterion("gmt_modified =", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedNotEqualTo(Date value) {
      addCriterion("gmt_modified <>", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedGreaterThan(Date value) {
      addCriterion("gmt_modified >", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedGreaterThanOrEqualTo(Date value) {
      addCriterion("gmt_modified >=", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedLessThan(Date value) {
      addCriterion("gmt_modified <", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedLessThanOrEqualTo(Date value) {
      addCriterion("gmt_modified <=", value, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedIn(List<Date> values) {
      addCriterion("gmt_modified in", values, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedNotIn(List<Date> values) {
      addCriterion("gmt_modified not in", values, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedBetween(Date value1, Date value2) {
      addCriterion("gmt_modified between", value1, value2, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andGmtModifiedNotBetween(Date value1, Date value2) {
      addCriterion("gmt_modified not between", value1, value2, "gmtModified");
      return (Criteria) this;
    }

    public Criteria andUserNameIsNull() {
      addCriterion("user_name is null");
      return (Criteria) this;
    }

    public Criteria andUserNameIsNotNull() {
      addCriterion("user_name is not null");
      return (Criteria) this;
    }

    public Criteria andUserNameEqualTo(String value) {
      addCriterion("user_name =", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotEqualTo(String value) {
      addCriterion("user_name <>", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameGreaterThan(String value) {
      addCriterion("user_name >", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameGreaterThanOrEqualTo(String value) {
      addCriterion("user_name >=", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameLessThan(String value) {
      addCriterion("user_name <", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameLessThanOrEqualTo(String value) {
      addCriterion("user_name <=", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameLike(String value) {
      addCriterion("user_name like", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotLike(String value) {
      addCriterion("user_name not like", value, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameIn(List<String> values) {
      addCriterion("user_name in", values, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotIn(List<String> values) {
      addCriterion("user_name not in", values, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameBetween(String value1, String value2) {
      addCriterion("user_name between", value1, value2, "userName");
      return (Criteria) this;
    }

    public Criteria andUserNameNotBetween(String value1, String value2) {
      addCriterion("user_name not between", value1, value2, "userName");
      return (Criteria) this;
    }

    public Criteria andUserAgeIsNull() {
      addCriterion("user_age is null");
      return (Criteria) this;
    }

    public Criteria andUserAgeIsNotNull() {
      addCriterion("user_age is not null");
      return (Criteria) this;
    }

    public Criteria andUserAgeEqualTo(Byte value) {
      addCriterion("user_age =", value, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeNotEqualTo(Byte value) {
      addCriterion("user_age <>", value, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeGreaterThan(Byte value) {
      addCriterion("user_age >", value, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeGreaterThanOrEqualTo(Byte value) {
      addCriterion("user_age >=", value, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeLessThan(Byte value) {
      addCriterion("user_age <", value, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeLessThanOrEqualTo(Byte value) {
      addCriterion("user_age <=", value, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeIn(List<Byte> values) {
      addCriterion("user_age in", values, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeNotIn(List<Byte> values) {
      addCriterion("user_age not in", values, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeBetween(Byte value1, Byte value2) {
      addCriterion("user_age between", value1, value2, "userAge");
      return (Criteria) this;
    }

    public Criteria andUserAgeNotBetween(Byte value1, Byte value2) {
      addCriterion("user_age not between", value1, value2, "userAge");
      return (Criteria) this;
    }

    public Criteria andAvatarIsNull() {
      addCriterion("avatar is null");
      return (Criteria) this;
    }

    public Criteria andAvatarIsNotNull() {
      addCriterion("avatar is not null");
      return (Criteria) this;
    }

    public Criteria andAvatarEqualTo(String value) {
      addCriterion("avatar =", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarNotEqualTo(String value) {
      addCriterion("avatar <>", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarGreaterThan(String value) {
      addCriterion("avatar >", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarGreaterThanOrEqualTo(String value) {
      addCriterion("avatar >=", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarLessThan(String value) {
      addCriterion("avatar <", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarLessThanOrEqualTo(String value) {
      addCriterion("avatar <=", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarLike(String value) {
      addCriterion("avatar like", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarNotLike(String value) {
      addCriterion("avatar not like", value, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarIn(List<String> values) {
      addCriterion("avatar in", values, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarNotIn(List<String> values) {
      addCriterion("avatar not in", values, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarBetween(String value1, String value2) {
      addCriterion("avatar between", value1, value2, "avatar");
      return (Criteria) this;
    }

    public Criteria andAvatarNotBetween(String value1, String value2) {
      addCriterion("avatar not between", value1, value2, "avatar");
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
  }
}