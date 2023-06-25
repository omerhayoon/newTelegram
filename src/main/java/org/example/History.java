package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class History extends JPanel {
    private Window window;

    public History(Window window) {
        this.setLayout(new GridLayout(10, 1, 10, 10));
        this.window = window;
        new Thread(()->{
            while(true){
                creatingButtons();
                Utils.sleep();
            }
        }).start();
    }



    public void creatingButtons() {
        String[] list = formatActivityHistory(TelegramBot.activityHistory);
        JLabel line1 = new JLabel(list[0]);
        JLabel line2 = new JLabel(list[1]);
        JLabel line3 = new JLabel(list[2]);
        JLabel line4 = new JLabel(list[3]);
        JLabel line5 = new JLabel(list[4]);
        JLabel line6 = new JLabel(list[5]);
        JLabel line7 = new JLabel(list[6]);
        JLabel line8 = new JLabel(list[7]);
        JLabel line9 = new JLabel(list[8]);
        JLabel line10 = new JLabel(list[9]);
        this.add(line1);
        this.add(line2);
        this.add(line3);
        this.add(line4);
        this.add(line5);
        this.add(line6);
        this.add(line7);
        this.add(line8);
        this.add(line9);
        this.add(line10);
    }

    private String[] formatActivityHistory(Queue<String> activityHistory) {
        String[] formattedHistory = new String[10];
        int count = 1;
        for (String activity : activityHistory) {
            formattedHistory[count - 1] = count + ") " + activity;
            if (count >= 10) {
                break;
            }
            count++;
        }
        return formattedHistory;
    }
}
