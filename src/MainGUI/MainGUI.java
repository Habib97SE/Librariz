package MainGUI;

import User.User;
import MainGUI.CustomMenuBar;


import javax.swing.*;
import java.util.Objects;

public class MainGUI
{
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private User user;

    public MainGUI (User user)
    {
        JFrame frame = new JFrame("Library Management System");

        this.user = user;

        CustomMenuBar customMenuBar = new CustomMenuBar(user);

        JMenuBar menuBar = null;
        if (Objects.equals(this.user.getUserType(), "0"))
        {
            menuBar = customMenuBar.createAdminMenuBar();
        }
        else if (Objects.equals(this.user.getUserType(), "1"))
        {
            menuBar = customMenuBar.createLibrarianMenuBar();
        }
        else if (Objects.equals(this.user.getUserType(), "2"))
        {
            menuBar = customMenuBar.createBorrowerMenuBar();
        }


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setJMenuBar(menuBar);

        JLabel fullNameLabel = new JLabel(user.getFirstName() + " " + user.getLastName());
        JLabel currentDebtLabel = new JLabel("Current debt: " + user.getCurrentFine());


        frame.add(fullNameLabel);
        frame.add(currentDebtLabel);
        frame.setVisible(true);

    }
}
