package org.example.bot.commands;

import org.example.bot.constants.Messages;
import org.example.bot.entities.CommandRequest;
import org.example.bot.entities.CommandResponse;

public class HelloCommand implements Command {
    @Override
    public CommandResponse execute(CommandRequest request) {
        return new CommandResponse(Messages.HELLO_COMMAND);
    }
}
