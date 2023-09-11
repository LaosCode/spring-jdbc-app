package com.example.spring.jdbc.app.dao.Impl;

import com.example.spring.jdbc.app.dao.mappers.CourseRowMapper;
import com.example.spring.jdbc.app.model.Course;
import com.example.spring.jdbc.app.dao.CourseDao;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@AllArgsConstructor
@Repository
public class JdbcCourseDAO implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public void add(String courseName, String courseDescription) {
        jdbcTemplate.update("INSERT INTO courses(course_name, course_description) VALUES (?,?)", courseName, courseDescription);
    }

    public List<Course> getAllCourses() {
        return jdbcTemplate.query("select * from courses", new CourseRowMapper());
    }
}
