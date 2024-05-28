package controller;

import loader.XMLLoader;
import model.*;
import model.Scenario;
import view.GameView;

import java.util.List;
import java.util.Scanner;

public class GameController {
    private List<Scenario> scenarios;
    private GameView view;
    private Scanner scanner;
    private Scenario currentScenario;

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

        // Start with the first scenario
        currentScenario = scenarios.get(0);

        while (true) {
            view.displayScenario(currentScenario);
            view.displayChoices(currentScenario.getChoices());

            System.out.println("Enter your choice ID:");
            String choiceId = scanner.nextLine();

            Choice chosenChoice = handleChoiceInput(choiceId);

            if (chosenChoice != null) {
                // Apply the effects of the chosen choice
                applyChoiceEffects(chosenChoice);

                // Update currentScenario based on the chosen choice
                if (!chosenChoice.getSubChoices().isEmpty()) {
                    // If there are sub-choices, treat it as a new scenario branch
                    currentScenario = new Scenario();
                    currentScenario.setChoices(chosenChoice.getSubChoices());
                    currentScenario.setDialogues(chosenChoice.getDialogues());
                } else {
                    // Otherwise, move to the next scenario (for simplicity, we assume linear progression)
                    int nextCheckpoint = chosenChoice.getCheckpoint();
                    currentScenario = findScenarioByCheckpoint(nextCheckpoint);
                }
            } else {
                System.out.println("Invalid choice ID. Please try again.");
            }
        }
    }

    private Choice handleChoiceInput(String choiceId) {
        for (Choice choice : currentScenario.getChoices()) {
            if (choice.getId().equals(choiceId)) {
                return choice;
            }
        }
        return null;
    }

    private void applyChoiceEffects(Choice choice) {
        // Apply the effects of the choice, e.g., update player state (items, reputation, etc.)
        for (Effect effect : choice.getEffects()) {
            // Example: print out the effects (you can update player's state here)
            System.out.println("Effect: Items = " + effect.getItems() + ", Reputation = " + effect.getReputation());
        }
    }

    private Scenario findScenarioByCheckpoint(int checkpoint) {
        for (Scenario scenario : scenarios) {
            if (scenario.getCheckpoint() == checkpoint) {
                return scenario;
            }
        }
        return null; // or handle as a game over or invalid state
    }
}
