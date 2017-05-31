package org.spring.springboot.service.impl;

import org.spring.springboot.dao.BlogDao;
import org.spring.springboot.domain.Blog;
import org.spring.springboot.domain.Comment;
import org.spring.springboot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Bruce Lee on 2017/5/30.
 */
@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogDao blogDao;

    @Override
    public Blog getBlogInfoById(int id) {
        return blogDao.selectBlog(id);
    }

    @Override
    public List<Comment> getCommentsByBlogId(int id) {
        return blogDao.selectCommentsByBlog(id);
    }
}
