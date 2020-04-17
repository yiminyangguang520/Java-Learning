/**
 *
 */
package com.devglan.service;

import com.devglan.model.UserDetails;
import java.util.List;

/**
 * @author min
 */
public interface UserService {

  /**
   * 获取用户细节
   * @return
   */
  List<UserDetails> getUserDetails();

}
