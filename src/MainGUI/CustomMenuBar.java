package MainGUI;

import javax.swing.*;
import java.awt.event.*;

import Database.DatabaseHandling;
import Database.SecureData;
import User.Borrower;

public class CustomMenuBar extends JMenuBar
{
    public CustomMenuBar ()
    {
        JMenu borrowersMenu = new JMenu("Borrowers");
        JMenu booksMenu = new JMenu("Books");

        JMenuItem newBorrower = new JMenuItem("Add new borrower");
        JMenuItem editBorrower = new JMenuItem("Edit an existing borrower");
        JMenuItem removeBorrower = new JMenuItem("Remove a borrower");
        JMenuItem searchBorrower = new JMenuItem("Search for a borrower");

        borrowersMenu.add(newBorrower);
        borrowersMenu.add(editBorrower);
        borrowersMenu.add(removeBorrower);
        borrowersMenu.addSeparator();
        borrowersMenu.add(searchBorrower);

        JMenuItem addNewStaff = new JMenuItem("Add new staff");
        JMenuItem editStaff = new JMenuItem("Edit an existing staff");
        JMenuItem deleteStaff = new JMenuItem("Delete a staff");

        booksMenu.add(addNewStaff);
        booksMenu.add(editStaff);
        booksMenu.add(deleteStaff);

        add(borrowersMenu);
        add(booksMenu);

        // Add action listeners to menu items
        newBorrower.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                addNewBorrower();
            }
        });
        editBorrower.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                editBorrower();
            }
        });


    }

    /**
     * This method will manage the window for add new borrower, and will add the new borrower to the database
     *
     * @return void
     */
    private void addNewBorrower ()
    {
        JFrame frame = new JFrame("Add new borrower");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 600);
        frame.setVisible(true);

        // Add new borrower form
        JPanel panel = new JPanel();

        // Add first name label and text field
        JLabel firstNameLabel = new JLabel("First name: ");
        JTextField firstNameTextField = new JTextField(20);

        // Add last name label and text field
        JLabel lastNameLabel = new JLabel("Last name: ");
        JTextField lastNameTextField = new JTextField(20);

        // Add personal number label and text field
        JLabel personalNumberLabel = new JLabel("Personal number: ");
        JTextField personalNumberTextField = new JTextField(20);

        // Add email address label and text field
        JLabel emailAddressLabel = new JLabel("Email address: ");
        JTextField emailAddressTextField = new JTextField(20);

        // Add phone number label and text field
        JLabel phoneNumberLabel = new JLabel("Phone number: ");
        JTextField phoneNumberTextField = new JTextField(20);

        // Add home address label and text field
        JLabel homeAddressLabel = new JLabel("Home address: ");
        JTextField homeAddressTextField = new JTextField(20);

        // Add zip code label and text field
        JLabel zipCodeLabel = new JLabel("Zip code: ");
        JTextField zipCodeTextField = new JTextField(20);

        // Add city label and text field
        JLabel cityLabel = new JLabel("City: ");
        JTextField cityTextField = new JTextField(20);

        panel.add(firstNameLabel);
        panel.add(firstNameTextField);

        panel.add(lastNameLabel);
        panel.add(lastNameTextField);

        panel.add(personalNumberLabel);
        panel.add(personalNumberTextField);

        panel.add(emailAddressLabel);
        panel.add(emailAddressTextField);

        panel.add(phoneNumberLabel);
        panel.add(phoneNumberTextField);

        panel.add(homeAddressLabel);
        panel.add(homeAddressTextField);

        panel.add(zipCodeLabel);
        panel.add(zipCodeTextField);

        panel.add(cityLabel);
        panel.add(cityTextField);

        frame.add(panel);

        // Add button to add new borrower
        JButton addNewBorrowerButton = new JButton("Add new borrower");
        panel.add(addNewBorrowerButton);

        // Add action listener to add new borrower button
        addNewBorrowerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                // Get the values from the text fields
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String personalNumber = personalNumberTextField.getText();
                String emailAddress = emailAddressTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String homeAddress = homeAddressTextField.getText();
                String zipCode = zipCodeTextField.getText();
                String city = cityTextField.getText();

                // validate inputs
                boolean error = false;

                if (!SecureData.isValidName(firstName))
                {
                    JOptionPane.showMessageDialog(null, "First name is not valid");
                    error = true;
                }

                if (!SecureData.isValidName(lastName))
                {
                    JOptionPane.showMessageDialog(null, "Last name is not valid");
                    error = true;
                }

                if (!SecureData.isValidPersonalNumber(personalNumber))
                {
                    JOptionPane.showMessageDialog(null, "Personal number is not valid");
                    error = true;
                }

                if (!SecureData.isValidEmail(emailAddress))
                {
                    JOptionPane.showMessageDialog(null, "Email address is not valid");
                    error = true;
                }

                if (!SecureData.isValidPhoneNumber(phoneNumber))
                {
                    JOptionPane.showMessageDialog(null, "Phone number is not valid");
                    error = true;
                }

                if (SecureData.isValidAddress(homeAddress))
                {
                    JOptionPane.showMessageDialog(null, "Home address is not valid");
                    error = true;
                }

                if (!SecureData.isValidZipCode(zipCode))
                {
                    JOptionPane.showMessageDialog(null, "Zip code is not valid");
                    error = true;
                }

                if (!SecureData.isValidCity(city))
                {
                    JOptionPane.showMessageDialog(null, "City is not valid");
                    error = true;
                }

                if (error)
                {
                    return;
                }


                Borrower borrower = new Borrower(firstName, lastName, personalNumber, emailAddress, phoneNumber, homeAddress, zipCode, city);


                // Add new borrower to database
                boolean result = DatabaseHandling.addBorrower(borrower);

                if (result)
                {
                    JOptionPane.showMessageDialog(null, "Borrower added successfully");
                    // Close the window
                    frame.dispose();
                } else
                {
                    JOptionPane.showMessageDialog(null, "Borrower could not be added");
                }

            }
        });
    }

    private void editBorrower()
    {
        JFrame frame = new JFrame("Edit borrower");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setSize(800, 600);


        // find the user by its personal number
        String personalNumber = JOptionPane.showInputDialog("Enter the personal number of the borrower you want to edit");
        System.out.println(personalNumber);
        Borrower borrower = DatabaseHandling.findBorrowerByPersonalNumber(personalNumber);

        System.out.println(borrower.toString());

        // Add new borrower form
        JPanel panel = new JPanel();

        // Add first name label and text field
        JLabel firstNameLabel = new JLabel("First name: ");
        JTextField firstNameTextField = new JTextField(20);
        firstNameTextField.setText(borrower.getFirstName());

        // Add last name label and text field
        JLabel lastNameLabel = new JLabel("Last name: ");
        JTextField lastNameTextField = new JTextField(20);
        lastNameTextField.setText(borrower.getLastName());

        // Add personal number label and text field
        JLabel personalNumberLabel = new JLabel("Personal number: ");
        JTextField personalNumberTextField = new JTextField(20);
        personalNumberTextField.setText(borrower.getPersonalNumber());
        // Set the personal number text field to not editable
        personalNumberTextField.setEditable(false);

        // Add email address label and text field
        JLabel emailAddressLabel = new JLabel("Email address: ");
        JTextField emailAddressTextField = new JTextField(20);
        emailAddressTextField.setText(borrower.getEmailAddress());

        // Add phone number label and text field
        JLabel phoneNumberLabel = new JLabel("Phone number: ");
        JTextField phoneNumberTextField = new JTextField(20);
        phoneNumberTextField.setText(borrower.getPhoneNumber());

        // Add home address label and text field
        JLabel homeAddressLabel = new JLabel("Home address: ");
        JTextField homeAddressTextField = new JTextField(20);
        homeAddressTextField.setText(borrower.getHomeAddress());

        // Add zip code label and text field
        JLabel zipCodeLabel = new JLabel("Zip code: ");
        JTextField zipCodeTextField = new JTextField(20);
        zipCodeTextField.setText(borrower.getZipCode());

        // Add city label and text field
        JLabel cityLabel = new JLabel("City: ");
        JTextField cityTextField = new JTextField(20);
        cityTextField.setText(borrower.getCity());

        panel.add(firstNameLabel);
        panel.add(firstNameTextField);

        panel.add(lastNameLabel);
        panel.add(lastNameTextField);

        panel.add(personalNumberLabel);
        panel.add(personalNumberTextField);

        panel.add(emailAddressLabel);
        panel.add(emailAddressTextField);

        panel.add(phoneNumberLabel);
        panel.add(phoneNumberTextField);

        panel.add(homeAddressLabel);
        panel.add(homeAddressTextField);

        panel.add(zipCodeLabel);
        panel.add(zipCodeTextField);

        panel.add(cityLabel);
        panel.add(cityTextField);

        frame.add(panel);

        // Add button to add new borrower
        JButton addNewBorrowerButton = new JButton("Edit borrower");
        panel.add(addNewBorrowerButton);


        frame.setVisible(true);

        // Add action listener to add new borrower button
        addNewBorrowerButton.addActionListener(new ActionListener()
        {
            public void actionPerformed (ActionEvent e)
            {
                // Get the values from the text fields
                String firstName = firstNameTextField.getText();
                String lastName = lastNameTextField.getText();
                String personalNumber = personalNumberTextField.getText();
                String emailAddress = emailAddressTextField.getText();
                String phoneNumber = phoneNumberTextField.getText();
                String homeAddress = homeAddressTextField.getText();
                String zipCode = zipCodeTextField.getText();
                String city = cityTextField.getText();

                // validate inputs
                boolean error = false;

                if (!SecureData.isValidName(firstName))
                {
                    JOptionPane.showMessageDialog(null, "First name is not valid");
                    error = true;
                }

                if (!SecureData.isValidName(lastName))
                {
                    JOptionPane.showMessageDialog(null, "Last name is not valid");
                    error = true;
                }

                if (!SecureData.isValidPersonalNumber(personalNumber))
                {
                    JOptionPane.showMessageDialog(null, "Personal number is not valid");
                    error = true;
                }

                if (!SecureData.isValidEmail(emailAddress))
                {
                    JOptionPane.showMessageDialog(null, "Email address is not valid");
                    error = true;
                }

                if (!SecureData.isValidPhoneNumber(phoneNumber))
                {
                    JOptionPane.showMessageDialog(null, "Phone number is not valid");
                    error = true;
                }

                if (SecureData.isValidAddress(homeAddress))
                {
                    JOptionPane.showMessageDialog(null, "Home address is not valid");
                    error = true;
                }

                if (!SecureData.isValidZipCode(zipCode))
                {
                    JOptionPane.showMessageDialog(null, "Zip code is not valid");
                    error = true;
                }

                if (!SecureData.isValidCity(city))
                {
                    JOptionPane.showMessageDialog(null, "City is not valid");
                    error = true;
                }

                if (error)
                {
                    return;
                }

                Borrower borrower = new Borrower(firstName, lastName, personalNumber, emailAddress, phoneNumber, homeAddress, zipCode, city);

                // Edit borrower in database
                boolean result = DatabaseHandling.editBorrower(borrower);

                if (result)
                {
                    JOptionPane.showMessageDialog(null, "Borrower edited successfully");
                    // Close the window
                    frame.dispose();
                } else
                {
                    JOptionPane.showMessageDialog(null, "Borrower could not be edited");
                }

            }
        });
    }
}
