package com.ns.gwttoken.repository;

import com.ns.gwttoken.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
