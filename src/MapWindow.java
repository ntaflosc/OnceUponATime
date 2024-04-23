import javax.swing.*;
import java.awt.*;

public class MapWindow extends JFrame {

    private final int rows;
    private final int cols;
    private JLabel[][] mapCells;
    private String[] locationNames;  // Added location names array

    public MapWindow(int rows, int cols) {
        super("Map");
        this.rows = rows;
        this.cols = cols;
        initializeMap();
        createLayout();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  // Close this window, not the entire app
        setVisible(true);
    }

    private void initializeMap() {
        mapCells = new JLabel[rows][cols];
        String[] mapSymbols = {"Mountaintop", "Allay", "Countryside", "Forest", "Citadel", "Town Square", "Mountain", "Sea", "Military Base"};
        locationNames = new String[]{"Psychic", "Mount Rocky", "Kingdom 2", "Druids", "Kingdom 1", "Village", "Starting Point", "The Red Sea", "Enemy Territory"};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mapCells[i][j] = new JLabel("<html><center>" + locationNames[i * cols + j] + "<br>" + mapSymbols[i * cols + j] + "</html>");
                mapCells[i][j].setOpaque(true);  // Make JLabel background opaque for overlay
                mapCells[i][j].setHorizontalAlignment(SwingConstants.CENTER);  // Center text
                mapCells[i][j].setVerticalAlignment(SwingConstants.CENTER);   // Center text
            }
        }
    }

    private void createLayout() {
        JPanel mapPanel = new JPanel(new GridLayout(rows, cols));
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                mapPanel.add(mapCells[i][j]);
            }
        }
        getContentPane().add(mapPanel, BorderLayout.CENTER);
        setSize(300, 300);
    }

    // Method to update the map display based on player location (single "X")
    public void updateMap(int row, int col) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String existingText = mapCells[i][j].getText();
                if (existingText.contains("<b>X</b>")) {  // Check if existing text already contains "<b>X</b>"
                    existingText = existingText.replace("<b>X</b><br>", "");  // Remove previous "X" if found
                }
                if (i == row && j == col) {
                    mapCells[i][j].setText("<html><center><b>X</b><br>" + existingText + "</html>");
                } else {
                    mapCells[i][j].setText(existingText);
                }
            }
        }
    }

    // Optional method to retrieve map information (location name and symbol)
    public String getMapInfo(int row, int col, String[] mapSymbols) {
        return "<" + locationNames[row * cols + col] + ">: " + mapSymbols[row * cols + col];
    }
}
