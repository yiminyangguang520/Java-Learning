package com.example.repository;

import com.example.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
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
