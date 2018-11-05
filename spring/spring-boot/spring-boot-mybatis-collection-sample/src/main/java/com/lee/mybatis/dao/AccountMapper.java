package com.lee.mybatis.dao;

import com.lee.mybatis.model.Account;
import com.lee.mybatis.model.AccountExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {

  long countByExample(AccountExample example);

  int deleteByExample(AccountExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(Account record);

  int insertSelective(Account record);

  List<Account> selectByExample(AccountExample example);

  Account selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

  int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

  int updateByPrimaryKeySelective(Account record);

  int updateByPrimaryKey(Account record);

  /**
   * 根据id将deleted字段标记为删除
   * @param accountBillId
   * @return
   */
  int markDeletedByPrimarykey(Integer accountBillId);

  /**
   * 获取账户信息(包含账户流水)
   * @param example
   * @return
   */
  List<Account> selectWithAccountBillByExample(AccountExample example);

  /**
   * 获取账户信息(包含账户流水)
   * @param customerId
   * @return
   */
  List<Account> selectWithAccountBillByCustomerId(Integer customerId);
}