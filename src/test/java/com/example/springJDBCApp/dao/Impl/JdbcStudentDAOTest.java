package com.example.springJDBCApp.dao.Impl;

import com.example.springJDBCApp.dao.StudentDao;
import com.example.springJDBCApp.dao.mappers.StudentRowMapper;
import com.example.springJDBCApp.model.Student;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.example.springJDBCApp")
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
    public void shouldAddStudent() {

        Student studentToAdd = new Student(1, 1, "Alex", "test_student_last_name");
        String sqlRequest = "select * from students";

        jdbcTemplate.update("insert into groups (group_name) values (?)", "test_group");
        underTestDao.add(studentToAdd);
        List<Student> students = jdbcTemplate.query(sqlRequest, new StudentRowMapper());

        assertEquals(studentToAdd, students.get(0));
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldDeleteStudent() {
        Student studentToDelete = new Student(1, 1, "Alex", "test_student_last_name");
        String sqlRequest = "select * from students where student_id=1";
        List<Student> expected = Collections.EMPTY_LIST;

        underTestDao.delete(studentToDelete.getStudentId());
        List<Student> result = jdbcTemplate.query(sqlRequest, new StudentRowMapper());

        assertEquals(expected, result);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldGetAllStudentsByNameAndCourse() {
        List<Student> expected = List.of(
                new Student(1, 1, "Alex", "lastname_1"),
                new Student(2, 1, "Alex", "lastname_2"));

        List<Student> result =
                underTestDao.findAllStudentsByCourseAndByName("course_2", "Alex");

        assertEquals(expected, result);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldAssignCourseToStudent() {
        @Data
        @AllArgsConstructor
        class CourseStudent {
            private int student_id;
            private int course_id;
        }
        String sql = "select * from students_courses where student_id=3 and course_id=3";
        List<CourseStudent> result = new ArrayList<>();
        List<CourseStudent> expected = List.of(
                new CourseStudent(3, 3));


        underTestDao.assignCourseToStudentById(3, 3);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        while (rowSet.next()) {
            int studentId = rowSet.getInt(1);
            int courseId = rowSet.getInt(2);
            result.add(new CourseStudent(studentId, courseId));
        }
        assertEquals(expected, result);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldRemoveStudentFromCourse() {
        @Data
        @AllArgsConstructor
        class CourseStudent {
            private int student_id;
            private int course_id;
        }
        String sql = "select * from students_courses";

        underTestDao.removeStudentFromCourse(1, 1);
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql);
        List<CourseStudent> expected = List.of(new CourseStudent(1, 2),
                new CourseStudent(1, 3),
                new CourseStudent(2, 1),
                new CourseStudent(2, 2),
                new CourseStudent(3, 1));

        List<CourseStudent> result = new ArrayList<>();
        while (rowSet.next()) {
            int studentId = rowSet.getInt(1);
            int courseId = rowSet.getInt(2);
            result.add(new CourseStudent(studentId, courseId));
        }

        assertThat(result).containsAll(expected).isNotEmpty().hasSize(5);
    }
}