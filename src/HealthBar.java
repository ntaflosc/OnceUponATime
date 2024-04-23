import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class HealthBar extends JFrame {

    private final int MAX_HEALTH;
    private int currentHealth;
    private JLabel healthLabel;  // Declare healthLabel as a class variable

    public HealthBar(int maxHealth) {
        super();
        this.setTitle("Health Bar");

        this.MAX_HEALTH = maxHealth;
        this.currentHealth = maxHealth;

        // Set preferred size as minimum size to prevent resizing
        setPreferredSize(new Dimension(200, 90));  // Adjust size as needed
        setMinimumSize(getPreferredSize());
        pack();

        healthLabel = new JLabel();  // Create empty JLabel
        healthLabel.setFont(new Font("Arial", Font.BOLD, 14));  // Set font (adjust as needed)
        getContentPane().add(healthLabel);  // Add directly to content pane

        // Call updateHealthBar to display initial value
        updateHealthBar();
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
        healthLabel.setText(String.format("%d/%d", currentHealth, MAX_HEALTH));
    }
}
