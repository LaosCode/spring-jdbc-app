package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.CourseDao;
import com.example.spring.jdbc.app.dao.repository.CourseRepository;
import com.example.spring.jdbc.app.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {CourseService.class})
class CourseServiceTest {

    @MockBean
    CourseRepository courseRepository;

    @Autowired
    CourseService underTestService;

    @Test
    public void shouldRetrieveAllCourses() {
        underTestService.getAllCourses();

        verify(courseRepository).findAll();
    }

    @Test
    public void testShouldCreateNewCourse() {
        Course course =  new Course("Philosophy","Some course");

        underTestService.add(course);

        verify(courseRepository).save(course);
    }
}