package me.aboullaite.viewResolver;

import java.util.Locale;
import me.aboullaite.view.PdfView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

/**
 * Created by aboullaite on 2017-02-25.
 */
public class PdfViewResolver implements ViewResolver {

  @Override
  public View resolveViewName(String s, Locale locale) throws Exception {
    PdfView view = new PdfView();
    return view;
  }
}
