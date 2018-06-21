package com.example.entity;

/**
 * @author litz-a
 */
import java.util.ArrayList;
import java.util.List;

public class UserExample {

  protected String orderByClause;

  protected boolean distinct;

  protected List<Criteria> oredCriteria;

  public UserExample() {
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
      addCriterion("user.id is null");
      return (Criteria) this;
    }

    public Criteria andIdIsNotNull() {
      addCriterion("user.id is not null");
      return (Criteria) this;
    }

    public Criteria andIdEqualTo(Integer value) {
      addCriterion("user.id =", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotEqualTo(Integer value) {
      addCriterion("user.id <>", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThan(Integer value) {
      addCriterion("user.id >", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdGreaterThanOrEqualTo(Integer value) {
      addCriterion("user.id >=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThan(Integer value) {
      addCriterion("user.id <", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdLessThanOrEqualTo(Integer value) {
      addCriterion("user.id <=", value, "id");
      return (Criteria) this;
    }

    public Criteria andIdIn(List<Integer> values) {
      addCriterion("user.id in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotIn(List<Integer> values) {
      addCriterion("user.id not in", values, "id");
      return (Criteria) this;
    }

    public Criteria andIdBetween(Integer value1, Integer value2) {
      addCriterion("user.id between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andIdNotBetween(Integer value1, Integer value2) {
      addCriterion("user.id not between", value1, value2, "id");
      return (Criteria) this;
    }

    public Criteria andUsernameIsNull() {
      addCriterion("user.username is null");
      return (Criteria) this;
    }

    public Criteria andUsernameIsNotNull() {
      addCriterion("user.username is not null");
      return (Criteria) this;
    }

    public Criteria andUsernameEqualTo(String value) {
      addCriterion("user.username =", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameNotEqualTo(String value) {
      addCriterion("user.username <>", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameGreaterThan(String value) {
      addCriterion("user.username >", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameGreaterThanOrEqualTo(String value) {
      addCriterion("user.username >=", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameLessThan(String value) {
      addCriterion("user.username <", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameLessThanOrEqualTo(String value) {
      addCriterion("user.username <=", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameLike(String value) {
      addCriterion("user.username like", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameNotLike(String value) {
      addCriterion("user.username not like", value, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameIn(List<String> values) {
      addCriterion("user.username in", values, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameNotIn(List<String> values) {
      addCriterion("user.username not in", values, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameBetween(String value1, String value2) {
      addCriterion("user.username between", value1, value2, "username");
      return (Criteria) this;
    }

    public Criteria andUsernameNotBetween(String value1, String value2) {
      addCriterion("user.username not between", value1, value2, "username");
      return (Criteria) this;
    }

    public Criteria andPasswordIsNull() {
      addCriterion("user.password is null");
      return (Criteria) this;
    }

    public Criteria andPasswordIsNotNull() {
      addCriterion("user.password is not null");
      return (Criteria) this;
    }

    public Criteria andPasswordEqualTo(String value) {
      addCriterion("user.password =", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotEqualTo(String value) {
      addCriterion("user.password <>", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordGreaterThan(String value) {
      addCriterion("user.password >", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordGreaterThanOrEqualTo(String value) {
      addCriterion("user.password >=", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordLessThan(String value) {
      addCriterion("user.password <", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordLessThanOrEqualTo(String value) {
      addCriterion("user.password <=", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordLike(String value) {
      addCriterion("user.password like", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotLike(String value) {
      addCriterion("user.password not like", value, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordIn(List<String> values) {
      addCriterion("user.password in", values, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotIn(List<String> values) {
      addCriterion("user.password not in", values, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordBetween(String value1, String value2) {
      addCriterion("user.password between", value1, value2, "password");
      return (Criteria) this;
    }

    public Criteria andPasswordNotBetween(String value1, String value2) {
      addCriterion("user.password not between", value1, value2, "password");
      return (Criteria) this;
    }

    public Criteria andUserSexIsNull() {
      addCriterion("user.user_sex is null");
      return (Criteria) this;
    }

    public Criteria andUserSexIsNotNull() {
      addCriterion("user.user_sex is not null");
      return (Criteria) this;
    }

    public Criteria andUserSexEqualTo(String value) {
      addCriterion("user.user_sex =", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexNotEqualTo(String value) {
      addCriterion("user.user_sex <>", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexGreaterThan(String value) {
      addCriterion("user.user_sex >", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexGreaterThanOrEqualTo(String value) {
      addCriterion("user.user_sex >=", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexLessThan(String value) {
      addCriterion("user.user_sex <", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexLessThanOrEqualTo(String value) {
      addCriterion("user.user_sex <=", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexLike(String value) {
      addCriterion("user.user_sex like", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexNotLike(String value) {
      addCriterion("user.user_sex not like", value, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexIn(List<String> values) {
      addCriterion("user.user_sex in", values, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexNotIn(List<String> values) {
      addCriterion("user.user_sex not in", values, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexBetween(String value1, String value2) {
      addCriterion("user.user_sex between", value1, value2, "userSex");
      return (Criteria) this;
    }

    public Criteria andUserSexNotBetween(String value1, String value2) {
      addCriterion("user.user_sex not between", value1, value2, "userSex");
      return (Criteria) this;
    }

    public Criteria andNickNameIsNull() {
      addCriterion("user.nick_name is null");
      return (Criteria) this;
    }

    public Criteria andNickNameIsNotNull() {
      addCriterion("user.nick_name is not null");
      return (Criteria) this;
    }

    public Criteria andNickNameEqualTo(String value) {
      addCriterion("user.nick_name =", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameNotEqualTo(String value) {
      addCriterion("user.nick_name <>", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameGreaterThan(String value) {
      addCriterion("user.nick_name >", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameGreaterThanOrEqualTo(String value) {
      addCriterion("user.nick_name >=", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameLessThan(String value) {
      addCriterion("user.nick_name <", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameLessThanOrEqualTo(String value) {
      addCriterion("user.nick_name <=", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameLike(String value) {
      addCriterion("user.nick_name like", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameNotLike(String value) {
      addCriterion("user.nick_name not like", value, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameIn(List<String> values) {
      addCriterion("user.nick_name in", values, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameNotIn(List<String> values) {
      addCriterion("user.nick_name not in", values, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameBetween(String value1, String value2) {
      addCriterion("user.nick_name between", value1, value2, "nickName");
      return (Criteria) this;
    }

    public Criteria andNickNameNotBetween(String value1, String value2) {
      addCriterion("user.nick_name not between", value1, value2, "nickName");
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