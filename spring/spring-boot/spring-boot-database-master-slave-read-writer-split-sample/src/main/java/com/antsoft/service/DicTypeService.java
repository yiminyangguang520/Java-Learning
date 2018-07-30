package com.antsoft.service;

import com.antsoft.database.mybatis.ReadOnlyConnection;
import com.antsoft.mapper.DicTypeMapper;
import com.antsoft.model.DicType;
import com.github.pagehelper.PageHelper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jason
 * @date 2017/3/2
 */
@Service
public class DicTypeService {

  @Autowired
  private DicTypeMapper dicTypeMapper;

  @ReadOnlyConnection
  public List<DicType> getAll(DicType dicType) {
    if (dicType.getPage() != null && dicType.getRows() != null) {
      PageHelper.startPage(dicType.getPage(), dicType.getRows());
    }
    return dicTypeMapper.selectAll();
  }

}
