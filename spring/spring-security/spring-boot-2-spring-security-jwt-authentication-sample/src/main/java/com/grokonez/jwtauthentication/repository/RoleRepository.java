package com.grokonez.jwtauthentication.repository;

import com.grokonez.jwtauthentication.model.Role;
import com.grokonez.jwtauthentication.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

  /**
   * findByName
   * @param roleName
   * @return
   */
  Optional<Role> findByName(RoleName roleName);
}