package com.us.example.dao;

import com.us.example.model.Message;
import com.us.example.model.MessageExample;
import java.util.List;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

/**
 * @author min
 */
public interface MessageMapper {

  /**
   * countByExample
   * @param example
   * @return
   */
  long countByExample(MessageExample example);

  /**
   * deleteByExample
   * @param example
   * @return
   */
  int deleteByExample(MessageExample example);

  /**
   * insert
   * @param record
   * @return
   */
  @Insert({
      "insert into msg (titile, content, ",
      "etra_info)",
      "values (#{titile,jdbcType=VARCHAR}, #{content,jdbcType=VARCHAR}, ",
      "#{etraInfo,jdbcType=VARCHAR})"
  })
  int insert(Message record);

  /**
   * insertSelective
   * @param record
   * @return
   */
  int insertSelective(Message record);

  /**
   * selectByExample
   * @param example
   * @return
   */
  List<Message> selectByExample(MessageExample example);

  /**
   * updateByExampleSelective
   * @param record
   * @param example
   * @return
   */
  int updateByExampleSelective(@Param("record") Message record, @Param("example") MessageExample example);

  /**
   * updateByExample
   * @param record
   * @param example
   * @return
   */
  int updateByExample(@Param("record") Message record, @Param("example") MessageExample example);
}