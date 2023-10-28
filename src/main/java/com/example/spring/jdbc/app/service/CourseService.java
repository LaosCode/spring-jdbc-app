package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.CourseDao;
import com.example.spring.jdbc.app.model.Course;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CourseService {

    private CourseDao courseDao;

    public List<Course> getAllCourses() {
        return courseDao.getAllCourses();
    }

    public void add(Course course) {
        courseDao.add(course);
    }
}
