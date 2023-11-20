package com.example.spring.jdbc.app.dao.impl;

import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.model.Course;
import com.example.spring.jdbc.app.model.Group;
import com.example.spring.jdbc.app.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
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

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.example.spring.jdbc.app")
class JpaStudentDAOTest {

    @Autowired
    private StudentDao underTestDao;

    @Autowired
    private TestEntityManager em;

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
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data_add_group.sql"}
    )
    public void shouldAddStudent() {
        Student studentToAdd = new Student(1, "Alex", "test_student_last_name");
        List<Student> expected = List.of(studentToAdd);

        underTestDao.add(studentToAdd);
        List<Student> resultList = em.getEntityManager()
                .createQuery("select s from Student s", Student.class)
                .getResultList();

        assertEquals(expected, resultList);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldDeleteStudent() {
        Student studentToDelete = new Student(2, "firstname_3", "lastname_2");
        studentToDelete.setStudentId(3);

        underTestDao.delete(studentToDelete.getStudentId());
        List<Student> result = em.getEntityManager()
                .createQuery("select s from Student s", Student.class)
                .getResultList();

        assertThat(result).doesNotContain(studentToDelete);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldGetAllStudentsByNameAndCourse() {
        Group group = new Group("group1");
        group.setId(1);
        List<Student> expected = List.of(
                new Student(1, group, "Alex", "lastname_1"),
                new Student(2, group, "Alex", "lastname_2"));

        List<Student> result =
                underTestDao.findAllStudentsByCourseAndByName("course_2", "Alex");

        assertEquals(expected, result);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldAssignCourseToStudent() {


        Group group = new Group("group2");
        group.setId(2);
        Course course = new Course("course_3", "course_3");
        course.setId(3);
        Student expected = new Student(3, group, "firstname_3", "lastname_2");
        expected.setCourses(List.of(course));

        underTestDao.assignCourseToStudentById(3, 3);
        Student result = em.find(Student.class, 3);

        assertEquals(expected, result);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldRemoveStudentFromCourse() {

        underTestDao.removeStudentFromCourse(1, 1);

        List<Course> result = em.find(Student.class, 1).getCourses();

        assertThat(result)
                .isNotEmpty()
                .hasSize(2);
    }
}