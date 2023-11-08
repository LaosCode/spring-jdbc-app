package com.example.spring.jdbc.app;


import com.example.spring.jdbc.app.command.*;
import com.example.spring.jdbc.app.service.GeneratorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * Hello world!
 */
@Component
public class App {
    private Boolean exit = true;
    private final Controller controller;
    private final View view;
    private final GeneratorService generatorService;
    private final CommandMapHandler commandMapHandler;
    private final Logger logger = LogManager.getLogger(App.class);

    @Autowired
    public App(Controller controller, View view, GeneratorService generatorService, CommandMapHandler commandMapHandler) {
        this.controller = controller;
        this.view = view;
        this.generatorService = generatorService;
        this.commandMapHandler = commandMapHandler;
    }

    public void run() {
        logger.info("Application started");
        generatorService.initDb();
        view.printMsg("Welcome to Console Application type EXIT to quit application");


        while (exit) {
//            view.showAllActions();
            commandMapHandler.getListOfCommands().forEach(System.out::println);
            String userCommand = view.requestString();
            if (!userCommand.equals("EXIT")) {
                processUserCommand(userCommand);
            } else {
                exit = false;
            }
        }
    }

    public void processUserCommand(String command) {

        Map<String,CommandAction> commands = commandMapHandler.getCommandActionMap();
        if(commands.containsKey(command)) {
            commands.get(command).execute();
        }else{
            view.printMsg("Wrong command.");
        }
    }


}
