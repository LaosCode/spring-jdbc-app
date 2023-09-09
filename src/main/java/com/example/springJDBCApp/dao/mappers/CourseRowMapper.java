package com.example.springJDBCApp.dao.mappers;

import com.example.springJDBCApp.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt(1));
        course.setName(rs.getString(2));
        course.setDescription(rs.getString(3));
        return course;
    }
}
