package com.example.spring.jdbc.app.service;

import com.example.spring.jdbc.app.model.Course;
import com.example.spring.jdbc.app.model.Group;
import com.example.spring.jdbc.app.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class GeneratorService {

    private final StudentService studentService;
    private final CourseService courseService;
    private final GroupService groupService;


    public void initDb() {
        List<String> groupNames = Arrays.asList(
                "AB-45",
                "XY-78",
                "CD-23",
                "EF-56",
                "GH-12",
                "IJ-34",
                "KL-67",
                "MN-89",
                "OP-01",
                "QR-78"
        );
        groupNames.forEach(groupName -> groupService.add(new Group(groupName)));
        List<String> courses = Arrays.asList(
                "Mathematics",
                "English Language Arts",
                "Science",
                "History",
                "Foreign Languages",
                "Physical Education",
                "Social Studies",
                "Art",
                "Music",
                "Computer Science"
        );
        courses.forEach(courseName -> courseService.add(new Course(courseName,courseName)));

        List<String> firstNamesList = Arrays.asList(
                "Aiden",
                "Madison",
                "Oliver",
                "Ella",
                "Jackson",
                "Grace",
                "Caleb",
                "Lily",
                "Lucas",
                "Avery",
                "Mia",
                "Henry",
                "Sophia",
                "Sebastian",
                "Charlotte",
                "Elijah",
                "Emma",
                "Mason",
                "Aria",
                "Scarlett"
        );
        List<String> lastNameList = Arrays.asList(
                "Anderson",
                "Smith",
                "Johnson",
                "Williams",
                "Brown",
                "Davis",
                "Jones",
                "Miller",
                "Wilson",
                "Taylor",
                "Martinez",
                "Harris",
                "Jackson",
                "Clark",
                "Young",
                "Turner",
                "Walker",
                "White",
                "Hall",
                "Lewis"
        );
        for (int i = 0; i < 200; i++) {
            studentService.add(
                    new Student(
                            generateRandomInt(10) + 1,
                            firstNamesList.get(generateRandomInt(20)),
                            lastNameList.get(generateRandomInt(20))));
        }

        for (int i = 0; i < 200; i++) {
            studentService.assignStudentToCourse(generateRandomInt(10) + 1, generateRandomInt(200) + 1);
        }
    }

    private int generateRandomInt(int number) {
        return (int) ((Math.random() * number));
    }

}
