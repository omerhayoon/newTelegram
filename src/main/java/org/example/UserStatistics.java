package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class UserStatistics  {
    private Window window;
    public static Integer countNumberAPI = 0;
    public static Integer countCatsAPI = 0;
    public static Integer countJokesAPI = 0;
    public static Integer countIpAPI = 0;
    public static Integer countTriviaAPI = 0;
    public static Integer amountUsers = 0;


    public UserStatistics() {

        showMessage("Most Activity - " + mostActivity() + "\n" +
                mostActiveUser() + "\n" +
                "Total request " + totalRequest());
    }

    public String mostActivity() {
        Map<String, Integer> allActivity = new HashMap<>();
        allActivity.put("Number API", countNumberAPI);
        allActivity.put("Cats API", countCatsAPI);
        allActivity.put("Jokes API", countJokesAPI);
        allActivity.put("Ip API", countIpAPI);
        allActivity.put("Fact API", countTriviaAPI);
        if(allActivity.values().stream().reduce(Integer::sum).orElse(0)==0){
            return  "No activity found";
//        if (allActivity.values().stream().anyMatch(count -> count <= 0)) {
//            return "No activity found";
        } else {
            return allActivity.entrySet()
                    .stream()
                    .max(Map.Entry.comparingByValue())
                    .map(Map.Entry::getKey)
                    .orElse("No activity found");
        }

    }

    public static int totalRequest() {
        return countNumberAPI + countCatsAPI + countJokesAPI + countIpAPI + countTriviaAPI;

    }

    public String mostActiveUser() {
        Responder maxResponder = null;
        int maxActivity = Integer.MIN_VALUE;
        for (Long chatId : TelegramBot.responderMap.keySet()) {
            Responder responder = TelegramBot.responderMap.get(chatId);
            int activity = responder.getAmountActivity();

            if (activity > maxActivity) {
                maxResponder = responder;
                maxActivity = activity;
            }
        }
        if (maxResponder != null) {
            long chatId = maxResponder.getChatId();
            String userName = maxResponder.getName();
            return "Most Common chat ID: " + chatId + ", name: " + userName + " With " + maxActivity + " activities.";
        } else {
            return "No responders found.";
        }
    }


    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }

}






