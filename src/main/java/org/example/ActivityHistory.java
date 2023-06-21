package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActivityHistory extends JPanel {
    private Window window;
    public ActivityHistory(Window window){
        this.window=window;
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(50, 400, 100, 40);
        returnButton.setVisible(true);
        this.setLayout(null);

        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                window.mainPanel();
            }
        });
        this.add(returnButton);
    }
}


