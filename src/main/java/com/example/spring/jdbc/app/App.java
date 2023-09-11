package com.example.spring.jdbc.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Hello world!
 */
@Component
public class App {
    private Boolean exit = true;
    private final Controller controller;
    private final View view;
    private final DbInitializer dbInitializer;

    @Autowired
    public App(Controller controller, View view, DbInitializer dbInitializer) {
        this.controller = controller;
        this.view = view;
        this.dbInitializer = dbInitializer;
    }

    public void run() {
        dbInitializer.createAndInitDb();
        view.printMsg("Welcome to Console Application type EXIT to quit application");
        while (exit) {
            view.showAllActions();
            getUserCommand();
        }
    }

    public void getUserCommand() {
        String command = view.requestString();
        switch (command) {
            case "a":
                controller.getAllGroupWithLessOrEqualStudentsCommand();
                break;
            case "b":
                controller.getStudentByCourseAndName();
                break;
            case "c":
                controller.createNewStudent();
                break;
            case "d":
                controller.deleteStudent();
                break;
            case "e":
                controller.assignStudentToCourse();
                break;
            case "f":
                controller.removeStudentFromCourse();
                break;
            case "x":
                exit = false;
                break;
            default:
                view.printMsg("Wrong command.");
                break;
        }
        view.printMsg("Completed!");
    }


}
