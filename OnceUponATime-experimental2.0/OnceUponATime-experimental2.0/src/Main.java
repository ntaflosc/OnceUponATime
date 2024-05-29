import loader.XMLLoader;
import model.Choice;
import model.Dialogue;  // Import the Dialogue class
import model.Scenario;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        XMLLoader loader = new XMLLoader();
        List<Scenario> scenarios = loader.loadScenarios("C:\\Users\\Admin\\Desktop\\OnceUponATime-experimental2.0\\OnceUponATime-experimental2.0\\test2.xml");

        if (scenarios != null && !scenarios.isEmpty()) {
            playScenario(scenarios.get(0));
        } else {
            System.out.println("No scenarios found.");
        }
    }

    private static void playScenario(Scenario scenario) {
        Scanner scanner = new Scanner(System.in);
        Choice currentChoice = null;

        System.out.println(scenario.getStory());

        while (true) {
            if (currentChoice == null) {
                System.out.println("Available choices:");
                for (Choice choice : scenario.getChoices()) {
                    System.out.println("Choice ID: " + choice.getId() + " - " + choice.getAction());
                }
            } else {
                for (Dialogue dialogue : currentChoice.getDialogues()) {
                    System.out.println(dialogue.getCharacter() + ": " + dialogue.getText());
                }

                if (currentChoice.getSubchoices().isEmpty()) {
                    break;
                }

                System.out.println("Available subchoices:");
                for (Choice subchoice : currentChoice.getSubchoices()) {
                    System.out.println("Choice ID: " + subchoice.getId() + " - " + subchoice.getAction());
                }
            }

            System.out.print("Enter your choice ID: ");
            String choiceId = scanner.nextLine().trim();

            if (currentChoice == null) {
                currentChoice = findChoiceById(scenario.getChoices(), choiceId);
            } else {
                currentChoice = findChoiceById(currentChoice.getSubchoices(), choiceId);
            }

            if (currentChoice == null) {
                System.out.println("Invalid choice ID. Please try again.");
            }
        }

        scanner.close();
    }

    private static Choice findChoiceById(List<Choice> choices, String choiceId) {
        for (Choice choice : choices) {
            if (choice.getId().equals(choiceId)) {
                return choice;
            }
        }
        return null;
    }
}
