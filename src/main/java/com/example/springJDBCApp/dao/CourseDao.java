package com.example.springJDBCApp.dao;

import com.example.springJDBCApp.model.Course;

import java.util.List;

public interface CourseDao {
    void add(String courseName, String courseDescription);
    List<Course> getAllCourses();
}
