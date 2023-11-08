package com.example.spring.jdbc.app.command;

import com.example.spring.jdbc.app.Controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
public class DeleteStudentCommand implements CommandAction {

    private final Controller controller;
    private final String commandName = "Delete student command";

    public String getCommandName() {
        return commandName;
    }

    @Override
    public void execute() {
    controller.deleteStudent();
    }

}
