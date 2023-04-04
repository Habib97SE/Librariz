import Database.DatabaseHandling;
import Book.*;
import MainGUI.*;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import User.View;

public class Main
{
    public static void main (String[] argh)
    {


        JFrame mainFrame = new JFrame("Library Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();

        JButton addUserButton = new JButton("Add User");
        JButton editUserButton = new JButton("Edit User");
        JButton deleteUserButton = new JButton("Delete User");
        JButton viewUserButton = new JButton("View User");
        JButton viewUsersButton = new JButton("View Users");

        panel.add(addUserButton);
        panel.add(editUserButton);
        panel.add(deleteUserButton);
        panel.add(viewUserButton);
        panel.add(viewUsersButton);

        mainFrame.add(panel, BorderLayout.NORTH);
        addUserButton.addActionListener(e ->
        {
            View view = new User.View();
            view.addUser();
        });

        editUserButton.addActionListener(e ->
        {
            User.View userView = new User.View();
            userView.editUser();
        });

        deleteUserButton.addActionListener(e ->
        {
            User.View view = new User.View();
            view.deleteUser();
        });

        viewUserButton.addActionListener(e ->
        {
            User.View view = new User.View();
            try
            {
                view.showUser();
            } catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }
        });

        viewUsersButton.addActionListener(e ->
        {
            User.View view = new User.View();
            view.showUsers();
        });

        mainFrame.setVisible(true);

    }
}