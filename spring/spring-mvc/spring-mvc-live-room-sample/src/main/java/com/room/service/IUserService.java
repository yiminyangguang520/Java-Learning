package com.room.service;

import com.room.entity.StatusMessage;
import javax.servlet.http.HttpSession;

/**
 * @author min 
 * Created by Doublestar on 2017/11/29 16:26).
 */
public interface IUserService {

  /**
   * isLogin
   *
   * @return boolean
   * @author yuxx
   */
  boolean isLogin(String name);

  /**
   * userLogin
   *
   * @return StatusMessage
   * @author yuxx
   */
  StatusMessage userLogin(HttpSession session, String name, String password, String authcode);

  /**
   * userLogin
   *
   * @return StatusMessage
   * @author yuxx
   */
  StatusMessage userResistor(String name, String password, String repassword);

  /**
   * @return StatusMessage
   * @author yuxx
   */
  StatusMessage userLogoutAjax(HttpSession session, String name);

  /**
   * @return boolean
   * @author yuxx
   */
  boolean userLogout(HttpSession session);

  /**
   * @return StatusMessage
   * @author yuxx
   */
  StatusMessage forgetPassword(String name, String address);
}
