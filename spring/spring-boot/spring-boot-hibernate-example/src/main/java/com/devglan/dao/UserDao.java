package com.devglan.dao;

import com.devglan.model.UserDetails;
import java.util.List;

/**
 * @author min
 */
public interface UserDao {

  /**
   * 获取用户细节
   * @return
   */
  List<UserDetails> getUserDetails();

}
