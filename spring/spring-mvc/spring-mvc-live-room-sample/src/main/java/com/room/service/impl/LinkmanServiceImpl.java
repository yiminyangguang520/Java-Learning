package com.room.service.impl;

import com.room.dao.LinkmanDao;
import com.room.entity.Linkman;
import com.room.entity.StatusMessage;
import com.room.service.ILinkmanService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author litz-a
 * Created by Doublestar on 2017/12/5 21:06).
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class LinkmanServiceImpl implements ILinkmanService {

  @Autowired
  LinkmanDao linkmanDao;

  @Override
  public List<Linkman> findLinkmanByName(String name) {
    return linkmanDao.findLinkmanByName(name);
  }

  @Override
  public List<Linkman> findLinkmanByCid(int cid) {
    return linkmanDao.findLinkmanByCid(cid);
  }

  @Override
  public boolean updateLinkman(int id, Linkman linkman) {
    linkman.setId(id);
    return linkmanDao.updateLinkman(linkman) > 0;
  }

  @Override
  public StatusMessage insertLinkman(int cid, Linkman linkman) {
    linkman.setCid(cid);
    if (linkmanDao.insertLinkman(linkman) > 0) {
      int id = linkman.getId();
      return new StatusMessage(200, "success", String.valueOf(id));
    } else {
      return new StatusMessage(500, "error", "联系人添加失败！");
    }
  }

  @Override
  public boolean deleteLinkman(int id) {
    return linkmanDao.deleteLinkman(id) > 0;
  }
}
