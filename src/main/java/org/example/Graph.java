package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import Utility.Utils;
import io.quickchart.QuickChart;


public class Graph extends JPanel {
    private Window window;
    private ImageIcon icon;

    public Graph(Window window) {
        this.window = window;
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(50, 400, 100, 40);
        returnButton.setVisible(true);
        this.setLayout(null);
        QuickChart chart = new QuickChart();
        chart.setWidth(480);
        chart.setHeight(400);
        chart.setBackgroundColor("white");
        new Thread(() -> {
            while (true) {
                setConfig(chart);
                String path = chart.getUrl();
                try {
                    icon = new ImageIcon(new URL(path));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                repaint();
                Utils.sleep();
            }
        }).start();


        returnButton.addActionListener(e -> window.mainPanel());
        this.add(returnButton);
    }

    private void setConfig(QuickChart chart) {
        String config = "{\n" +
                "  type: 'bar',\n" +
                "  data: {\n" +
                "    labels: ['Cat', 'Joke', 'Trivia', 'Number', 'ip'],\n" +
                "    datasets: [{\n" +
                "      label: 'Total Request: " + UserStatistics.totalRequest() + " Current time is " + Utils.getCurrentTime() + "',\n" +
                "      data: [" + UserStatistics.countCatsAPI + ", " + UserStatistics.countJokesAPI + ", " + UserStatistics.countTriviaAPI + ", " + UserStatistics.countNumberAPI + ", " + UserStatistics.countIpAPI + "]\n" +
                "    }]\n" +
                "  }\n" +
                "}";

        chart.setConfig(config);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(icon.getImage(), 0, 0, this);
    }

}

