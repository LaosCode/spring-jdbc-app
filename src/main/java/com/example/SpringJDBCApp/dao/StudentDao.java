package com.example.SpringJDBCApp.dao;

import com.example.SpringJDBCApp.dao.mappers.StudentRowMapper;
import com.example.SpringJDBCApp.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentDao {

     void addStudent(Student student);

     void deleteStudent(int studentId);

    Optional<List<Student>> findAllStudentsByCourseAndByName(String courseName, String firstName);

    void assignCourseToStudentById(int courseId, int studentId);

    void removeStudentFromCourse(int courseId, int studentId);
}
