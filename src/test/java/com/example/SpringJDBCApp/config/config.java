package com.example.SpringJDBCApp.config;


import com.example.SpringJDBCApp.App;
import com.example.SpringJDBCApp.Controller;
import com.example.SpringJDBCApp.DbInitializer;
import com.example.SpringJDBCApp.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;

@Configuration
public class config {

//  @Bean
////  @Profile("test")
//    public CommandLineRunner commandLineRunner(ApplicationContext applicationContext){
//      return args -> {
//
//      };
//  }
//



}
