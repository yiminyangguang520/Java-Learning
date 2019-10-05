package org.lee.mybatis.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.lee.mybatis.model.Institute;
import org.springframework.stereotype.Repository;

/**
 * @author bruce
 */
@Mapper
@Repository
public interface InstituteMapper {

  @Delete({
    "delete from institute",
    "where id = #{id,jdbcType=INTEGER}"
  })
  int deleteById(Integer id);

  @Insert({
    "insert into institute (id, name)",
    "values (#{id,jdbcType=INTEGER}, #{name,jdbcType=VARCHAR})"
  })
  int save(Institute record);

  @Select({
    "select",
    "id, name",
    "from institute",
    "where id = #{id,jdbcType=INTEGER}"
  })
  @ResultMap("org.lee.mybatis.mapper.InstituteMapper.BaseResultMap")
  Institute findById(Integer id);

  int update(Institute record);

  List<Institute> findAll();

  List<Institute> findByIds(List<Integer> list);

  int deleteByIds(List<Integer> list);

  int saveBatch(List<Institute> list);

  int updateBatch(List<Institute> list);
}
