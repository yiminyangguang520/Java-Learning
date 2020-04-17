package com.my.blog.website.modal.bo;

import com.my.blog.website.modal.vo.ContentVo;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * @author min
 * Created by 13 on 2017/2/23.
 */
@Data
public class ArchiveBo implements Serializable {

  private String date;
  private String count;
  private List<ContentVo> articles;
}
