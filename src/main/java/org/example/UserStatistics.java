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


    public UserStatistics(Window window) {
        this.window = window;

        JButton returnButton = new JButton("Return");
        returnButton.setBounds(50, 400, 100, 40);
        returnButton.setVisible(true);
        this.setLayout(null);

//        JButton show = new JButton("Show");
//        returnButton.setBounds(200, 400, 100, 40);
//        returnButton.setVisible(true);
//        this.setLayout(null);
//
//
//        JLabel mostActivity=new JLabel(mostActivity());
//        mostActivity.setBounds(200,200,150,40);
//        mostActivity.setForeground(Color.BLACK);
//        mostActivity.setVisible(true);
//        this.add(mostActivity);
//        this.setLayout(null);




        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                mostActivity();
                mostActiveUser();
                allRequests();
                window.mainPanel();
            }
        });
        this.add(returnButton);
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
    public Long mostActiveUser(){

        Map<Long, Integer> result = TelegramBot.responderMap.entrySet().stream()
                .sorted(Comparator.comparingInt(entry -> entry.getValue().getAmountActivity()))
                .collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().getAmountActivity(), (e1, e2) -> e1));
        System.out.println(result);




        //System.out.println("mostActiveUser: " + result.get(0).toString());

//        Long maxKey = TelegramBot.responderMap.entrySet().stream()
//                .max(Comparator.comparingInt(entry -> entry.getValue().getAmountActivity()))
//                .map(Map.Entry::getKey).stream().mapToLong();

        return null;
    }
//    Long maxActiveResponderId = findMaxActiveResponderId(responderMap);
//        System.out.println("המפתח של הערך הגבוה ביותר הוא: " + maxActiveResponderId);
//}
//
//    public static Long findMaxActiveResponderId(Map<Long, Integer> map) {
//        Optional<Entry<Long, Integer>> maxEntry = map.entrySet().stream()
//                .max(Map.Entry.comparingByValue());
//
//        return maxEntry.map(Entry::getKey).orElse(null);
//    }

   

    }



