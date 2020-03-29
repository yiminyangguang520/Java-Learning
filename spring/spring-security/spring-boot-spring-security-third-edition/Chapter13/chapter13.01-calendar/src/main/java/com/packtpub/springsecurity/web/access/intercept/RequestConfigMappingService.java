package com.packtpub.springsecurity.web.access.intercept;

import java.util.List;

/**
 * @author bruce
 */
public interface RequestConfigMappingService {

  List<RequestConfigMapping> getRequestConfigMappings();

}
