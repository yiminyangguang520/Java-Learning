package demo.repository;

import demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author litz-a
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);
}
