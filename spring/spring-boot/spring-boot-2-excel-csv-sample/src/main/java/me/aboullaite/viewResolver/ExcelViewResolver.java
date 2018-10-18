package me.aboullaite.viewResolver;

import java.util.Locale;
import me.aboullaite.view.ExcelView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * @author litz-a
 * Created by aboullaite on 2017-02-24.
 */
public class ExcelViewResolver implements ViewResolver {

  @Override
  public View resolveViewName(String s, Locale locale) throws Exception {
    ExcelView view = new ExcelView();
    return view;
  }
}
