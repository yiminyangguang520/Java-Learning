package com.algerfan.controller;

import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author bruce
 * @Date 18-11-4 16:45
 * @Description 测试Controller
 */
@Slf4j
@RestController
public class SyncAsyncController {

  @GetMapping("/1")
  private String get1() {
    log.info("get1 start");
    // 模拟逻辑处理5秒
    String result = createStr();
    log.info("get1 end.");
    return result;
  }

  @GetMapping("/2")
  private Mono<String> get2() {
    log.info("get2 start");
    // 模拟逻辑处理5秒
    Mono<String> result = Mono.fromSupplier(this::createStr);
    log.info("get2 end.");
    return result;
  }

  /**
   * Flux : 返回0-n个元素  以流的形式返回
   */
  @GetMapping(value = "/3", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  private Flux<String> flux() {
    Flux<String> result = Flux.fromStream(IntStream.range(1, 5).mapToObj(i -> {
        try {
          TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        return "flux data--" + i;
      }));
    return result;
  }

  private String createStr() {
    try {
      TimeUnit.SECONDS.sleep(5);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return "some string";
  }

}
