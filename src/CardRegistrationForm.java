import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CardRegistrationForm extends JFrame {

    private JTextField cardNumberField;
    private JComboBox<String> cardTypeComboBox;
    private JTextArea cardHolderArea;
    private JCheckBox agreeCheckBox;
    private JButton registerButton;

    public CardRegistrationForm() {
        setTitle("Card Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(5, 2));

        JLabel cardNumberLabel = new JLabel("Card Number:");
        cardNumberField = new JTextField();

        JLabel cardTypeLabel = new JLabel("Card Type:");
        String[] cardTypes = {"Visa", "Mastercard", "American Express"};
        cardTypeComboBox = new JComboBox<>(cardTypes);

        JLabel cardHolderLabel = new JLabel("Card Holder:");
        cardHolderArea = new JTextArea();

        JLabel agreeLabel = new JLabel("Agree to Terms:");
        agreeCheckBox = new JCheckBox();

        registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registerCard();
            }
        });

        formPanel.add(cardNumberLabel);
        formPanel.add(cardNumberField);
        formPanel.add(cardTypeLabel);
        formPanel.add(cardTypeComboBox);
        formPanel.add(cardHolderLabel);
        formPanel.add(cardHolderArea);
        formPanel.add(agreeLabel);
        formPanel.add(agreeCheckBox);
        formPanel.add(new JLabel());
        formPanel.add(registerButton);

        add(formPanel, BorderLayout.CENTER);
    }

    private void registerCard() {
        String cardNumber = cardNumberField.getText();
        String cardType = (String) cardTypeComboBox.getSelectedItem();
        String cardHolder = cardHolderArea.getText();
        boolean agreedToTerms = agreeCheckBox.isSelected();

        if (cardNumber.isEmpty() || cardHolder.isEmpty() || !agreedToTerms) {
            JOptionPane.showMessageDialog(this, "Please fill in all the required fields and agree to the terms.");
        } else {
            try {
                FileWriter fileWriter = new FileWriter("card_data.txt", true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println("Card Number: " + cardNumber);
                printWriter.println("Card Type: " + cardType);
                printWriter.println("Card Holder: " + cardHolder);
                printWriter.println();
                printWriter.close();
                JOptionPane.showMessageDialog(this, "Card registered successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "Error saving card data.");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                CardRegistrationForm registrationForm = new CardRegistrationForm();
                registrationForm.setVisible(true);
            }
        });
    }
}