package com.example.spring.jdbc.app;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@AllArgsConstructor
@SpringBootApplication
public class SpringJdbcAppApplication {
    final App app;

    public static void main(String[] args) {
        SpringApplication.run(SpringJdbcAppApplication.class, args);
    }

    @Bean
    @Profile("!test")
    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext) {
        return args -> {
            app.run();
        };
    }
}
