package com.michaelcgood.dao;

import com.michaelcgood.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

  /**
   * 根据用户名查找
   * @param username
   * @return
   */
  Optional<Account> findByUsername(String username);
}
