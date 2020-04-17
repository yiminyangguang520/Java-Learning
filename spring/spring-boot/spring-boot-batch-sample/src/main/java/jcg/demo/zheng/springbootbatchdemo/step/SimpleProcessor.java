package jcg.demo.zheng.springbootbatchdemo.step;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author min
 */
public class SimpleProcessor implements ItemProcessor<String, String> {

  @Override
  public String process(String data) throws Exception {
    System.out.println("process for " + data);
    return data.toUpperCase();
  }

}
