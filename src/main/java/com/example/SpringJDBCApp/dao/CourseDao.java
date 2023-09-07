package com.example.SpringJDBCApp.dao;

import com.example.SpringJDBCApp.model.Course;
import com.example.SpringJDBCApp.model.Group;

import java.util.List;
import java.util.Optional;

public interface CourseDao {
    void addCourse(String courseName, String courseDescription);
    Optional<List<Course>> getAllCourses();
}
