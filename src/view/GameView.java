package view;

import model.Choice;
import model.Dialogue;
import model.Scenario;

import java.util.List;

public class GameView {
    public void displayScenario(Scenario scenario) {
        System.out.println("Location: " + scenario.getLocation());
        System.out.println("Story: " + scenario.getStory());

        List<Dialogue> dialogues = scenario.getDialogues();
        if (dialogues != null) {
            for (Dialogue dialogue : dialogues) {
                System.out.println("Character: " + dialogue.getCharacter());
                System.out.println("Text: " + dialogue.getText());
            }
        }
    }

    public void displayChoices(List<Choice> choices) {
        for (Choice choice : choices) {
            System.out.println("Choice ID: " + choice.getId());
            System.out.println("Action: " + choice.getAction());
            if (choice.getDialogues() != null) {
                for (Dialogue dialogue : choice.getDialogues()) {
                    System.out.println("Character: " + dialogue.getCharacter());
                    System.out.println("Text: " + dialogue.getText());
                }
            }
            if (choice.getEffects() != null) {
                choice.getEffects().forEach(effect -> {
                    if (effect.getItems() != null) {
                        System.out.println("Items: " + effect.getItems());
                    }
                    if (effect.getReputation() != null) {
                        System.out.println("Reputation: " + effect.getReputation());
                    }
                    System.out.println("Checkpoint: " + effect.getCheckpoint());
                });
            }
            if (choice.getSubChoices() != null) {
                displayChoices(choice.getSubChoices());
            }
        }
    }
}
