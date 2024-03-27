package ui.panels;

import calculator.CalculatorManager;
import calculator.Values;
import ui.UIManager;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class CalculatorPanel extends JPanel {

    private int[] stars = new int[UIManager.width * UIManager.height / 64 + 1];

    private int selected = 2;

    public CalculatorPanel() {
        Random random = new Random();

        int i = 0;
        while (i < UIManager.width * UIManager.height) {
            setStar(i, true);
            i += random.nextInt(2000);
        }
    }

    public void shiftBodySelected() {
        selected++;
        if (selected == 3) {
            selected = 0;
        }
    }

    public int getSelectedBody() {
        return selected;
    }

    private boolean getStar(int index) {
        int a = index % 64;
        int b = stars[index / 64];
        b = b >> a;
        return ((b & 0x0001) == 1);
    }

    private void setStar(int index, boolean value) {
        int i = index / 64;
        int a = index % 64;
        int b = stars[i];
        if (value) {
            b = (b | (0x0001 << a));
        }else {
            b = (b & ~(0x0001 << a));
        }
        stars[i] = b;
    }

    protected void paintComponent(Graphics g) {
        //Background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, UIManager.width, UIManager.height);

        //Stars
        g.setColor(Color.WHITE);
        for (int y = 0; y < UIManager.height; y++) {
            int add = y * UIManager.height;
            for (int x = 0; x < UIManager.width; x++) {
                if (getStar(x+add)) {
                    g.fillRect(x, y, 1, 1);
                }
            }
        }

        //Instructions
        g.setColor(new Color(0, 192, 255));
        g.drawString("Press Space to Shift Selected Body", 10, 20);
        g.drawString("Use Arrow Keys to Shift Bodies", 10, 35);

        //Sun
        int sunX = -(int)(CalculatorManager.lSun*100d-50d);
        if (selected == 0) {
            g.setColor(new Color(210, 142, 16));
            g.fillOval(70+sunX, UIManager.height/2 - 80, 160, 160);
        }
        g.setColor(new Color(210, 100, 16));
        g.fillOval(75+sunX, UIManager.height/2 - 75, 150, 150);

        //Earth
        g.setColor(new Color(55, 182, 92));
        g.fillOval(UIManager.width - 130, UIManager.height/2 - 40, 80, 80);

        //Moon
        int moonX = -(int)(CalculatorManager.lMoon*50d-50d);
        if (selected == 1) {
            g.setColor(new Color(248, 248, 248));
            g.fillOval(UIManager.width - 305+moonX, UIManager.height/2 - 25, 50, 50);
        }
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(UIManager.width - 300+moonX, UIManager.height/2 - 20, 40, 40);

        //Distance Labels
        double dETS = CalculatorManager.inverseLerp(Values.minEarthToSun, Values.maxEarthToSun, CalculatorManager.lSun);
        double dETM = CalculatorManager.inverseLerp(Values.minEarthToMoon, Values.maxEarthToMoon, CalculatorManager.lMoon);
        double dMTS = dETS - dETM;
        g.setColor(new Color(210, 100, 16));
        g.drawString((int)dMTS+" miles", 400, UIManager.height/2-5);
        g.drawString((int)(CalculatorManager.lerp(Values.minMoonToSun, Values.maxMoonToSun, dMTS)*100)+"%", 400, UIManager.height/2-15);
        g.setColor(Color.LIGHT_GRAY);
        g.drawString((int)dETM+" miles", 800, UIManager.height/2-5);
        g.drawString((int)(CalculatorManager.lerp(Values.minEarthToMoon, Values.maxEarthToMoon, dETM)*100)+"%", 800, UIManager.height/2-15);
        String s = "Eclipse Size: " + Values.calculateEclipseSize(Values.rSun, dMTS, Values.rMoon, dETM)+" miles";
        g.setColor(new Color(0, 192, 255));
        g.drawString(s, UIManager.width/2-g.getFontMetrics().stringWidth(s)/2, UIManager.height/4*3);
    }
}
