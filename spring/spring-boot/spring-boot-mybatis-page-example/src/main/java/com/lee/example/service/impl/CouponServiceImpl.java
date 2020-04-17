package com.lee.example.service.impl;

import com.lee.example.dao.CouponMapper;
import com.lee.example.model.Coupon;
import com.lee.example.model.CouponExample;
import com.lee.example.service.CouponService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.PageRowBounds;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * https://github.com/pagehelper/Mybatis-PageHelper/blob/master/wikis/en/HowToUse.md
 * @author min
 */
@Service
public class CouponServiceImpl implements CouponService {

  @Autowired
  private CouponMapper couponMapper;

  /**
   * 静态方法startPage
   */
  @Override
  public List<Coupon> getCouponListByPage(CouponExample couponExample, Integer pageNum, Integer pageSize) {
    //  在你需要进行分页的 MyBatis 查询方法前调用 PageHelper.startPage 静态方法即可，紧跟在这个方法后的第一个MyBatis 查询方法会被进行分页。
    //  只要你可以保证在 PageHelper 方法调用后紧跟 MyBatis 查询方法，这就是安全的
    PageHelper.startPage(pageNum, pageSize);
    return couponMapper.selectByExample(couponExample);
  }

  /**
   * 分页时，实际返回的结果list类型是Page<E>，如果想取出分页信息，需要强制转换为Page<E> 因为  public class Page<E> extends ArrayList<E> implements Closeable
   */
  @Override
  public Page<Coupon> getCouponListByPage1(CouponExample couponExample, Integer pageNum, Integer pageSize) {
    PageHelper.startPage(pageNum, pageSize);
    List<Coupon> list = couponMapper.selectByExample(couponExample);
    if (null != list) {
      Page<Coupon> page = (Page<Coupon>) list;
      System.out.println(page);
      return page;
    }
    return null;
  }

  /**
   * 用PageRowBounds
   */
  @Override
  public List<Coupon> getCouponListByPage2(CouponExample couponExample, Integer pageNum, Integer pageSize) {
    PageRowBounds pageRowBounds = new PageRowBounds(pageNum, pageSize);
    List<Coupon> couponList = couponMapper.selectByExample(couponExample, pageRowBounds);

    System.out.println(pageRowBounds.getTotal());

    Page<Coupon> page = (Page<Coupon>) couponList;
    System.out.println(page);

    return couponList;
  }

  @Override
  public Page<Coupon> getCouponListByPage3(CouponExample couponExample, Integer pageNum, Integer pageSize) {
    Page<Coupon> page = PageHelper.startPage(pageNum, pageSize).doSelectPage(() -> couponMapper.selectByExample(couponExample));
    System.out.println(page);
    return page;
  }

  /**
   * 方法参数
   */
  @Override
  public PageInfo<Coupon> getCouponListByPage4(CouponExample couponExample, Integer pageNum, Integer pageSize) {
    PageInfo<Coupon> pageInfo = PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(() -> couponMapper.selectByExample(couponExample));
    System.out.println(pageInfo);
    return pageInfo;
  }

  /**
   * PageInfo
   */
  @Override
  public PageInfo<Coupon> getCouponListByPage5(CouponExample couponExample, Integer pageNum, Integer pageSize) {
    List<Coupon> list = couponMapper.selectByExample(couponExample);
    if (null == list) {
      return null;
    }
    PageInfo<Coupon> pageInfo = new PageInfo<>(list);
    System.out.println(pageInfo);
    return pageInfo;
  }

  @Override
  public Page<Coupon> getCouponListByPage6(CouponExample couponExample, Integer offset, Integer limit) {
    return (Page<Coupon>) couponMapper.selectByExample(couponExample, new PageRowBounds(offset, limit));
  }
}
