package com.hmily.apigateway.filter;

import com.hmily.apigateway.constant.RedisConstant;
import com.hmily.apigateway.utils.CookieUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * 权限拦截（区分买家和卖家）
 * @author litz-a
 */
@Component
public class AuthSellerFilter extends ZuulFilter {

  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Override
  public String filterType() {
    return PRE_TYPE;
  }

  @Override
  public int filterOrder() {
    return PRE_DECORATION_FILTER_ORDER - 1;
  }

  @Override
  public boolean shouldFilter() {
    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();

    if ("/order/order/finish".equals(request.getRequestURI())) {
      return false;
    }

    return false;
  }

  @Override
  public Object run() {
    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();

    /**
     * /order/finish 只能卖家访问(cookie里有token, 并且对应的redis中值)
     */

    Cookie cookie = CookieUtil.get(request, "token");
    if (cookie == null
        || StringUtils.isEmpty(cookie.getValue())
        || StringUtils.isEmpty(stringRedisTemplate.opsForValue().get(String.format(RedisConstant.TOKEN_TEMPLATE, cookie.getValue())))) {
      requestContext.setSendZuulResponse(false);
      requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
    }

    return null;
  }
}
