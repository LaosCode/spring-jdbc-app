package com.example.SpringJDBCApp;

import com.example.SpringJDBCApp.dao.GroupDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.ConfigDataApplicationContextInitializer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ActiveProfiles("test")
@Testcontainers
class SpringJdbcAppApplicationTests {
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

	@Test
	void contextLoads() {
	groupDao.addGroup("test_name");
		System.out.println("yes");
	}

}
