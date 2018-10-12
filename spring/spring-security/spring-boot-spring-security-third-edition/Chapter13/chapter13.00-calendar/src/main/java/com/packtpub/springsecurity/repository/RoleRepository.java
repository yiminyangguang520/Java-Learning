package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author litz-a
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
