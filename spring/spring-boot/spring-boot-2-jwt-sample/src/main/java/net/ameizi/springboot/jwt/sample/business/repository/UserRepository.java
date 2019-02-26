package net.ameizi.springboot.jwt.sample.business.repository;

import net.ameizi.springboot.jwt.sample.business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

  User findByLoginNameAndPassword(String loginName, String password);

  User findByLoginName(String loginName);
}
