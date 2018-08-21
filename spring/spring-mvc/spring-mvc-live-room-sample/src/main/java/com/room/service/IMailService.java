package com.room.service;

/**
 * @author litz-a
 * Created by Doublestar on 2017/12/1 8:53).
 */
public interface IMailService {

  /**
   * send mail
   * @param adress
   * @param content
   * @return
   */
  boolean sendMail(String adress, String content);
}
