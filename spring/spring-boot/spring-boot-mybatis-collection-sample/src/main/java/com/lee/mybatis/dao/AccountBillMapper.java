package com.lee.mybatis.dao;

import com.lee.mybatis.model.AccountBill;
import com.lee.mybatis.model.AccountBillExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccountBillMapper {

  long countByExample(AccountBillExample example);

  int deleteByExample(AccountBillExample example);

  int deleteByPrimaryKey(Integer id);

  int insert(AccountBill record);

  int insertSelective(AccountBill record);

  List<AccountBill> selectByExample(AccountBillExample example);

  AccountBill selectByPrimaryKey(Integer id);

  int updateByExampleSelective(@Param("record") AccountBill record, @Param("example") AccountBillExample example);

  int updateByExample(@Param("record") AccountBill record, @Param("example") AccountBillExample example);

  int updateByPrimaryKeySelective(AccountBill record);

  int updateByPrimaryKey(AccountBill record);

  /**
   * 根据id将deleted字段标记为删除
   * @param accountBillId
   * @return
   */
  int markDeletedByPrimarykey(Integer accountBillId);

  /**
   * 根据账户id查询账户流水
   * @param accountId
   * @return
   */
  List<AccountBill> selectByAccountId(Integer accountId);
}