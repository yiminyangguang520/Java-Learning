package org.newtutorials.elasticsearch.binary;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsRequest;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.node.NodeValidationException;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

/**
 *
 * @author dani
 * @date 5/12/2017
 */
public class UploadBinaryFilesToElasticSearch {

  public static void main(String[] args) throws IOException, ExecutionException, InterruptedException, NodeValidationException {
    Settings settings = Settings.builder()
        .put("cluster.name", "bruce-lee-application")
        .put("client.transport.sniff", true)
        .put("client.transport.ping_timeout", 10, TimeUnit.SECONDS)
        .build();
    Client client = new PreBuiltTransportClient(settings)
        .addTransportAddress(new InetSocketTransportAddress(Inet4Address.getByName("10.1.70.17"), 9300));
    String indexName = "binary_index2";
    String documentsType = "binary_doc";
    ActionFuture<IndicesExistsResponse> indicesExistsResponseAction = client.admin().indices().exists(new IndicesExistsRequest(indexName));
    IndicesExistsResponse indicesExistsResponse = indicesExistsResponseAction.actionGet();
    if (!indicesExistsResponse.isExists()) {
      ActionFuture<CreateIndexResponse> createIndexResponseAction = client.admin().indices().create(new CreateIndexRequest(indexName));
      CreateIndexResponse createIndexResponse = createIndexResponseAction.get();
      if (!createIndexResponse.isAcknowledged()) {
        throw new IllegalStateException("Failed to create index " + indexName);
      }
      putMapping(indexName, client, documentsType, "{\"properties\":{\"name\":{\"type\":\"string\"},\"data\":{\"type\":\"binary\",\"store\":\"false\"}}}");
    }
    Files.walk(new File("F:\\C++Demo\\GEPDemo\\").toPath())
        .map(Path::toFile)
        .forEach(file -> {
          if (!file.isFile()) {
            return;
          }
          System.out.println("encoding :" + file.getAbsoluteFile());
          try {
            String base64 = Base64.getEncoder().encodeToString(Files.readAllBytes(file.toPath()));
            if (base64.length() > 0) {
              XContentBuilder document = createDocumentXContent(file.getName(), base64);
              IndexResponse response = client.prepareIndex(indexName, documentsType).setSource(document).get();
            }
            System.out.println(base64);
          } catch (IOException e) {
            e.printStackTrace();
          }
        });
  }

  private static void putMapping(String index, Client client, String type, String mappingSource) {
    PutMappingResponse mappingResponse = client.admin().indices().preparePutMapping(index)
        .setType(type)
        .setSource(mappingSource, XContentType.JSON)
        .get();
    if (!mappingResponse.isAcknowledged()) {
      throw new IllegalStateException("Failed to create type " + index);
    }
  }

  private static XContentBuilder createDocumentXContent(String name, String data) throws IOException {
    return XContentFactory.jsonBuilder()
        .startObject()
        .field("name", name)
        .field("data", data)
        .endObject();
  }
}
