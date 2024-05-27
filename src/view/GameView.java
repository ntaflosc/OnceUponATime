package view;

import model.*;
import java.util.List;

public class GameView {
    public void displayScenario(Scenario scenario) {
        System.out.println("Story: " + scenario.getStory());
        displayChoices(scenario.getChoices());
    }

    public void displayChoices(List<Choice> choices) {
        for (Choice choice : choices) {
            System.out.println("Choice ID: " + choice.getId());
            System.out.println("Action: " + choice.getAction());
            displayDialogues(choice.getDialogues());
            displayEffects(choice.getEffects());
            if (choice.getSubChoices() != null) {
                displayChoices(choice.getSubChoices());
            }
        }
    }

    public void displayDialogues(List<Dialogue> dialogues) {
        for (Dialogue dialogue : dialogues) {
            System.out.println("Character: " + dialogue.getCharacter());
            for (String text : dialogue.getTexts()) {
                System.out.println("Text: " + text);
            }
        }
    }

    public void displayEffects(List<Effect> effects) {
        for (Effect effect : effects) {
            System.out.println("Items: " + effect.getItems());
            System.out.println("Reputation: " + effect.getReputation());
            System.out.println("Checkpoint: " + effect.getCheckpoint());
            // Check if health is a valid attribute and display it
            if (effect.getHealth() != 0) {
                System.out.println("Health: " + effect.getHealth());
            }
        }
    }
}
