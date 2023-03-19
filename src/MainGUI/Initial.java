package MainGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Initial
{
    private String userType;
    private String emailAddress;
    private String password;

    public Initial ()
    {
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel();

        // set two alternative buttons for user to choose if it is a borrower or librarian that is logging in
        JLabel label = new JLabel("Are you a borrower or a librarian?");
        JButton borrower = new JButton("Borrower");
        JButton librarian = new JButton("Librarian");

        // put label in a row and the two buttons in another row
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


        // add the buttons to the panel
        panel.add(label);
        panel.add(borrower);
        panel.add(librarian);
        frame.add(panel);

        // add action listener on the buttons
        borrower.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowerActionPerformed(evt);
            }
        });

        librarian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                librarianActionPerformed(evt);
            }
        });



        frame.setVisible(true);
    }

    private void librarianActionPerformed (ActionEvent evt)
    {
    }

    private void borrowerActionPerformed (ActionEvent evt)
    {
        // pop up a showInputDialog to ask for the email address and password

    }


}
