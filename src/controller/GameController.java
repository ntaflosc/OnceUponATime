package controller;

import loader.XMLLoader;
import model.Scenario;
import view.GameView;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private List<Scenario> scenarios;
    private GameView view;
    private Scanner scanner;

    public GameController(String filePath) {
        XMLLoader loader = new XMLLoader(filePath);
        scenarios = loader.getScenarios();
        view = new GameView();
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        if (scenarios.isEmpty()) {
            System.out.println("No scenarios available.");
            return;
        }

        for (Scenario scenario : scenarios) {
            view.displayScenario(scenario);
            view.displayChoices(scenario.getChoices());

            System.out.println("Enter your choice ID:");
            String choiceId = scanner.nextLine();
            // You need to handle player choice input here

            // For the demo, we just show the choices without interaction handling
            // Add your interaction logic here
        }
    }
}
