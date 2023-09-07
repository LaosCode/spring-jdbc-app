package com.example.SpringJDBCApp.dao.Impl;

import com.example.SpringJDBCApp.App;
import com.example.SpringJDBCApp.dao.GroupDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;


import static org.junit.jupiter.api.Assertions.*;
@JdbcTest
@ActiveProfiles("test")
@Testcontainers
class JdbcCourseDAOTest {

    @Autowired
    private GroupDao groupDao;


    @Container
    private static PostgreSQLContainer sqlContainer =
            new PostgreSQLContainer("postgres:14.9")
                    .withDatabaseName("test")
                    .withUsername("root")
                    .withPassword("test");

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.url",sqlContainer::getJdbcUrl);
        registry.add("spring.datasource.username",sqlContainer::getUsername);
        registry.add("spring.datasource.password",sqlContainer::getPassword);
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void addCourse() {
    groupDao.addGroup("test_group");
    }
//
//    @Test
//    void getAllCourses() {
//    }
}