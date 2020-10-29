package org.example.bot.commands;

import org.example.bot.entities.CommandRequest;
import org.example.bot.entities.CommandResponse;

public interface Command {
    CommandResponse execute(CommandRequest key);
}
