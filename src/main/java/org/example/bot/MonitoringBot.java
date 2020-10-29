package org.example.bot;

import org.example.bot.constants.BotConfig;
import org.example.bot.constants.Messages;
import org.example.bot.entities.CommandRequest;
import org.example.bot.entities.CommandResponse;
import org.example.bot.services.CommandOrchestratorService;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MonitoringBot extends TelegramLongPollingBot  {
    @Override
    public void onUpdateReceived(Update update) {
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            CommandRequest commandRequest = new CommandRequest(chatId, messageText);
            CommandResponse response = CommandOrchestratorService.getInstance().matchCommands(commandRequest);

            try {
                if (response == null || response.getResponseMessage() == null) {
                    SendMessage message = new SendMessage().
                            setChatId(chatId).
                            setText(Messages.ERROR).
                            setParseMode("html");
                    execute(message);
                } else {
//                    if (response.getFile() != null) {
//                        SendPhoto message = new SendPhoto().
//                                setChatId(chatId).
//                                setPhoto(response.getFile());
//                        execute(message);
//                    }
                    SendMessage message = new SendMessage().
                            setChatId(chatId).
                            setText(response.getResponseMessage()).
                            setParseMode("html").
                            setReplyMarkup(response.getInlineKeyboardMarkup());
                    execute(message);
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
//        else if(update.hasCallbackQuery()) {
//            try {
////                String callbackData = update.getCallbackQuery().getData();
//                String callbackData = "Sorry, but we temporarily disabled the pagination feature. \uD83D\uDE14";
//                execute(new SendMessage().
//                        setText(callbackData)
//                        .setChatId(update.getCallbackQuery().getMessage().getChatId()));
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }
    }

    @Override
    public String getBotUsername() {
        return BotConfig.EXPLORER_USER;
    }

    @Override
    public String getBotToken() {
        return BotConfig.EXPLORER_TOKEN;
    }
}
