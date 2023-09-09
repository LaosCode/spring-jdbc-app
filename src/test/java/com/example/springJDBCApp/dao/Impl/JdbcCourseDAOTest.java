package com.example.springJDBCApp.dao.Impl;

import com.example.springJDBCApp.dao.CourseDao;
import com.example.springJDBCApp.dao.mappers.CourseRowMapper;
import com.example.springJDBCApp.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Testcontainers
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.example.springJDBCApp")
class JdbcCourseDAOTest {

    @Autowired
    private CourseDao underTestDao;
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
    public void shouldAddCourseToTable() {
        Course courseToAdd = new Course(1, "test_course_name", "test_course_description");
        String sqlRequest = "select * from courses";

        underTestDao.add(courseToAdd.getName(), courseToAdd.getDescription());
        List<Course> courses = jdbcTemplate.query(sqlRequest, new CourseRowMapper());

        assertEquals(courseToAdd, courses.get(0));
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void shouldGetAllCoursesFromTable() {
        List<Course> expected = new ArrayList<>();
        expected.add(new Course(1, "course_1", "course_1"));
        expected.add(new Course(2, "course_2", "course_2"));
        expected.add(new Course(3, "course_3", "course_3"));

        List<Course> result = underTestDao.getAllCourses();

        assertThat(result)
                .isNotEmpty()
                .hasSize(3)
                .doesNotHaveDuplicates()
                .containsAll(expected);

    }
}