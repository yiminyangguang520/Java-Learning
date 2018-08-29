package com.us.example.dao;

import com.us.example.domain.Permission;
import java.util.List;


/**
 * @author litz-a
 */
public interface PermissionDao {

  /**
   * findAll
   * @return
   */
  List<Permission> findAll();

  /**
   * findByAdminUserId
   * @param userId
   * @return
   */
  List<Permission> findByAdminUserId(int userId);
}
