package pl.piomin.services.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import javax.servlet.http.HttpServletResponse;

/**
 * @author min
 */
public class AddResponseIDHeaderFilter extends ZuulFilter {

  private int id = 1;

  @Override
  public String filterType() {
    return "post";
  }

  @Override
  public int filterOrder() {
    return 10;
  }

  @Override
  public boolean shouldFilter() {
    return true;
  }

  @Override
  public Object run() {
    RequestContext context = RequestContext.getCurrentContext();
    HttpServletResponse servletResponse = context.getResponse();
    servletResponse.addHeader("X-Response-ID", String.valueOf(id++));
    return null;
  }

}
