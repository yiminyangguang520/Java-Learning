package org.spring.springboot.dao;

import org.apache.ibatis.annotations.Param;
import org.spring.springboot.domain.Blog;
import org.spring.springboot.domain.Comment;

/**
 * Created by litz-a on 2017/5/23.
 */
public interface CommentDao {

    /**
     * @param id
     */
    Comment selectComment(@Param("id") int id);

    /**
     * @param id
     * @return
     */
    Blog selectBlog(@Param("id") int id);
}
