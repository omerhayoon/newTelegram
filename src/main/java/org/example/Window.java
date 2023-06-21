package org.example;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Window extends JFrame {
    private static final int WIDTH = 500;
    private static final int HEIGHT = 500;
    private Image background;
    private Panel panel;
    private ManagementActivities  managementActivities;


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
    }
    public void mainPanel(){
        managementActivities.setVisible(false);
        panel.setVisible(true);
    }
    public void managementActivities(){
        panel.setVisible(false);
        this.add(managementActivities);
        this.managementActivities.setBounds(0, 0, WIDTH, HEIGHT);
        managementActivities.requestFocus();
        managementActivities.setVisible(true);

    }




}
