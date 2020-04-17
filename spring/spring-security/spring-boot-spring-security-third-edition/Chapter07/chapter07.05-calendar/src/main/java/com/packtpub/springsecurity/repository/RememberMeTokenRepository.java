package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.PersistentLogin;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author min
 */
public interface RememberMeTokenRepository extends JpaRepository<PersistentLogin, String> {

  /**
   * findBySeries
   * @param series
   * @return
   */
  PersistentLogin findBySeries(String series);

  /**
   * findByUsername
   * @param username
   * @return
   */
  List<PersistentLogin> findByUsername(String username);

} // The End...
