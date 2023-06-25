package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserStatistics extends JPanel {
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
       Map<String,Integer>allActivity=new HashMap<>();
        allActivity.put("Number API",countNumberAPI);
        allActivity.put("Cats API",countCatsAPI);
        allActivity.put("Jokes API",countJokesAPI);
        allActivity.put("Ip API",countIpAPI);
        allActivity.put("Fact API", countTriviaAPI);
        System.out.println(allActivity);

        String result=allActivity.entrySet().stream().max(Map.Entry.comparingByValue()).toString();

        System.out.println(result);
        return result;
        
    }
    public static int allRequests(){
        //System.out.println("allRequests: "+ (countNumberAPI + countCatsAPI + countJokesAPI + countIpAPI + countTriviaAPI));
        return countNumberAPI + countCatsAPI + countJokesAPI + countIpAPI + countTriviaAPI;

    }

    public String mostActiveUser() {
        Map<Long, Integer> result = TelegramBot.responderMap.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getAmountActivity()))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getAmountActivity(), (e1, e2) -> e1));

        Long userId = result.keySet().stream().findFirst().orElse(null);
        Integer activity = result.values().stream().findFirst().orElse(null);
        String userName = null;
        if (userId != null) {
            Responder responder = TelegramBot.responderMap.get(userId);
            if (responder != null) {
                userName = responder.getName();
            }
        }

        return "The id with the most activity - " + userId + ", name "+ userName +" With " + activity + " activities";
    }


    public static void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}






