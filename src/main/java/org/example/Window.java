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


    public Window(){
        this.panel=new Panel();
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

    }

}
