package org.example;

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
    private static final int HEIGHTBUTTON = 30,WIDTHBUTTON=180,DALTE=10;

    public Panel(){
        addBackgroundPicture();
        this.setLayout(null);
//        this.setLayout(new GridLayout(4, 1, 5, 5));
//        this.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder("System administrator"),
//                BorderFactory.createEmptyBorder(10, 5, 2, 5)));
        buttonAndAction();

    }
    public void buttonAndAction(){
        JButton managementActivities = new JButton("Management Activities ");
        managementActivities.setBounds(HEIGHTBUTTON,HEIGHTBUTTON,WIDTHBUTTON,HEIGHTBUTTON);
        managementActivities.setVisible(true);
        managementActivities.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Management Activities");
            }

        });
        JButton userStatistics = new JButton("User Statistics ");
        userStatistics.setBounds(HEIGHTBUTTON,managementActivities.getY()+HEIGHTBUTTON+DALTE,WIDTHBUTTON,HEIGHTBUTTON);
        userStatistics.setVisible(true);
        userStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("User Statistics");
            }
        });

        JButton activityHistory = new JButton("Activity history ");
        activityHistory.setBounds(HEIGHTBUTTON,userStatistics.getY()+HEIGHTBUTTON+DALTE,WIDTHBUTTON,HEIGHTBUTTON);
        activityHistory.setVisible(true);
        activityHistory.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Activity history");
            }
        });
        JButton graph = new JButton("graph ");
        graph.setBounds(HEIGHTBUTTON,activityHistory.getY()+HEIGHTBUTTON+DALTE,WIDTHBUTTON,HEIGHTBUTTON);
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
            background=ImageIO.read(new File("C:\\Users\\עומר\\IdeaProjects\\telegramAPI\\src\\main\\java\\org\\example\\telegram image.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(background,0,0,getWidth(),getHeight(),this);
    }


}
