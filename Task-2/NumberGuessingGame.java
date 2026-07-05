import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class NumberGuessingGame {
    private static int targetNumber;
    private static int attempts;
    private static final Random random = new Random();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ignored) {}

            targetNumber = random.nextInt(100) + 1;
            attempts = 0;

            JFrame frame = new JFrame("Number Guessing Game");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(480, 360);
            frame.setLocationRelativeTo(null);
            frame.setResizable(false);

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
            mainPanel.setBackground(new Color(240, 244, 248));

            JLabel titleLabel = new JLabel("Guess the Number (1-100)");
            titleLabel.setFont(new Font("SansSerif", Font.BOLD, 22));
            titleLabel.setForeground(new Color(16, 42, 67));
            titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
            inputPanel.setOpaque(false);

            JTextField inputField = new JTextField(6);
            inputField.setFont(new Font("SansSerif", Font.BOLD, 18));
            inputField.setHorizontalAlignment(JTextField.CENTER);
            inputField.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(204, 214, 224), 2),
                    BorderFactory.createEmptyBorder(6, 6, 6, 6)
            ));

            JButton guessButton = new JButton("GUESS");
            guessButton.setFont(new Font("SansSerif", Font.BOLD, 14));
            guessButton.setBackground(new Color(38, 128, 235));
            guessButton.setForeground(Color.WHITE);
            guessButton.setFocusPainted(false);
            guessButton.setOpaque(true);
            guessButton.setBorderPainted(false);
            guessButton.setPreferredSize(new Dimension(100, 42));
            guessButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

            inputPanel.add(inputField);
            inputPanel.add(guessButton);

            JLabel feedbackLabel = new JLabel("Enter a number to start!");
            feedbackLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            feedbackLabel.setForeground(new Color(98, 125, 152));
            feedbackLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel attemptsLabel = new JLabel("Attempts: 0");
            attemptsLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            attemptsLabel.setForeground(new Color(98, 125, 152));
            attemptsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton playAgainButton = new JButton("PLAY AGAIN");
            playAgainButton.setFont(new Font("SansSerif", Font.BOLD, 14));
            playAgainButton.setBackground(new Color(19, 115, 51));
            playAgainButton.setForeground(Color.WHITE);
            playAgainButton.setFocusPainted(false);
            playAgainButton.setOpaque(true);
            playAgainButton.setBorderPainted(false);
            playAgainButton.setPreferredSize(new Dimension(150, 40));
            playAgainButton.setMaximumSize(new Dimension(150, 40));
            playAgainButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            playAgainButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            playAgainButton.setVisible(false);

            guessButton.addActionListener(e -> {
                try {
                    int guess = Integer.parseInt(inputField.getText());
                    attempts++;
                    attemptsLabel.setText("Attempts: " + attempts);

                    if (guess < 1 || guess > 100) {
                        feedbackLabel.setText("Please guess between 1 and 100.");
                        feedbackLabel.setForeground(new Color(217, 48, 37));
                    } else if (guess < targetNumber) {
                        feedbackLabel.setText("Too low! Try a higher number.");
                        feedbackLabel.setForeground(new Color(245, 124, 0));
                    } else if (guess > targetNumber) {
                        feedbackLabel.setText("Too high! Try a lower number.");
                        feedbackLabel.setForeground(new Color(245, 124, 0));
                    } else {
                        feedbackLabel.setText("Correct! You guessed the number!");
                        feedbackLabel.setForeground(new Color(19, 115, 51));
                        guessButton.setEnabled(false);
                        inputField.setEnabled(false);
                        playAgainButton.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    feedbackLabel.setText("Invalid input. Enter a number.");
                    feedbackLabel.setForeground(new Color(217, 48, 37));
                }
                inputField.setText("");
                inputField.requestFocus();
            });

            playAgainButton.addActionListener(e -> {
                targetNumber = random.nextInt(100) + 1;
                attempts = 0;
                attemptsLabel.setText("Attempts: 0");
                feedbackLabel.setText("Enter a number to start!");
                feedbackLabel.setForeground(new Color(98, 125, 152));
                guessButton.setEnabled(true);
                inputField.setEnabled(true);
                playAgainButton.setVisible(false);
                inputField.requestFocus();
            });

            // Allow hitting "Enter" key to submit guess
            frame.getRootPane().setDefaultButton(guessButton);

            mainPanel.add(titleLabel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            mainPanel.add(inputPanel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 15)));
            mainPanel.add(feedbackLabel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 10)));
            mainPanel.add(attemptsLabel);
            mainPanel.add(Box.createRigidArea(new Dimension(0, 20)));
            mainPanel.add(playAgainButton);

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}