package src;

import javax.swing.*;
import java.awt.*;

public class Window {

    public static void main(String[] Strings) {
        EventQueue.invokeLater(new Runnable() {
           @Override
           public void run() {
                SimpleFrame frame = new SimpleFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("Simple Frame");
                frame.setVisible(true);
           } 
        });
    }  
    
}

class SimpleFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    public SimpleFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        JPanel inPanel = new JPanel();

        JLabel inLabel = new JLabel("Input text", JLabel.RIGHT);
        inPanel.add(inLabel);

        JTextField inText = new JTextField("Insert text", 20);
        inPanel.add(inText);

        JCheckBox newLine = new JCheckBox("New line", true);
        inPanel.add(newLine);

        JComboBox<String> changeType = new JComboBox<>();
        changeType.addItem("none");
        changeType.addItem("upper");
        changeType.addItem("lower");
        inPanel.add(changeType);

        JPanel outPanel = new JPanel();
        JTextArea outText = new JTextArea(10, 50);
        JScrollPane scrollPane = new JScrollPane(outText);
        outPanel.add(scrollPane);

        JPanel actPanel = new JPanel();
        JButton sButton = new JButton("Submit");
        actPanel.add(sButton);

        JButton cButton = new JButton("Clear");
        actPanel.add(cButton);

        add(inPanel, BorderLayout.NORTH);
        add(outPanel, BorderLayout.CENTER);
        add(actPanel, BorderLayout.SOUTH);
        pack();

        sButton.addActionListener(event -> {
            String text = inText.getText();
            switch(changeType.getSelectedItem().toString()) {
                case "upper":
                    text = text.toUpperCase();
                    break;
                case "lower":
                    text = text.toLowerCase();
                    break;
            }
            if(newLine.isSelected())
                outText.append(text + "\n");
            else
                outText.append(text);
        });
        cButton.addActionListener(event -> outText.setText(""));
    }
}