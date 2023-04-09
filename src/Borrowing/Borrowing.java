package Borrowing;

import Book.*;
import Database.SecureData;
import User.*;
import Database.DatabaseHandling;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Borrowing
{
    private Book book;
    private User user;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String today = LocalDate.now().format(formatter);

    public Borrowing ()
    {
        book = new Book();
        user = new User();
    }

    public Borrowing (Book book, User user)
    {
        this.book = book;
        this.user = user;
    }

    public void setBook (Book book)
    {
        this.book = book;
    }

    public Book getBook ()
    {
        return book;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public User getUser ()
    {
        return user;
    }

    /**
     * Get the current date in the format of yyyy-MM-dd and return it as a string
     *
     * @return the current date
     */
    public String getDateString ()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        return today.format(formatter);
    }

    /**
     * Get the date 30 days from now in the format of yyyy-MM-dd and return it as a string
     *
     * @return the date 30 days from now
     */
    public String getReturnDateString ()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusDays(30);
        return returnDate.format(formatter);
    }

    public boolean equals (Borrowing other)
    {
        return book.equals(other.book) && user.equals(other.user);
    }

    @Override
    public String toString ()
    {
        return String.format("""
                Book details: \n
                Title: %s \n
                First and last name: %s %s \n
                """, book.getTitle(), user.getFirstName(), user.getLastName());
    }

//    public void borrowBook (String bookID) throws Exception
//    {
//        String personalNumber = "";
//        int userId = -1;
//        while (true) {
//            personalNumber = JOptionPane.showInputDialog("Enter your personal number: ");
//            String encryptedPersonalNumber = SecureData.encrypt(personalNumber);
//            userId = DatabaseHandling.getUserIdByPersonalNumber(encryptedPersonalNumber);
//            if (userId == -1) {
//                JOptionPane.showMessageDialog(null, "User not found!");
//                return;
//            } else {
//                break;
//            }
//        }
//
//        Borrowing borrowing = new Borrowing(bookID, userId, LocalDate.now());
//        boolean borrowingResult = DatabaseHandling.insertNewBorrowing(borrowing);
//        if (borrowingResult) {
//            boolean updateResult = DatabaseHandling.updateBookNumberOfAvailableCopies(bookID, -1);
//            if (updateResult) {
//                String title = getBookTitle(bookID);
//                String message = String.format("""
//                    Book borrowed successfully!
//                    Book details: \n
//                    Title: %s \n
//                    Last returned at: %s \n
//                    Please keep in mind that for each late day you will be charged 10 SEK. \n
//                    """, title, getReturnDateString());
//                showDetailsDialog(message);
//            } else {
//                JOptionPane.showMessageDialog(null, "Book borrowing failed!");
//            }
//        } else {
//            JOptionPane.showMessageDialog(null, "Book borrowing failed!");
//        }
//
//    }
//
//    /**
//     * Show a dialog with the given message
//     *
//     * @param message the message to show
//     */
//    private void showDetailsDialog (String message)
//    {
//        JOptionPane.showMessageDialog(null, message);
//    }
//
//
//    /**
//     * Receive the book's id in the books table and return the book's title. If the book is not found, return an empty
//     *
//     * @param bookID the book's id in the books table
//     * @return the book's title
//     * @throws SQLException if the query fails
//     */
//    private String getBookTitle (String bookID) throws SQLException
//    {
//        String query = "SELECT title FROM books WHERE id = '" + bookID + "'";
//        ResultSet resultSet = DatabaseHandling.getRow(query);
//        assert resultSet != null;
//        if (resultSet.next())
//        {
//            return resultSet.getString("title");
//        }
//        return "";
//    }


}

