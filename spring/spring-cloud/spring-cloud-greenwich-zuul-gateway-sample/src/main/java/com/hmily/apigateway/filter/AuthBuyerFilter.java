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
 * @author min
 */
@Component
public class AuthBuyerFilter extends ZuulFilter {

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
    //当访问的是create就开启这个过滤器
    if ("/order/order/create".equals(request.getRequestURI())) {
      return false;
    }

    return false;
  }

  @Override
  public Object run() {
    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();
    /**
     * /order/create 只能买家访问(cookie里有openid，redis里也有)
     */
    Cookie cookie = CookieUtil.get(request, "openid");
    if (cookie == null || StringUtils.isEmpty(cookie.getValue())
        || StringUtils.isEmpty(
        stringRedisTemplate.opsForValue().get(String.format(RedisConstant.OPENID_TEMPLATE, cookie.getValue())))) {
      requestContext.setSendZuulResponse(false);
      requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
    }

    return null;
  }
}
