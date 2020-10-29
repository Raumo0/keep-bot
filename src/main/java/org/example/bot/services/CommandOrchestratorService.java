package org.example.bot.services;

import org.example.bot.commands.Command;
import org.example.bot.commands.HelloCommand;
import org.example.bot.commands.HelpCommand;
import org.example.bot.commands.StartCommand;
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
//    private final Map<String, Command> bruteCommands;

    public static CommandOrchestratorService getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private CommandOrchestratorService() {
        HelpCommand helpCommand = new HelpCommand();
        StartCommand startCommand = new StartCommand();
        HelloCommand helloCommand = new HelloCommand();
//        LastBlockCommand lastBlockCommand = new LastBlockCommand();
        commands = new HashMap<>(){{
            put(Commands.HELP, helpCommand);
            put(Commands.HELP2, helpCommand);
            put(Commands.START, startCommand);
            put(Commands.START2, startCommand);
            put(Commands.HELLO, helloCommand);
            put(Commands.HELLO2, helloCommand);
//            put(Commands.HELLO3, helloCommand);
//            put(Commands.HELLO4, helloCommand);
//            put(Commands.LAST_BLOCK, lastBlockCommand);
//            put(Commands.LAST_BLOCK2, lastBlockCommand);
        }};
//        bruteCommands = new HashMap<>(){{
//            put(Commands.ACCOUNT_BY_ADDRESS, new AccountByAddressCommand());
//            put(Commands.BLOCK_BY_HASH, new BlockByHashCommand());
//            put(Commands.BLOCK_BY_HEIGHT, new BlockByHeightCommand());
//            put(Commands.TRANSACTION_BY_HASH, new TransactionByHashCommand());
//        }};
    }

    public CommandResponse matchCommands(CommandRequest request) {
        if (request == null || request.getRequestMessage() == null)
            return null;
        Command command = commands.get(request.getRequestMessage().toLowerCase());
        if (command == null) {
//            for (String commandName : getCommandNamesByRequest(request.getRequestMessage())) {
//                CommandResponse result = bruteCommands.get(commandName).execute(request);
//                if (result != null) //todo:change it (error code or something else)
//                    return result;
//            }
//            todo: error
            return new CommandResponse(Messages.NOT_FOUND);
        }
        return command.execute(request);
    }

//    private List<String> getCommandNamesByRequest(String request) {
//        ArrayList<String> commandNames = new ArrayList<>();
//        if (isInteger(request) || isInteger(request.substring(1)))
//            commandNames.add(Commands.BLOCK_BY_HEIGHT);
//        else {
//            commandNames.add(Commands.ACCOUNT_BY_ADDRESS);
//            commandNames.add(Commands.TRANSACTION_BY_HASH);
//            commandNames.add(Commands.BLOCK_BY_HASH);
//        }
//        return commandNames;
//    }

    private boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        }
        catch(Exception e) {
            return false;
        }
    }

    private static class SingletonHolder {
        private static final CommandOrchestratorService INSTANCE = new CommandOrchestratorService();
    }
}
