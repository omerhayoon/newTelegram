package org.example;

import API.*;
import Utility.Utils;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class TelegramBot extends TelegramLongPollingBot {

    public static Map<Long, Responder> responderMap = Collections.synchronizedMap(new HashMap<>());

    private List<InlineKeyboardButton> buttonList = Collections.synchronizedList(new ArrayList<>());

    public static Queue<String> activityHistory = new ConcurrentLinkedQueue<>();
    private final int MAX_SIZE = 10;



    public TelegramBot() {
        this.responderMap = new HashMap<>();
        buttonList = new ArrayList<>();
        activityHistory = new LinkedList<>();

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
        long chatId = getChatId(update);
        String name = getName(update);
        activityHistory.forEach(System.out::println);

        SendMessage sendMessage = new SendMessage();// פותח משתנה של הודעה
        Responder responder = this.responderMap.get(chatId);
        sendMessage.setChatId(chatId);// לשלוח את ההודעה לכתובת הרצוייה
        if (update.hasCallbackQuery()) {// אם בוצע לחיצה בכפתור
            String callBack = update.getCallbackQuery().getData();
            switch (callBack) {
                case ("NumberFact") -> {
                    UserStatistics.countNumberAPI++;
                    System.out.println();
                    this.responderMap.get(chatId).updateAmountActivity();
                    sendMessage.setText("Sure, here is a number fact -\n" + NumbersFactAPI.NumbersFactAPI());
                    updateHistory(chatId,this.responderMap.get(chatId).getName(),"NumberFact");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("catsAPI") -> {
                    UserStatistics.countCatsAPI++;
                    this.responderMap.get(chatId).updateAmountActivity();
                    sendMessage.setText("Sure, here is a Cat fact -\n" + CatFactAPI.catFactAPI());
                    updateHistory(chatId,this.responderMap.get(chatId).getName(),"catsAPI");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("jokesAPI") -> {
                    UserStatistics.countJokesAPI++;
                    this.responderMap.get(chatId).updateAmountActivity();
                    sendMessage.setText("Sure, here is a Joke -\n" + JokesAPI.jokeAPI());
                    updateHistory(chatId,this.responderMap.get(chatId).getName(),"jokesAPI");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }

                case ("ipAPI") -> {
                    UserStatistics.countIpAPI++;
                    this.responderMap.get(chatId).updateAmountActivity();
                    sendMessage.setText("Sure, here is your ip -\n" + ipAPI.ipAPI());
                    updateHistory(chatId,this.responderMap.get(chatId).getName(),"ipAPI");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("Trivia") -> {
                    UserStatistics.countTriviaAPI++;
                    this.responderMap.get(chatId).updateAmountActivity();
                    sendMessage.setText("Great! here is a random trivia question: \n" + TriviaAPI.TriviaAPI());
                    updateHistory(chatId,this.responderMap.get(chatId).getName(),"Trivia");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }
                case ("Refresh") -> {
                    sendMessage.setText("Please press a button on Management Activities.");
                    showButtons(sendMessage, ManagementActivities.telegramButtonList);
                }

            }

        } else {
            sendMessage.setChatId(chatId);// משתנה של ההודעה שולח לכתובת הרצוייה
            if (responder == null) { // במידה ולא דיברנו תשמור את הכתובת והתגובה
                responder = new Responder(chatId, name);
                this.responderMap.put(chatId, responder);
                UserStatistics.amountUsers = (responderMap.size() + 1);
                sendMessage.setText("Hey, Welcome to my bot!" + "\n" + // כותב את ההודעה
                        "Please choose any subject!");
                showButtons(sendMessage, ManagementActivities.telegramButtonList);// מתודה קבועה שניתן להיעזר להכנת הכפתורים
            }
        }
        send(sendMessage);

    }
    private void updateHistory(long chatId, String name,String callBack){
        String updated = "Name: "+name+", ID: "+ chatId+", Interaction: "+callBack+",Time: "+ Utils.getCurrentTime();
        if(activityHistory.size()>=MAX_SIZE){
            activityHistory.poll();
        }
        activityHistory.add(updated);
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

    private String getName(Update update) {
        String name;
        if (update.hasCallbackQuery()) { //אם הייתה לחיצה על כפתור
            name = update.getCallbackQuery().getFrom().getFirstName();
        } else {
            name = update.getMessage().getFrom().getFirstName();
        }
        return name;
    }


    public void send(SendMessage sendMessage) {
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

}
