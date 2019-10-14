package com.devglan.springbootgoogleoauth.dao;

import com.devglan.springbootgoogleoauth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

  User findByEmail(String email);
}
