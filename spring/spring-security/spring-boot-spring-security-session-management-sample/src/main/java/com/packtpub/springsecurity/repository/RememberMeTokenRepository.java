package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.PersistentLogin;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RememberMeTokenRepository extends JpaRepository<PersistentLogin, String> {

  PersistentLogin findBySeries(String series);

  List<PersistentLogin> findByUsername(String username);

  Iterable<PersistentLogin> findByLastUsedAfter(Date expiration);
}
