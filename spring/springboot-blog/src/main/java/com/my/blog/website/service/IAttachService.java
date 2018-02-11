package com.my.blog.website.service;

import com.github.pagehelper.PageInfo;
import com.my.blog.website.modal.vo.AttachVo;

/**
 * Created by wangq on 2017/3/20.
 */
public interface IAttachService {

  /**
   * 分页查询附件
   */
  PageInfo<AttachVo> getAttachs(Integer page, Integer limit);

  /**
   * 保存附件
   */
  void save(String fname, String fkey, String ftype, Integer author);

  /**
   * 根据附件id查询附件
   */
  AttachVo selectById(Integer id);

  /**
   * 删除附件
   */
  void deleteById(Integer id);
}
