import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class App extends JFrame implements ActionListener {
    private JButton generateButton;
    private JTextField passwordField;
    private JCheckBox includeSmallLetters;
    private JCheckBox includeCapitalLetters;
    private JCheckBox includeNumbers;
    private JCheckBox includeSpecialSymbols;
    private JTextField lengthField;

    public App() {
        setTitle("Password Generator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel lengthLabel = new JLabel("Password Length:");
        add(lengthLabel, gbc);

        gbc.gridx = 1;
        lengthField = new JTextField(10);
        lengthField.setText("12");
        add(lengthField, gbc);

        gbc.gridy = 1;
        gbc.gridwidth = 2;
        includeSmallLetters = new JCheckBox("Include Small Letters");
        includeCapitalLetters = new JCheckBox("Include Capital Letters");
        includeNumbers = new JCheckBox("Include Numbers");
        includeSpecialSymbols = new JCheckBox("Include Special Symbols");
        JPanel checkBoxPanel = new JPanel();
        checkBoxPanel.setLayout(new GridLayout(2, 2));
        checkBoxPanel.add(includeSmallLetters);
        checkBoxPanel.add(includeCapitalLetters);
        checkBoxPanel.add(includeNumbers);
        checkBoxPanel.add(includeSpecialSymbols);
        add(checkBoxPanel, gbc);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        generateButton = new JButton("Generate Password");
        generateButton.addActionListener(this);
        add(generateButton, gbc);

        gbc.gridy = 3;
        passwordField = new JTextField(20);
        passwordField.setEditable(false);
        add(passwordField, gbc);

        pack();
        setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == generateButton) {
            int length = Integer.parseInt(lengthField.getText());
            boolean useSmallLetters = includeSmallLetters.isSelected();
            boolean useCapitalLetters = includeCapitalLetters.isSelected();
            boolean useNumbers = includeNumbers.isSelected();
            boolean useSpecialSymbols = includeSpecialSymbols.isSelected();

            String password = generatePassword(length, useSmallLetters, useCapitalLetters, useNumbers, useSpecialSymbols);
            passwordField.setText(password);
        }
    }

    private String generatePassword(int length, boolean useSmallLetters, boolean useCapitalLetters, boolean useNumbers, boolean useSpecialSymbols) {
        StringBuilder passwordBuilder = new StringBuilder();
        Random random = new Random();

        String smallLetters = "abcdefghijklmnopqrstuvwxyz";
        String capitalLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialSymbols = "!@#$%^&*()-_=+[{]}\\|;:'\",<.>/?";

        String allCharacters = "";
        if (useSmallLetters) allCharacters += smallLetters;
        if (useCapitalLetters) allCharacters += capitalLetters;
        if (useNumbers) allCharacters += numbers;
        if (useSpecialSymbols) allCharacters += specialSymbols;

        if (allCharacters.isEmpty()) {
            return "Please select at least one character type.";
        }

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            passwordBuilder.append(allCharacters.charAt(randomIndex));
        }

        return passwordBuilder.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
}
