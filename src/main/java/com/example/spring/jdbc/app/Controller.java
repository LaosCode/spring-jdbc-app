package com.example.spring.jdbc.app;

import com.example.spring.jdbc.app.dao.GroupDao;
import com.example.spring.jdbc.app.dao.StudentDao;
import com.example.spring.jdbc.app.model.Student;
import com.example.spring.jdbc.app.dao.CourseDao;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Controller {
    private final CourseDao courseDao;
    private final GroupDao groupDao;
    private final StudentDao studentDao;
    private final View view;

    public void getAllGroupWithLessOrEqualStudentsCommand() {
        view.printMsg("Type number of students:");
        int studentNumber = view.requestInt();
        groupDao.findAllHaveCertainAmountOfStudents(studentNumber)
                .forEach(System.out::println);
    }

    public void getStudentByCourseAndName() {
        view.printMsg("Type course name:");
        String courseName = view.requestString();
        view.printMsg("Type student name:");
        String studentName = view.requestString();
        studentDao.findAllStudentsByCourseAndByName(courseName, studentName)
                .forEach(System.out::println);
    }

    public void createNewStudent() {
        view.printMsg("Type student group id:");
        int groupId = view.requestInt();
        view.printMsg("Type student first name:");
        String firstName = view.requestString();
        view.printMsg("Type student last name:");
        String lastName = view.requestString();
        studentDao.add(new Student(groupId, firstName, lastName));
    }

    public void deleteStudent() {
        view.printMsg("Type student id:");
        int studentId = view.requestInt();
        studentDao.delete(studentId);
    }

    public void assignStudentToCourse() {
        view.printMsg("Type student id:");
        int studentId = view.requestInt();
        courseDao.getAllCourses()
                .forEach(System.out::println);
        view.printMsg("Type course id:");
        int courseId = view.requestInt();
        studentDao.assignCourseToStudentById(courseId, studentId);
    }

    public void removeStudentFromCourse() {
        view.printMsg("Type student id:");
        int studentId = view.requestInt();
        view.printMsg("Type course id to be removed:");
        int courseId = view.requestInt();
        studentDao.removeStudentFromCourse(courseId, studentId);
    }


}



