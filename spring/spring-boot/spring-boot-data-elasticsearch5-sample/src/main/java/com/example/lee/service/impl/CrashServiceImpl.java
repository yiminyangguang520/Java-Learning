package com.example.lee.service.impl;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;


import com.example.lee.entity.Crash;
import com.example.lee.repository.CrashRepository;
import com.example.lee.service.CrashService;
import java.util.List;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;


/**
 * @author min
 */
@Service
public class CrashServiceImpl implements CrashService {

  @Autowired
  private ElasticsearchTemplate elasticsearchTemplate;

  @Autowired
  private TransportClient transportClient;

  @Autowired
  private CrashRepository crashRepository;

  @Override
  public Crash findOneByDumpId(String id) {

    final SearchQuery searchQuery = new NativeSearchQueryBuilder()
        .withQuery(termQuery("id.raw", id))
        .build();
    final List<Crash> crashList = elasticsearchTemplate.queryForList(searchQuery, Crash.class);
    return crashList.get(0);
  }

  @Override
  public String findById(String id) {
    Crash crash = crashRepository.findById(id).get();
    System.out.println(crash);
    GetResponse getReponse = transportClient.prepareGet("gcrs-crash", "crash", id)
        .setOperationThreaded(false)
        .get();
    return getReponse.getSourceAsString();
  }

}
