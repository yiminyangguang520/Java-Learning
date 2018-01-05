package io.ken.springboot.course.dao;

import io.ken.springboot.course.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author litz-a
 */
@Mapper
public interface UserMapper {

    /**
     * 根据ID查询用户
     * @param id
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User queryById(@Param("id") int id);

    /**
     * 查询所有用户
     * @return
     */
    @Select("SELECT * FROM user")
    List<User> queryAll();

    /**
     * 添加用户
     * @param user
     * @return
     */
    @Insert({"INSERT INTO user(name,age,hobby) VALUES(#{name},#{age},#{hobby})"})
    int add(User user);

    /**
     * 根据ID删除用户
     * @param id
     * @return
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int delById(int id);

    /**
     * 根据ID更新用户
     * @param user
     * @return
     */
    @Update("UPDATE user SET name=#{name},age=#{age},hobby=#{hobby} WHERE id = #{id}")
    int updateById(User user);
}
