package com.mkyong.repository;

/**
 * @author litz-a
 */
public interface DomainRepositoryCustom {

  /**
   * 更新
   * @param domain
   * @param displayAds
   * @return
   */
  long updateDomain(String domain, boolean displayAds);

}
