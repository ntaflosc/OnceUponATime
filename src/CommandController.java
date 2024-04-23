import java.util.HashMap;
import java.util.Map;

public class CommandController {

    private Game game;
    private View view;

    public CommandController(Game game, View view) {
        this.game = game;
        this.view = view;
    }

    public void handleUserInput(String command) {
        if (game.getCommands().containsKey(command)) {
            game.getCommands().get(command).run();
            updateView();
        } else {
            view.updateConsoleText("Error: Unknown command '" + command + "'");
        }
    }

    private void updateView() {
        // Update console text based on game state
        view.updateConsoleText("You are at position (" + game.getCurrentRow() + ", " + game.getCurrentCol() + ").");
        view.updateConsoleText("Current Health: " + game.getCurrentHealth() + "/" + game.getHealth().getMAX_HEALTH());
        view.showMapWindow(); // Assuming the map window reflects the current position
    }

    private Map<String, Runnable> commands = new HashMap<>();

    {
        // Movement commands (lowercase for case-insensitive matching)
        commands.put("go north", () -> handleMovement(0, -1));
        commands.put("go east", () -> handleMovement(1, 0));
        commands.put("go south", () -> handleMovement(0, 1));
        commands.put("go west", () -> handleMovement(-1, 0));
        commands.put("go northeast", () -> handleMovement(1, -1));
        commands.put("go northwest", () -> handleMovement(-1, -1));
        commands.put("go southeast", () -> handleMovement(1, 1));
        commands.put("go southwest", () -> handleMovement(-1, 1));
        // Doesn't detect the location, just changes the col and row, we can make it check the location in map window
        // Interaction commands (replace with your actual logic)
        commands.put("obtain item", () -> console.append("You try to obtain an item. But nothing is there. \n"));
        commands.put("talk to character", () -> console.append("There seems to be no character to talk to here.\n"));

        //healthbar related commands (for testing) currently trying to add/remove health value from the hp bar
        commands.put("damage", () -> console.append("You took some damage.\n"));
        // Map related commands
        commands.put("show map", () -> {
            if (mapInstance == null) {
                mapInstance = new MapWindow(mapRows, mapCols);  // Create new MapWindow if not already open
            } else {
                mapInstance.setVisible(true);  // Make existing MapWindow visible if hidden
            }
        });

        // Show health command
        commands.put("show health", () -> {
            healthBar.setVisible(true);  // Make the health bar window visible
        });
    }
}
