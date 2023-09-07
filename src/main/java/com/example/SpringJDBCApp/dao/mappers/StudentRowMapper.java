package com.example.SpringJDBCApp.dao.mappers;

import com.example.SpringJDBCApp.model.Course;
import com.example.SpringJDBCApp.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class StudentRowMapper implements RowMapper<Student> {

    @Override
    public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
        Student student = new Student();
        student.setStudentId(rs.getInt(1));
        student.setGroupId(rs.getInt(2));
        student.setFirstName(rs.getString(3));
        student.setLastName(rs.getString(4));
        return student;
    }
}
