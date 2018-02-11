package com.my.blog.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.my.blog.website.dao.AttachVoMapper;
import com.my.blog.website.modal.vo.AttachVo;
import com.my.blog.website.modal.vo.AttachVoExample;
import com.my.blog.website.service.IAttachService;
import com.my.blog.website.utils.DateKit;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author litz-a
 * Created by wangq on 2017/3/20.
 */
@Service
@Slf4j
public class AttachServiceImpl implements IAttachService {

  @Autowired
  private AttachVoMapper attachDao;

  @Override
  public PageInfo<AttachVo> getAttachs(Integer page, Integer limit) {
    PageHelper.startPage(page, limit);
    AttachVoExample attachVoExample = new AttachVoExample();
    attachVoExample.setOrderByClause("id desc");
    List<AttachVo> attachVos = attachDao.selectByExample(attachVoExample);
    return new PageInfo<>(attachVos);
  }

  @Override
  public AttachVo selectById(Integer id) {
    if (null != id) {
      return attachDao.selectByPrimaryKey(id);
    }
    return null;
  }

  @Override
  public void save(String fname, String fkey, String ftype, Integer author) {
    AttachVo attach = new AttachVo();
    attach.setFname(fname);
    attach.setAuthorId(author);
    attach.setFkey(fkey);
    attach.setFtype(ftype);
    attach.setCreated(DateKit.getCurrentUnixTime());
    attachDao.insertSelective(attach);
  }

  @Override
  public void deleteById(Integer id) {
    if (null != id) {
      attachDao.deleteByPrimaryKey(id);
    }
  }
}
