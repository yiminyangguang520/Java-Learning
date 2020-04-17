package com.packtpub.springsecurity.repository;

import com.packtpub.springsecurity.domain.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author min
 */
public interface RoleRepository extends MongoRepository<Role, Integer> {

} // The End...
