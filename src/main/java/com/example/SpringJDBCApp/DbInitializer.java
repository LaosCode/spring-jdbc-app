package com.example.SpringJDBCApp;

import com.example.SpringJDBCApp.dao.CourseDao;
import com.example.SpringJDBCApp.dao.GroupDao;
import com.example.SpringJDBCApp.dao.StudentDao;
import com.example.SpringJDBCApp.model.Student;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DbInitializer {

    private final StudentDao studentDAO;
    private final GroupDao groupDao;
    private final CourseDao courseDAO;

    public void createAndInitDb() {
        initDb();
    }

    private void initDb() {
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
        groupNames.forEach(group -> groupDao.addGroup(group));

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
        courses.forEach(course -> courseDAO.addCourse(course, course + "-desccription"));

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
            studentDAO.addStudent(
                    new Student(
                            generateRandomInt(10) + 1,
                            firstNamesList.get(generateRandomInt(20)),
                            lastNameList.get(generateRandomInt(20))));
        }

        for (int i = 0; i < 200; i++) {
            studentDAO.assignCourseToStudentById(generateRandomInt(10) + 1, generateRandomInt(200) + 1);
        }
    }

    private int generateRandomInt(int number) {
        return (int) ((Math.random() * number));
    }


}


