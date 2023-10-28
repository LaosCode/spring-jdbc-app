package com.example.spring.jdbc.app.dao;

import com.example.spring.jdbc.app.model.Student;

import java.util.List;

public interface StudentDao {

     void add(Student student);

     void delete(int studentId);

    List<Student> findAllStudentsByCourseAndByName(String courseName, String firstName);

    void assignCourseToStudentById(int courseId, int studentId);

    void removeStudentFromCourse(int courseId, int studentId);
}
