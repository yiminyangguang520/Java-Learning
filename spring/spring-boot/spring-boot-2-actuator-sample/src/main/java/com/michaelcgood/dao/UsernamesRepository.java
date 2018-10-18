package com.michaelcgood.dao;

import com.michaelcgood.model.Usernames;
import java.util.Collection;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface UsernamesRepository extends JpaRepository<Usernames, Long> {

  /**
   * 根据账户用户名查找
   * @param username
   * @return
   */
  Collection<Usernames> findByAccountUsername(String username);
}
