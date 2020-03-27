package com.wanari.customlogin.example.repository;

import com.wanari.customlogin.example.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {

  @Override
  List<User> findAll();

  Optional<User> findByLogin(String login);
}
