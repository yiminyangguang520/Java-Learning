package org.newtutorials.elasticsearch.node;

import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.node.Node;
import org.elasticsearch.node.NodeValidationException;

/**
 *
 * @author dani
 * @date 5/4/2017
 */
public class NodeBuilder {

  public static final String TEMP_FOLDER = "./target/newtutorials/";
  public static final String CLUSTER_NAME = "newtutorials-elasticsearch";
  static Node theNode;

  public static Node getNode(String tempFolder, String clusterName, boolean isClientNode) throws NodeValidationException {
    if (theNode != null) {
      return theNode;
    }
    Settings settings = Settings.builder()
        .put("path.home", tempFolder)
        .put("cluster.name", clusterName)
        .put("transport.type", "local")
        .put("http.enabled", false)
        .build();
    theNode = new Node(settings).start();

    return theNode;
  }


}
