package com.luoshupeng.multidatasource.controller.message;

import com.luoshupeng.multidatasource.secondary.entity.Message;
import com.luoshupeng.multidatasource.secondary.service.MessageService;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author litz-a
 * Created by luoshupeng on 2018-03-20 10:32
 */
@RestController
@RequestMapping("/message/*")
public class MessageController {

  @Autowired
  private MessageService messageService;

  @RequestMapping("list")
  public ResponseEntity<List<Message>> list() {
    return new ResponseEntity<>(messageService.list(), HttpStatus.OK);
  }
}
