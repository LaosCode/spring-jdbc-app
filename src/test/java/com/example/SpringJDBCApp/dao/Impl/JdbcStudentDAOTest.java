package com.example.SpringJDBCApp.dao.Impl;

import com.example.SpringJDBCApp.dao.StudentDao;
import com.example.SpringJDBCApp.dao.mappers.StudentRowMapper;
import com.example.SpringJDBCApp.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.example.SpringJDBCApp")
class JdbcStudentDAOTest {

    @Autowired
    private StudentDao underTestDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Container
    private static PostgreSQLContainer sqlContainer =
            new PostgreSQLContainer("postgres:14.9")
                    .withDatabaseName("test")
                    .withUsername("root")
                    .withPassword("test");

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username", sqlContainer::getUsername);
        registry.add("spring.datasource.password", sqlContainer::getPassword);
    }


    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql"}
    )
    void shouldAddStudent() {

        Student studentToAdd = new Student(1, 1, "Alex", "test_student_last_name");
        String sqlRequest = "select * from students";

        jdbcTemplate.update("insert into groups (group_name) values (?)", "test_group");
        underTestDao.addStudent(studentToAdd);
        List<Student> students = jdbcTemplate.query(sqlRequest, new StudentRowMapper());

        assertEquals(studentToAdd, students.get(0));
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    void shouldDeleteStudent() {
        Student studentToDelete = new Student(1, 1, "Alex", "test_student_last_name");
        String sqlRequest = "select * from students where student_id=1";
        List<Student> expected = Collections.EMPTY_LIST;

        underTestDao.deleteStudent(studentToDelete.getStudentId());
        List<Student> result = jdbcTemplate.query(sqlRequest, new StudentRowMapper());

        assertEquals(expected,result);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    void shouldGetAllStudentsByNameAndCourse() {
        List<Student> expected = List.of(
                new Student(1, 1, "Alex", "lastname_1"),
                new Student(2, 1, "Alex", "lastname_2"));

        List<Student> result =
                underTestDao.findAllStudentsByCourseAndByName("course_2", "Alex").get();

        assertEquals(expected, result);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    void shouldAssignCourseToStudent() {
        @Data
        @AllArgsConstructor
        class CourseStudent {
            private int student_id;
            private int course_id;
        }
        String sql = "select * from students_courses where student_id=3 and course_id=3";
        List<CourseStudent> result = new ArrayList<>();
        List<CourseStudent> expected = List.of(
                new CourseStudent(3,3));


        underTestDao.assignCourseToStudentById(3,3);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            int studentId = rowSet.getInt(1);
            int courseId = rowSet.getInt(2);
            result.add(new CourseStudent(studentId,courseId));
        }
        assertEquals(expected,result);

    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    void shouldRemoveStudentFromCourse() {
        @Data
        @AllArgsConstructor
        class CourseStudent {
            private int student_id;
            private int course_id;
        }
        String sql = "select * from students_courses";

        underTestDao.removeStudentFromCourse(1, 1);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<CourseStudent> expected = List.of(new CourseStudent(1,2),
                new CourseStudent(1,3),
                new CourseStudent(2,1),
                new CourseStudent(2,2),
                new CourseStudent(3,1));

        List<CourseStudent> result = new ArrayList<>();
        while (rowSet.next()) {
            int studentId = rowSet.getInt(1);
            int courseId = rowSet.getInt(2);
            result.add(new CourseStudent(studentId,courseId));
        }

        assertEquals(expected, result);
    }
}