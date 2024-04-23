import javax.swing.*;
import java.awt.*;

public class HealthBar extends JFrame {

    private final int MAX_HEALTH;
    private int currentHealth;
    private JPanel healthBarPanel;  // Panel for the health percentage

    public HealthBar(int maxHealth) {
        super();  // Call superclass constructor (implicit modal)
        // Remove setModal(true)

        this.setTitle("Health Bar");

        this.MAX_HEALTH = maxHealth;
        this.currentHealth = maxHealth;

        // Set preferred size as minimum size to prevent resizing
        setPreferredSize(new Dimension(250, 70));  // Adjust size as needed
        setMinimumSize(getPreferredSize());
        pack();  // Adjust window size based on preferred size
        // Set location (optional)
        // setLocationRelativeTo(null);  // Center on screen
    }


    public void setCurrentHealth(int newHealth) {
        this.currentHealth = Math.max(0, Math.min(newHealth, MAX_HEALTH)); // Clamp value between 0 and max health
        updateHealthBar();
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getMAX_HEALTH() {
        return MAX_HEALTH;
    }

    // Make updateHealthBar public to allow access from CustomCommandPrompt
    public void updateHealthBar() {
        // Calculate health percentage
        float healthPercentage = (float) currentHealth / MAX_HEALTH;

        // Update the text of the healthBarPanel to show percentage
        healthBarPanel.removeAll();  // Remove any existing components
        healthBarPanel.add(new JLabel(String.format("%.0f%%", healthPercentage * 100)));  // Display percentage with one decimal place
        healthBarPanel.revalidate();  // Revalidate to trigger layout changes
    }

    {
        healthBarPanel = new JPanel();
        healthBarPanel.setLayout(new FlowLayout(FlowLayout.CENTER));  // Center text
        getContentPane().add(healthBarPanel);
    }
}