package com.glodon.es.controller;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.ToXContent;
import org.elasticsearch.common.xcontent.ToXContent.MapParams;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
public class ElasticSearchController {

  @Autowired
  private RestHighLevelClient restHighLevelClient;

  @GetMapping("/search")
  public Object getData() throws IOException {
    SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
    sourceBuilder.query(QueryBuilders.boolQuery().must(QueryBuilders.termQuery("product.raw", "gczs"))
        .must(QueryBuilders.termQuery("version.raw", "2.0.0.1956")));
    sourceBuilder.from(0);
    sourceBuilder.size(5000);
    sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

    SearchRequest searchRequest = new SearchRequest();
    searchRequest.indices("gcrs-activity");
    searchRequest.types("activity");
    searchRequest.source(sourceBuilder);
    System.out.println(searchRequest.source().toString(new MapParams(Collections.singletonMap("pretty", "true"))));

    SearchResponse searchResponse = restHighLevelClient.search(searchRequest);
    SearchHits hits = searchResponse.getHits();
    SearchHit[] searchHits = hits.getHits();
//    for (SearchHit hit : searchHits) {
//      System.out.println(hit.getSourceAsString());
//    }

    return hits.toString();
  }
}
