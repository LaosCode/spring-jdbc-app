package com.example.spring.jdbc.app.dao.impl;

import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.model.Group;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Sql(
        scripts = {"/sql/clear_tables.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
)
@Testcontainers
@ActiveProfiles("test")
@ComponentScan(basePackages = "com.example.spring.jdbc.app")
class JpaGroupDAOTest {

    @Autowired
    private GroupDao underTestDao;

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
            scripts = {"/sql/clear_tables.sql"}
    )
    public void shouldAddGroup() {
        Group groupToAdd = new Group("test_group_name");
        List<Group> expectedResult = List.of(groupToAdd);
        underTestDao.add(groupToAdd);
        List<Group> resultGroup = em.getEntityManager().
                createQuery("select g from Group g",Group.class)
                .getResultList();

        assertEquals(expectedResult, resultGroup);
    }

    @Test
    @Sql(
            scripts = {"/sql/clear_tables.sql", "/sql/samples_data.sql"}
    )
    public void findAllHaveCertainAmountOfStudents() {
        List<Group> result = underTestDao.findAllHaveCertainAmountOfStudents(2);

        Group group = new Group("group2");
        group.setId(2);
        List<Group> expected = new ArrayList<>();
        expected.add(group);

        assertThat(expected).hasSameElementsAs(result);
        }
}