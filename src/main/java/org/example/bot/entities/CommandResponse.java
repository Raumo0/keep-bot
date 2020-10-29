package org.example.bot.entities;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.io.File;
import java.util.Objects;

public class CommandResponse {
//    private File file;
    private String responseMessage;
    private InlineKeyboardMarkup inlineKeyboardMarkup;

    public CommandResponse() {
    }

    public CommandResponse(String responseMessage) {
        this.responseMessage = responseMessage;
    }

//    public CommandResponse(File file, String responseMessage) {
//        this.file = file;
//        this.responseMessage = responseMessage;
//    }

    public CommandResponse(String responseMessage, InlineKeyboardMarkup inlineKeyboardMarkup) {
//        this.file = file;
        this.responseMessage = responseMessage;
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

//    public File getFile() {
//        return file;
//    }
//
//    public void setFile(File file) {
//        this.file = file;
//    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public InlineKeyboardMarkup getInlineKeyboardMarkup() {
        return inlineKeyboardMarkup;
    }

    public void setInlineKeyboardMarkup(InlineKeyboardMarkup inlineKeyboardMarkup) {
        this.inlineKeyboardMarkup = inlineKeyboardMarkup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandResponse that = (CommandResponse) o;
        return Objects.equals(responseMessage, that.responseMessage) &&
                Objects.equals(inlineKeyboardMarkup, that.inlineKeyboardMarkup);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseMessage, inlineKeyboardMarkup);
    }
}
