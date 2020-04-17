package com.wolf.mongodbit.controller;


import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.wolf.mongodbit.exception.BlacklistExcepton;
import com.wolf.mongodbit.customenum.BlackEnum;
import com.wolf.mongodbit.entity.mongodb.Blacklist;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 通过mongoTemplate直接调用提供的方法。 黑名单业务处理controller
 * @author min
 */
@RestController
@RequestMapping("/mongodb")
public class BlackListController {

  private final static Logger logger = LoggerFactory.getLogger(BlackListController.class);

  @Autowired
  private MongoTemplate mongoTemplate;

  @PostMapping("/check")
  public Blacklist checkBlack(Blacklist blackList) throws Exception {
    List<Blacklist> list = mongoTemplate.find(query(where("userid").is(blackList.getUserid())), Blacklist.class);
    logger.info("springboot+mongodb:size={}", list.size());
    if (list.size() > 0) {
      return list.get(0);
    }
    return null;
  }

  @PostMapping("/insert")
  public int insertBlack(Blacklist blacklist) throws Exception {
    if ("".equals(blacklist.getStatus())) {
      throw new BlacklistExcepton(BlackEnum.NOTSTATUS);
    }
    if ("".equals(blacklist.getBlacktype())) {
      throw new BlacklistExcepton(BlackEnum.NOTTYPE);
    }
    if ("".equals(blacklist.getUsername())) {
      throw new BlacklistExcepton(BlackEnum.NOTUSERNAME);
    }
    List<Blacklist> list = mongoTemplate.find(query(where("userid").is(blacklist.getUserid())), Blacklist.class);
    logger.info("springboot+mongodb:size={}", list.size());
    if (list.size() > 0) {
      throw new BlacklistExcepton(BlackEnum.EXIST);
    }
    mongoTemplate.insert(blacklist);
    list = mongoTemplate.find(query(where("userid").is(blacklist.getUserid())), Blacklist.class);
    logger.info("springboot+mongodb:size={}", list.size());
    if (list.size() > 0) {
      return list.size();
    }
    return 0;
  }

}
