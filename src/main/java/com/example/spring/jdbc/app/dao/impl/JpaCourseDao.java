package com.example.spring.jdbc.app.dao.impl;

import com.example.spring.jdbc.app.dao.CourseDao;
import com.example.spring.jdbc.app.model.Course;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@AllArgsConstructor
public class JpaCourseDao implements CourseDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void add(Course course) {
        entityManager.persist(course);
    }

    @Override
    public List<Course> getAllCourses() {
        return entityManager
                .createQuery("SELECT c from Course c", Course.class)
                .getResultList();


    }
}
