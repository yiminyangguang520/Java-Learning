package jcg.demo.zheng.springbootbatchdemo.step;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author min
 */
public class SimpleReader implements ItemReader<String> {

  private String[] tenMessages = {"Message 1", "Message 2", "Message 3", "Message 4", "Message 5", "Message 6",
      "Message 7", "Message 8", "Message 9", "Message 10"};

  private int count = 0;

  @Override
  public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

    if (count < tenMessages.length) {
      return tenMessages[count++];
    } else {
      count = 0;
    }
    return null;
  }

}