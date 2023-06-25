package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ManagementActivities extends JPanel {

    private final int MAX_SELECTION_COUNT = 3;
    private int currentSelectionCount = 0;
    private Window window;
    private final int BUTTON_HEIGHT = 25;
    private final int BUTTON_WIDTH = 150;
    private final int BUTTON_X = 50;
    private final int BUTTON_Y = 40;
    BufferedImage imageIcon;
    private List<JToggleButton> buttons;
    private TelegramBot telegramBot = new TelegramBot();
    public static List<InlineKeyboardButton> telegramButtonList = new ArrayList<>();


    public ManagementActivities(int x, int y, int width, int height, Window window) {
        this.window = window;
        addBackgroundPicture();
        this.buttons = new ArrayList<>();
        InlineKeyboardButton refreshButton = new InlineKeyboardButton("Refresh"); // בניית כפתור
        refreshButton.setCallbackData("Refresh");
        telegramButtonList.add(refreshButton);
        JToggleButton ipAPIButton = createButton("ipAPI");
        JToggleButton catsAPIButton = createButton("catsAPI");
        JToggleButton jokesAPIButton = createButton("jokesAPI");
        JToggleButton numberAPIButton = createButton("NumberFact");
        JToggleButton triviaAPIButton = createButton("Trivia");
        addButtonsToPanel(ipAPIButton, catsAPIButton, jokesAPIButton, numberAPIButton, triviaAPIButton);
        buttonsBounds(ipAPIButton, catsAPIButton, jokesAPIButton, numberAPIButton, triviaAPIButton);
        this.setBounds(x, y, width, height);
        this.setLayout(null);
        JButton returnButton = new JButton("Return");
        returnButton.setBounds(BUTTON_X, 400, 100, 40);
        returnButton.setVisible(true);
        returnButton.addActionListener(e -> window.mainPanel());
        this.add(returnButton);
    }


    private void buttonsBounds(JToggleButton ipAPIButton, JToggleButton catsAPIButton,
                               JToggleButton jokesAPIButton, JToggleButton NumberFactButton, JToggleButton triviaButton) {
        ipAPIButton.setBounds(BUTTON_X, BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        catsAPIButton.setBounds(BUTTON_X, ipAPIButton.getY()+BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        jokesAPIButton.setBounds(BUTTON_X, catsAPIButton.getY()+BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        NumberFactButton.setBounds(BUTTON_X, jokesAPIButton.getY()+BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        triviaButton.setBounds(BUTTON_X, NumberFactButton.getY()+BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
    }

    private JToggleButton createButton(String name) {
        JToggleButton button = new JToggleButton(name);
        button.addActionListener(e -> {
            if (button.isSelected()) {
                if (currentSelectionCount < MAX_SELECTION_COUNT) {
                    InlineKeyboardButton numbersButton = new InlineKeyboardButton(button.getText());
                    numbersButton.setCallbackData(button.getText());
                    telegramButtonList.add(numbersButton);
                    currentSelectionCount++;
                } else {
                    button.setSelected(false);
                }
            } else {
                currentSelectionCount--;
                Iterator<InlineKeyboardButton> iterator = telegramButtonList.iterator();
                while (iterator.hasNext()) {
                    InlineKeyboardButton telegramButton = iterator.next();
                    if (button.getText().equals(telegramButton.getText())) {
                        iterator.remove();
                    }
                }
            }
        });
        buttons.add(button);
        return button;
    }

    private void addButtonsToPanel(JToggleButton... buttons) {
        for (JToggleButton button : buttons) {
            add(button);
        }
    }

    public void addBackgroundPicture() {
        try {
            imageIcon = ImageIO.read(new File("src/main/java/Utility/telegram image.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        g.drawImage(imageIcon, 0, 0, getWidth(), getHeight(), this);
    }


}
