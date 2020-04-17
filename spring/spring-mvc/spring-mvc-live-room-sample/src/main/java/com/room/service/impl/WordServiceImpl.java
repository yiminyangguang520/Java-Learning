package com.room.service.impl;

import com.room.mq.sender.QueueSender;
import com.room.service.IWordService;
import com.room.utils.WordUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author min
 * Created by Doublestar on 2017/12/27 23:50).
 */
@Service
public class WordServiceImpl implements IWordService {

  @Autowired
  private WordUtils wordUtils;

  @Autowired
  private QueueSender queueSender;

  @Override
  public boolean filterWords(String str) {
    //调用工具类
    JSONObject data = wordUtils.filterWords(str);
    //检测API返回的状态
    if (!(boolean) data.get("status")) {
      //发送给activeMQ，由敏感词拦截系统作响应
      queueSender.send("yunsle.filter", str);
      return false;
    } else {
      return true;
    }
  }
}
