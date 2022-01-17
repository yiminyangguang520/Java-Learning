package net.codejava.repository;

import net.codejava.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * @author min
 */
public interface UserRepository extends CrudRepository<User, Long> {

  @Query("SELECT u FROM User u WHERE u.username = :username")
  User getUserByUsername(@Param("username") String username);
}
