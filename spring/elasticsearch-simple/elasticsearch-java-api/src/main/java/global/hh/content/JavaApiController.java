package global.hh.content;

import com.google.gson.Gson;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Function:
 * <P> 版权所有 ©2013 Biostime Inc.. All Rights Reserved
 * <p> 未经本公司许可，不得以任何方式复制或使用本程序任何部分 <p>
 * User: 12360
 * Date: 2017/9/15
 * Time: 16:13
 */
@RestController
@RequestMapping("/api")
public class JavaApiController {

    @Autowired
    private TransportClient transportClient;

    @RequestMapping("/get")
    public String get() throws Exception {
        Gson gson = new Gson();
        GetResponse response = transportClient.prepareGet("megacorp", "employee", "1")
                .setOperationThreaded(false)
                .get();

        System.out.println(gson.toJson(response.getSource()));
        return gson.toJson(response);
    }

  @RequestMapping("/getobject")
  public Object getObject() throws Exception {
    Gson gson = new Gson();
    GetResponse response = transportClient.prepareGet("megacorp", "employee", "1")
        .setOperationThreaded(false)
        .get();

    Document customer = gson.fromJson(gson.toJson(response.getSource()), Document.class);
    return customer;
  }
}
