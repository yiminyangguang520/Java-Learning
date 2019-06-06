package org.lee.kit;

import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicReference;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import reactor.core.publisher.Flux;

/**
 * @author litz-a
 */
public class RequestBodyKit {
  /**
   * 这种方法在spring-boot-starter-parent 2.0.6.RELEASE + Spring Cloud Finchley.SR2 body 中生效，
   * 但是在spring-boot-starter-parent 2.1.0.RELEASE + Spring Cloud Greenwich.M3 body中不生效，总是为空
   */
  public static String resolveBodyFromRequest(ServerHttpRequest serverHttpRequest) {
    Flux<DataBuffer> body = serverHttpRequest.getBody();
    AtomicReference<String> bodyRef = new AtomicReference<>();
    body.subscribe(buffer -> {
      CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer.asByteBuffer());
      DataBufferUtils.release(buffer);
      bodyRef.set(charBuffer.toString());
    });
    return bodyRef.get();
  }
}
