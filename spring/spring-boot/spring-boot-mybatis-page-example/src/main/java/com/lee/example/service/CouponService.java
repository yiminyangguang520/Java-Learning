package com.lee.example.service;

import com.lee.example.model.Coupon;
import com.lee.example.model.CouponExample;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import java.util.List;

/**
 * @author litz-a
 */
public interface CouponService {

  List<Coupon> getCouponListByPage(CouponExample couponExample, Integer pageNum, Integer pageSize);

  Page<Coupon> getCouponListByPage1(CouponExample couponExample, Integer pageNum, Integer pageSize);

  List<Coupon> getCouponListByPage2(CouponExample couponExample, Integer pageNum, Integer pageSize);

  Page<Coupon> getCouponListByPage3(CouponExample couponExample, Integer pageNum, Integer pageSize);

  PageInfo<Coupon> getCouponListByPage4(CouponExample couponExample, Integer pageNum, Integer pageSize);

  PageInfo<Coupon> getCouponListByPage5(CouponExample couponExample, Integer pageNum, Integer pageSize);

  Page<Coupon> getCouponListByPage6(CouponExample couponExample, Integer offset, Integer limit);

}
