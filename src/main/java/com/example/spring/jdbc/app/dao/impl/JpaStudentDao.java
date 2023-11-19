package com.example.spring.jdbc.app.dao.impl;

import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.model.Course;
import com.example.spring.jdbc.app.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@AllArgsConstructor
public class JpaStudentDao implements StudentDao {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void add(Student student) {
        entityManager.persist(student);
    }

    @Override
    @Transactional
    public void delete(int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        entityManager.remove(student);
//                createQuery("delete from Student where studentId = : studentId")
//                .setParameter(studentId, studentId)
//                .executeUpdate();
    }

    @Override
    public List<Student> findAllStudentsByCourseAndByName(String courseName, String firstName) {

        return entityManager.createQuery("select s from Student s join s.courses c where s.firstName =: studentName and c.name=:courseName", Student.class)
                .setParameter("studentName", firstName)
                .setParameter("courseName", courseName)
                .getResultList();
    }

    @Override
    @Transactional
    public void assignCourseToStudentById(int courseId, int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        student.getCourses().add(course);
        course.getStudents().add(student);
    }

    @Override
    public void removeStudentFromCourse(int courseId, int studentId) {
        Student student = entityManager.find(Student.class, studentId);
        Course course = entityManager.find(Course.class, courseId);
        student.getCourses().remove(course);
        course.getStudents().remove(student);

    }
}
