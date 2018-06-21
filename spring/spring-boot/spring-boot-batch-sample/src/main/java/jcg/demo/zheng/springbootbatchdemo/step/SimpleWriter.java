package jcg.demo.zheng.springbootbatchdemo.step;

import java.util.List;
import org.springframework.batch.item.ItemWriter;

/**
 * @author litz-a
 */
public class SimpleWriter implements ItemWriter<String> {

  @Override
  public void write(List<? extends String> messages) throws Exception {
    for (String msg : messages) {
      System.out.println("Writing the data " + msg);
    }
  }

}