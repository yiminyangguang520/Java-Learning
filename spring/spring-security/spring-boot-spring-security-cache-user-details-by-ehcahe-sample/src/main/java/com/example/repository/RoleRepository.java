package com.example.repository;

import com.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

  /**
   * findByRole
   * @param role
   * @return
   */
  Role findByRole(String role);

}
