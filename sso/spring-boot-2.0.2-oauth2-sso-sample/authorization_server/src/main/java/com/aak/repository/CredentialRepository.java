package com.aak.repository;

import com.aak.domain.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author ahmed
 * @date 21.5.18
 */
public interface CredentialRepository extends JpaRepository<Credentials, Long> {

  /**
   * findByName
   * @param name
   * @return
   */
  Credentials findByName(String name);
}
