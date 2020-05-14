package com.walking.techie.common.repository;

import com.walking.techie.model.primary.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}
