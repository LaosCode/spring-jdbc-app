package com.example.spring.jdbc.app.command;

import com.example.spring.jdbc.app.Controller;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CommandMapHandler {
    private final Controller controller;

    public CommandMapHandler(Controller controller) {
        this.controller = controller;
        commands = Map.of(
                "a", new GetAllGroupWithLessOrEqualStudentsCommand(controller),
                "b", new GetStudentByCourseAndNameCommand(controller),
                "c", new AddStudentCommand(controller),
                "d", new DeleteStudentCommand(controller),
                "e", new AssignStudentCommand(controller),
                "f", new RemoveStudentFromCourseCommand(controller));
    }

    private final Map<String, CommandAction> commands;

    public Map<String, CommandAction> getCommandActionMap() {
        return commands;
    }

    public List<String> getListOfCommands() {
        List<String> commandList = new ArrayList<>();
        for (Map.Entry<String, CommandAction> command : commands.entrySet()) {
            commandList.add(command.getKey() + ". " + command.getValue().getCommandName());
        }
        return commandList.stream().sorted().collect(Collectors.toList());
    }
}
