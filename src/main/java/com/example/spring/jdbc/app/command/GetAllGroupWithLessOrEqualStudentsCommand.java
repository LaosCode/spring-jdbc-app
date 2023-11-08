package com.example.spring.jdbc.app.command;

import com.example.spring.jdbc.app.Controller;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
@AllArgsConstructor
public class GetAllGroupWithLessOrEqualStudentsCommand implements CommandAction {

    private final Controller controller;
    private final String commandName = "Get all group with less or equal students";

    public String getCommandName() {
        return commandName;
    }

    @Override
    public void execute() {
    controller.getAllGroupWithLessOrEqualStudentsCommand();
    }

}
