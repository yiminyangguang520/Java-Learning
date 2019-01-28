package com.lee.example.model;

import java.math.BigDecimal;
import java.util.Date;

public class Coupon {

  private Long id;

  private Byte scene;

  private Long vendorId;

  private String couponName;

  private Byte couponType;

  private Long couponNum;

  private BigDecimal couponAmount;

  private BigDecimal trigerAmount;

  private Date releaseStartTime;

  private Date releaseEndTime;

  private Byte availableType;

  private Byte availableDay;

  private Date availableStartTime;

  private Date availableEndTime;

  private Byte productRange;

  private Byte productRangeWithout;

  private Byte shopRange;

  private Long limitNum;

  private String status;

  private String couponExplain;

  private String createDepartment;

  private Integer sysVersion;

  private Date createTime;

  private String createUser;

  private Date updateTime;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column tb_coupon.update_user
   *
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  private String updateUser;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column tb_coupon.yn
   *
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  private Integer yn;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column tb_coupon.discount_rate
   *
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  private Integer discountRate;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column tb_coupon.is_reminder
   *
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  private Byte isReminder;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.id
   *
   * @return the value of tb_coupon.id
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Long getId() {
    return id;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.id
   *
   * @param id the value for tb_coupon.id
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.scene
   *
   * @return the value of tb_coupon.scene
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getScene() {
    return scene;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.scene
   *
   * @param scene the value for tb_coupon.scene
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setScene(Byte scene) {
    this.scene = scene;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.vendor_id
   *
   * @return the value of tb_coupon.vendor_id
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Long getVendorId() {
    return vendorId;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.vendor_id
   *
   * @param vendorId the value for tb_coupon.vendor_id
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setVendorId(Long vendorId) {
    this.vendorId = vendorId;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.coupon_name
   *
   * @return the value of tb_coupon.coupon_name
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public String getCouponName() {
    return couponName;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.coupon_name
   *
   * @param couponName the value for tb_coupon.coupon_name
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCouponName(String couponName) {
    this.couponName = couponName == null ? null : couponName.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.coupon_type
   *
   * @return the value of tb_coupon.coupon_type
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getCouponType() {
    return couponType;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.coupon_type
   *
   * @param couponType the value for tb_coupon.coupon_type
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCouponType(Byte couponType) {
    this.couponType = couponType;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.coupon_num
   *
   * @return the value of tb_coupon.coupon_num
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Long getCouponNum() {
    return couponNum;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.coupon_num
   *
   * @param couponNum the value for tb_coupon.coupon_num
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCouponNum(Long couponNum) {
    this.couponNum = couponNum;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.coupon_amount
   *
   * @return the value of tb_coupon.coupon_amount
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public BigDecimal getCouponAmount() {
    return couponAmount;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.coupon_amount
   *
   * @param couponAmount the value for tb_coupon.coupon_amount
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCouponAmount(BigDecimal couponAmount) {
    this.couponAmount = couponAmount;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.triger_amount
   *
   * @return the value of tb_coupon.triger_amount
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public BigDecimal getTrigerAmount() {
    return trigerAmount;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.triger_amount
   *
   * @param trigerAmount the value for tb_coupon.triger_amount
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setTrigerAmount(BigDecimal trigerAmount) {
    this.trigerAmount = trigerAmount;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.release_start_time
   *
   * @return the value of tb_coupon.release_start_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Date getReleaseStartTime() {
    return releaseStartTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.release_start_time
   *
   * @param releaseStartTime the value for tb_coupon.release_start_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setReleaseStartTime(Date releaseStartTime) {
    this.releaseStartTime = releaseStartTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.release_end_time
   *
   * @return the value of tb_coupon.release_end_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Date getReleaseEndTime() {
    return releaseEndTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.release_end_time
   *
   * @param releaseEndTime the value for tb_coupon.release_end_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setReleaseEndTime(Date releaseEndTime) {
    this.releaseEndTime = releaseEndTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.available_type
   *
   * @return the value of tb_coupon.available_type
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getAvailableType() {
    return availableType;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.available_type
   *
   * @param availableType the value for tb_coupon.available_type
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setAvailableType(Byte availableType) {
    this.availableType = availableType;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.available_day
   *
   * @return the value of tb_coupon.available_day
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getAvailableDay() {
    return availableDay;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.available_day
   *
   * @param availableDay the value for tb_coupon.available_day
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setAvailableDay(Byte availableDay) {
    this.availableDay = availableDay;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.available_start_time
   *
   * @return the value of tb_coupon.available_start_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Date getAvailableStartTime() {
    return availableStartTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.available_start_time
   *
   * @param availableStartTime the value for tb_coupon.available_start_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setAvailableStartTime(Date availableStartTime) {
    this.availableStartTime = availableStartTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.available_end_time
   *
   * @return the value of tb_coupon.available_end_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Date getAvailableEndTime() {
    return availableEndTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.available_end_time
   *
   * @param availableEndTime the value for tb_coupon.available_end_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setAvailableEndTime(Date availableEndTime) {
    this.availableEndTime = availableEndTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.product_range
   *
   * @return the value of tb_coupon.product_range
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getProductRange() {
    return productRange;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.product_range
   *
   * @param productRange the value for tb_coupon.product_range
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setProductRange(Byte productRange) {
    this.productRange = productRange;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.product_range_without
   *
   * @return the value of tb_coupon.product_range_without
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getProductRangeWithout() {
    return productRangeWithout;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.product_range_without
   *
   * @param productRangeWithout the value for tb_coupon.product_range_without
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setProductRangeWithout(Byte productRangeWithout) {
    this.productRangeWithout = productRangeWithout;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.shop_range
   *
   * @return the value of tb_coupon.shop_range
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getShopRange() {
    return shopRange;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.shop_range
   *
   * @param shopRange the value for tb_coupon.shop_range
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setShopRange(Byte shopRange) {
    this.shopRange = shopRange;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.limit_num
   *
   * @return the value of tb_coupon.limit_num
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Long getLimitNum() {
    return limitNum;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.limit_num
   *
   * @param limitNum the value for tb_coupon.limit_num
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setLimitNum(Long limitNum) {
    this.limitNum = limitNum;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.status
   *
   * @return the value of tb_coupon.status
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public String getStatus() {
    return status;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.status
   *
   * @param status the value for tb_coupon.status
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setStatus(String status) {
    this.status = status == null ? null : status.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.coupon_explain
   *
   * @return the value of tb_coupon.coupon_explain
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public String getCouponExplain() {
    return couponExplain;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.coupon_explain
   *
   * @param couponExplain the value for tb_coupon.coupon_explain
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCouponExplain(String couponExplain) {
    this.couponExplain = couponExplain == null ? null : couponExplain.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.create_department
   *
   * @return the value of tb_coupon.create_department
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public String getCreateDepartment() {
    return createDepartment;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.create_department
   *
   * @param createDepartment the value for tb_coupon.create_department
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCreateDepartment(String createDepartment) {
    this.createDepartment = createDepartment == null ? null : createDepartment.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.sys_version
   *
   * @return the value of tb_coupon.sys_version
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Integer getSysVersion() {
    return sysVersion;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.sys_version
   *
   * @param sysVersion the value for tb_coupon.sys_version
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setSysVersion(Integer sysVersion) {
    this.sysVersion = sysVersion;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.create_time
   *
   * @return the value of tb_coupon.create_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Date getCreateTime() {
    return createTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.create_time
   *
   * @param createTime the value for tb_coupon.create_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCreateTime(Date createTime) {
    this.createTime = createTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.create_user
   *
   * @return the value of tb_coupon.create_user
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public String getCreateUser() {
    return createUser;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.create_user
   *
   * @param createUser the value for tb_coupon.create_user
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setCreateUser(String createUser) {
    this.createUser = createUser == null ? null : createUser.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.update_time
   *
   * @return the value of tb_coupon.update_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Date getUpdateTime() {
    return updateTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.update_time
   *
   * @param updateTime the value for tb_coupon.update_time
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setUpdateTime(Date updateTime) {
    this.updateTime = updateTime;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.update_user
   *
   * @return the value of tb_coupon.update_user
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public String getUpdateUser() {
    return updateUser;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.update_user
   *
   * @param updateUser the value for tb_coupon.update_user
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setUpdateUser(String updateUser) {
    this.updateUser = updateUser == null ? null : updateUser.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.yn
   *
   * @return the value of tb_coupon.yn
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Integer getYn() {
    return yn;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.yn
   *
   * @param yn the value for tb_coupon.yn
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setYn(Integer yn) {
    this.yn = yn;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.discount_rate
   *
   * @return the value of tb_coupon.discount_rate
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Integer getDiscountRate() {
    return discountRate;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.discount_rate
   *
   * @param discountRate the value for tb_coupon.discount_rate
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setDiscountRate(Integer discountRate) {
    this.discountRate = discountRate;
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column tb_coupon.is_reminder
   *
   * @return the value of tb_coupon.is_reminder
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public Byte getIsReminder() {
    return isReminder;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column tb_coupon.is_reminder
   *
   * @param isReminder the value for tb_coupon.is_reminder
   * @mbg.generated Wed Jul 25 18:14:09 CST 2018
   */
  public void setIsReminder(Byte isReminder) {
    this.isReminder = isReminder;
  }
}