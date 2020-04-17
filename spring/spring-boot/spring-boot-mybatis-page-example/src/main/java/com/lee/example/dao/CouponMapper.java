package com.lee.example.dao;

import com.lee.example.model.Coupon;
import com.lee.example.model.CouponExample;
import com.github.pagehelper.PageRowBounds;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author min
 */
@Repository
@Mapper
public interface CouponMapper {

  List<Coupon> selectByExample(CouponExample example);

  List<Coupon> selectByExample(CouponExample example, PageRowBounds pageRowBounds);

  long countByExample(CouponExample example);

  int deleteByExample(CouponExample example);

  int deleteByPrimaryKey(Long id);

  int insert(Coupon record);

  int insertSelective(Coupon record);

  Coupon selectByPrimaryKey(Long id);

  int updateByExampleSelective(@Param("record") Coupon record, @Param("example") CouponExample example);

  int updateByExample(@Param("record") Coupon record, @Param("example") CouponExample example);

  int updateByPrimaryKeySelective(Coupon record);

  int updateByPrimaryKey(Coupon record);
}