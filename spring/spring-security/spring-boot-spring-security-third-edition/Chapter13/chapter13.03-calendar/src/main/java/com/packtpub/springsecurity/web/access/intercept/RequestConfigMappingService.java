package com.packtpub.springsecurity.web.access.intercept;

import java.util.List;

/**
 * @author min
 */
public interface RequestConfigMappingService {

  /**
   * getRequestConfigMappings
   * @return
   */
  List<RequestConfigMapping> getRequestConfigMappings();

}