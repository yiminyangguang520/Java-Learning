package com.room.dao;

import com.room.entity.Linkman;
import java.util.List;


/**
 * @author min
 * DAO接口,mybatis动态完成Impl
 */
public interface LinkmanDao {

  /**
   * 根据联系人姓名查找联系人相关信息
   * @param name
   * @return
   */
  List<Linkman> findLinkmanByName(String name);

  /**
   * 根据联系人客户id查找
   * @param cid
   * @return
   */
  List<Linkman> findLinkmanByCid(int cid);

  /**
   * 修改联系人
   * @param linkman
   * @return
   */
  int updateLinkman(Linkman linkman);

  /**
   * 删除联系人
   * @param id
   * @return
   */
  int deleteLinkman(int id);

  /**
   * 根据客户Id删除联系人
   * @param id
   * @return
   */
  int deleteLinkmanByCid(int id);

  /**
   * 添加联系人
   * @param linkman 联系人
   * @return
   */
  int insertLinkman(Linkman linkman);

}
