package com.example.spring.jdbc.app.dao.Impl;

import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.dao.mappers.GroupRowMapper;
import com.example.spring.jdbc.app.model.Group;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Testcontainers
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.example.spring.jdbc.app")
class JdbcGroupDAOTest {

    @Autowired
    private GroupDao underTestDao;
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
    public void shouldAddGroup() {
        Group groupToAdd = new Group(1, "test_group_name");
        String sqlRequest = "select * from groups";

        underTestDao.add(groupToAdd.getName());
        List<Group> groups = jdbcTemplate.query(sqlRequest, new GroupRowMapper());

        assertEquals(groupToAdd, groups.get(0));
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void findAllHaveCertainAmountOfStudents() {
        List<Group> result = underTestDao.findAllHaveCertainAmountOfStudents(2);
        List<Group> expected = List.of(new Group(1, "group1"));

        assertEquals(expected, result);
    }
}