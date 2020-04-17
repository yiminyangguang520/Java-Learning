package com.lee.security.tools;

/**
 * @author min
 */
public interface UrlMatcher {

  /**
   * compile
   * @param paramString
   * @return
   */
  Object compile(String paramString);

  /**
   * pathMatchesUrl
   * @param paramObject
   * @param paramString
   * @return
   */
  boolean pathMatchesUrl(Object paramObject, String paramString);

  /**
   * getUniversalMatchPattern
   * @return
   */
  String getUniversalMatchPattern();

  /**
   * requiresLowerCaseUrl
   * @return
   */
  boolean requiresLowerCaseUrl();
}
