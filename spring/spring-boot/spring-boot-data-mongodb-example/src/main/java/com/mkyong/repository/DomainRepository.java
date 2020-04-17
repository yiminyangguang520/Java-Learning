package com.mkyong.repository;

import com.mkyong.domain.Domain;
import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * @author min
 */
public interface DomainRepository extends MongoRepository<Domain, Long>, DomainRepositoryCustom {

  /**
   * 根据domain查找
   * @param domain
   * @return
   */
  Domain findFirstByDomain(String domain);

  /**
   * 根据domain和displayAds查找
   * @param domain
   * @param displayAds
   * @return
   */
  Domain findByDomainAndDisplayAds(String domain, boolean displayAds);

  /**
   * Mongo JSON query string
   * @param domain
   * @return
   */
  @Query("{domain:'?0'}")
  Domain findCustomByDomain(String domain);

  /**
   * 正则查找
   * @param domain
   * @return
   */
  @Query("{domain: { $regex: ?0 } })")
  List<Domain> findCustomByRegExDomain(String domain);

}
