package com.example.spring.jdbc.app.command;

import com.example.spring.jdbc.app.Controller;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@AllArgsConstructor
public class AssignStudentCommand implements CommandAction {
    private final Controller controller;
    private final String commandName = "Assign student to course";
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void execute() {
    controller.assignStudentToCourse();
    }

}