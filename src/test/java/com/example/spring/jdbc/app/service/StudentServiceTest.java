package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.model.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {StudentService.class})
class StudentServiceTest {

    @MockBean
    StudentDao studentDao;

    @Autowired
    StudentService underTestService;

    @Test
    public void shouldAddStudent() {
        Student student = Student.builder()
                .firstName("Name")
                .lastName("LastName")
                .groupId(1)
                .build();

        underTestService.add(student);

        verify(studentDao).add(student);
    }

    @Test
    public void shouldFindAllStudentsByCourseAndByName() {
        String courseName = "Course";
        String firstName = "Name";

        underTestService.findAllStudentsByCourseAndByName(courseName, firstName);

        verify(studentDao).findAllStudentsByCourseAndByName(courseName, firstName);
    }

    @Test
    public void shouldDeleteStudent() {
        int studentId = 1;

        underTestService.delete(1);

        verify(studentDao).delete(studentId);
    }

    @Test
    public void shouldAssignStudentToCourse() {
        int courseId = 1;
        int studentId = 5;

        underTestService.assignStudentToCourse(courseId, studentId);

        verify(studentDao).assignCourseToStudentById(courseId, studentId);
    }

    @Test
    public void shouldRemoveStudentFromCourse() {
        int courseId = 1;
        int studentId = 5;

        underTestService.removeStudentFromCourse(courseId, studentId);

        verify(studentDao).removeStudentFromCourse(courseId, studentId);
    }
}