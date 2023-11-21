package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.dao.repository.CourseRepository;
import com.example.spring.jdbc.app.dao.repository.StudentRepository;
import com.example.spring.jdbc.app.model.Group;
import com.example.spring.jdbc.app.model.Student;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {StudentService.class})
class StudentServiceTest {
    @MockBean
    StudentRepository studentRepository;

    @MockBean
    CourseRepository courseRepository;
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

//    @Test
//    public void shouldAssignStudentToCourse() {
//        int courseId = 1;
//        int studentId = 5;
//
//        underTestService.assignStudentToCourse(courseId, studentId);
//
//        verify(studentRepository).save(new Student());
//    }
//
//    @Test
//    public void shouldRemoveStudentFromCourse() {
//        int courseId = 1;
//        int studentId = 5;
//
//        underTestService.removeStudentFromCourse(courseId, studentId);
//
////        verify(studentDao).removeStudentFromCourse(courseId, studentId);
//    }
}