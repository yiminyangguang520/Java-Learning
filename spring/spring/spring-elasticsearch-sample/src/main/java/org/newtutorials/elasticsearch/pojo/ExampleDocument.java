package org.newtutorials.elasticsearch.pojo;

/**
 * Created by dani on 5/7/2017.
 */
public class ExampleDocument {

  private String title;
  private String author;
  private Integer pages;

  public ExampleDocument() {
  }

  public ExampleDocument(String title, String author, Integer pages) {
    this.title = title;
    this.author = author;
    this.pages = pages;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public Integer getPages() {
    return pages;
  }

  public void setPages(Integer pages) {
    this.pages = pages;
  }

  @Override
  public String toString() {
    return "ExampleDocument{" +
        "title='" + title + '\'' +
        ", author='" + author + '\'' +
        ", pages=" + pages +
        '}';
  }
}
