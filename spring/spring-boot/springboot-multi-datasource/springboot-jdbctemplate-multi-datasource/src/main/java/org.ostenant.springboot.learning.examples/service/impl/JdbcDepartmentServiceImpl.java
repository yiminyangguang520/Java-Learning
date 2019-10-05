package org.ostenant.springboot.learning.examples.service.impl;

import org.ostenant.springboot.learning.examples.model.primary.Department;
import org.ostenant.springboot.learning.examples.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service("jdbcDepartmentServiceImpl")
@Transactional
public class JdbcDepartmentServiceImpl implements DepartmentService {

    private final JdbcTemplate primaryJdbcTemplate;

    @Autowired
    public JdbcDepartmentServiceImpl(@Qualifier("primaryJdbcTemplate") JdbcTemplate primaryJdbcTemplate) {
        this.primaryJdbcTemplate = primaryJdbcTemplate;
    }

    @Override
    public int deleteById(String id) {
        final String deleteSQL = "DELETE FROM department WHERE id = ?";
        return primaryJdbcTemplate.execute(deleteSQL, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(1, id);
            return psmt.executeUpdate();
        });
    }

    @Override
    public int save(Department department) {
        return primaryJdbcTemplate.execute(conn -> {
            final String insertSQL = "INSERT INTO department (id, name, description) VALUES (?, ?, ?)";

            return conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        }, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(1, department.getId());
            psmt.setString(2, department.getName());
            psmt.setString(3, department.getDescription());

            return psmt.executeUpdate();
        });
    }

    @Override
    public List<Department> findAll() {
        final String querySQL = "SELECT * FROM department";
        return primaryJdbcTemplate.query(querySQL, new DepartmentRowMapper());
    }

    @Override
    public Department findById(String id) {
        final String querySQL = "SELECT * FROM department WHERE id = ?";
        return primaryJdbcTemplate.queryForObject(querySQL, new DepartmentRowMapper(), id);
    }

    @Override
    public int update(Department department) {
        return primaryJdbcTemplate.execute(conn -> {
            final String updateSQL = "UPDATE department SET name = ?, description = ? WHERE id = ?";

            return conn.prepareStatement(updateSQL);
        }, (PreparedStatement psmt) -> {
            psmt.setString(1, department.getName());
            psmt.setString(2, department.getDescription());

            psmt.setString(3, department.getId());

            return psmt.executeUpdate();
        });
    }

    static class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int index) throws SQLException {
            Department department = new Department();

            department.setId(rs.getString("id"));
            department.setName(rs.getString("name"));
            department.setDescription(rs.getString("description"));

            return department;
        }
    }
}
