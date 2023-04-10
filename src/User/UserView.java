package User;

import Borrowing.Borrowing;
import Database.SecureData;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;


public class UserView
{
    private final int WIDTH = 800;
    private final int HEIGHT = 800;

    private Controller controller;
    private User user;

    public UserView (User user)
    {
        this.user = user;
        this.controller = new Controller(user);
    }

    public UserView ()
    {
        controller = new Controller();
    }


    public void showUser () throws Exception
    {
        JFrame frame = new JFrame("Show user information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new GridLayout(12, 2));


        String columnNames[] = {"First name", "Last name", "Email address", "Phone number", "Home address", "Zip code", "City", "Identifier number", "Current fine"};
        String data[][] = new String[1][9];

        user = controller.getUserById(Integer.toString(user.getUserID()));

        data[0][0] = user.getFirstName();
        data[0][1] = user.getLastName();
        data[0][2] = user.getEmailAddress();
        data[0][3] = user.getPhoneNumber();
        data[0][4] = user.getHomeAddress();
        data[0][5] = user.getZipCode();
        data[0][6] = user.getCity();
        data[0][7] = user.getIdentifierNumber();
        data[0][8] = String.valueOf(user.getCurrentFine());

        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        table.setEnabled(false);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);
        frame.setVisible(true);
    }

    public void showUsers ()
    {

        ArrayList<User> users = controller.getAllUsers();
        if (users != null)
        {
            JFrame frame = new JFrame("Users");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(WIDTH, HEIGHT);
            frame.setLayout(new GridLayout(users.size() + 1, 12));

            String columnNames[] = {"First name", "Last name", "Email address", "Phone number", "Home address", "Zip code", "City", "Identifier number", "Current fine", "User type"};
            String data[][] = new String[users.size()][10];

            for (int i = 0; i < users.size(); i++)
            {
                data[i][0] = users.get(i).getFirstName();
                data[i][1] = users.get(i).getLastName();
                data[i][2] = users.get(i).getEmailAddress();
                data[i][3] = users.get(i).getPhoneNumber();
                data[i][4] = users.get(i).getHomeAddress();
                data[i][5] = users.get(i).getZipCode();
                data[i][6] = users.get(i).getCity();
                data[i][7] = users.get(i).getIdentifierNumber();
                data[i][8] = String.valueOf(users.get(i).getCurrentFine());
                data[i][9] = users.get(i).getUserType();
            }

            JTable table = new JTable(data, columnNames);
            JScrollPane scrollPane = new JScrollPane(table);
            frame.add(scrollPane);

            frame.setVisible(true);

        } else
        {
            JOptionPane.showMessageDialog(null, "No users found");
        }
    }

    /**
     * Adds a new user to the database and displays a message if the user was successfully added or not to the database
     */
    public void addUser ()
    {
        JFrame frame = new JFrame("Add user");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new GridLayout(12, 2));

        JLabel firstNameLabel = new JLabel("First name: ");
        JTextField firstName = new JTextField();
        JLabel lastNameLabel = new JLabel("Last name: ");
        JTextField lastName = new JTextField();
        JLabel emailAddressLabel = new JLabel("Email address: ");
        JTextField emailAddress = new JTextField();
        JLabel phoneNumberLabel = new JLabel("Phone number: ");
        JTextField phoneNumber = new JTextField();
        JLabel homeAddressLabel = new JLabel("Home address: ");
        JTextField homeAddress = new JTextField();
        JLabel zipCodeLabel = new JLabel("Zip code: ");
        JTextField zipCode = new JTextField();
        JLabel cityLabel = new JLabel("City: ");
        JTextField city = new JTextField();
        JLabel personalNumberLabel = new JLabel("Personal number: ");
        JTextField personalNumber = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField password = new JPasswordField();

        JButton addUserButton = new JButton("Add user");


        frame.add(firstNameLabel);
        frame.add(firstName);
        frame.add(lastNameLabel);
        frame.add(lastName);
        frame.add(emailAddressLabel);
        frame.add(emailAddress);
        frame.add(phoneNumberLabel);
        frame.add(phoneNumber);
        frame.add(homeAddressLabel);
        frame.add(homeAddress);
        frame.add(zipCodeLabel);
        frame.add(zipCode);
        frame.add(cityLabel);
        frame.add(city);
        frame.add(personalNumberLabel);
        frame.add(personalNumber);
        frame.add(passwordLabel);
        frame.add(password);
        frame.add(addUserButton);


