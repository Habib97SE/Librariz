package User;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;


public class View
{
    private String userType;
    private String emailAddress;
    private String password;

    public void addNewBorrower ()
    {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JLabel firstNameLabel = new JLabel("First Name: ");
        JTextField firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameField = new JTextField(20);

        JLabel personalNumberLabel = new JLabel("Personal Number: ");
        JTextField personalNumberField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email Address: ");
        JTextField emailField = new JTextField(20);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        JTextField phoneNumberField = new JTextField(20);

        JLabel homeAddressLabel = new JLabel("Home Address: ");
        JTextField homeAddressField = new JTextField(20);

        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        JTextField zipCodeField = new JTextField(20);

        JLabel cityLabel = new JLabel("City: ");
        JTextField cityField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        JButton resetButton = new JButton("Reset");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(personalNumberLabel);
        panel.add(personalNumberField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(homeAddressLabel);
        panel.add(homeAddressField);
        panel.add(zipCodeLabel);
        panel.add(zipCodeField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);
        panel.add(resetButton);


        // add action listener to back button
        backButton.addActionListener(e ->
        {
            frame.dispose();
            new MainGUI.Initial();
        });

        // add action listener to reset button
        resetButton.addActionListener(e ->
        {
            firstNameField.setText("");
            lastNameField.setText("");
            personalNumberField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            homeAddressField.setText("");
            zipCodeField.setText("");
            cityField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });


        // add action listener to register button
        registerButton.addActionListener(e ->
        {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String personalNumber = personalNumberField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String homeAddress = homeAddressField.getText();
            String zipCode = zipCodeField.getText();
            String city = cityField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
        });

        frame.add(panel);
        frame.setVisible(true);

    }

    public void addNewLibrarian ()
    {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JLabel firstNameLabel = new JLabel("First Name: ");
        JTextField firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameField = new JTextField(20);

        JLabel personalNumberLabel = new JLabel("Personal Number: ");
        JTextField personalNumberField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email Address: ");
        JTextField emailField = new JTextField(20);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        JTextField phoneNumberField = new JTextField(20);

        JLabel homeAddressLabel = new JLabel("Home Address: ");
        JTextField homeAddressField = new JTextField(20);

        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        JTextField zipCodeField = new JTextField(20);

        JLabel cityLabel = new JLabel("City: ");
        JTextField cityField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        JButton resetButton = new JButton("Reset");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(personalNumberLabel);
        panel.add(personalNumberField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(homeAddressLabel);
        panel.add(homeAddressField);
        panel.add(zipCodeLabel);
        panel.add(zipCodeField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);
        panel.add(resetButton);

        // add action listener to back button
        backButton.addActionListener(e ->
        {
            frame.dispose();
            new MainGUI.Initial();
        });

        // add action listener to reset button
        resetButton.addActionListener(e ->
        {
            firstNameField.setText("");
            lastNameField.setText("");
            personalNumberField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            homeAddressField.setText("");
            zipCodeField.setText("");
            cityField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        // add action listener to register button
        registerButton.addActionListener(e ->
        {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String personalNumber = personalNumberField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String homeAddress = homeAddressField.getText();
            String zipCode = zipCodeField.getText();
            String city = cityField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
        });
    }

    /**
     * <p>
     * This method is used to edit the user information in the database and the GUI interface.
     * </p>
     * <p>
     * Example of how to use this method:
     * <pre>
     *          {@code
     *          new MainGUI.EditUser(userType);
     *          }
     *          </pre>
     * This will open the edit user interface.
     * </p>
     */
    public void editUser ()
    {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JLabel firstNameLabel = new JLabel("First Name: ");
        JTextField firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameField = new JTextField(20);

        JLabel personalNumberLabel = new JLabel("Personal Number: ");
        JTextField personalNumberField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email Address: ");
        JTextField emailField = new JTextField(20);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        JTextField phoneNumberField = new JTextField(20);

        JLabel homeAddressLabel = new JLabel("Home Address: ");
        JTextField homeAddressField = new JTextField(20);

        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        JTextField zipCodeField = new JTextField(20);

        JLabel cityLabel = new JLabel("City: ");
        JTextField cityField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        JButton resetButton = new JButton("Reset");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(personalNumberLabel);
        panel.add(personalNumberField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(homeAddressLabel);
        panel.add(homeAddressField);
        panel.add(zipCodeLabel);
        panel.add(zipCodeField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);
        panel.add(resetButton);

        // add action listener to back button
        backButton.addActionListener(e ->
        {
            frame.dispose();
            new MainGUI.Initial();
        });

        // add action listener
        resetButton.addActionListener(e ->
        {
            firstNameField.setText("");
            lastNameField.setText("");
            personalNumberField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            homeAddressField.setText("");
            zipCodeField.setText("");
            cityField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        // add action listener to register button
        registerButton.addActionListener(e ->
        {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String personalNumber = personalNumberField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String homeAddress = homeAddressField.getText();
            String zipCode = zipCodeField.getText();
            String city = cityField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
        });

        frame.add(panel);
        frame.setVisible(true);
    }

    /**
     * <p>
     * This method is used to delete the user information in the database and the GUI interface.
     * </p>
     * <p>
     * <pre>
     *         Example of how to use this method:
     *                              {@code
     *                                  new MainGUI.DeleteUser(userType);
     *                                  }
     *
     *     </pre>
     * </p>
     */
    public void deleteUser ()
    {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        JLabel firstNameLabel = new JLabel("First Name: ");
        JTextField firstNameField = new JTextField(20);

        JLabel lastNameLabel = new JLabel("Last Name: ");
        JTextField lastNameField = new JTextField(20);

        JLabel personalNumberLabel = new JLabel("Personal Number: ");
        JTextField personalNumberField = new JTextField(20);

        JLabel emailLabel = new JLabel("Email Address: ");
        JTextField emailField = new JTextField(20);

        JLabel phoneNumberLabel = new JLabel("Phone Number: ");
        JTextField phoneNumberField = new JTextField(20);

        JLabel homeAddressLabel = new JLabel("Home Address: ");
        JTextField homeAddressField = new JTextField(20);

        JLabel zipCodeLabel = new JLabel("Zip Code: ");
        JTextField zipCodeField = new JTextField(20);

        JLabel cityLabel = new JLabel("City: ");
        JTextField cityField = new JTextField(20);

        JLabel passwordLabel = new JLabel("Password: ");
        JPasswordField passwordField = new JPasswordField(20);

        JLabel confirmPasswordLabel = new JLabel("Confirm Password: ");
        JPasswordField confirmPasswordField = new JPasswordField(20);

        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back");
        JButton resetButton = new JButton("Reset");

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(firstNameLabel);
        panel.add(firstNameField);
        panel.add(lastNameLabel);
        panel.add(lastNameField);
        panel.add(personalNumberLabel);
        panel.add(personalNumberField);
        panel.add(emailLabel);
        panel.add(emailField);
        panel.add(phoneNumberLabel);
        panel.add(phoneNumberField);
        panel.add(homeAddressLabel);
        panel.add(homeAddressField);
        panel.add(zipCodeLabel);
        panel.add(zipCodeField);
        panel.add(cityLabel);
        panel.add(cityField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(confirmPasswordLabel);
        panel.add(confirmPasswordField);
        panel.add(registerButton);
        panel.add(backButton);
        panel.add(resetButton);

        // add action listener to back button
        backButton.addActionListener(e ->
        {
            frame.dispose();
            new MainGUI.Initial();
        });

        // add action listener
        resetButton.addActionListener(e ->
        {
            firstNameField.setText("");
            lastNameField.setText("");
            personalNumberField.setText("");
            emailField.setText("");
            phoneNumberField.setText("");
            homeAddressField.setText("");
            zipCodeField.setText("");
            cityField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");
        });

        // add action listener to register button
        registerButton.addActionListener(e ->
        {
            String firstName = firstNameField.getText();
            String lastName = lastNameField.getText();
            String personalNumber = personalNumberField.getText();
            String email = emailField.getText();
            String phoneNumber = phoneNumberField.getText();
            String homeAddress = homeAddressField.getText();
            String zipCode = zipCodeField.getText();
            String city = cityField.getText();
            String password = passwordField.getText();
            String confirmPassword = confirmPasswordField.getText();
        });

        frame.add(panel);
        frame.setVisible(true);

    }

    /**
     * <p>
     * This method is used to update the user information in the database and the GUI interface.
     * </p>
     * <p>
     * <pre>
     * Example of how to use this method:
     *  {@code
     *      new MainGUI.UpdateUser(userType);
     *  }
     *
     *     </pre>
     * </p>
     */
    public void getUserList ()
    {
        ArrayList<ArrayList<String>> users = new ArrayList<>();
        users = Controller.getUsers();


        String[] columnNames = {"First Name", "Last Name", "Personal Number", "Email Address", "Phone Number", "Home Address", "Zip Code", "City", "Password"};
        String[][] data = new String[users.size()][10];
        for (int i = 0; i < users.size(); i++)
        {
            for (int j = 0; j < 10; j++)
            {
                data[i][j] = users.get(i).get(j);
            }
        }
        JTable table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);

        JScrollPane scrollPane = new JScrollPane(table);

        // set padding around panel
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setSize(800, 800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        table.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked (MouseEvent e)
            {
                int row = table.getSelectedRow();
                int col = table.getSelectedColumn();
                Object cellData = table.getValueAt(row, col);

                StringSelection stringSelection = new StringSelection(cellData.toString());
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);

                JOptionPane.showMessageDialog(null, "Copied to clipboard");

            }
        });

    }

}
