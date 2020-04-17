package com.hmily.apigateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author min
 */
@Component
public class TokenFilter extends ZuulFilter {

  /**
   * 指定过滤器类型
   * @return
   */
  @Override
  public String filterType() {
    return PRE_TYPE;
  }

  /**
   * 指定其在过滤器链上所处的顺序,数字越小，优先级越高
   * @return
   */
  @Override
  public int filterOrder() {
    return PRE_DECORATION_FILTER_ORDER - 1;
  }

  /**
   * 是否启用这个filter
   * @return
   */
  @Override
  public boolean shouldFilter() {
    return false;
  }

  /**
   * 这里面就是你的验证逻辑
   * @return
   * @throws ZuulException
   */
  @Override
  public Object run() throws ZuulException {
    RequestContext requestContext = RequestContext.getCurrentContext();
    HttpServletRequest request = requestContext.getRequest();

    //这里从url参数里获取, 也可以从cookie, header里获取
    String token = request.getParameter("token");
    if (StringUtils.isEmpty(token)) {
      requestContext.setSendZuulResponse(false);
      //判断url上没有token就不让它继续往下访问了
      requestContext.setResponseStatusCode(HttpStatus.UNAUTHORIZED.value());
    }
    return null;
  }
}
