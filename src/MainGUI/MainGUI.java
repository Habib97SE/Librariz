package MainGUI;

import User.User;
import MainGUI.CustomMenuBar;


import javax.swing.*;

public class MainGUI
{
    private final int WIDTH = 800;
    private final int HEIGHT = 800;
    private User user;

    public MainGUI (User user)
    {

        this.user = user;

        CustomMenuBar customMenuBar = new CustomMenuBar(user);
        String[] menuNames = {"User", "Book", "Borrowing"};
        String[][] menuItems = {{"Add new user", "Edit user", "Delete user", "Show user",
                "Show users"}, {"Find book", "Add new book", "Edit book", "Delete book", "Show books"},
                {"Borrow book", "Return book", "Show borrowings"}};
        JMenuBar menuBar = customMenuBar.createMenuBar("Menu Bar", menuNames, menuItems);


        JFrame frame = new JFrame("Library Management System");
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
