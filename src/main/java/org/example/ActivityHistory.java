package org.example;

import javax.swing.*;
import java.awt.*;
import java.util.Queue;

public class ActivityHistory extends JPanel {
    private Window window;
    private JTextArea textArea;

    public ActivityHistory(Window window) {
        this.window = window;
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton returnButton = new JButton("Return");
        returnButton.addActionListener(e -> window.mainPanel());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(returnButton);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(buttonPanel, BorderLayout.SOUTH);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);

        updateScreenThread();
    }

    private void updateScreenThread() {
        Thread thread = new Thread(() -> {
            while (true) {
                Queue<String> activityHistory = TelegramBot.activityHistory;
                updateTextArea(formatActivityHistory(activityHistory));

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });
        thread.start();
    }

    private void updateTextArea(String[] formattedHistory) {
        StringBuilder sb = new StringBuilder();
        for (String activity : formattedHistory) {
            sb.append(activity).append("\n");
        }
        SwingUtilities.invokeLater(() -> textArea.setText(sb.toString()));
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

    public Dimension getPreferredSize() {
        return new Dimension(500, 500); // Set the desired size of the panel
    }
}
