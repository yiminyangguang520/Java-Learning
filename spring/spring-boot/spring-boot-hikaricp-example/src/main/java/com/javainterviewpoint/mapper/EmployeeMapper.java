package com.javainterviewpoint.mapper;

import com.javainterviewpoint.entity.Employee;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * @author min
 */
public class EmployeeMapper implements RowMapper {

  @Override
  public Object mapRow(ResultSet rs, int row) throws SQLException {
    Employee employee = new Employee();

    employee.setId(rs.getInt(1));
    employee.setName(rs.getString(2));
    employee.setAge(rs.getInt(3));
    employee.setDept(rs.getString(4));

    return employee;
  }
}