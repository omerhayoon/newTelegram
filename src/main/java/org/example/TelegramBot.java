package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class TelegramBot extends TelegramLongPollingBot {

    private Map<Long, Responder> responderMap;
    private List<InlineKeyboardButton> buttonList;

    //private JokesAPI jokesAPI;


    public TelegramBot() {
        this.responderMap = new HashMap<>();
        buttonList = new ArrayList<>();

    }

    public String getBotUsername() {
        return "Submittingprojectbot";
    }


    public String getBotToken() {
        return "6126755513:AAEMVgQ9W9grpNnhQYy4zhieBFrRDNqtbUo";
    }


    private void showButtons(SendMessage sendMessage, List<InlineKeyboardButton> buttons) {
        List<List<InlineKeyboardButton>> keyboard = Arrays.asList(buttons);// יצירת מטריצת כפתורים
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);// מחלקת מקלדת שמקבל את המטריצה
        sendMessage.setReplyMarkup(inlineKeyboardMarkup); // מכניס את מחלקת המקלדת עם המטריצה
    }

    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage().getFrom().getFirstName());
        long chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage();// פותח משתנה של הודעה
        Responder responder = this.responderMap.get(chatId);
        sendMessage.setChatId(chatId);// לשלוח את ההודעה לכתובת הרצוייה
        if (update.hasCallbackQuery()) {// אם בוצע לחיצה בכפתור
            String callBack = update.getCallbackQuery().getData();
            switch (callBack) {
                case ("weatherAPI") -> {
                    //responder.updateSupportStatus('W');
                    sendMessage.setText("Sure, here is a Number fact -\n" + NumbersFactAPI.NumbersFactAPI());
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("catsAPI") -> {
                    sendMessage.setText("Sure, here is a Cat fact -\n" + CatFactAPI.catFactAPI());
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("jokesAPI") -> {
                    // responder.updateSupportStatus('J');
                    sendMessage.setText("Sure, here is a Joke fact -\n" + JokesAPI.jokeAPI());
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }

                case ("ipAPI") -> {
                    //responder.updateSupportStatus('I');
                    sendMessage.setText("Sure, here is your ip -\n" + ipAPI.ipAPI());
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("factAPI") -> {
                    //responder.updateSupportStatus('C');
                    sendMessage.setText(" Great! Covid-19 Data API: ");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("Refresh") -> {
                    //System.out.println(ManagementActivities.telegramButtonList.size() + " size");
                    //System.out.println("Pressed refresh");
                    sendMessage.setText("Please press a button on Management Activities.");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }

            }

        } else {
            sendMessage.setChatId(chatId);// משתנה של ההודעה שולח לכתובת הרצוייה
            if (responder == null) { // במידה ולא דיברנו תישמור את הכתובת והתגובה
                responder = new Responder(chatId);
                this.responderMap.put(chatId, responder);
                sendMessage.setText("Hey, Welcome to my facts and jokes bot!" + "\n" + // כותב את ההודעה
                        "Please choose any subject to get a fact/jokes about!");
                showButtons(sendMessage, ManagementActivities.telegramButtonList);// מתודה קבועה שניתן להיעזר להכנת הכפתורים
            }
        }
        send(sendMessage);

    }


    private long getChatId(Update update) {
        long chatId;
        if (update.hasCallbackQuery()) { //אם הייתה לחיצה על כפתור
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatId = update.getMessage().getChatId();
        }
        System.out.println(chatId);
        return chatId;
    }


    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    //////////////////////////
    private String getRandomDogPictureUrl() {
        String apiUrl = "https://dog.ceo/api/breeds/image/random";
        String randomDogPictureUrl = "";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // Parse the JSON response to extract the picture URL
                String responseData = response.toString();
                randomDogPictureUrl = responseData.substring(responseData.indexOf("\"message\":\"") + 12, responseData.indexOf("\",\"status\":"));

            } else {
                System.out.println("Error: " + responseCode);
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return randomDogPictureUrl;

    }
}
