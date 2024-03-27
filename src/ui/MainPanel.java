package ui;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {

    public MainPanel() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(UIManager.width, UIManager.height));
    }

    public void setPanel(JPanel panel) {
        removeAll();
        revalidate();
        add(panel, BorderLayout.CENTER);
        repaint();
    }

}
