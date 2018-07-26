package com.memorynotfound.spring.security.repository;

import com.memorynotfound.spring.security.model.PasswordResetToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {

  /**
   * findByToken
   * @param token
   * @return
   */
  PasswordResetToken findByToken(String token);

}
