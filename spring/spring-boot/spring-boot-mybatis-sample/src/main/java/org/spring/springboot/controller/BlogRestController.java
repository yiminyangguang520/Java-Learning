package org.spring.springboot.controller;

import org.spring.springboot.domain.Blog;
import org.spring.springboot.domain.Comment;
import org.spring.springboot.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bruce Lee on 2017/5/30.
 */
@RestController
public class BlogRestController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/api/blog", method = RequestMethod.GET)
    public Blog findOneBlog(@RequestParam(value = "id", required = true) int blogId) {
        return blogService.getBlogInfoById(blogId);
    }

    @RequestMapping(value = "/api/blog/comment", method = RequestMethod.GET)
    public List<Comment> findComments(@RequestParam(value = "id", required = true) int blogId) {
        return blogService.getCommentsByBlogId(blogId);
    }
}
