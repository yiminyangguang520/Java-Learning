package org.newtutorials.elasticsearch.query.searchrequestbuilder;

import static org.elasticsearch.index.query.QueryBuilders.boolQuery;
import static org.elasticsearch.index.query.QueryBuilders.termQuery;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeValidationException;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;
import org.newtutorials.elasticsearch.pojo.ExampleDocument;

/**
 *
 * @author dani
 * @date 5/7/2017
 */
public class SearchRequestBuilderExample {

  public static void main(String[] args) throws NodeValidationException, ExecutionException, InterruptedException, IOException {

    String tempFolder = "./target/newtutorials/";
    String clusterName = "newtutorials-elasticsearch";
    Settings settings = Settings.builder()
        .put("path.home", tempFolder)
        .put("cluster.name", clusterName)
        .put("transport.type", "local")
        .put("http.enabled", false)
        .build();
    Node node = new Node(settings).start();
    Client client = node.client();

    String indexName = "searchrequestbuilder";
    ActionFuture<IndicesExistsResponse> indicesExistsResponseAction = client.admin().indices().exists(new IndicesExistsRequest(indexName));
    IndicesExistsResponse indicesExistsResponse = indicesExistsResponseAction.actionGet();
    if (!indicesExistsResponse.isExists()) {
      ActionFuture<CreateIndexResponse> createIndexResponseAction = client.admin().indices().create(new CreateIndexRequest(indexName));
      CreateIndexResponse createIndexResponse = createIndexResponseAction.get();
      if (!createIndexResponse.isAcknowledged()) {
        throw new IllegalStateException("Failed to create index " + indexName);
      }
    }

    String documentType = "documents";
    ObjectMapper objectMapper = new ObjectMapper();
    ExampleDocument exampleDocument;
    exampleDocument = new ExampleDocument("first document", "Not Me", 10);
    client.prepareIndex(indexName, documentType, "1").setSource(objectMapper.writeValueAsString(exampleDocument), XContentType.JSON).get();
    exampleDocument = new ExampleDocument("A nice book", "Unknown Again", 20);
    client.prepareIndex(indexName, documentType, "2").setSource(objectMapper.writeValueAsString(exampleDocument), XContentType.JSON).get();
    exampleDocument = new ExampleDocument("Old dusty", "Author Again", 50);
    client.prepareIndex(indexName, documentType, "3").setSource(objectMapper.writeValueAsString(exampleDocument), XContentType.JSON).get();
    exampleDocument = new ExampleDocument("New Car", "Car Lover", 55);
    client.prepareIndex(indexName, documentType, "4").setSource(objectMapper.writeValueAsString(exampleDocument), XContentType.JSON).get();

    searchRequestBuilderMatchAllQuery(indexName, client);
    searchRequestBuilderBoolQuery(indexName, client);
    searchRequestBuilderBoolQueryTree(indexName, client);
    node.close();
  }

  private static void searchRequestBuilderMatchAllQuery(String indexName, Client client) throws IOException {
    System.out.println("SearchRequestBuilderExample.searchRequestBuilderMatchAllQuery");
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.readerFor(ExampleDocument.class);
    SearchRequestBuilder builder = client.prepareSearch(indexName);
    builder.setQuery(QueryBuilders.matchAllQuery());
    builder.addSort("pages", SortOrder.DESC);
    SearchResponse response = builder.get();
    for (SearchHit searchHit : response.getHits()) {
      ExampleDocument document = objectMapper.readValue(searchHit.getSourceAsString(), ExampleDocument.class);
      System.out.println(document);
    }
  }

  private static void searchRequestBuilderBoolQuery(String indexName, Client client) throws IOException {
    System.out.println("SearchRequestBuilderExample.searchRequestBuilderBoolQuery");
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.readerFor(ExampleDocument.class);
    SearchRequestBuilder builder = client.prepareSearch(indexName);
    builder.setQuery(boolQuery()
        .must(termQuery("title", "first"))
        .mustNot(termQuery("title", "book")));
    builder.addSort("pages", SortOrder.DESC);
    SearchResponse response = builder.get();
    for (SearchHit searchHit : response.getHits()) {
      ExampleDocument document = objectMapper.readValue(searchHit.getSourceAsString(), ExampleDocument.class);
      System.out.println(document);
    }
  }

  private static void searchRequestBuilderBoolQueryTree(String indexName, Client client) throws IOException {
    System.out.println("SearchRequestBuilderExample.searchRequestBuilderBoolQueryTree");
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.readerFor(ExampleDocument.class);
    SearchRequestBuilder builder = client.prepareSearch(indexName);
    builder.setQuery(boolQuery()
        .must(termQuery("title", "first"))
        .must(boolQuery()
            .must(termQuery("title", "document"))
            .must(termQuery("pages", 10))));
    builder.addSort("pages", SortOrder.DESC);
    SearchResponse response = builder.get();
    for (SearchHit searchHit : response.getHits()) {
      ExampleDocument document = objectMapper.readValue(searchHit.getSourceAsString(), ExampleDocument.class);
      System.out.println(document);
    }
  }
}
