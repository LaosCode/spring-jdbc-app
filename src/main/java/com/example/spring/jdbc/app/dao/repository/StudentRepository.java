package com.example.spring.jdbc.app.dao.repository;

import com.example.spring.jdbc.app.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query("select s from Student s join s.courses c where s.firstName = ?2 and c.name=?1")
    List<Student> findAllByCoursesAndFirstName(String courseName,String firstName);
}
