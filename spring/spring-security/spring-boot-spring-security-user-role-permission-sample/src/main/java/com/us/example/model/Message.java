package com.us.example.model;

import lombok.AllArgsConstructor;

/**
 * @author litz-a
 */
@AllArgsConstructor
public class Message {

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column msg.titile
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  private String title;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column msg.content
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  private String content;

  /**
   * This field was generated by MyBatis Generator. This field corresponds to the database column msg.etra_info
   *
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  private String etraInfo;

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column msg.titile
   *
   * @return the value of msg.titile
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public String getTitle() {
    return title;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column msg.titile
   *
   * @param title the value for msg.titile
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setTitle(String title) {
    this.title = title == null ? null : title.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column msg.content
   *
   * @return the value of msg.content
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public String getContent() {
    return content;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column msg.content
   *
   * @param content the value for msg.content
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setContent(String content) {
    this.content = content == null ? null : content.trim();
  }

  /**
   * This method was generated by MyBatis Generator. This method returns the value of the database column msg.etra_info
   *
   * @return the value of msg.etra_info
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public String getEtraInfo() {
    return etraInfo;
  }

  /**
   * This method was generated by MyBatis Generator. This method sets the value of the database column msg.etra_info
   *
   * @param etraInfo the value for msg.etra_info
   * @mbg.generated Fri Aug 24 08:56:53 CST 2018
   */
  public void setEtraInfo(String etraInfo) {
    this.etraInfo = etraInfo == null ? null : etraInfo.trim();
  }
}