package com.glodon.sso.server.service.impl;

import com.glodon.sso.server.constant.Domain;
import com.glodon.sso.server.service.UserService;
import java.io.StringReader;
import java.text.MessageFormat;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import lombok.Cleanup;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * @author litz-a
 */
@Service
public class UserServiceImpl implements UserService {

  private OkHttpClient okClient = new OkHttpClient();

  /**
   * 域账户认证
   */
  @Override
  public String domainAccountAuthenticate(String account, String password) throws Exception {
    RequestBody body = RequestBody.create(MediaType.parse("text/html"),
        MessageFormat.format(Domain.DOMAIN_ACCOUNT_AUTHENTICATE_REQUEST_BODY, account, password));
    Request request = new Request.Builder()
        .url(Domain.DOMAIN_ACCOUNT_AUTHENTICATE_URL)
        .post(body)
        .build();
    Call call = okClient.newCall(request);
    @Cleanup Response response = call.execute();
    if (!response.isSuccessful()) {
      return Domain.DOMAIN_ACCOUNT_AUTHENTICATE_RESULT_FAIL;
    }
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
    Document document = documentBuilder.parse(new InputSource(new StringReader(response.body().string())));
    NodeList nodelist = document.getElementsByTagName("result");
    return nodelist.item(0).getFirstChild().getNodeValue();
  }
}
