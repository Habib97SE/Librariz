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

        while (true)
        {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(2, 2));
            panel.setPreferredSize(new Dimension(250, 100));
            panel.add(new JLabel("E-mail:"));
            JTextField emailAddressTextField = new JTextField();
            panel.add(emailAddressTextField);
            panel.add(new JLabel("Password:"));
            JPasswordField passwordField = new JPasswordField();
            panel.add(passwordField);
            int result = JOptionPane.showConfirmDialog(null, panel, "Login:", JOptionPane.OK_CANCEL_OPTION);

            if (!SecureData.isValidEmail(emailAddressTextField.getText()))
            {
                // show error message and ask if user want to cancel or continue
                int option = JOptionPane.showConfirmDialog(null, "Invalid email address. Do you want to continue?", "Error", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION)
                {
                    continue;
                } else
                {
                    break;
                }
            }

            if (result == JOptionPane.OK_OPTION)
            {
                String emailAddress = emailAddressTextField.getText();
                char[] passwordArr = passwordField.getPassword();

                StringBuilder password = new StringBuilder();
                for (char c : passwordArr)
                {
                    password.append(c);
                }
                // Use the input values as needed
                int userID = DatabaseHandling.checkLogin(emailAddress, password.toString());
                if (userID != -1)
                {
                    user = DatabaseHandling.getUserById(userID);
                    new MainGUI(user);
                    break;
                } else
                {
                    int option = JOptionPane.showConfirmDialog(null, "Invalid email address or password. Do you want " +
                            "to continue?", "Error", JOptionPane.YES_NO_OPTION);
                    if (option == JOptionPane.YES_OPTION)
                    {
                        continue;
                    } else
                    {
                        break;
                    }
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
