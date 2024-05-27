package controller;

import model.*;
import loader.XMLLoader;
import java.util.List;

public class GameController {
    private List<Scenario> scenarios;

    public GameController(String xmlFilePath) {
        XMLLoader loader = new XMLLoader(xmlFilePath);
        this.scenarios = loader.loadScenarios();
    }

    public void startGame() {
        if (scenarios != null && !scenarios.isEmpty()) {
            Scenario scenario = scenarios.get(0);
            System.out.println(scenario.getStory());
            processChoices(scenario.getChoices());
        }
    }

    private void processChoices(List<Choice> choices) {
        for (Choice choice : choices) {
            System.out.println("Choice ID: " + choice.getId());
            System.out.println("Action: " + choice.getAction());
            for (Dialogue dialogue : choice.getDialogues()) {
                System.out.println("Character: " + dialogue.getCharacter());
                for (String text : dialogue.getTexts()) {
                    System.out.println("Text: " + text);
                }
            }
            for (Effect effect : choice.getEffects()) {
                System.out.println("Items: " + effect.getItems());
                System.out.println("Reputation: " + effect.getReputation());
                System.out.println("Checkpoint: " + effect.getCheckpoint());
            }
            if (choice.getSubChoices() != null) {
                processChoices(choice.getSubChoices());
            }
        }
    }
}
