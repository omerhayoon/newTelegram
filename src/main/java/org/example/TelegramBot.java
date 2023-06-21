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
                case ("Refresh button") -> {
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                    System.out.println(ManagementActivities.telegramButtonList.size() + " size");
                    System.out.println("Pressed refresh");
                }
            }

        } else {
            sendMessage.setChatId(chatId);// משתנה של ההודעה שולח לכתובת הרצוייה
            if (responder == null) { // במידה ולא דיברנו תישמור את הכתובת והתגובה
                responder = new Responder(chatId);
                this.responderMap.put(chatId, responder);
                sendMessage.setText("Hey, Welcome to my facts and jokes bot!" + "\n" + // כותב את ההודעה
                        "Please choose any subject to get a fact/jokes about!");
                if (ManagementActivities.telegramButtonList.isEmpty()) {
                    sendMessage.setText("Please send a message after you pressed activity buttons");
                    InlineKeyboardButton refreshButton = new InlineKeyboardButton("Refresh button"); // בניית כפתור
                    refreshButton.setCallbackData("Refresh button");
                    ManagementActivities.telegramButtonList.add(refreshButton);
                }

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


}
