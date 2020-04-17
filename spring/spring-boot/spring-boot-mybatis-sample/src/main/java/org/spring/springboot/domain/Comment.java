package org.spring.springboot.domain;


/**
 * Created by min on 2017/5/23.
 */

import java.util.Date;

public class Comment {
    private int id;
    private String content;
    private Date commentDate = new Date();

    private Blog blog;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", commentDate=" + commentDate +
                ", blog=" + blog +
                '}';
    }
}
