package com.example.springJDBCApp.dao;

import com.example.springJDBCApp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

     void add(Student student);

     void delete(int studentId);

    List<Student> findAllStudentsByCourseAndByName(String courseName, String firstName);

    void assignCourseToStudentById(int courseId, int studentId);

    void removeStudentFromCourse(int courseId, int studentId);
}
