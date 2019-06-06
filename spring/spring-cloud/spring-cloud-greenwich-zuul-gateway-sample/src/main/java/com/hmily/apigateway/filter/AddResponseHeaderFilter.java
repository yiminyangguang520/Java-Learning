package com.hmily.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.POST_TYPE;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.SEND_RESPONSE_FILTER_ORDER;

/**
 * @author litz-a
 */
@Component
public class AddResponseHeaderFilter extends ZuulFilter {

  @Override
  public String filterType() {
    return POST_TYPE;
  }

  @Override
  public int filterOrder() {
    return SEND_RESPONSE_FILTER_ORDER - 1;
  }

  @Override
  public boolean shouldFilter() {
    return false;
  }

  @Override
  public Object run() {
    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletResponse response = requestContext.getResponse();
    //这里是针对响应做的处理！！
    response.setHeader("X-Test", UUID.randomUUID().toString());
    return null;
  }
}
