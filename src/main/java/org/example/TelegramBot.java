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

    @Override
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
//                    responder.updateSupportStatus('F');
//                    sendMessage.setText(" Great! Fixer.io API: ");
                    sendMessage.setText(deleteLater("C"));

                }
                case ("J") -> {
                    responder.updateSupportStatus('J');
                    //sendMessage.setText(" Great! JokeAPI: ");
                    sendMessage.setText(JokesAPI.returnJoke());
                    //sendMessage.setText(deleteLater("J"));
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


    public String deleteLater(String site) {
        Random random = new Random();
        String apiUrl = null;
        switch (site) {
            case ("J") -> {
                String[] jokes = {"Christmas", "Spooky", "Pun", "Dark", "Programming", "Misc", "Any"};
                apiUrl = "https://v2.jokeapi.dev/joke/" + jokes[random.nextInt(jokes.length)];
            }
            case ("C") -> apiUrl = "https://catfact.ninja/fact?max_length=140";

        }

        // Replace with the desired API URL
        String joke = "";

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                String jsonResponse = response.toString();

                // Parse the JSON response
                switch (site) {
                    case ("J") -> joke = parseJokeFromJson(jsonResponse);
                    case ("C") -> joke = cats(jsonResponse);

                }
                System.out.println(joke); // or store it in a variable or use it as needed
            } else {
                System.out.println("Failed to fetch joke. Response Code: " + responseCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return joke;

    }
    //DELETE LATER !! :


    private static String parseJokeFromJson(String jsonResponse) {
        String joke = "";
        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);
            try {
                joke = jsonObject.getString("joke");
            } catch (Exception e) {
                System.out.println("joke didnt work trying setup");
            }
            try {
                joke = jsonObject.getString("setup");
                joke += " " + jsonObject.getString("delivery");

            } catch (Exception e) {
                System.out.println("Setup worked");
            }
            return joke;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String cats(String jsonResponse) {
        String joke = "";
        try {
            // Parse the JSON response
            JSONObject jsonObject = new JSONObject(jsonResponse);
            try {
                joke = jsonObject.getString("fact");
            } catch (Exception e) {
                System.out.println("Cat fact doesn't work");
            }
            return joke;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
