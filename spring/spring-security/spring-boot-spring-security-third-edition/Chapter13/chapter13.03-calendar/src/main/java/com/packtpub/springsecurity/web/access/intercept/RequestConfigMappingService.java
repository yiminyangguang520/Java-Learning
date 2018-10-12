package com.packtpub.springsecurity.web.access.intercept;

import java.util.List;

/**
 * @author litz-a
 */
public interface RequestConfigMappingService {

  /**
   * getRequestConfigMappings
   * @return
   */
  List<RequestConfigMapping> getRequestConfigMappings();

}