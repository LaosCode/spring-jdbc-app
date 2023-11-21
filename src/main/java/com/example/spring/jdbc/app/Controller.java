package com.example.spring.jdbc.app;

import com.example.spring.jdbc.app.model.Student;
import com.example.spring.jdbc.app.service.CourseService;
import com.example.spring.jdbc.app.service.GroupService;
import com.example.spring.jdbc.app.service.StudentService;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class Controller {

    private final GroupService groupService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final View view;

    private final Logger logger = LogManager.getLogger(Controller.class);

    public void getAllGroupWithLessOrEqualStudentsCommand() {
        view.printMsg("Type number of students:");
        int studentNumber = view.requestInt();
        logger.info("Finding groups with * or less students in group : {}", studentNumber);
        groupService.findAllHaveCertainAmountOfStudents(studentNumber)
                .forEach(System.out::println);
    }

    public void getStudentByCourseAndName() {
        view.printMsg("Type course name:");
        String courseName = view.requestString();
        view.printMsg("Type student name:");
        String studentName = view.requestString();
        logger.info("Getting students with student name: {}, Course Name: {}", studentName, courseName);
        studentService.findAllStudentsByCourseAndByName(courseName, studentName).get()
                .forEach(System.out::println);
    }

    public void createNewStudent() {
        view.printMsg("Type student group id:");
        int groupId = view.requestInt();
        view.printMsg("Type student first name:");
        String firstName = view.requestString();
        view.printMsg("Type student last name:");
        String lastName = view.requestString();
        logger.info("Creating new students with group ID: {}, First name: {}, Last Name: {}", groupId, firstName, lastName);
        studentService.add(new Student(groupId, firstName, lastName));
    }

    public void deleteStudent() {
        view.printMsg("Type student id:");
        int studentId = view.requestInt();
        logger.info("Deleting student with student ID: {}", studentId);
        studentService.delete(studentId);
    }

    public void assignStudentToCourse() {
        view.printMsg("Type student id:");
        int studentId = view.requestInt();
        courseService.getAllCourses()
                .forEach(System.out::println);
        view.printMsg("Type course id:");
        int courseId = view.requestInt();
        logger.info("Assigning student to course student ID: {}, Course ID: {}", studentId, courseId);
        studentService.assignStudentToCourse(courseId, studentId);
    }

    public void removeStudentFromCourse() {
        view.printMsg("Type student id:");
        int studentId = view.requestInt();
        view.printMsg("Type course id to be removed:");
        int courseId = view.requestInt();
        logger.info("Removing student from course - student id: {}, Course ID: {}", studentId, courseId);
        studentService.removeStudentFromCourse(courseId, studentId);
    }
}



