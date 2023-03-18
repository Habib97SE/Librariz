package MainGUI;
import javax.swing.*;


public class Login
{
    private String userType;
    private String emailAddress;
    private String password;

    public Login(String userType)
    {
        this.userType = userType;

        JFrame frame = new JFrame("Library Management System");

        if (userType.equals("borrower"))
        {
            frame.setTitle("Borrower Login");
        }
        else if (userType.equals("librarian"))
        {
            frame.setTitle("Librarian Login");
        }

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);


        JPanel emailAddress = new JPanel();
        JPanel password = new JPanel();

        JLabel emailLabel = new JLabel("Email Address: ");
        JLabel passwordLabel = new JLabel("Password: ");
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        emailAddress.add(emailLabel);
        emailAddress.add(emailField);
        password.add(passwordLabel);
        password.add(passwordField);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(emailAddress);
        panel.add(password);

        JButton login = new JButton("Login");
        JButton back = new JButton("Back");


        panel.add(login);
        panel.add(back);

        frame.add(panel);

        // add action listener to the login button
        login.addActionListener(e -> {
            // get the email address and password from the text fields
            String emailAddressStr = emailField.getText();
            String passwordStr = passwordField.getText();

            // check if the email address and password are correct
            // if they are correct, then call the appropriate method
            // if they are not correct, then display an error message
            if (userType.equals("borrower"))
            {
                // call the borrower login method
                Borrower borrowerLogin = new BorrowerLogin(emailAddress, password);
                frame.dispose();
            }
            else if (userType.equals("librarian"))
            {
                // call the librarian login method
                LibrarianLogin librarianLogin = new LibrarianLogin(emailAddress, password);
                frame.dispose();
            }
        });

        // return the current user to the initial page
        back.addActionListener(e -> {
            Initial initial = new Initial();
            frame.dispose();
        });

    }
}
