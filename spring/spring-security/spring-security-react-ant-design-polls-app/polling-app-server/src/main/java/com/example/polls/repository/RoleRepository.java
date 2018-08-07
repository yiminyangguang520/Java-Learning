package com.example.polls.repository;

import com.example.polls.model.Role;
import com.example.polls.model.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author rajeevkumarsingh
 * @date 02/08/17
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
