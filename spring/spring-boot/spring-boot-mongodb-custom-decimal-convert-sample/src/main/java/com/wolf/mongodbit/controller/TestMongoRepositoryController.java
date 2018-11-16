package com.wolf.mongodbit.controller;

import com.wolf.mongodbit.entity.mongodb.Blacklist;
import com.wolf.mongodbit.service.BlacklistService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注意通过继承MongoRepository，访问mongodb也是通过MongoTemplate，所以不需要对MongoTemplate再进行处理了。
 * @author litz-a
 */
@RestController
@RequestMapping("/tmongodb")
public class TestMongoRepositoryController {

  private final static Logger logger = LoggerFactory.getLogger(BlackListController.class);

  @Autowired
  private BlacklistService blacklistService;

  @PostMapping("/tone")
  public Blacklist findOne(Blacklist blacklist) {
    logger.info("------------------:", blacklistService);
    return blacklistService.findByuserid(blacklist.getUserid());
  }
}
