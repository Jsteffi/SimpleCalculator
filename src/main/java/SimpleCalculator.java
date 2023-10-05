/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Justin
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleCalculator extends JFrame {
    private JTextField textField;
    private double firstOperand = 0;
    private char operation = ' ';

    public SimpleCalculator() {
        // Set up the JFrame
        setTitle("Simple Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 400);
        setLayout(new BorderLayout());

        // Create the text field for displaying the result
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        add(textField, BorderLayout.NORTH);

        // Create the panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));
        add(buttonPanel, BorderLayout.CENTER);

        // Define button labels
        String[] buttonLabels = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        // Add buttons to the panel
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.PLAIN, 20));
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        // Make the JFrame visible
        setVisible(true);
    }

    // ActionListener for button clicks
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if (command.matches("[0-9]")) {
                textField.setText(textField.getText() + command);
            } else if (command.equals("C")) {
                textField.setText("");
                firstOperand = 0;
                operation = ' ';
            } else if (command.equals("=")) {
                try {
                    double secondOperand = Double.parseDouble(textField.getText());
                    double result = performCalculation(firstOperand, secondOperand, operation);
                    textField.setText(Double.toString(result));
                    firstOperand = result;
                    operation = ' ';
                } catch (NumberFormatException ex) {
                    textField.setText("Error");
                }
            } else {
                firstOperand = Double.parseDouble(textField.getText());
                operation = command.charAt(0);
                textField.setText("");
            }
        }
    }

    // Perform the calculation based on the selected operation
    private double performCalculation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    return Double.POSITIVE_INFINITY; // Division by zero error
                }
            default:
                return operand2;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SimpleCalculator());
    }
}
