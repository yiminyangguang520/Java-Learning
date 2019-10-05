package org.ostenant.springboot.learning.examples.service.impl;

import org.ostenant.springboot.learning.examples.model.secondary.Course;
import org.ostenant.springboot.learning.examples.service.CourseService;
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

@Service("jdbcCourseServiceImpl")
@Transactional
public class JdbcCourseServiceImpl implements CourseService {

    private final JdbcTemplate secondaryJdbcTemplate;

    @Autowired
    public JdbcCourseServiceImpl(@Qualifier("secondaryJdbcTemplate") JdbcTemplate secondaryJdbcTemplate) {
        this.secondaryJdbcTemplate = secondaryJdbcTemplate;
    }

    @Override
    public int deleteById(String id) {
        final String deleteSQL = "DELETE FROM course WHERE id = ?";
        return secondaryJdbcTemplate.execute(deleteSQL, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(1, id);
            return psmt.executeUpdate();
        });
    }

    @Override
    public int save(Course course) {
        return secondaryJdbcTemplate.execute(conn -> {
            final String insertSQL = "INSERT INTO course (id, name, description, lesson_period, total_course) VALUES (?, ?, ?, ?, ?)";

            return conn.prepareStatement(insertSQL, Statement.RETURN_GENERATED_KEYS);
        }, (PreparedStatementCallback<Integer>) psmt -> {
            psmt.setString(1, course.getId());
            psmt.setString(2, course.getName());
            psmt.setString(3, course.getDescription());
            psmt.setDouble(4, course.getLessonPeriod());
            psmt.setInt(5, course.getTotalCourse());

            return psmt.executeUpdate();
        });
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        final String querySQL = "SELECT * FROM course";
        return secondaryJdbcTemplate.query(querySQL, new CourseRowMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public Course findById(String id) {
        final String querySQL = "SELECT * FROM course WHERE id = ?";
        return secondaryJdbcTemplate.queryForObject(querySQL, new CourseRowMapper(), id);
    }

    @Override
    public int update(Course course) {
        return secondaryJdbcTemplate.execute(conn -> {
            final String updateSQL = "UPDATE course SET name = ?, description = ?, lesson_period = ?, total_course = ? WHERE id = ?";

            return conn.prepareStatement(updateSQL);
        }, (PreparedStatement psmt) -> {
            psmt.setString(1, course.getName());
            psmt.setString(2, course.getDescription());
            psmt.setDouble(3, course.getLessonPeriod());
            psmt.setInt(4, course.getTotalCourse());
            psmt.setString(5, course.getId());

            return psmt.executeUpdate();
        });
    }

    static class CourseRowMapper implements RowMapper<Course> {
        @Override
        public Course mapRow(ResultSet rs, int index) throws SQLException {
            Course course = new Course();

            course.setId(rs.getString("id"));
            course.setName(rs.getString("name"));
            course.setDescription(rs.getString("description"));
            course.setLessonPeriod(rs.getDouble("lesson_period"));
            course.setTotalCourse(rs.getInt("total_course"));

            return course;
        }
    }
}
