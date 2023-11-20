package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class StudentService {

    private final StudentDao studentDao;

    public void add(Student student) {
        studentDao.add(student);
    }

    public List<Student> findAllStudentsByCourseAndByName(String courseName, String firstName) {
        return studentDao.findAllStudentsByCourseAndByName(courseName, firstName);
    }

    public void delete(int id) {
        studentDao.delete(id);
    }

    public void assignStudentToCourse(int courseId, int studentId) {
        studentDao.assignCourseToStudentById(courseId, studentId);
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        studentDao.removeStudentFromCourse(courseId, studentId);
    }
}
