package org.spring.springboot.service;

import org.spring.springboot.domain.Blog;
import org.spring.springboot.domain.Comment;

import java.util.List;

/**
 * Created by Bruce Lee on 2017/5/30.
 */
public interface BlogService {

    Blog getBlogInfoById(int id);

    List<Comment> getCommentsByBlogId(int id);
}
