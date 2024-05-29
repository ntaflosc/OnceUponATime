import controller.GameController;

public class Main {
    public static void main(String[] args) {

        String xmlFilePath = "all.xml";
        GameController gameController = new GameController(xmlFilePath);
        gameController.startGame();
    }
}
