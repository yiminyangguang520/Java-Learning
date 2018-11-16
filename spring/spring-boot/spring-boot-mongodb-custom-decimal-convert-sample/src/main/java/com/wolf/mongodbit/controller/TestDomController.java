package com.wolf.mongodbit.controller;

import static com.mongodb.client.model.Filters.eq;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 以dom方式处理
 * @author litz-a
 */
@RestController
@RequestMapping("/dommongodb")
public class TestDomController {

  private final static Logger logger = LoggerFactory.getLogger(TestDomController.class);

  @Autowired
  @Qualifier("mongoDatabase")
  private MongoDatabase mongoDatabase;

  @PostMapping("/findone")
  public Document findOne(String userid) {
    //获取集合
    MongoCollection<Document> mongoCollectionDoc = mongoDatabase.getCollection("blacklist");
    FindIterable<Document> find = mongoCollectionDoc.find(eq("userid", userid));
    return find.first();
  }
}
