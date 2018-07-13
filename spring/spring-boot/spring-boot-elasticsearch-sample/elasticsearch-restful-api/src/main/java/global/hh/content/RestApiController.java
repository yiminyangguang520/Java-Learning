package global.hh.content;

import com.google.gson.Gson;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ApiIgnore
@RestController
@RequestMapping("/document-old/")
public class RestApiController {

    @Autowired
    private RestClient restClient;

    private final String indexName =  "test_index";

    private final String typeName =  "document";

    @GetMapping("/{id}")
    public String get(@PathVariable String id) throws Exception {
        Map<String, String> params = Collections.emptyMap();
        Response response = restClient.performRequest(RequestMethod.GET.name(), "/"+indexName+"/"+typeName+"/"+id, params);
        return EntityUtils.toString(response.getEntity());
    }

    @PostMapping("/")
    public String create(@RequestBody Document document) throws Exception {
        Gson gson = new Gson();
        Map<String, String> params = Collections.emptyMap();
        HttpEntity entity = new NStringEntity(gson.toJson(document), ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(RequestMethod.POST.name(), "/"+indexName+"/"+typeName+"/", params, entity);
        return EntityUtils.toString(response.getEntity());
    }

    @PutMapping("/{index}")
    public String update(@PathVariable String index,@RequestBody Document document) throws Exception {
        Gson gson = new Gson();
        Map<String, String> params = Collections.emptyMap();
        HttpEntity entity = new NStringEntity(gson.toJson(document), ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(RequestMethod.PUT.name(), "/"+indexName+"/"+typeName+"/"+index, params,entity);
        return EntityUtils.toString(response.getEntity());
    }

    @DeleteMapping("/{index}")
    public String delete(@PathVariable String index) throws Exception {
        Map<String, String> params = Collections.emptyMap();
        Response response = restClient.performRequest(RequestMethod.DELETE.name(),"/"+indexName+"/"+typeName+"/" + index, params);
        return EntityUtils.toString(response.getEntity());
    }

    @GetMapping("/search/{context}")
    public String search(@PathVariable String context) throws Exception {
        Map<String, String> params = new HashMap<>();
        StringBuffer sb = new StringBuffer();
        if(StringUtils.isNotEmpty(context) && !"null".equals(context)) {
            sb.append("content:");
            sb.append(context);
            sb.append("+title:");
            sb.append(context);
        }
      /*  params.put("q",sb.toString());*/
        Response response = restClient.performRequest(RequestMethod.GET.name(), "/" + indexName + "/" + typeName + "/_search?q=" + sb, params);
        return EntityUtils.toString(response.getEntity());
    }
}
