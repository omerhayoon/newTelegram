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
    Window window;

    public UserStatistics(Window window){
        this.window=window;
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
    }