        addUserButton.addActionListener(e ->
        {

            // Secure data
            Controller.Error error = controller.validateInputs();

            switch (error)
            {
                case USER_EXISTS -> JOptionPane.showMessageDialog(null, "User already exists", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case FIRST_NAME -> JOptionPane.showMessageDialog(null, "First name is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case LAST_NAME -> JOptionPane.showMessageDialog(null, "Last name is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case EMAIL_ADDRESS -> JOptionPane.showMessageDialog(null, "Email address is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case PHONE_NUMBER -> JOptionPane.showMessageDialog(null, "Phone number is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case HOME_ADDRESS -> JOptionPane.showMessageDialog(null, "Home address is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case ZIP_CODE -> JOptionPane.showMessageDialog(null, "Zip code is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case CITY -> JOptionPane.showMessageDialog(null, "City is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case IDENTIFIER_NUMBER -> JOptionPane.showMessageDialog(null, "Identifier number is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case CURRENT_FINE -> JOptionPane.showMessageDialog(null, "Current fine is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case USER_TYPE -> JOptionPane.showMessageDialog(null, "User type is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                case PERSONAL_NUMBER -> JOptionPane.showMessageDialog(null, "Personal number is not valid", "Error",
                        JOptionPane.ERROR_MESSAGE);
                default ->
                {
                    break;
                }
            }


            User user = new User();
            user.setFirstName(firstName.getText());
            user.setLastName(lastName.getText());
            user.setEmailAddress(emailAddress.getText());
            user.setPhoneNumber(phoneNumber.getText());
            user.setHomeAddress(homeAddress.getText());
            user.setZipCode(zipCode.getText());
            user.setCity(city.getText());
            user.setPersonalNumber(personalNumber.getText());
            user.setPassword(password.getText());

            try
            {
                if (controller.addUser(user))
                {
                    JOptionPane.showMessageDialog(null, "User added, click OK to continue", "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose();
                } else
                {
                    JOptionPane.showMessageDialog(null, "User not added", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex)
            {

            }
            // set frame visible
        });
        frame.setVisible(true);
    }

    public void deleteUser ()
    {
        JFrame frame = new JFrame("Delete user");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new GridLayout(2, 2));

        JLabel personalNumberLabel = new JLabel("Personal number: ");
        JTextField personalNumber = new JTextField();

        JButton deleteUserButton = new JButton("Delete user");

        frame.add(personalNumberLabel);
        frame.add(personalNumber);
        frame.add(deleteUserButton);

        deleteUserButton.addActionListener(e ->
        {
            User user = new User();

            if (controller.deleteUser())
            {
                JOptionPane.showMessageDialog(null, "User deleted, click OK to continue", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else
            {
                JOptionPane.showMessageDialog(null, "User not deleted", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.setVisible(true);
    }

    public void editUser ()
    {
        JFrame frame = new JFrame("Edit user");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new GridLayout(6, 2));

        JLabel phoneNumberTextField = new JLabel("Phone number: ");
        JTextField phoneNumber = new JTextField();
        JLabel emailAddressLabel = new JLabel("Email address: ");
        JTextField emailAddress = new JTextField();
        JLabel homeAddressLabel = new JLabel("Home address: ");
        JTextField homeAddress = new JTextField();
        JLabel zipCodeLabel = new JLabel("Zip code: ");
        JTextField zipCode = new JTextField();
        JLabel cityLabel = new JLabel("City: ");
        JTextField city = new JTextField();
        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField();

        StringBuilder password = new StringBuilder();

        for (char c : passwordField.getPassword())
        {
            password.append(c);
        }


        JButton editUserButton = new JButton("Edit user");
        JButton cancelButton = new JButton("Cancel");

        frame.add(phoneNumberTextField);
        frame.add(phoneNumber);
        frame.add(emailAddressLabel);
        frame.add(emailAddress);
        frame.add(homeAddressLabel);
        frame.add(homeAddress);
        frame.add(zipCodeLabel);
        frame.add(zipCode);
        frame.add(cityLabel);
        frame.add(city);
        frame.add(passwordLabel);
        frame.add(passwordField);

        frame.add(cancelButton);
        frame.add(editUserButton);

        cancelButton.addActionListener(e ->
        {
            frame.dispose();
        });

        editUserButton.addActionListener(e ->
        {
            User editedUser = new User();
            editedUser.setFirstName(user.getFirstName());
            editedUser.setLastName(user.getLastName());
            editedUser.setEmailAddress(emailAddress.getText());
            editedUser.setPhoneNumber(phoneNumber.getText());
            editedUser.setHomeAddress(homeAddress.getText());
            editedUser.setZipCode(zipCode.getText());
            editedUser.setCity(city.getText());
            editedUser.setPersonalNumber(user.getPersonalNumber());
            editedUser.setPassword(password.toString());
            editedUser.setUserType(user.getUserType());
            editedUser.setUserID(user.getUserID());
            editedUser.setIdentifierNumber(user.getIdentifierNumber());
            editedUser.setCurrentFine(user.getCurrentFine());

            if (controller.editUser(editedUser))
            {
                JOptionPane.showMessageDialog(null, "User edited, click OK to continue", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            } else
            {
                JOptionPane.showMessageDialog(null, "User not edited", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        frame.setVisible(true);
    }


    /**
     * Show borrowings history
     */
    public void showBorrowingsHistory ()
    {
        JFrame frame = new JFrame("Borrowings history");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(WIDTH, HEIGHT);
        frame.setLayout(new GridLayout(2, 2));

        ArrayList<Borrowing> borrowings = controller.getBorrowingsHistory();
        String fullName = this.user.getFullName();
        // make first letter of first name and last name uppercase
        fullName = fullName.substring(0, 1).toUpperCase() + fullName.substring(1);
        JLabel fullNameLabel = new JLabel(fullName + "'s borrowings history");

        String[] columnNames = {"Book title", "Borrowing date", "Return date"};
        Object[][] data = new Object[borrowings.size()][3];
        for (int i = 0; i < borrowings.size(); i++)
        {
            data[i][0] = borrowings.get(i).getBook().getTitle();
            data[i][1] = borrowings.get(i).getBorrowingDate();
            data[i][2] = borrowings.get(i).getReturnDate();
        }
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(fullNameLabel);
        frame.add(scrollPane);
        frame.setVisible(true);

    }

}
