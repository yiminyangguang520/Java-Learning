package com.lee.mybatis.controller;

import com.lee.mybatis.dao.AccountMapper;
import com.lee.mybatis.model.Account;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
public class AccountController {

  @Autowired
  private AccountMapper accountMapper;

  @GetMapping(value = "account/{id}")
  List<Account> getAccountByCustomerId(@PathVariable("id") Integer id) {
    return accountMapper.selectWithAccountBillByCustomerId(id);
  }
}
