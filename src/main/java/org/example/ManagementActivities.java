package org.example;

import org.glassfish.jersey.server.internal.scanning.PackageNamesScanner;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ManagementActivities extends JPanel {

    public ManagementActivities(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(50, 400, 100, 40);
        returnButton.setVisible(true);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               window.mainPanel();
            }
        });
        this.add(returnButton);
    }

//    public void paintComponent(Graphics g) {
//        super.paintComponents(g);
//    }
}
