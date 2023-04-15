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

    public Initial() throws Exception {
        User user = null;
        boolean isValidEmail = false;

        while (true) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.setPreferredSize(new Dimension(350, 100));
            panel.add(new JLabel("E-mail:"));
            JTextField emailAddressTextField = new JTextField();
            panel.add(emailAddressTextField);
            panel.add(new JLabel("Password:"));
            JPasswordField passwordField = new JPasswordField();
            panel.add(passwordField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Login:", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.CANCEL_OPTION) {
                break;
            }

            String emailAddress = emailAddressTextField.getText();
            char[] passwordArr = passwordField.getPassword();
            String password = new String(passwordArr);

            if (!SecureData.isValidEmail(emailAddress)) {
                int option = JOptionPane.showConfirmDialog(null, "Invalid email address. Do you want to continue?", "Error", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    continue;
                } else {
                    break;
                }
            }

            int userID = DatabaseHandling.checkLogin(emailAddress, password);
            if (userID != -1) {
                user = DatabaseHandling.getUserById(userID);
                new MainGUI(user);
                break;
            } else {
                int option = JOptionPane.showConfirmDialog(null, "Invalid email address or password. Do you want to continue?", "Error", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    continue;
                } else {
                    break;
                }
            }
        }

        // Call the MainGUI
        if (user != null) {
            MainGUI mainGUI = new MainGUI(user);
        }
    }


}
