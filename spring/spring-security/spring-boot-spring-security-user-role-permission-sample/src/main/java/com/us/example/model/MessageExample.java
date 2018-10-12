package com.us.example.model;

import java.util.ArrayList;
import java.util.List;

public class MessageExample {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  protected String orderByClause;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  protected boolean distinct;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  protected List<Criteria> oredCriteria;

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public MessageExample() {
    oredCriteria = new ArrayList<Criteria>();
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setOrderByClause(String orderByClause) {
    this.orderByClause = orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public String getOrderByClause() {
    return orderByClause;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setDistinct(boolean distinct) {
    this.distinct = distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public boolean isDistinct() {
    return distinct;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public List<Criteria> getOredCriteria() {
    return oredCriteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void or(Criteria criteria) {
    oredCriteria.add(criteria);
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public Criteria or() {
    Criteria criteria = createCriteriaInternal();
    oredCriteria.add(criteria);
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public Criteria createCriteria() {
    Criteria criteria = createCriteriaInternal();
    if (oredCriteria.size() == 0) {
      oredCriteria.add(criteria);
    }
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  protected Criteria createCriteriaInternal() {
    Criteria criteria = new Criteria();
    return criteria;
  }

  /**
   * This method was generated by MyBatis Generator. This method corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void clear() {
    oredCriteria.clear();
    orderByClause = null;
    distinct = false;
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
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

    public Criteria andTitileIsNull() {
      addCriterion("msg.titile is null");
      return (Criteria) this;
    }

    public Criteria andTitileIsNotNull() {
      addCriterion("msg.titile is not null");
      return (Criteria) this;
    }

    public Criteria andTitileEqualTo(String value) {
      addCriterion("msg.titile =", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileNotEqualTo(String value) {
      addCriterion("msg.titile <>", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileGreaterThan(String value) {
      addCriterion("msg.titile >", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileGreaterThanOrEqualTo(String value) {
      addCriterion("msg.titile >=", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileLessThan(String value) {
      addCriterion("msg.titile <", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileLessThanOrEqualTo(String value) {
      addCriterion("msg.titile <=", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileLike(String value) {
      addCriterion("msg.titile like", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileNotLike(String value) {
      addCriterion("msg.titile not like", value, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileIn(List<String> values) {
      addCriterion("msg.titile in", values, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileNotIn(List<String> values) {
      addCriterion("msg.titile not in", values, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileBetween(String value1, String value2) {
      addCriterion("msg.titile between", value1, value2, "titile");
      return (Criteria) this;
    }

    public Criteria andTitileNotBetween(String value1, String value2) {
      addCriterion("msg.titile not between", value1, value2, "titile");
      return (Criteria) this;
    }

    public Criteria andContentIsNull() {
      addCriterion("msg.content is null");
      return (Criteria) this;
    }

    public Criteria andContentIsNotNull() {
      addCriterion("msg.content is not null");
      return (Criteria) this;
    }

    public Criteria andContentEqualTo(String value) {
      addCriterion("msg.content =", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentNotEqualTo(String value) {
      addCriterion("msg.content <>", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentGreaterThan(String value) {
      addCriterion("msg.content >", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentGreaterThanOrEqualTo(String value) {
      addCriterion("msg.content >=", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentLessThan(String value) {
      addCriterion("msg.content <", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentLessThanOrEqualTo(String value) {
      addCriterion("msg.content <=", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentLike(String value) {
      addCriterion("msg.content like", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentNotLike(String value) {
      addCriterion("msg.content not like", value, "content");
      return (Criteria) this;
    }

    public Criteria andContentIn(List<String> values) {
      addCriterion("msg.content in", values, "content");
      return (Criteria) this;
    }

    public Criteria andContentNotIn(List<String> values) {
      addCriterion("msg.content not in", values, "content");
      return (Criteria) this;
    }

    public Criteria andContentBetween(String value1, String value2) {
      addCriterion("msg.content between", value1, value2, "content");
      return (Criteria) this;
    }

    public Criteria andContentNotBetween(String value1, String value2) {
      addCriterion("msg.content not between", value1, value2, "content");
      return (Criteria) this;
    }

    public Criteria andEtraInfoIsNull() {
      addCriterion("msg.etra_info is null");
      return (Criteria) this;
    }

    public Criteria andEtraInfoIsNotNull() {
      addCriterion("msg.etra_info is not null");
      return (Criteria) this;
    }

    public Criteria andEtraInfoEqualTo(String value) {
      addCriterion("msg.etra_info =", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoNotEqualTo(String value) {
      addCriterion("msg.etra_info <>", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoGreaterThan(String value) {
      addCriterion("msg.etra_info >", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoGreaterThanOrEqualTo(String value) {
      addCriterion("msg.etra_info >=", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoLessThan(String value) {
      addCriterion("msg.etra_info <", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoLessThanOrEqualTo(String value) {
      addCriterion("msg.etra_info <=", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoLike(String value) {
      addCriterion("msg.etra_info like", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoNotLike(String value) {
      addCriterion("msg.etra_info not like", value, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoIn(List<String> values) {
      addCriterion("msg.etra_info in", values, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoNotIn(List<String> values) {
      addCriterion("msg.etra_info not in", values, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoBetween(String value1, String value2) {
      addCriterion("msg.etra_info between", value1, value2, "etraInfo");
      return (Criteria) this;
    }

    public Criteria andEtraInfoNotBetween(String value1, String value2) {
      addCriterion("msg.etra_info not between", value1, value2, "etraInfo");
      return (Criteria) this;
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table msg
   *
   * @mbg.generated do_not_delete_during_merge Fri Aug 24 08:56:53 CST 2018
   */
  public static class Criteria extends GeneratedCriteria {

    protected Criteria() {
      super();
    }
  }

  /**
   * This class was generated by MyBatis Generator. This class corresponds to the database table msg
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
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