package com.hmily.apigateway.filter;

import com.google.common.util.concurrent.RateLimiter;
import com.hmily.apigateway.exception.RateLimitException;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SERVLET_DETECTION_FILTER_ORDER;

/**
 * @author litz-a
 */
@Component
public class RateLimitFilter extends ZuulFilter {

  /**
   * 设定限流次数
   */
  private static final RateLimiter RATE_LIMITER = RateLimiter.create(200);

  @Override
  public String filterType() {
    return PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    //这个限流过滤器要放在最前面
    return SERVLET_DETECTION_FILTER_ORDER - 1;
  }

  @Override
  public boolean shouldFilter() {
    return false;
  }

  @Override
  public Object run() throws ZuulException {
    if (!RATE_LIMITER.tryAcquire()) {
      //尝试获取令牌
      throw new RateLimitException();
    }
    return null;
  }
}
