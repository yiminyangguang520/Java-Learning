package com.devglan.controller;

import com.devglan.model.UserDetails;
import com.devglan.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public ResponseEntity<List<UserDetails>> userDetails() {

    List<UserDetails> userDetails = userService.getUserDetails();
    return new ResponseEntity<List<UserDetails>>(userDetails, HttpStatus.OK);
  }

}
