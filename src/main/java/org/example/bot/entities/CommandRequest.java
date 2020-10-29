package org.example.bot.entities;

import java.util.Objects;

public class CommandRequest {
    private Long chatId;
    private String requestMessage;

    public CommandRequest() {
    }

    public CommandRequest(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    public CommandRequest(Long chatId, String requestMessage) {
        this.chatId = chatId;
        this.requestMessage = requestMessage;
    }

    public Long getChatId() {
        return chatId;
    }

    public void setChatId(Long chatId) {
        this.chatId = chatId;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommandRequest that = (CommandRequest) o;
        return Objects.equals(chatId, that.chatId) &&
                Objects.equals(requestMessage, that.requestMessage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chatId, requestMessage);
    }

    @Override
    public String toString() {
        return "CommandRequest{" +
                "chatId=" + chatId +
                ", requestMessage='" + requestMessage + '\'' +
                '}';
    }
}
