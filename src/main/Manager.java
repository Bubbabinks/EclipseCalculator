package main;

import calculator.CalculatorManager;
import calculator.Values;
import ui.UIManager;

public class Manager {

    public static void main(String[] args) {
        Values.init();
        UIManager.init();
        CalculatorManager.init();
    }

}
