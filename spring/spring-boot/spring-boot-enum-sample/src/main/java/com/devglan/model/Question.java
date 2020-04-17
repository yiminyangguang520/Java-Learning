package com.devglan.model;

/**
 * @author min
 */
public class Question {

  private QuestionCategory type;
  private String question;
  private String answer;

  public QuestionCategory getType() {
    return type;
  }

  public void setType(QuestionCategory type) {
    this.type = type;
  }

  public String getQuestion() {
    return question;
  }

  public void setQuestion(String question) {
    this.question = question;
  }

  public String getAnswer() {
    return answer;
  }

  public void setAnswer(String answer) {
    this.answer = answer;
  }

}
