package github.javaguide.springsecurityjwtguide.system.repository;

import github.javaguide.springsecurityjwtguide.system.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shuang.kou
 */
public interface UserRepository extends JpaRepository<User, Integer> {

  Optional<User> findByUsername(String username);

  @Transactional
  void deleteByUsername(String username);

  //    @Query("select status from user where username= :username")
//    Optional<String> findUserStatusByName(@Param("username") String username);
}
