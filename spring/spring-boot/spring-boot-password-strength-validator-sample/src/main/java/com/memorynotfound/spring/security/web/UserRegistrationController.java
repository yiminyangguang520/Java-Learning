package com.memorynotfound.spring.security.web;

import com.memorynotfound.spring.security.web.dto.UserRegistrationDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * @author litz-a
 */
@Controller
@RequestMapping("/registration")
public class UserRegistrationController {

  @ModelAttribute("user")
  public UserRegistrationDto userRegistrationDto() {
    return new UserRegistrationDto();
  }

  @GetMapping
  public String showRegistrationForm(Model model) {
    return "registration";
  }

  @PostMapping
  public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
      BindingResult result) {

    if (result.hasErrors()) {
      return "registration";
    }

    return "redirect:/registration?success";
  }

}
