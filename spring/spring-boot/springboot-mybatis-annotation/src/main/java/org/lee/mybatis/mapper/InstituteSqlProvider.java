package org.lee.mybatis.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.lee.mybatis.model.Institute;
import org.lee.mybatis.utils.CoreSQL;

/**
 * @author bruce
 */
public class InstituteSqlProvider {

  public String update(Institute institute) {
    return new SQL() {
      {
        UPDATE("institute");

        if (institute.getName() != null) {
          SET("name = #{name,jdbcType=VARCHAR}");
        }
        WHERE("id = #{id,jdbcType=INTEGER}");
      }
    }.toString();
  }

  public String saveBatch(Map<String, Object> paramMap) {
    return new CoreSQL() {
      {
        INSERT_INTO("institute");

        List<Institute> list = (List<Institute>) paramMap.get("list");

        INTO_COLUMNS("name");

        List<List<String>> params = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
          List<String> columns = new ArrayList<>();
          columns.add("#{list[" + i + "].name,jdbcType=VARCHAR}");
          params.add(columns);
        }

        INTO_MULTI_VALUES(params);
      }

    }.toString();
  }
}
