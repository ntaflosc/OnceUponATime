public class Game {

    private final int mapRows;
    private final int mapCols;
    private int currentRow;
    private int currentCol;
    private HealthBar healthBar;

    // Optional: You can have a separate Map class to represent the actual map data structure

    public Game(int mapRows, int mapCols) {
        this.mapRows = mapRows;
        this.mapCols = mapCols;
        this.currentRow = mapRows - 1; // Initial position at bottom left (can be adjusted)
        this.currentCol = 0;
        this.healthBar = new HealthBar(100); // Replace 100 with your max health
    }

    // Getter and setter methods for game state variables (currentRow, currentCol, etc.)
    public void handleMovement(int colChange, int rowChange) {
        int newRow = currentRow + rowChange;
        int newCol = currentCol + colChange;

        if (isValidMove(newRow, newCol)) {
            currentRow = newRow;
            currentCol = newCol;
        }
    }

    private boolean isValidMove(int newRow, int newCol) {
        return newRow >= 0 && newRow < mapRows && newCol >= 0 && newCol < mapCols;
    }

    public void updateHealth(int delta) {
        health.changeHealth(delta);
    }

    public int getCurrentHealth() {
        return health.getCurrentHealth();
    }
}
