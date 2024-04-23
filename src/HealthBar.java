import javax.swing.*;
import java.awt.*;

public class HealthBar extends JFrame {

    private final int MAX_HEALTH;
    private int currentHealth;
    private JLabel healthLabel;  // Use JLabel directly for displaying percentage

    public HealthBar(int maxHealth) {
        super();
        this.setTitle("Health Bar");

        this.MAX_HEALTH = maxHealth;
        this.currentHealth = maxHealth;

        // Set preferred size as minimum size to prevent resizing
        setPreferredSize(new Dimension(100, 30));  // Adjust size as needed
        setMinimumSize(getPreferredSize());
        pack();

        healthLabel = new JLabel();  // Create empty JLabel
        healthLabel.setFont(new Font("Arial", Font.BOLD, 14));  // Set font (adjust as needed)
        getContentPane().add(healthLabel);  // Add directly to content pane
    }


    public void setCurrentHealth(int newHealth) {
        this.currentHealth = Math.max(0, Math.min(newHealth, MAX_HEALTH));
        updateHealthBar();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    public void updateHealthBar() {
        float healthPercentage = (float) currentHealth / MAX_HEALTH;
        healthLabel.setText(String.format("%.0f%%", healthPercentage * 100));
    }
}
