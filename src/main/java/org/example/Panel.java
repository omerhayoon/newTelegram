package org.example;

import org.glassfish.grizzly.http.server.Constants;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Panel extends JPanel {
    private Image background;
    private Window  window;
    private static final int HEIGHT_BUTTON = 30,WIDTH_BUTTON=180,DELTA=10,X_LINE=10,Y_LINE=440,WIDTH_LINE=480,HEIGHT_LINE=20;

    public Panel(Window window){
        addBackgroundPicture();
        this.window=window;
        this.setLayout(null);
        addByLine();
        buttonAndAction();
        //managementActivities=new ManagementActivities(0,0,500,500,Panel.this);

    }
//    public void returnMainPanel(){
//        removeAll();
//
//    }
//    public  void addManagementActivities(){
//        this.setVisible(false);
//        add(managementActivities);
//        managementActivities.requestFocus();
//        managementActivities.setVisible(true);
//        this.setVisible(false);
//
//    }
    public void addByLine() {
        JLabel by = new JLabel("@By Avihay Navon, David Even-Haim, Omer Hayoon, Avihay Ben-Ami,Idan Zakheym AAC-CS 2023");
        by.setBounds(X_LINE, Y_LINE, WIDTH_LINE, HEIGHT_LINE);
        by.setFont(new Font("Arial", Font.BOLD, 9));
        by.setVisible(true);
        by.setForeground(Color.BLACK);
        this.add(by);
    }
    public void buttonAndAction(){
        JButton managementActivities = new JButton("Management Activities ");
        managementActivities.setBounds(HEIGHT_BUTTON,HEIGHT_BUTTON,WIDTH_BUTTON,HEIGHT_BUTTON);
        managementActivities.setVisible(true);
        managementActivities.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Management Activities");
                //addManagementActivities();
                window.managementActivities();
            }

        });
        JButton userStatistics = new JButton("User Statistics ");
        userStatistics.setBounds(HEIGHT_BUTTON,managementActivities.getY()+HEIGHT_BUTTON+DELTA,WIDTH_BUTTON,HEIGHT_BUTTON);
        userStatistics.setVisible(true);
        userStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("User Statistics");
            }
        });

        JButton activityHistory = new JButton("Activity history ");
        activityHistory.setBounds(HEIGHT_BUTTON,userStatistics.getY()+HEIGHT_BUTTON+DELTA,WIDTH_BUTTON,HEIGHT_BUTTON);
        activityHistory.setVisible(true);
        activityHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Activity history");
            }
        });
        JButton graph = new JButton("graph ");
        graph.setBounds(HEIGHT_BUTTON,activityHistory.getY()+HEIGHT_BUTTON+DELTA,WIDTH_BUTTON,HEIGHT_BUTTON);
        graph.setVisible(true);
        graph.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(" graph");
            }
        });
        this.add(managementActivities);
        this.add(userStatistics);
        this.add(activityHistory);
        this.add(graph);
    }
    public void addBackgroundPicture() {
        try {
            background=ImageIO.read(new File("src/main/java/org/example/telegram image.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(background,0,0,getWidth(),getHeight(),this);
    }


}
