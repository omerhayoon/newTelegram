package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

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
//        if(update.hasMessage() && update.getMessage().hasText()){
//            System.out.println(update.getMessage().getText());
//        }
        System.out.println(update.getMessage().getFrom().getFirstName()+": "+update.getMessage().getText());

        long chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage();
        Responder responder = this.responderMap.get(chatId);
        sendMessage.setChatId(chatId); // נותן להודעה כתובת
        if (update.hasCallbackQuery()) {// אם בוצע לחיצה בכפתור
            String callBack = update.getCallbackQuery().getData();
            switch (callBack) {
                case ("W") -> {
                    responder.updateSupportStatus('W');
                    //sendMessage.setText("Sure, here is a Number fact -\n" + NumbersFactAPI.NumbersFactAPI());
                    sendMessage.setText("Sure, here is a Number fact");
                }
                case ("C") -> {
                   // sendMessage.setText("Sure, here is a Cat fact -\n" + CatFactAPI.catFactAPI());
                    sendMessage.setText("Sure, here is a Cat fact");
                }
                case ("J") -> {
                    responder.updateSupportStatus('J');
                    //sendMessage.setText("Sure, here is a Joke fact -\n" + JokesAPI.joke());
                    sendMessage.setText("Sure, here is a Joke fact");
                }

                case ("I") -> {
                    responder.updateSupportStatus('I');
                    //sendMessage.setText("Sure, here is your ip -\n" + ipAPI.ipAPI());
                    sendMessage.setText("Sure, here is your ip");
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
                sendMessage.setText("Hey, Welcome to my facts and jokes bot!" + "\n" + // כותב את ההודעה
                        "Please choose any subject to get a fact/jokes about!");
                InlineKeyboardButton numbersButton = new InlineKeyboardButton("Numbers"); // בניית כפתור
                numbersButton.setCallbackData("W");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton fixerButton = new InlineKeyboardButton("Cats"); // בניית כפתור
                fixerButton.setCallbackData("C");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton jokeButton = new InlineKeyboardButton("Jokes"); // בניית כפתור
                jokeButton.setCallbackData("J");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton ipButton = new InlineKeyboardButton("IP"); // בניית כפתור
                ipButton.setCallbackData("I");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton covidButton = new InlineKeyboardButton("Covid-19 Data API"); // בניית כפתור
                covidButton.setCallbackData("F");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                List<InlineKeyboardButton> topRow = Arrays.asList(numbersButton, fixerButton, jokeButton, ipButton, covidButton);// בניית רשימת כפתורים והכנסה שני כפתורים
                showButtons(sendMessage, topRow);// מתודה קבועה שניתן להיעזר להכנת הכפתורים

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
        return chatId;
    }



    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }





}
