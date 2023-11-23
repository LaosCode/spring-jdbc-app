package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.dao.repository.CourseRepository;
import com.example.spring.jdbc.app.dao.repository.StudentRepository;
import com.example.spring.jdbc.app.model.Course;
import com.example.spring.jdbc.app.model.Group;
import com.example.spring.jdbc.app.model.Student;
import jakarta.persistence.EntityManager;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.postgresql.copy.CopyOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = {StudentService.class})
class StudentServiceTest {

    @MockBean
    CourseRepository courseRepository;

    @MockBean
    StudentRepository studentRepository;
    @Autowired
    StudentService underTestService;

    @Test
    public void shouldAddStudent() {
        Student student = Student.builder()
                .firstName("Name")
                .lastName("LastName")
                .build();
        student.setGroup(new Group("group"));
        underTestService.add(student);

        verify(studentRepository).save(student);
    }

    @Test
    public void shouldFindAllStudentsByCourseAndByName() {
        String courseName = "Course";
        String firstName = "Name";

        underTestService.findAllStudentsByCourseAndByName(courseName, firstName);

        verify(studentRepository).findAllByCoursesAndFirstName(courseName, firstName);
    }

    @Test
    public void shouldDeleteStudent() {
        int studentId = 1;

        underTestService.delete(1);

        verify(studentRepository).deleteById(studentId);
    }

    @Test
    public void shouldAssignStudentToCourse() {
        Student student = new Student(1, "test", "test");
        student.setStudentId(1);
        student.setCourses(new ArrayList<Course>());
        Course course = new Course("test", "test");
        course.setId(2);
        course.setStudents(new ArrayList<Student>());

        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(student));
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(course));
        underTestService.assignStudentToCourse(1, 2);

        verify(studentRepository, times(1)).save(Optional.of(student).get());
    }

    @Test
    public void shouldRemoveStudentFromCourse() {
        Student student = new Student(1, "test", "test");
        student.setStudentId(1);
        student.setCourses(new ArrayList<Course>());
        Course course = new Course("test", "test");
        course.setId(2);
        course.setStudents(new ArrayList<Student>());

        when(studentRepository.findById(anyInt())).thenReturn(Optional.of(student));
        when(courseRepository.findById(anyInt())).thenReturn(Optional.of(course));
        underTestService.removeStudentFromCourse(1, 2);

        verify(studentRepository, times(1)).save(Optional.of(student).get());
    }
}