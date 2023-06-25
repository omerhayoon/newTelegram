package org.example;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ManagementActivities extends JPanel {

    private final int MAX_SELECTION_COUNT = 3;
    private int currentSelectionCount = 0;
    private Window window;
    private List<JToggleButton> buttons;
    private TelegramBot telegramBot = new TelegramBot();
    public static List<InlineKeyboardButton> telegramButtonList = new ArrayList<>();



    public ManagementActivities(int x, int y, int width, int height,Window window) {
        this.window=window;

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
        returnButton.setBounds(50, 400, 100, 40);
        returnButton.setVisible(true);
        returnButton.addActionListener(e -> window.mainPanel());
        this.add(returnButton);
    }


    private void buttonsBounds(JToggleButton ipAPIButton, JToggleButton catsAPIButton,
                               JToggleButton jokesAPIButton, JToggleButton NumberFactButton, JToggleButton triviaButton) {
        ipAPIButton.setBounds(200, 10, 100, 25);
        catsAPIButton.setBounds(200, 50, 100, 25);
        jokesAPIButton.setBounds(200, 90, 100, 25);
        NumberFactButton.setBounds(200, 130, 100, 25);
        triviaButton.setBounds(200, 170, 100, 25);
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
                    System.out.println(button.getText() + " pressed");
                    telegramButtonList.forEach(System.out::println);

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
                System.out.println(button.getText() + " unpressed");
                telegramButtonList.forEach(System.out::println);
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


}
