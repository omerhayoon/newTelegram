package org.example;

import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import java.util.concurrent.CompletableFuture;

public class TelegramBot extends TelegramLongPollingBot {
    private Map<Long, Responder> responderMap;
    private JokesAPI jokesAPI;

    public TelegramBot() {
        this.responderMap = new HashMap<>();
    }

    public String getBotUsername() {
        return "Submittingprojectbot";
    }


    public String getBotToken() {
        return "6126755513:AAEMVgQ9W9grpNnhQYy4zhieBFrRDNqtbUo";
    }

    public CompletableFuture<Void> handleMessage(Update update) {
        return CompletableFuture.runAsync(() -> {
            long chatId = getChatId(update);
            SendMessage sendMessage = new SendMessage();
            Responder responder = this.responderMap.get(chatId);
            sendMessage.setChatId(chatId); // נותן להודעה כתובת
            if (update.hasCallbackQuery()) {// אם בוצע לחיצה בכפתור
                String callBack = update.getCallbackQuery().getData();
                switch (callBack) {
                    case ("W") -> {
                        responder.updateSupportStatus('W');
                        sendMessage.setText(" Great! Weather data:  ");
                    }
                    case ("C") -> {
                        sendMessage.setText(CatFactAPI.catFactAPI());
                    }
                    case ("J") -> {
                        responder.updateSupportStatus('J');
//                    newButton(sendMessage, update);
                        sendMessage.setText(JokesAPI.joke());
//                        InlineKeyboardButton jokeButton1 = new InlineKeyboardButton("JokeAPI1 "); // בניית כפתור
//                        jokeButton1.setCallbackData("M");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
//                        List<InlineKeyboardButton> topRow1 = Arrays.asList(jokeButton1);// בניית רשימת כפתורים והכנסה שני כפתורי
//                        if(update.getCallbackQuery().getData().equals(jokeButton1.getCallbackData())){
//                            showButtons(sendMessage, topRow1);
//
//                        }

                    }

                    case ("N") -> {
                        responder.updateSupportStatus('N');
                        sendMessage.setText(" Great! NewsAPI:  ");
                    }
                    case ("F") -> {
                        responder.updateSupportStatus('C');
                        sendMessage.setText(" Great! Covid-19 Data API: ");
                    }
                }


            } else {
                sendMessage.setChatId(chatId);// משתנה של ההודעה שולח לכתובת הרצוייה
                if (responder == null) { // במידה ולא דיברנו תישמור את הכתובת והתגובה
                    responder = new Responder(chatId);
                    this.responderMap.put(chatId, responder);
                    sendMessage.setText("What service do you want to receive? "); // כותב את ההודעה
                    InlineKeyboardButton weatherButton = new InlineKeyboardButton("Weather data"); // בניית כפתור
                    weatherButton.setCallbackData("W");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                    InlineKeyboardButton fixerButton = new InlineKeyboardButton("CatsFact API"); // בניית כפתור
                    fixerButton.setCallbackData("C");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                    InlineKeyboardButton jokeButton = new InlineKeyboardButton("JokeAPI "); // בניית כפתור
                    jokeButton.setCallbackData("J");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                    InlineKeyboardButton newsButton = new InlineKeyboardButton("NewsAPI"); // בניית כפתור
                    newsButton.setCallbackData("N");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                    InlineKeyboardButton covidButton = new InlineKeyboardButton("Covid-19 Data API"); // בניית כפתור
                    covidButton.setCallbackData("F");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                    List<InlineKeyboardButton> topRow = Arrays.asList(weatherButton, fixerButton, jokeButton, newsButton, covidButton);// בניית רשימת כפתורים והכנסה שני כפתורים
                    showButtons(sendMessage, topRow);// מתודה קבועה שניתן להיעזר להכנת הכפתורים


                }
            }
            try {
                Thread.sleep(2000);
                send(sendMessage);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }

    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage().getText());
        long chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage();
        Responder responder = this.responderMap.get(chatId);
        sendMessage.setChatId(chatId); // נותן להודעה כתובת
        if (update.hasCallbackQuery()) {// אם בוצע לחיצה בכפתור
            String callBack = update.getCallbackQuery().getData();
            switch (callBack) {
                case ("W") -> {
                    responder.updateSupportStatus('W');
                    sendMessage.setText(" Great! Weather data:  ");
                }
                case ("C") -> {
                    sendMessage.setText(CatFactAPI.catFactAPI());
                }
                case ("J") -> {
                    responder.updateSupportStatus('J');
//                    newButton(sendMessage, update);
                    sendMessage.setText(JokesAPI.joke());
                    InlineKeyboardButton jokeButton1 = new InlineKeyboardButton("JokeAPI1 "); // בניית כפתור
                    jokeButton1.setCallbackData("M");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                           this.topRow = Arrays.asList(this.jokeButton1);// בניית רשימת כפתורים והכנסה שני כפתורי
                    showButtons(sendMessage, this.topRow);

                }

                case ("N") -> {
                    responder.updateSupportStatus('N');
                    sendMessage.setText(" Great! NewsAPI:  ");
                }
                case ("F") -> {
                    responder.updateSupportStatus('C');
                    sendMessage.setText(" Great! Covid-19 Data API: ");
                }
            }


        } else {
            sendMessage.setChatId(chatId);// משתנה של ההודעה שולח לכתובת הרצוייה
            if (responder == null) { // במידה ולא דיברנו תישמור את הכתובת והתגובה
                responder = new Responder(chatId);
                this.responderMap.put(chatId, responder);
                sendMessage.setText("What service do you want to receive? "); // כותב את ההודעה
                InlineKeyboardButton weatherButton = new InlineKeyboardButton("Weather data"); // בניית כפתור
                weatherButton.setCallbackData("W");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton fixerButton = new InlineKeyboardButton("CatsFact API"); // בניית כפתור
                fixerButton.setCallbackData("C");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton jokeButton = new InlineKeyboardButton("JokeAPI "); // בניית כפתור
                jokeButton.setCallbackData("J");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton newsButton = new InlineKeyboardButton("NewsAPI"); // בניית כפתור
                newsButton.setCallbackData("N");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton covidButton = new InlineKeyboardButton("Covid-19 Data API"); // בניית כפתור
                covidButton.setCallbackData("F");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                List<InlineKeyboardButton> topRow = Arrays.asList(weatherButton, fixerButton, jokeButton, newsButton, covidButton);// בניית רשימת כפתורים והכנסה שני כפתורים
                showButtons(sendMessage, topRow);// מתודה קבועה שניתן להיעזר להכנת הכפתורים

            }
        }
        send(sendMessage);

    }

//    private void newButton(SendMessage sendMessage, Update update) {
//        sendMessage.setText("What .... ");
//        System.out.println();
//        this.topRow = Arrays.asList(this.jokeButton1);// בניית רשימת כפתורים והכנסה שני כפתורים
//        showButtons(sendMessage, this.topRow);
//
//        String callBack1 = update.getCallbackQuery().getData();
//        System.out.println(callBack1);
//        if (callBack1.equals("M")) {
//            System.out.println(callBack1);
//            JokesAPI.joke();
//        }
//    }


    private long getChatId(Update update) {
        long chatId;
        if (update.hasCallbackQuery()) { //אם הייתה לחיצה על כפתור
            chatId = update.getCallbackQuery().getMessage().getChatId();
        } else {
            chatId = update.getMessage().getChatId();
        }
        return chatId;
    }

    private void showButtons(SendMessage sendMessage, List<InlineKeyboardButton> buttons) {
        List<List<InlineKeyboardButton>> keyboard = Arrays.asList(buttons);// יצירת מטריצת כפתורים
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup(keyboard);// מחלקת מקלדת שמקבל את המטריצה
        sendMessage.setReplyMarkup(inlineKeyboardMarkup); // מכניס את מחלקת המקלדת עם המטריצה
    }

    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }



}
