package com.room.service;

import com.room.entity.Linkman;
import com.room.entity.StatusMessage;
import java.util.List;

/**
 * @author litz-a 
 * Created by Doublestar on 2017/12/5 21:06).
 */
public interface ILinkmanService {

  /**
   * 根据用户名查找联系人
   * @param name
   * @return
   */
  List<Linkman> findLinkmanByName(String name);

  /**
   * 根据customer id查找联系人
   * @param cid
   * @return
   */
  List<Linkman> findLinkmanByCid(int cid);

  /**
   * 更新联系人
   * @param id
   * @param linkman
   * @return
   */
  boolean updateLinkman(int id, Linkman linkman);

  /**
   * 插入联系人
   * @param cid
   * @param linkman
   * @return
   */
  StatusMessage insertLinkman(int cid, Linkman linkman);

  /**
   * 删除联系人
   * @param id
   * @return
   */
  boolean deleteLinkman(int id);
}
