package org.example.bot.services;

import org.example.bot.commands.*;
import org.example.bot.constants.Commands;
import org.example.bot.constants.Messages;
import org.example.bot.entities.CommandRequest;
import org.example.bot.entities.CommandResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommandOrchestratorService {
    private final Map<String, Command> commands;
    private final Map<String, Command> bruteCommands;

    public static CommandOrchestratorService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CommandOrchestratorService() {
        HelpCommand helpCommand = new HelpCommand();
        StartCommand startCommand = new StartCommand();
        HelloCommand helloCommand = new HelloCommand();
        MonitoringCommand monitoringCommand = MonitoringCommand.getInstance();
        commands = new HashMap<>(){{
            put(Commands.HELP, helpCommand);
            put(Commands.HELP2, helpCommand);
            put(Commands.START, startCommand);
            put(Commands.START2, startCommand);
            put(Commands.HELLO, helloCommand);
            put(Commands.HELLO2, helloCommand);
        }};
        bruteCommands = new HashMap<>(){{
            put(Commands.METRICS2, monitoringCommand);
        }};
    }

    public CommandResponse matchCommands(CommandRequest request) {
        if (request == null || request.getRequestMessage() == null)
            return null;
        Command command = commands.get(request.getRequestMessage().toLowerCase());
        if (command == null) {
            for (String commandName : getCommandNamesByRequest(request.getRequestMessage())) {
                CommandResponse result = bruteCommands.get(commandName).execute(request);
                if (result != null) //todo:change it (error code or something else)
                    return result;
            }
//            todo: error
            return new CommandResponse(Messages.NOT_FOUND);
        }
        return command.execute(request);
    }

    private List<String> getCommandNamesByRequest(String request) {
        ArrayList<String> commandNames = new ArrayList<>();
        if (isCorrectUrl(request))
            commandNames.add(Commands.METRICS2);
        return commandNames;
    }

    private boolean isCorrectUrl(String request) {
        return true; //stub
    }

    private static class SingletonHolder {
        private static final CommandOrchestratorService INSTANCE = new CommandOrchestratorService();
    }
}
