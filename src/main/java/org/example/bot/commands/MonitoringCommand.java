package org.example.bot.commands;

import com.google.gson.JsonSyntaxException;
import org.example.bot.constants.BotConfig;
import org.example.bot.entities.CommandRequest;
import org.example.bot.entities.CommandResponse;
import org.example.bot.exceptions.CommandException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

public class MonitoringCommand implements Command{
    private HttpClient client;
    private Duration duration;

    public static MonitoringCommand getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private MonitoringCommand(){
        client = HttpClient.newHttpClient();
        duration = Duration.ofMinutes(1);
    }

    @Override
    public CommandResponse execute(CommandRequest request) {
        String responseBodyString = "nothing";
        String nodeAddress = request.getRequestMessage();
        try {
            HttpResponse<?> response = executeQuery("http://" + nodeAddress + "/metrics");
            responseBodyString = response.body().toString();
        } catch (CommandException | JsonSyntaxException | NullPointerException e) {
            e.printStackTrace();
        }

        String[] strings = responseBodyString.split("\n\n");
        String result = "";
        for (String str: strings) {
            String[] metric = str.split("\n");
            String[] words = metric[1].split(" ");
            result += words[0].replace("_", " ") + ": ";
            if (words[0].equals("eth_connectivity")) {
                if (words[1].equals("1"))
                    result += "yes\n";
                else
                    if (words[1].equals("0"))
                        result += "no\n";
            } else {
                result += words[1] + "\n";
            }
        }
        return new CommandResponse(result);
    }

    private HttpResponse<?> executeQuery(String nodeAddress) throws CommandException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(nodeAddress))
                .timeout(duration)
                .headers(BotConfig.HEADERS)
                .GET()
                .build();
        HttpResponse<?> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new CommandException(e);
        }
        return response;
    }

    private static class SingletonHolder {
        private static final MonitoringCommand INSTANCE = new MonitoringCommand();
    }
}
