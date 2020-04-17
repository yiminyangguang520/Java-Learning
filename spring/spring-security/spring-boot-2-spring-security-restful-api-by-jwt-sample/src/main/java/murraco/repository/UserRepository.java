package murraco.repository;

import murraco.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author min
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  /**
   * existsByUsername
   * @param username
   * @return
   */
  boolean existsByUsername(String username);

  /**
   * findByUsername
   * @param username
   * @return
   */
  User findByUsername(String username);

  /**
   * deleteByUsername
   * @param username
   */
  @Transactional(rollbackFor = Exception.class)
  void deleteByUsername(String username);

}
