import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class CustomCommandPrompt extends JFrame {

    private JTextArea console;
    private JTextField inputField;
    private int currentRow;
    private int currentCol;
    private final int mapRows = 3;
    private final int mapCols = 3;
    private MapWindow mapInstance = null;
    private HealthBar healthBar = new HealthBar(100); // Replace 100 with your max health

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

        inputField = new JTextField();
        inputField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userInput = inputField.getText().trim().toLowerCase();
                inputField.setText("");

                if (commands.containsKey(userInput)) {
                    console.append( "----" + userInput + "----\n");
                    commands.get(userInput).run();
                    updateMapDisplay();
                    updateHealthBar();  // Ensure updateHealthBar is called after setting health
                } else {
                    console.append("> Error: Unknown command '" + userInput + "'\n");
                }
            }
        });
    }

    private void createLayout() {
        getContentPane().add(console, BorderLayout.CENTER);
        getContentPane().add(inputField, BorderLayout.SOUTH);

        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void handleMovement(int colChange, int rowChange) {
        int newRow = currentRow + rowChange;
        int newCol = currentCol + colChange;

        if (isValidMove(newRow, newCol)) {
            currentRow = newRow;

            currentCol = newCol;
            console.append("You move " + getDirection(colChange, rowChange) + ".\n");
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
            return (colChange > 0 ? "east" : "west") + (rowChange > 0 ? "south" : "north");
        }
    }

    private void updateMapDisplay() {
        if (mapInstance != null) {
            mapInstance.updateMap(currentRow, currentCol);
        }
    }

    private void updateHealthBar() {
        // Print debugging information to check health and color updates (optional)
        // System.out.println("Current Health: " + healthBar.getCurrentHealth() + "/" + healthBar.getMAX_HEALTH());



        // Ensure updateHealthBar is called to reflect health percentage on the panel
        healthBar.updateHealthBar();
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