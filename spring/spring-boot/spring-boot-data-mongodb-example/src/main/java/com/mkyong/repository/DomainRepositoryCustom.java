package com.mkyong.repository;

/**
 * @author min
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
