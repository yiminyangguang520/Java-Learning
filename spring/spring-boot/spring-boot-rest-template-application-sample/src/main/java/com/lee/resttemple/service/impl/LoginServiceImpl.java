package com.lee.resttemple.service.impl;

import com.lee.resttemple.constant.Constant;
import com.lee.resttemple.model.User;
import com.lee.resttemple.result.AccessToken;
import com.lee.resttemple.service.LoginService;
import com.lee.resttemple.utils.BasicHeaderBuilder;
import java.io.IOException;
import java.util.Collections;
import okhttp3.Call;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * @author litz-a
 */
@Service
public class LoginServiceImpl implements LoginService {

  private OkHttpClient okHttpClient = new OkHttpClient();

  @Autowired
  private RestTemplate restTemplate;

  /**
   * login
   */
  @Override
  public AccessToken login(String username, String password) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(BasicHeaderBuilder.headerKey(), BasicHeaderBuilder.headerValue());
    headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    MultiValueMap<String, String> postParameters = new LinkedMultiValueMap<>();
    postParameters.add(Constant.GRANT_TYPE, Constant.GRANT_MODE);
    postParameters.add(Constant.USERNAME, username);
    postParameters.add(Constant.PASSWORD, password);

    HttpEntity entity = new HttpEntity<>(postParameters, headers);
    ResponseEntity<AccessToken> response = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    try {
      response = restTemplate.exchange(Constant.URL, HttpMethod.POST, entity, AccessToken.class);
    } catch (RestClientException e) {
      e.printStackTrace();
    }

    if (response.getStatusCode().is2xxSuccessful()) {
      return response.getBody();
    }

    return new AccessToken();
  }

  /**
   * login
   */
  @Override
  public AccessToken login(User user) {
    String username = user.getUsername();
    String password = user.getPassword();
    return login(username, password);
  }

  /**
   * validate
   */
  @Override
  public Boolean validate(String account) throws IOException {
    return validateByRestTemplate(account);
  }

  protected Boolean validateByOkHttp(String account) throws IOException {
    HttpUrl.Builder urlBuilder = HttpUrl.parse(Constant.VALIDATE_URL_).newBuilder();
    urlBuilder.addQueryParameter("identity", account);

    String url = urlBuilder.build().toString();

    Request request = new Request.Builder()
        .url(url)
        .addHeader(BasicHeaderBuilder.headerKey(), BasicHeaderBuilder.headerValue())
        .build();
    Call call = okHttpClient.newCall(request);
    try (Response response = call.execute()) {
      if (!response.isSuccessful()) {
        return false;
      }
      return response.code() == HttpStatus.OK.value();
    }
  }

  /**
   * 采用RestTemplate方式执行请求,此种方式出异常时,无法获取状态码
   * @param account
   * @return
   */
  protected Boolean validateByRestTemplate(String account) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(BasicHeaderBuilder.headerKey(), BasicHeaderBuilder.headerValue());
    HttpEntity entity = new HttpEntity(headers);
    try {
      // 方式1: 例如  restTemplate.getForObject("http://example.com/hotels/{hotel}/rooms/{hotel2}", String.class, "42", "21")
      // ResponseEntity<String> response = restTemplate.exchange(Constant.VALIDATE_URL, HttpMethod.GET, entity, String.class, account);
      // 方式2: map中的key应与https://account.glodon.com/api/identity?identity={identity}中占位符{}中的字符串一致
      ResponseEntity<String> response = restTemplate.exchange(Constant.VALIDATE_URL, HttpMethod.GET, entity, String.class, Collections.singletonMap("identity", account));
      return response.getStatusCodeValue() == HttpStatus.OK.value();
    } catch (HttpClientErrorException ex) {
      System.out.println(ex.getStatusCode().value());
    } catch (RestClientException e) {
      System.out.println(e.getMessage());
    }
    return false;
  }

  /**
   * 采用RestTemplate方式执行请求,此种方式出异常时,无法获取状态码
   * @param account
   * @return
   */
  protected Boolean validateByRestTemplate1(String account) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(BasicHeaderBuilder.headerKey(), BasicHeaderBuilder.headerValue());

    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(Constant.VALIDATE_URL_)
        .queryParam("identity", account);

    HttpEntity entity = new HttpEntity(headers);

    try {
      ResponseEntity<String> response = restTemplate.exchange(builder.build().encode().toUri(), HttpMethod.GET, entity, String.class);
      return response.getStatusCodeValue() == HttpStatus.OK.value();
    } catch (HttpClientErrorException ex) {
      System.out.println(ex.getStatusCode().value());
    } catch (RestClientException e) {
      System.out.println(e.getMessage());
    }
    return false;
  }
}
