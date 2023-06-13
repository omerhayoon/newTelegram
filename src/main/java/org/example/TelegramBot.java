package org.example;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TelegramBot extends TelegramLongPollingBot {
    private Map<Long,Responder> responderMap;
    public TelegramBot(){
        this.responderMap=new HashMap<>();
    }

    public String getBotUsername() {
        return "Submittingprojectbot";
    }


    public String getBotToken() {
        return "6126755513:AAEMVgQ9W9grpNnhQYy4zhieBFrRDNqtbUo";
    }

    @Override
    public void onUpdateReceived(Update update) {
//        System.out.println(update.getMessage().getText());
        long chatId = getChatId(update);
        SendMessage sendMessage = new SendMessage();
        Responder responder = this.responderMap.get(chatId);
        sendMessage.setChatId(chatId); // נותן להודעה כתובת
        if (update.hasCallbackQuery()) {// אם בוצע לחיצה בכפתור
            String callBack = update.getCallbackQuery().getData();
            switch (callBack){
                case("W")->{
                    responder.updateSupportStatus('W');
                    sendMessage.setText(" Great! Weather data:  ");
                }
                case ("F")->{
                    responder.updateSupportStatus('F');
                    sendMessage.setText(" Great! Fixer.io API: ");
                }
                case ("J")->{
                    responder.updateSupportStatus('J');
                    sendMessage.setText(" Great! JokeAPI: ");
                }
                case ("N")->{
                    responder.updateSupportStatus('N');
                sendMessage.setText(" Great! NewsAPI:  ");
                }
                case ("C")->{
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
                InlineKeyboardButton fixerButton = new InlineKeyboardButton("Fixer.io API"); // בניית כפתור
                fixerButton.setCallbackData("F");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton jokeButton = new InlineKeyboardButton("JokeAPI "); // בניית כפתור
                jokeButton.setCallbackData("J");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton newsButton = new InlineKeyboardButton("NewsAPI"); // בניית כפתור
                newsButton.setCallbackData("N");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                InlineKeyboardButton covidButton = new InlineKeyboardButton("Covid-19 Data API"); // בניית כפתור
                covidButton.setCallbackData("C");// גורם לפונקצייה לפעול שוב ולהחזיר את התשובה שלחץ המשתמש
                List<InlineKeyboardButton> topRow = Arrays.asList(weatherButton, fixerButton,jokeButton,newsButton,covidButton);// בניית רשימת כפתורים והכנסה שני כפתורים
                showButtons(sendMessage, topRow);// מתודה קבועה שניתן להיעזר להכנת הכפתורים

            }
        }
        send(sendMessage);

    }



    private long getChatId(Update update) {
        long chatId;
        if(update.hasCallbackQuery()){ //אם הייתה לחיצה על כפתור
            chatId= update.getCallbackQuery().getMessage().getChatId();
        }else {
            chatId=update.getMessage().getChatId();
        }
        return chatId;
    }
    private void showButtons (SendMessage sendMessage,List<InlineKeyboardButton> buttons){
        List<List<InlineKeyboardButton>>keyboard=Arrays.asList(buttons);// יצירת מטריצת כפתורים
        InlineKeyboardMarkup inlineKeyboardMarkup=new InlineKeyboardMarkup(keyboard);// מחלקת מקלדת שמקבל את המטריצה
        sendMessage.setReplyMarkup(inlineKeyboardMarkup); // מכניס את מחלקת המקלדת עם המטריצה
    }
    public void send (SendMessage sendMessage){
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
