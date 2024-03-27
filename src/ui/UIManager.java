package ui;

import com.formdev.flatlaf.FlatDarkLaf;

import javax.swing.*;

public class UIManager {

    public static final int width = 1000, height = 1000;

    private static JFrame frame;
    private static MainPanel mainPanel;

    public static void init() {
        FlatDarkLaf.setup();
        frame = new JFrame("Eclipse Calculator");
        mainPanel = new MainPanel();
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    public static void setPanel(JPanel panel) {
        mainPanel.setPanel(panel);
    }

}
