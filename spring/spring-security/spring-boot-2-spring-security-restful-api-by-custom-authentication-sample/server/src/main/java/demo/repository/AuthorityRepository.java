package demo.repository;

import demo.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

  /**
   * findByAuthority
   * @param authority
   * @return
   */
  Authority findByAuthority(String authority);
}
