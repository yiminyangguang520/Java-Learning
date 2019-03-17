package com.thoughtmechanix.zuulsvr.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.thoughtmechanix.zuulsvr.config.ServiceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author litz-a
 */
@Component
public class TrackingFilter extends ZuulFilter {

  private static final int FILTER_ORDER = 1;
  private static final boolean SHOULD_FILTER = true;

  private static final Logger logger = LoggerFactory.getLogger(TrackingFilter.class);

  @Autowired
  private FilterUtils filterUtils;

  @Autowired
  private ServiceConfig serviceConfig;

  @Override
  public String filterType() {
    return FilterUtils.PRE_FILTER_TYPE;
  }

  @Override
  public int filterOrder() {
    return FILTER_ORDER;
  }

  @Override
  public boolean shouldFilter() {
    return SHOULD_FILTER;
  }

  private boolean isCorrelationIdPresent() {
    if (filterUtils.getCorrelationId() != null) {
      return true;
    }

    return false;
  }

  private String generateCorrelationId() {
    return java.util.UUID.randomUUID().toString();
  }

  @Override
  public Object run() {

    RequestContext ctx = RequestContext.getCurrentContext();

    if (isCorrelationIdPresent()) {
      logger.debug("tmx-correlation-id found in tracking filter: {}. ", filterUtils.getCorrelationId());
    } else {
      filterUtils.setCorrelationId(generateCorrelationId());
      logger.debug("tmx-correlation-id generated in tracking filter: {}.", filterUtils.getCorrelationId());
    }

    return null;
  }
}
