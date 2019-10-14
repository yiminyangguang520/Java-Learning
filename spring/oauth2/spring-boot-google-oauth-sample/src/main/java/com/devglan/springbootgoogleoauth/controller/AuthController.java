package com.devglan.springbootgoogleoauth.controller;

import static com.devglan.springbootgoogleoauth.common.Constants.homeUrl;

import com.devglan.springbootgoogleoauth.model.User;
import com.devglan.springbootgoogleoauth.service.UserService;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author litz-a
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

  @Autowired
  private UserService userService;

  @GetMapping("/custom-login")
  public String loadLoginPage() {
    return "login";
  }

  @PostMapping("/signup")
  public void login(@ModelAttribute("signup") User user, HttpServletRequest request, HttpServletResponse response) throws IOException {
    String token = userService.signUp(user);
    String dest = UriComponentsBuilder.fromUriString(homeUrl)
        .queryParam("auth_token", token)
        .build().toUriString();
    response.sendRedirect(dest);
  }

}
