package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManagementActivities extends JPanel {

    public ManagementActivities(int x, int y, int width, int height) {
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(50, 400, 100, 40);
        returnButton.setVisible(true);
        returnButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeAll();
                add(new Panel());
                revalidate();
                repaint();
            }
        });
        this.add(returnButton);
    }

//    public void paintComponent(Graphics g) {
//        super.paintComponents(g);
//    }
}
