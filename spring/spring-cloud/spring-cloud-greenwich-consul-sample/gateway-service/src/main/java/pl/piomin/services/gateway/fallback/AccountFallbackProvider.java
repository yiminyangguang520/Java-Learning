package pl.piomin.services.gateway.fallback;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import pl.piomin.services.gateway.fallback.response.AccountFallbackResponse;

/**
 * @author litz-a
 */
public class AccountFallbackProvider implements FallbackProvider {

  @Override
  public String getRoute() {
    return "account-service";
  }

  /**
   * Provides a fallback response based on the cause of the failed execution.
   *
   * @param route The route the fallback is for
   * @param cause cause of the main method failure, may be <code>null</code>
   * @return the fallback response
   */
  @Override
  public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
    return new ClientHttpResponse() {

      @Override
      public HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
      }

      @Override
      public InputStream getBody() throws IOException {
        AccountFallbackResponse response = new AccountFallbackResponse("1.2", cause.getMessage());
        return new ByteArrayInputStream(new ObjectMapper().writeValueAsBytes(response));
      }

      @Override
      public String getStatusText() throws IOException {
        return "OK";
      }

      @Override
      public HttpStatus getStatusCode() throws IOException {
        return HttpStatus.OK;
      }

      @Override
      public int getRawStatusCode() throws IOException {
        return 200;
      }

      @Override
      public void close() {

      }

    };
  }
}
