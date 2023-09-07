package com.example.SpringJDBCApp.dao.Impl;

import com.example.SpringJDBCApp.dao.CourseDao;
import com.example.SpringJDBCApp.dao.mappers.CourseRowMapper;
import com.example.SpringJDBCApp.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JdbcCourseDAO implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcCourseDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addCourse(String courseName, String courseDescription) {
        jdbcTemplate.update("INSERT INTO courses(course_name, course_description) VALUES (?,?)", courseName, courseDescription);
    }

    public Optional<List<Course>> getAllCourses() {
        return Optional.of(jdbcTemplate.query("select * from courses", new CourseRowMapper()));
    }
}
