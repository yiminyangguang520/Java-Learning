package com.devglan.controller;

import com.devglan.model.Question;
import com.devglan.model.QuestionCategory;
import com.devglan.model.QuestionCategoryConverter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author only2dhir
 */
@RestController
public class QuestionController {

  @RequestMapping(value = "/{type}", method = RequestMethod.GET)
  public List<Question> get(@PathVariable(value = "type") QuestionCategory category) {
    return getQuestionsByCategory(category);
  }

  private List<Question> getQuestionsByCategory(QuestionCategory category) {
    List<Question> questions = new ArrayList<>();
    Question question = new Question();
    question.setType(category);
    if (category == QuestionCategory.CSE) {
      question.setQuestion("What is Operating System.");
      question.setAnswer("This is the answer of what is os.");
    } else if (category == QuestionCategory.ECE) {
      question.setQuestion("What is a transistor.");
      question.setAnswer("This is the answer of what is transistor.");
    }
    questions.add(question);
    return questions;
  }

  @InitBinder
  public void initBinder(final WebDataBinder webdataBinder) {
    webdataBinder.registerCustomEditor(QuestionCategory.class, new QuestionCategoryConverter());
  }

}
