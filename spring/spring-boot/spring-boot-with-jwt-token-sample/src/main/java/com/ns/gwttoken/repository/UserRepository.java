package com.ns.gwttoken.repository;

import com.ns.gwttoken.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
