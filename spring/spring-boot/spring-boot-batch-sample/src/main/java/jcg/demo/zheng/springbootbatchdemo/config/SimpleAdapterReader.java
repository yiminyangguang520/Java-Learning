package jcg.demo.zheng.springbootbatchdemo.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author min
 */
@Component
public class SimpleAdapterReader implements InitializingBean {

  private List<String> messages = new ArrayList<>();

  @Override
  public void afterPropertiesSet() throws Exception {
    System.out.println("User bean initialized successfully...");
    loadItems();
  }

  private void loadItems() throws Exception {
    for (int i = 0; i < 20; i++) {
      messages.add("SimpleAdatperReader Message " + i);
    }
  }

  public String nextItem() {
    if (messages.size() > 0) {
      return messages.remove(0);
    } else {
      System.out.println("No more item to read");
      return null;
    }
  }

}
