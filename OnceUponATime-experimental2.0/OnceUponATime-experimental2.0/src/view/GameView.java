package view;

import model.Choice;
import model.Dialogue;
import model.Scenario;

import java.util.List;
import java.util.Scanner;

public class GameView {
    public void displayScenario(Scenario scenario) {
        for (Dialogue dialogue : scenario.getDialogues()) {
            System.out.println(dialogue.getText());
        }
    }

    public void displayChoices(List<Choice> choices) {
        for (Choice choice : choices) {
            System.out.println("Choice ID: " + choice.getId());
            for (Dialogue dialogue : choice.getDialogues()) {
                System.out.println(dialogue.getText());
            }
        }
    }
}
