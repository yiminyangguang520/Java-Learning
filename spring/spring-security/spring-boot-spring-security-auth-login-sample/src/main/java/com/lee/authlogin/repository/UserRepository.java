package com.lee.authlogin.repository;

import com.lee.authlogin.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

  /**
   * findByUserName
   * @param userName
   * @return
   */
  User findByUserName(String userName);

  /**
   * queryUserOwnedRoleCodes
   * @param userName
   * @return
   */
  @Query(value = "select r.roleCode from User u inner join u.roles as r where u.userName = :userName")
  List<String> queryUserOwnedRoleCodes(@Param(value = "userName") String userName);
}
