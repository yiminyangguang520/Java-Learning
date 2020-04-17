package org.spring.springboot.dao;

import org.spring.springboot.domain.Blog;
import org.spring.springboot.domain.Comment;

import java.util.List;

/**
 * Created by min on 2017/5/23.
 */
public interface BlogDao {
    Blog selectBlog(int id);

    List<Comment> selectCommentsByBlog(int blogId);
}
