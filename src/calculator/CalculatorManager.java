package calculator;

import ui.UIManager;
import ui.panels.CalculatorPanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;

public class CalculatorManager {

    private static CalculatorPanel calculatorPanel;

    public static double lSun = lerp(Values.minEarthToSun, Values.maxEarthToSun, Values.dEarthToSun);
    public static double lMoon = lerp(Values.minEarthToMoon, Values.maxEarthToMoon, Values.dEarthToMoon);

    private static HashSet<Integer> keysPressed = new HashSet<Integer>();

    public static void init() {
        calculatorPanel = new CalculatorPanel();
        UIManager.setPanel(calculatorPanel);
        calculatorPanel.requestFocus();
        calculatorPanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    calculatorPanel.shiftBodySelected();
                    calculatorPanel.repaint();
                }
                keysPressed.add(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                keysPressed.remove(e.getKeyCode());
            }
        });
        Thread thread = new Thread(() -> {
            while (true) {
                if (keysPressed.contains(KeyEvent.VK_RIGHT)) {
                    shiftRight();
                    calculatorPanel.repaint();
                }
                if (keysPressed.contains(KeyEvent.VK_LEFT)) {
                    shiftLeft();
                    calculatorPanel.repaint();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    public static void shiftLeft() {
        if (calculatorPanel.getSelectedBody() == 0) {
            lSun += 0.01;
            if (lSun > 1) {
                lSun = 1;
            }
            calculatorPanel.repaint();
        }else if (calculatorPanel.getSelectedBody() == 1) {
            lMoon += 0.01;
            if (lMoon > 1) {
                lMoon = 1;
            }
            calculatorPanel.repaint();
        }
    }

    public static void shiftRight() {
        if (calculatorPanel.getSelectedBody() == 0) {
            lSun -= 0.01;
            if (lSun < 0) {
                lSun = 0;
            }
            calculatorPanel.repaint();
        }else if (calculatorPanel.getSelectedBody() == 1) {
            lMoon -= 0.01;
            if (lMoon < 0) {
                lMoon = 0;
            }
            calculatorPanel.repaint();
        }
    }

    public static double lerp(double a, double b, double t) {
        double c = b - a;
        t = t - a;
        return t/c;
    }

    public static double inverseLerp(double a, double b, double t) {
        double c = b - a;
        c = c * t;
        return a + c;
    }

}
