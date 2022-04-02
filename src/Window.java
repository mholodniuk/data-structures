package src;

import javax.swing.*;
import java.awt.*;

public class Window {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
                MyMessengerFrame frame = new MyMessengerFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("MyMessenger");
                frame.setVisible(true);
            } 
        });
    }
}

class MyMessengerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 480;
    private static final int DEFAULT_HEIGHT = 640;
    MyMessenger messengerJan;
    MyMessenger messengerAnna;

    public MyMessengerFrame() {
        messengerJan = new MyMessenger();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        JPanel inPanel = new JPanel();

        JLabel inLabel = new JLabel("Wprowadź tekst", JLabel.RIGHT);
        inPanel.add(inLabel);

        JTextField inText = new JTextField("Tekst", 20);
        inPanel.add(inText);

        JCheckBox newLine = new JCheckBox("Nowa linia", true);
        inPanel.add(newLine);

        JComboBox<String> changeType = new JComboBox<>();
        changeType.addItem("domyślnie");
        changeType.addItem("Wielka litera");
        changeType.addItem("Mała litera");
        inPanel.add(changeType);

        JPanel outPanel = new JPanel();
        JTextArea outText = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(outText);
        outPanel.add(scrollPane);

        JPanel actPanel = new JPanel();
        JButton sButton = new JButton("Dodaj linię");
        actPanel.add(sButton);

        JButton cButton = new JButton("Wyślij");
        actPanel.add(cButton);

        add(inPanel, BorderLayout.NORTH);
        add(outPanel, BorderLayout.CENTER);
        add(actPanel, BorderLayout.SOUTH);
        pack();

        sButton.addActionListener(event -> {
            String text = inText.getText();
            switch(changeType.getSelectedItem().toString()) {
                case "Wielka litera":
                    text = text.toUpperCase();
                    break;
                case "Mała litera":
                    text = text.toLowerCase();
                    break;
            }
            String[] messages = text.split(" ");
            for(String message: messages) {
                messengerJan.enqueue(message);
            }
            if(newLine.isSelected()) {
                outText.append(text + "\n");
            }
            else {
                outText.append(text);
            }   
        });

        cButton.addActionListener(event -> {
            System.out.print("\033[H\033[2J");  
            System.out.flush();
            outText.setText(" ");

            System.out.println("Wiadomość wysłana przez Jana:");
            messengerJan.printMessage();

            System.out.println("Wiadomość otrzymana przez Annę:");
            messengerJan.shuffle();
            messengerJan.printMessage();
            messengerAnna = messengerJan;

            System.out.println("Wiadomość przetworzona przez Annę:");
            messengerAnna.sort();
            messengerAnna.printMessage();

            messengerJan.clear();
            messengerAnna.clear();
        });
    }
}