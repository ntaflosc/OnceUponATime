import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.text.DefaultCaret;

public class CustomCommandPrompt extends JFrame {

    private JTextArea console;
    private JTextField inputField;
    private int currentRow;
    private int currentCol;
    private final int mapRows = 3;  // Replace with your actual map dimensions
    private final int mapCols = 3;  // Replace with your actual map dimensions
    private MapWindow mapInstance = null;
    private HealthBar healthBar = new HealthBar(100); // Replace 100 with your max health

    private List<String> executedCommands = new ArrayList<>(); // Stores user-entered commands

    public CustomCommandPrompt() {
        super("Custom Command Prompt");
        initializeComponents();
        createLayout();
        currentRow = mapRows - 1;
        currentCol = 0;
        updateMapDisplay();
    }

    private void initializeComponents() {
        console = new JTextArea();
        console.setEditable(false);
        console.setLineWrap(true);

        // Ensure caret always scrolls to the end
        DefaultCaret caret = (DefaultCaret)console.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        // Initial preferred size (adjust as needed)
        console.setPreferredSize(new Dimension(300, 200));
        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim().toLowerCase();
                inputField.setText("");

                if (userInput.equals("save")) {
                    saveCommands(); // Call save method for "save" command
                } else {
                    executedCommands.add(userInput); // Store command before execution
                    if (commands.containsKey(userInput)) {
                        console.append("----" + userInput + "----\n");
                        commands.get(userInput).run();
                        updateMapDisplay();
                        updateHealthBar();
                        updateConsoleSize(); // Call after appending text
                    } else {
                        console.append("> Error: Unknown command '" + userInput + "'\n");
                    }
                }
            }
        });
    }

    private void createLayout() {
        JScrollPane scrollPane = new JScrollPane(console);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(inputField, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void updateConsoleSize() {
        int lines = console.getLineCount();
        int estimatedLineHeight = 16; // Adjust based on your font size
        int newHeight = lines * estimatedLineHeight;
        console.setPreferredSize(new Dimension(console.getPreferredSize().width, newHeight));
        console.revalidate(); // Inform the component hierarchy about the change
    }

    // Movement handling methods
    private void handleMovement(int colChange, int rowChange) {
        int newRow = currentRow + rowChange;
        int newCol = currentCol + colChange;

        if (isValidMove(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
            String direction = getDirection(colChange, rowChange);
            console.append("You move " + direction.toLowerCase() + ".\n");
            // Update map or perform actions based on the new location (optional)
        } else {
            console.append("You cannot move there. It's beyond the map boundaries.\n");
        }
    }

    private boolean isValidMove(int newRow, int newCol) {
        return newRow >= 0 && newRow < mapRows && newCol >= 0 && newCol < mapCols;
    }

    private String getDirection(int colChange, int rowChange) {
        if (colChange == 0) {
            return rowChange == -1 ? "north" : "south";
        } else if (rowChange == 0) {
            return colChange == 1 ? "east" : "west";
        } else {
            // Separate checks for diagonal movements with priority to vertical movement
            String direction = "";
            if (rowChange < 0) {
                direction += "north";
            } else {
                direction += "south";
            }
            if (colChange > 0) {
                direction += "east";
            } else {
                direction += "west";
            }
            return direction;
        }
    }

    private void updateMapDisplay() {
        if (mapInstance != null) {
            mapInstance.updateMap(currentRow, currentCol);
        }
    }
    private void updateHealthBar() {
    }
// for some reason it breaks when i add updatehealthbar, if i remove it it works perfectly so cool

    private int getHealth() {
        // No health reduction logic here anymore
        return healthBar.getCurrentHealth();
    }

    private void saveCommands() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("save.txt", true))) {
            for (String command : executedCommands) {
                if (!command.equals("load")) { // Remove "load" after saving
                    writer.write(command + "\n");
                }
            }
            console.append("Commands saved successfully!\n");
            executedCommands.clear(); // Clear list after saving
        } catch (IOException e) {
            console.append("Error saving commands: " + e.getMessage() + "\n");
        }
    }


    private void loadCommands() {
        try (BufferedReader reader = new BufferedReader(new FileReader("save.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim().toLowerCase();
                if (!line.isEmpty()) {
                    console.append("---- Executing: " + line + " ----\n");
                    if (commands.containsKey(line)) {
                        commands.get(line).run();
                        updateMapDisplay();
                        updateHealthBar();
                    } else {
                        console.append("> Error: Unknown command '" + line + "'\n");
                    }
                }
            }
            console.append("Commands loaded and executed successfully!\n");
        } catch (IOException e) {
            console.append("Error loading commands: " + e.getMessage() + "\n");
        }
    }

    private Map<String, Runnable> commands = new HashMap<>();

    {
        commands.put("go north", () -> handleMovement(0, -1));
        commands.put("go east", () -> handleMovement(1, 0));
        commands.put("go south", () -> handleMovement(0, 1));
        commands.put("go west", () -> handleMovement(-1, 0));
        commands.put("go northeast", () -> handleMovement(1, -1));
        commands.put("go northwest", () -> handleMovement(-1, -1));
        commands.put("go southeast", () -> handleMovement(1, 1));
        commands.put("go southwest", () -> handleMovement(-1, 1));
        // Interaction commands (replace with your actual logic)
        commands.put("obtain item", () -> console.append("You try to obtain an item. But nothing is there. \n"));
        commands.put("talk to character", () -> console.append("There seems to be no character to talk to here.\n"));
        commands.put("load", this::loadCommands);
        // Healthbar related commands
        commands.put("damage", () -> {
            healthBar.setCurrentHealth(Math.max(0, healthBar.getCurrentHealth() - 10));
            console.append("You took some damage.\n");
        });
        commands.put("help", () -> new HelpWindow(commands.keySet().toArray(new String[0])));
        commands.put("delete save", () -> {
            try {
                // Clear the existing file content
                new FileWriter("save.txt").close(); // Creates an empty file
                console.append("Save file deleted successfully.\n");
            } catch (IOException e) {
                console.append("Error deleting save file: " + e.getMessage() + "\n");
            }
        });

        commands.put("show map", () -> {
            if (mapInstance != null) {
                mapInstance.setVisible(true);
            } else {
                mapInstance = new MapWindow(mapRows, mapCols);
            }
        });


        commands.put("show health", () -> {
            healthBar.setVisible(true);
        });
//new class with healthbar in view
        commands.put("heal", () -> {
            if (Math.random() < 0.05) { // 5% chance of critical failure
                healthBar.setCurrentHealth(Math.max(0, healthBar.getCurrentHealth() - 10));
                console.append("Critical failure! You accidentally hurt yourself, losing 10 health points. (Current Health: " + healthBar.getCurrentHealth() + ")\n");
            } else if (Math.random() < 0.15) { // 10% chance of critical success (0.05 + 0.1 = 0.15)
                int newHealth = Math.min(healthBar.getCurrentHealth() + 20, healthBar.getMAX_HEALTH());
                healthBar.setCurrentHealth(newHealth);
                console.append("Critical heal! You recover 20 health points. (Current Health: " + newHealth + ")\n");
            } else {
                int newHealth = Math.min(healthBar.getCurrentHealth() + 10, healthBar.getMAX_HEALTH());
                healthBar.setCurrentHealth(newHealth);
                console.append("You healed 10 health points. (Current Health: " + newHealth + ")\n");
            }
        });

    }
}


