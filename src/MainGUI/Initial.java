package MainGUI;

import Database.DatabaseHandling;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

import Database.SecureData;
import User.User;

public class Initial
{
    private User user;

    public Initial () throws Exception
    {
        // ask user for their username and password and check if they are valid and if they are, then log them in
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);

        while (true)
        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.add(new JLabel("E-mail:"));
            JTextField emailAddressTextField = new JTextField();
            panel.add(emailAddressTextField);
            panel.add(new JLabel("Password:"));
            JTextField passwordField = new JPasswordField();
            panel.add(passwordField);

            int result = JOptionPane.showConfirmDialog(null, panel, "Login:", JOptionPane.OK_CANCEL_OPTION);

            if (!SecureData.isValidEmail(emailAddressTextField.getText()))
            {
                JOptionPane.showMessageDialog(null, "Invalid email address");
                continue;
            }

            if (result == JOptionPane.OK_OPTION)
            {
                String emailAddress = emailAddressTextField.getText();
                String password = passwordField.getText();
                // Use the input values as needed
                int userID = DatabaseHandling.checkLogin(emailAddress, SecureData.encrypt(password));
                if (userID != -1)
                {
                    user = DatabaseHandling.getUser(Integer.toString(userID));
                    new MainGUI(user);
                    break;
                } else
                {
                    JOptionPane.showMessageDialog(null, "Invalid username or password");
                }
            }
        } // end of while loop

        // call the MainGUI
        if (user != null)
        {
            MainGUI mainGUI = new MainGUI(user);
        }

    }



}
