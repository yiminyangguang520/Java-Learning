package github.javaguide.springsecurityjwtguide.security.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * @author shuang.kou AccessDeineHandler 用来解决认证过的用户访问无权限资源时的异常
 */
public class JWTAccessDeniedHandler implements AccessDeniedHandler {

  /**
   * 当用户尝试访问需要权限才能的REST资源而权限不足的时候， 将调用此方法发送401响应以及错误信息
   */
  @Override
  public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
    accessDeniedException = new AccessDeniedException("Sorry you don not enough permissions to access it!");
    response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
  }
}
