package User;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class View
{
    private String userType;
    private String emailAddress;
    private String password;

    public View (String userType, String emailAddress, String password)
    {
        this.userType = userType;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public void showUser ()
    {
        JFrame frame = new JFrame("Show user information");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new GridLayout(12, 2));

        JLabel personalNumberLabel = new JLabel("Personal number: ");
        JTextField personalNumber = new JTextField();
        JButton searchButton = new JButton("Search");


        Controller controller = new Controller();
        User user = controller.getUser(personalNumber.getText());
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
            personalNumberLabel = new JLabel("Personal number: ");
            JLabel personalNumberLabelValue = new JLabel(user.getPersonalNumber());

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
            showUserframe.add(personalNumberLabel);
            showUserframe.add(personalNumberLabelValue);
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
        Controller controller = new Controller();
        ArrayList<User> users = controller.getAllUsers();
        if (users != null)
        {
            JFrame frame = new JFrame("Users");
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setSize(500, 500);
            frame.setLayout(new GridLayout(users.size() + 1, 12));

            JLabel firstNameLabel = new JLabel("First name");
            JLabel lastNameLabel = new JLabel("Last name");
            JLabel emailAddressLabel = new JLabel("Email address");
            JLabel phoneNumberLabel = new JLabel("Phone number");
            JLabel homeAddressLabel = new JLabel("Home address");
            JLabel zipCodeLabel = new JLabel("Zip code");
            JLabel cityLabel = new JLabel("City");
            JLabel identifierNumberLabel = new JLabel("Identifier number");
            JLabel currentFineLabel = new JLabel("Current fine");
            JLabel userTypeLabel = new JLabel("User type");
            JLabel personalNumberLabel = new JLabel("Personal number");
            JLabel copyLabel = new JLabel("Copy");

            frame.add(firstNameLabel);
            frame.add(lastNameLabel);
            frame.add(emailAddressLabel);
            frame.add(phoneNumberLabel);
            frame.add(homeAddressLabel);
            frame.add(zipCodeLabel);
            frame.add(cityLabel);
            frame.add(identifierNumberLabel);
            frame.add(currentFineLabel);
            frame.add(userTypeLabel);
            frame.add(personalNumberLabel);
            frame.add(copyLabel);

            for (User user : users)
            {
                JLabel firstName = new JLabel(user.getFirstName());
                JLabel lastName = new JLabel(user.getLastName());
                JLabel emailAddress = new JLabel(user.getEmailAddress());
                JLabel phoneNumber = new JLabel(user.getPhoneNumber());
                JLabel homeAddress = new JLabel(user.getHomeAddress());
                JLabel zipCode = new JLabel(user.getZipCode());
                JLabel city = new JLabel(user.getCity());
                JLabel identifierNumber = new JLabel(user.getIdentifierNumber());
                JLabel currentFine = new JLabel(String.valueOf(user.getCurrentFine()));
                JLabel userType = new JLabel(user.getUserType());
                JLabel personalNumber = new JLabel(user.getPersonalNumber());
                JLabel copy = new JLabel("Copy");

                copy.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked (MouseEvent e)
                    {
                        StringSelection stringSelection = new StringSelection(personalNumber.getText());
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);
                    }
                });

                frame.add(firstName);
                frame.add(lastName);
                frame.add(emailAddress);
                frame.add(phoneNumber);
                frame.add(homeAddress);
                frame.add(zipCode);
                frame.add(city);
                frame.add(identifierNumber);
                frame.add(currentFine);
                frame.add(userType);
                frame.add(personalNumber);
                frame.add(copy);


                // add action listener to copy cell content to clipboard on click


            }
        } else
        {
            JOptionPane.showMessageDialog(null, "No users found");
        }
    }

    public void addUser ()
    {
        JFrame frame = new JFrame("Add user");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(500, 500);
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
        JLabel identifierNumberLabel = new JLabel("Identifier number: ");
        JTextField identifierNumber = new JTextField();
        JLabel currentFineLabel = new JLabel("Current fine: ");
        JTextField currentFine = new JTextField();
        JLabel userTypeLabel = new JLabel("User type: ");
        JTextField userType = new JTextField();
        JLabel personalNumberLabel = new JLabel("Personal number: ");
        JTextField personalNumber = new JTextField();

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
        frame.add(identifierNumberLabel);
        frame.add(identifierNumber);
        frame.add(currentFineLabel);
        frame.add(currentFine);
        frame.add(userTypeLabel);
        frame.add(userType);
        frame.add(personalNumberLabel);
        frame.add(personalNumber);
        frame.add(addUserButton);

        addUserButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                Controller controller = new Controller();
                User user = new User();
                user.setFirstName(firstName.getText());
                user.setLastName(lastName.getText());
                user.setEmailAddress(emailAddress.getText());
                user.setPhoneNumber(phoneNumber.getText());
                user.setHomeAddress(homeAddress.getText());
                user.setZipCode(zipCode.getText());
                user.setCity(city.getText());
                user.setIdentifierNumber(identifierNumber.getText());
                user.setCurrentFine(Double.parseDouble(currentFine.getText()));
                user.setUserType(userType.getText());
                user.setPersonalNumber(personalNumber.getText());

                controller.addUser(user);
                frame.dispose();
            }
        });

    }

}
