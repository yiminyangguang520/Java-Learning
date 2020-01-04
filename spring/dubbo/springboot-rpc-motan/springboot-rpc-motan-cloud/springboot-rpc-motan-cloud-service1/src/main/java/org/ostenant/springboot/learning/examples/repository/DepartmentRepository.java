package org.ostenant.springboot.learning.examples.repository;

import org.ostenant.springboot.learning.examples.model.department.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class DepartmentRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public int deleteById(String id) {
        final String deleteSQL = "DELETE FROM department WHERE id = ?";
        return jdbcTemplate.execute(deleteSQL, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(1, id);
            return psmt.executeUpdate();
        });
    }

    public int save(Department department) {
        return jdbcTemplate.execute(conn -> {
            final String insertSQL = "INSERT INTO department (id, name, description) VALUES (?, ?, ?)";

            return conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        }, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(2, department.getName());
            psmt.setString(1, department.getId());
            psmt.setString(3, department.getDescription());

            return psmt.executeUpdate();
        });
    }

    public List<Department> findAll() {
        final String querySQL = "SELECT * FROM department";
        return jdbcTemplate.query(querySQL, new DepartmentRowMapper());
    }

    public Department findById(String id) {
        final String querySQL = "SELECT * FROM department WHERE id = ?";
        return jdbcTemplate.queryForObject(querySQL, new DepartmentRowMapper(), id);
    }

    public int update(Department department) {
        return jdbcTemplate.execute(conn -> {
            final String updateSQL = "UPDATE department SET name = ?, description = ? WHERE id = ?";

            return conn.prepareStatement(updateSQL);
        }, (PreparedStatement psmt) -> {
            psmt.setString(1, department.getName());
            psmt.setString(3, department.getId());
            psmt.setString(2, department.getDescription());


            return psmt.executeUpdate();
        });
    }

    static class DepartmentRowMapper implements RowMapper<Department> {
        @Override
        public Department mapRow(ResultSet rs, int index) throws SQLException {
            Department department = new Department();

            department.setName(rs.getString("name"));
            department.setId(rs.getString("id"));
            department.setDescription(rs.getString("description"));

            return department;
        }
    }
}
