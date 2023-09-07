package com.example.SpringJDBCApp.dao.Impl;

import com.example.SpringJDBCApp.dao.StudentDao;
import com.example.SpringJDBCApp.dao.mappers.StudentRowMapper;
import com.example.SpringJDBCApp.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Component
public class JdbcStudentDAO implements StudentDao {
    private final JdbcTemplate jdbcTemplate;

    public void addStudent(Student student) {
        jdbcTemplate.update("INSERT into students (group_id, first_name, last_name) values (?,?,?)"
                , student.getGroupId()
                , student.getFirstName()
                , student.getLastName());
    }

    public void deleteStudent(int studentId) {
        jdbcTemplate.update("DELETE FROM students where student_id=?",studentId);
    }

    public Optional<List<Student>> findAllStudentsByCourseAndByName(String courseName, String firstName) {
        return Optional.of(jdbcTemplate.query("select sc.student_id,group_id,first_name,last_name from students join students_courses sc on students.student_id = sc.student_id" +
                " join courses c on c.course_id = sc.course_id\n" +
                "where course_name=? and first_name = ?", new StudentRowMapper(),courseName,firstName));
    }

    public void assignCourseToStudentById(int courseId, int studentId) {
        jdbcTemplate.update("insert into students_courses (student_id, course_id) values (?,?)", studentId, courseId);
    }

    public void removeStudentFromCourse(int courseId, int studentId) {
        jdbcTemplate.update("delete from students_courses where student_id=? and course_id=?",studentId,courseId);
    }
}