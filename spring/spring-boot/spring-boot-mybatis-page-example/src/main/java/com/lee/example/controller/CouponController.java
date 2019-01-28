package com.lee.example.controller;

import com.lee.example.domain.PageBean;
import com.lee.example.model.Coupon;
import com.lee.example.model.CouponExample;
import com.lee.example.service.CouponService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 */
@RestController
@RequestMapping("/coupon")
public class CouponController {

  @Autowired
  private CouponService couponService;

  @RequestMapping("/list")
  public List<Coupon> list() {
    CouponExample example = new CouponExample();
    return couponService.getCouponListByPage(example, 1, 5);
  }

  @RequestMapping("/list2")
  public List<Coupon> list2() {
    CouponExample example = new CouponExample();
    return couponService.getCouponListByPage2(example, 0, 5);
  }

  @RequestMapping("/list3")
  public List<Coupon> list3() {
    CouponExample example = new CouponExample();
    return couponService.getCouponListByPage3(example, 1, 5);
  }

  @RequestMapping("/list4")
  public PageInfo<Coupon> list4() {
    CouponExample example = new CouponExample();
    return couponService.getCouponListByPage4(example, 1, 5);
  }

  @RequestMapping("/list5")
  public PageInfo<Coupon> list5() {
    CouponExample example = new CouponExample();
    return couponService.getCouponListByPage5(example, 1, 5);
  }


  /**
   * Bootstrap Table http://bootstrap-table.wenzhixin.net.cn/documentation/
   */
  @RequestMapping("/listPage")
  public PageBean<Coupon> listPage(Integer offset, Integer limit) {
    CouponExample example = new CouponExample();
    example.or().andVendorIdEqualTo(10001L).andYnEqualTo(1);
    Page<Coupon> page = couponService.getCouponListByPage6(example, offset, limit);
    PageBean<Coupon> pageBean = new PageBean<>();
    pageBean.setTotal(page.getTotal());
    pageBean.setRows(page.getResult());
    return pageBean;
  }
}
