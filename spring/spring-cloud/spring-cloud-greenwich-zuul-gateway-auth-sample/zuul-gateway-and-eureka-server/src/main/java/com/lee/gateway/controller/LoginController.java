package com.lee.gateway.controller;

import com.lee.gateway.bean.auth.AuthResponse;
import com.lee.gateway.bean.auth.LoginRequest;
import com.lee.gateway.service.ILoginService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author min
 */
@Controller
@RequestMapping("/api")
public class LoginController {

  @Autowired
  private ILoginService iLoginService;

  @CrossOrigin("*")
  @PostMapping("/signin")
  @ResponseBody
  public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
    String token = iLoginService.login(loginRequest.getUsername(), loginRequest.getPassword());
    HttpHeaders headers = new HttpHeaders();

    List<String> headerlist = Stream.of("Content-Type", "Accept", "X-Requested-With", "Authorization").collect(Collectors.toList());
    headers.setAccessControlAllowHeaders(headerlist);

    headers.setAccessControlExposeHeaders(CollectionUtils.arrayToList("Authorization"));
    headers.set("Authorization", token);
    return new ResponseEntity<>(new AuthResponse(token), headers, HttpStatus.CREATED);
  }

  @CrossOrigin("*")
  @PostMapping("/signout")
  @ResponseBody
  public ResponseEntity<AuthResponse> logout(@RequestHeader(value = "Authorization") String token) {
    HttpHeaders headers = new HttpHeaders();
    if (iLoginService.logout(token)) {
      headers.remove("Authorization");
      return new ResponseEntity<>(new AuthResponse("logged out"), headers, HttpStatus.CREATED);
    }
    return new ResponseEntity<>(new AuthResponse("Logout Failed"), headers, HttpStatus.NOT_MODIFIED);
  }

  /**
   * @return boolean. if request reach here it means it is a valid token.
   */
  @PostMapping("/valid/token")
  @ResponseBody
  public Boolean isValidToken(@RequestHeader(value = "Authorization") String token) {
    return true;
  }


  @PostMapping("/signin/token")
  @CrossOrigin("*")
  @ResponseBody
  public ResponseEntity<AuthResponse> createNewToken(@RequestHeader(value = "Authorization") String token) {
    String newToken = iLoginService.createNewToken(token);
    HttpHeaders headers = new HttpHeaders();

    List<String> headerList = new ArrayList<>();
    headerList.add("Content-Type");
    headerList.add(" Accept");
    headerList.add("X-Requested-With");
    headerList.add("Authorization");
    headers.setAccessControlAllowHeaders(headerList);

    List<String> exposeList = new ArrayList<>();
    exposeList.add("Authorization");
    headers.setAccessControlExposeHeaders(exposeList);

    headers.set("Authorization", newToken);
    return new ResponseEntity<>(new AuthResponse(newToken), headers, HttpStatus.CREATED);
  }
}
