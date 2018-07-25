package rd.exception.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class HomeController {

  @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
  public String redirect() {
    return "home";
  }

  @RequestMapping(value = "/exception1", method = RequestMethod.GET)
  public String exception1() {
    throw new NullPointerException("Throwing null pointer exception");
  }

  @ExceptionHandler(NullPointerException.class)
  public String handleException1(NullPointerException e) {
    return "error/exception1";
  }

  @RequestMapping(value = "/exception2", method = RequestMethod.GET)
  public String exception2() {
    throw new IndexOutOfBoundsException("Ajax Exception inline text");
  }

  @ExceptionHandler(IndexOutOfBoundsException.class)
  @ResponseBody
  public String handleException2(IndexOutOfBoundsException ex) {
    return ex.getMessage();
  }

  @RequestMapping(value = "/exception3", method = RequestMethod.GET)
  public String exception3() {
    throw new IllegalStateException("Exception3 with response status");
  }

  @ExceptionHandler(IllegalStateException.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "exception3")
  public ModelAndView handleException3(IllegalStateException ex, HttpServletResponse response) throws IOException {
    return new ModelAndView();
  }

}
