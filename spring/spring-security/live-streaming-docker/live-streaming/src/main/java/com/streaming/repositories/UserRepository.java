package com.streaming.repositories;

import com.streaming.domains.User;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * @author litz-a
 */
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

  User findByUsername(String username);
}
