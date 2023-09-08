package com.example.SpringJDBCApp.dao.Impl;

import com.example.SpringJDBCApp.dao.CourseDao;
import com.example.SpringJDBCApp.dao.mappers.CourseRowMapper;
import com.example.SpringJDBCApp.model.Course;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Repository
public class JdbcCourseDAO implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public void addCourse(String courseName, String courseDescription) {
        jdbcTemplate.update("INSERT INTO courses(course_name, course_description) VALUES (?,?)", courseName, courseDescription);
    }

    public Optional<List<Course>> getAllCourses() {
        return Optional.of(jdbcTemplate.query("select * from courses", new CourseRowMapper()));
    }
}
