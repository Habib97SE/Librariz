package Borrowing;

import Database.SecureData;

import javax.swing.*;

public class View
{
    public View ()
    {
    }

    public void borrowBook () throws Exception
    {
        String personalNumber = JOptionPane.showInputDialog("Enter personal number");
        String encryptedPersonalNumber = SecureData.encrypt(personalNumber);
        User user =
    }
}
