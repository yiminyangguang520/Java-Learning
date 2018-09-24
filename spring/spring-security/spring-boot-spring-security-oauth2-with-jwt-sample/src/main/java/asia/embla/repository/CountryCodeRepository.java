package asia.embla.repository;

import asia.embla.entity.CountryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author tharsan
 * @date 2/22/18
 */
@Repository
public interface CountryCodeRepository extends JpaRepository<CountryCode, Integer> {

}
