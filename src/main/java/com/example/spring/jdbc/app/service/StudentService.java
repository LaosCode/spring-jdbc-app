package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.dao.repository.CourseRepository;
import com.example.spring.jdbc.app.dao.repository.StudentRepository;
import com.example.spring.jdbc.app.model.Course;
import com.example.spring.jdbc.app.model.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public void add(Student student) {
        studentRepository.save(student);
    }

    public Optional<List<Student>> findAllStudentsByCourseAndByName(String courseName, String firstName) {
        return studentRepository.findAllByCoursesAndFirstName(courseName, firstName);
    }

    public void delete(int id) {
        studentRepository.deleteById(id);
    }

    @Transactional
    public void assignStudentToCourse(int courseId, int studentId) {
        Student studentToBeUpdated = studentRepository.getReferenceById(studentId);
        List<Course> courses = studentToBeUpdated.getCourses();

        Course courseToAdd = courseRepository.getReferenceById(courseId);
        courses.add(courseToAdd);
        studentToBeUpdated.setCourses(courses);
        courseToAdd.getStudents().add(studentToBeUpdated);
    }

    @Transactional
    public void removeStudentFromCourse(int courseId, int studentId) {
        Student studentToBeUpdated = studentRepository.findById(studentId).get();
        Course courseToBeUpdated = courseRepository.findById(courseId).get();
        List<Course> courses = studentToBeUpdated.getCourses();
        List<Student> students = courseToBeUpdated.getStudents();
        courses.remove(courseToBeUpdated);
        students.remove(studentToBeUpdated);
    }
}
