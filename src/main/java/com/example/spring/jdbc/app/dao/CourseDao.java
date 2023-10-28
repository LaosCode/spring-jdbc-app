package com.example.spring.jdbc.app.dao;

import com.example.spring.jdbc.app.model.Course;

import java.util.List;

public interface CourseDao {
    void add(Course course);
    List<Course> getAllCourses();
}
