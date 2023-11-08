package com.example.spring.jdbc.app.command;

import com.example.spring.jdbc.app.Controller;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class AddStudentCommand implements CommandAction {
    private final Controller controller;
    private final String commandName = "Create new student";

    public AddStudentCommand(Controller controller) {
        this.controller = controller;
    }
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void execute() {
    controller.createNewStudent();
    }

}
