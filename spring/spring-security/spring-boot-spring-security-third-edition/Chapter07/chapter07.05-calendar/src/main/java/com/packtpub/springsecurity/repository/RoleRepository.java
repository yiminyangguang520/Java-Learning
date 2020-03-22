package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author bruce
 */
public interface RoleRepository extends JpaRepository<Role, Integer> {

} // The End...
