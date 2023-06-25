package org.example;

import javax.swing.*;
import java.awt.*;


public class Window extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private Image background;
    private Panel panel;
    private ManagementActivities  managementActivities;
    private UserStatistics userStatistics;
    private ActivityHistory activityHistory;
    private Graph graph;


    public Window(){
        this.panel=new Panel(this);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setSize(WIDTH, HEIGHT);
        this.setVisible(true);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setTitle("TelegramAPI");
        this.add(panel);
        this.panel.setBounds(0, 0, WIDTH, HEIGHT);
        panel.requestFocus();
        this.managementActivities=new ManagementActivities(0,0,500,500,this);
        this.activityHistory=new ActivityHistory(this);
        this.graph=new Graph(this);
    }
    public void mainPanel(){
        managementActivities.setVisible(false);
        activityHistory.setVisible(false);
        graph.setVisible(false);
        panel.setVisible(true);
    }
    public void managementActivities(){
        panel.setVisible(false);
        this.add(managementActivities);
        this.managementActivities.setBounds(0, 0, WIDTH, HEIGHT);
        managementActivities.requestFocus();
        managementActivities.setVisible(true);


    }
    public void userStatistic(){
        this.userStatistics = new UserStatistics();
    }
    public void activityHistory(){
        panel.setVisible(false);
        this.add(activityHistory);
        this.activityHistory.setBounds(0,0,WIDTH,HEIGHT);
        activityHistory.requestFocus();
        activityHistory.setVisible(true);
    }
    public void graph(){
        panel.setVisible(false);
        this.add(graph);
        this.graph.setBounds(0,0,WIDTH,HEIGHT);
        graph.requestFocus();
        graph.setVisible(true);

    }




}
