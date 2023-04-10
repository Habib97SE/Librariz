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


        String personalNumber = JOptionPane.showInputDialog("Enter personal number: ");


        String encryptedPersonalNumber = SecureData.encrypt(personalNumber);

        User user = controller.getUser(encryptedPersonalNumber);
        if (user != null)
        {
            JFrame showUserframe = new JFrame("User");
            showUserframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            showUserframe.setSize(500, 500);
            showUserframe.setLayout(new GridLayout(12, 2));

            JLabel firstNameLabel = new JLabel("First name: ");
            JLabel firstName = new JLabel(user.getFirstName());
            JLabel lastNameLabel = new JLabel("Last name: ");
            JLabel lastName = new JLabel(user.getLastName());
            JLabel emailAddressLabel = new JLabel("Email address: ");
            JLabel emailAddress = new JLabel(user.getEmailAddress());
            JLabel phoneNumberLabel = new JLabel("Phone number: ");
            JLabel phoneNumber = new JLabel(user.getPhoneNumber());
            JLabel homeAddressLabel = new JLabel("Home address: ");
            JLabel homeAddress = new JLabel(user.getHomeAddress());
            JLabel zipCodeLabel = new JLabel("Zip code: ");
            JLabel zipCode = new JLabel(user.getZipCode());
            JLabel cityLabel = new JLabel("City: ");
            JLabel city = new JLabel(user.getCity());
            JLabel identifierNumberLabel = new JLabel("Identifier number: ");
            JLabel identifierNumber = new JLabel(user.getIdentifierNumber());
            JLabel currentFineLabel = new JLabel("Current fine: ");
            JLabel currentFine = new JLabel(String.valueOf(user.getCurrentFine()));
            JLabel userTypeLabel = new JLabel("User type: ");
            JLabel userType = new JLabel(user.getUserType());
            JButton closeButton = new JButton("Close");

            showUserframe.add(firstNameLabel);
            showUserframe.add(firstName);
            showUserframe.add(lastNameLabel);
            showUserframe.add(lastName);
            showUserframe.add(emailAddressLabel);
            showUserframe.add(emailAddress);
            showUserframe.add(phoneNumberLabel);
            showUserframe.add(phoneNumber);
            showUserframe.add(homeAddressLabel);
            showUserframe.add(homeAddress);
            showUserframe.add(zipCodeLabel);
            showUserframe.add(zipCode);
            showUserframe.add(cityLabel);
            showUserframe.add(city);
            showUserframe.add(identifierNumberLabel);
            showUserframe.add(identifierNumber);
            showUserframe.add(currentFineLabel);
            showUserframe.add(currentFine);
            showUserframe.add(userTypeLabel);
            showUserframe.add(userType);
            showUserframe.add(closeButton);

            showUserframe.setVisible(true);

            closeButton.addActionListener(e -> showUserframe.dispose());


        } else
        {
            JOptionPane.showMessageDialog(null, "User not found");
        }
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
        // Get user by personal number
        JFrame getUserFrame = new JFrame("Get user");
        getUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getUserFrame.setSize(WIDTH, HEIGHT);
        getUserFrame.setLayout(new GridLayout(2, 2));
        User user = controller.getUser();

        AtomicReference<JLabel> personalNumberLabel = new AtomicReference<>(new JLabel("Personal number: "));
        AtomicReference<JTextField> personalNumberTextfield = new AtomicReference<>(new JTextField());
        JButton getUserButton = new JButton("Get user");

        getUserFrame.add(personalNumberLabel.get());
        getUserFrame.add(personalNumberTextfield.get());
        getUserFrame.add(getUserButton);

        getUserButton.addActionListener(e ->
        {
            String personalNumber = personalNumberTextfield.get().getText();
            AtomicReference<User> userToEdit = null;
            try
            {
                userToEdit = new AtomicReference<User>(controller.getUser(personalNumber));
            } catch (Exception ex)
            {
                throw new RuntimeException(ex);
            }

            if (userToEdit.get() != null)
            {
                // Edit user
                JFrame editUserFrame = new JFrame("Edit user");
                editUserFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                editUserFrame.setSize(500, 500);
                editUserFrame.setLayout(new GridLayout(2, 2));

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
                JLabel identifierNumberLabel = new JLabel("Identifier number: ");
                JTextField identifierNumber = new JTextField();
                JLabel currentFineLabel = new JLabel("Current fine: ");
                JTextField currentFine = new JTextField();
                JLabel userTypeLabel = new JLabel("User type: ");
                JTextField userType = new JTextField();
                personalNumberLabel.set(new JLabel("Personal number: "));
                personalNumberTextfield.set(new JTextField());

                JButton editUserButton = new JButton("Edit user");

                editUserFrame.add(firstNameLabel);
                editUserFrame.add(firstName);
                editUserFrame.add(lastNameLabel);
                editUserFrame.add(lastName);
                editUserFrame.add(emailAddressLabel);
                editUserFrame.add(emailAddress);
                editUserFrame.add(phoneNumberLabel);
                editUserFrame.add(phoneNumber);
                editUserFrame.add(homeAddressLabel);
                editUserFrame.add(homeAddress);
                editUserFrame.add(zipCodeLabel);
                editUserFrame.add(zipCode);
                editUserFrame.add(cityLabel);
                editUserFrame.add(city);
                editUserFrame.add(identifierNumberLabel);
                editUserFrame.add(identifierNumber);
                editUserFrame.add(currentFineLabel);
                editUserFrame.add(currentFine);
                editUserFrame.add(userTypeLabel);
                editUserFrame.add(userType);
                editUserFrame.add(personalNumberLabel.get());
                editUserFrame.add(personalNumberTextfield.get());
                editUserFrame.add(editUserButton);

                editUserButton.addActionListener(e1 ->
                {
                    User editedUser = new User();
                    editedUser.setFirstName(firstName.getText());
                    editedUser.setLastName(lastName.getText());
                    editedUser.setEmailAddress(emailAddress.getText());
                    editedUser.setPhoneNumber(phoneNumber.getText());
                    editedUser.setHomeAddress(homeAddress.getText());
                    editedUser.setZipCode(zipCode.getText());
                    editedUser.setCity(city.getText());
                    editedUser.setIdentifierNumber(identifierNumber.getText());
                    editedUser.setCurrentFine(Double.parseDouble(currentFine.getText()));
                    editedUser.setUserType(userType.getText());
                    editedUser.setPersonalNumber(personalNumberTextfield.get().getText());

                    if (controller.editUser(user))
                    {
                        JOptionPane.showMessageDialog(null, "User edited, click OK to continue", "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        editUserFrame.dispose();
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "User not edited", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
            } else
            {
                JOptionPane.showMessageDialog(null, "User not found", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        getUserFrame.setVisible(true);
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
