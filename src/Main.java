import controller.GameController;
import loader.XMLLoader;
import model.Scenario;
import view.GameView;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Load scenarios from XML (or another source)
        XMLLoader xmlLoader = new XMLLoader();
        List<Scenario> scenarios = xmlLoader.loadScenarios("test.xml"); // Update the path as needed

        // Create the GameView instance
        GameView view = new GameView();

        // Create the GameController instance
        GameController gameController = new GameController(scenarios, view);

        // Start the game
        gameController.startGame();
    }
}
//comment