import java.awt.BorderLayout;
import javax.swing.JTextArea;
import javax.swing.JFrame;

public class HelpWindow extends JFrame {

    private JTextArea helpText;

    public HelpWindow(String[] availableCommands) {
        super("Available Commands");

        helpText = new JTextArea();
        helpText.setEditable(false);

        StringBuilder allCommands = new StringBuilder("Available Commands:\n");
        for (String command : availableCommands) {
            // Add "save" explicitly if not already present
            if (!command.equals("save")) {
                allCommands.append("> ").append(command).append("\n");
            }
        }
        allCommands.append("> save\n"); // Ensure "save" is displayed
        helpText.setText(allCommands.toString());

        getContentPane().add(helpText, BorderLayout.CENTER);
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
