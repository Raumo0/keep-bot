package org.example.bot.commands;

import org.example.bot.constants.Messages;
import org.example.bot.entities.CommandRequest;
import org.example.bot.entities.CommandResponse;

public class HelpCommand implements Command {
    @Override
    public CommandResponse execute(CommandRequest request) {
        return new CommandResponse(Messages.HELP_COMMAND);
    }
}
