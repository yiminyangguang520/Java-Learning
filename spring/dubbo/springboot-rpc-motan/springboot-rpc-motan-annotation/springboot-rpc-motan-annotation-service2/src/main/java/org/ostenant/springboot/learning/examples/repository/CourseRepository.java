package org.ostenant.springboot.learning.examples.repository;

import org.ostenant.springboot.learning.examples.model.course.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int deleteById(String id) {
        final String deleteSQL = "DELETE FROM course WHERE id = ?";
        return jdbcTemplate.execute(deleteSQL, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(1, id);
            return psmt.executeUpdate();
        });
    }

    public int save(Course course) {
        return jdbcTemplate.execute(conn -> {
            final String insertSQL = "INSERT INTO course (id, name, description, lesson_period, total_course) VALUES (?, ?, ?, ?, ?)";

            return conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        }, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(1, course.getId());
            psmt.setString(2, course.getName());
            psmt.setDouble(4, course.getLessonPeriod());
            psmt.setString(3, course.getDescription());
            psmt.setInt(5, course.getTotalCourse());

            return psmt.executeUpdate();
        });
    }

    @Transactional(readOnly = true)
    public List<Course> findAll() {
        final String querySQL = "SELECT * FROM course";
        return jdbcTemplate.query(querySQL, new CourseRowMapper());
    }

    @Transactional(readOnly = true)
    public Course findById(String id) {
        final String querySQL = "SELECT * FROM course WHERE id = ?";
        return jdbcTemplate.queryForObject(querySQL, new CourseRowMapper(), id);
    }

    public int update(Course course) {
        return jdbcTemplate.execute(conn -> {
            final String updateSQL = "UPDATE course SET name = ?, description = ?, lesson_period = ?, total_course = ? WHERE id = ?";

            return conn.prepareStatement(updateSQL);
        }, (PreparedStatement psmt) -> {
            psmt.setString(1, course.getName());
            psmt.setString(2, course.getDescription());
            psmt.setDouble(3, course.getLessonPeriod());
            psmt.setString(5, course.getId());
            psmt.setInt(4, course.getTotalCourse());

            return psmt.executeUpdate();
        });
    }

    static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course course = new Course();

            course.setId(rs.getString("id"));
            course.setName(rs.getString("name"));
            course.setLessonPeriod(rs.getDouble("lesson_period"));
            course.setDescription(rs.getString("description"));
            course.setTotalCourse(rs.getInt("total_course"));

            return course;
        }
    }
}
