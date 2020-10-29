package org.example.bot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
////        curl -v 161.35.168.162:8081/metrics
//        String command = "curl -v 161.35.168.162:8081/metrics";
//        String[] args2 = new String[] {"/bin/bash", "-c", command};//"ls"};
//        Process proc = new ProcessBuilder(args2).start();
//
//        BufferedReader reader =
//                new BufferedReader(new InputStreamReader(proc.getInputStream()));
//
//        String line = "";
//        while((line = reader.readLine()) != null) {
//            System.out.print(line + "\n");
//        }
//
//        proc.waitFor();
//
////        System.out.println(proc.exitValue());

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new MonitoringBot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
