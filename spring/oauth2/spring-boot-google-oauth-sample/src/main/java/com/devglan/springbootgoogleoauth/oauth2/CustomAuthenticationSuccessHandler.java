package com.devglan.springbootgoogleoauth.oauth2;

import static com.devglan.springbootgoogleoauth.common.Constants.homeUrl;

import com.devglan.springbootgoogleoauth.dao.UserRepository;
import com.devglan.springbootgoogleoauth.model.User;
import com.devglan.springbootgoogleoauth.util.JwtTokenUtil;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author min
 */
@Component
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private JwtTokenUtil jwtTokenUtil;

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    if (response.isCommitted()) {
      return;
    }

    DefaultOidcUser oidcUser = (DefaultOidcUser) authentication.getPrincipal();
    Map<String, Object> attributes = oidcUser.getAttributes();
    String email = (String) attributes.get("email");
    User user = userRepository.findByEmail(email);
    String token = jwtTokenUtil.generateToken(user);
    String redirectionUrl = UriComponentsBuilder.fromUriString(homeUrl)
        .queryParam("auth_token", token)
        .build().toUriString();
    getRedirectStrategy().sendRedirect(request, response, redirectionUrl);
  }

}
