package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.PersistentLogin;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
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

  /**
   * findByLastUsedAfter
   * @param expiration
   * @return
   */
  Iterable<PersistentLogin> findByLastUsedAfter(Date expiration);

}
