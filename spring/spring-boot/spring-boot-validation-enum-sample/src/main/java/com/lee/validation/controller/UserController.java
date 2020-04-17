package com.lee.validation.controller;

import com.lee.validation.dto.UserAddDTO;
import com.lee.validation.dto.UserUpdateDTO;
import com.lee.validation.dto.UserUpdateGenderDTO;
import com.lee.validation.dto.UserUpdateStatusDTO;
import com.lee.validation.dto.UserUpdateStatusDTO.Group01;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author min
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

  private Logger logger = LoggerFactory.getLogger(getClass());

  @GetMapping("/get")
  public void get(@RequestParam("id") @Min(value = 1L, message = "编号必须大于 0") Integer id) {
    logger.info("[get][id: {}]", id);
  }

  @PostMapping("/add")
  public void add(@Valid UserAddDTO addDTO) {
    logger.info("[add][addDTO: {}]", addDTO);
  }

  @PostMapping("/update_gender")
  public void updateGender(@Valid UserUpdateGenderDTO updateGenderDTO) {
    logger.info("[updateGender][updateGenderDTO: {}]", updateGenderDTO);
  }

  @PostMapping("/update_status_true")
  public void updateStatusTrue(@Validated(Group01.class) UserUpdateStatusDTO updateStatusDTO) {
    logger.info("[updateStatusTrue][updateStatusDTO: {}]", updateStatusDTO);
  }

  @PostMapping("/update_status_false")
  public void updateStatusFalse(@Validated(UserUpdateStatusDTO.Group02.class) UserUpdateStatusDTO updateStatusDTO) {
    logger.info("[updateStatusFalse][updateStatusDTO: {}]", updateStatusDTO);
  }

  @PostMapping("/update")
  public void update(@Valid UserUpdateDTO updateDTO) {
    logger.info("[update][updateDTO: {}]", updateDTO);
  }

}
