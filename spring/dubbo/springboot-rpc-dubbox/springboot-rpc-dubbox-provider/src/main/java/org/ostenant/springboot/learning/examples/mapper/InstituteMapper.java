package org.ostenant.springboot.learning.examples.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.ostenant.springboot.learning.examples.entities.Institute;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface InstituteMapper {

    @Delete({
            "delete from institute",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteById(Integer id);


    @Insert({
            "insert into institute (name)",
            "values (#{name,jdbcType=VARCHAR})"
    })
    @SelectKey(statement = "select last_insert_id()", keyProperty = "id", before = false, resultType = int.class)
    int save(Institute institute);


    @Select({
            "select",
            "id, name",
            "from institute",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR)
    })
    Institute findById(Integer id);


    @UpdateProvider(type = InstituteSqlProvider.class, method = "update")
    int update(Institute institute);

    @Select({
            "select",
            "id, name",
            "from institute",
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "name", property = "name", jdbcType = JdbcType.VARCHAR)
    })
    List<Institute> findAll();

    @InsertProvider(type = InstituteSqlProvider.class, method = "saveBatch")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int saveBatch(@Param("list") List<Institute> list);
}