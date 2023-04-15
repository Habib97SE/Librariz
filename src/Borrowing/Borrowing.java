package Borrowing;

import Book.*;
import Database.SecureData;
import User.*;
import Database.DatabaseHandling;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * This class is used to create a borrowing object.
 * It is used to store information about a book and a user.
 * It is also used to get the current date and the date 30 days from now.
 */
public class Borrowing
{
    private Book book;
    private User user;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final String today = LocalDate.now().format(formatter);
    private String startDate;
    private String endDate;
    private boolean activeBorrowing;

    public Borrowing ()
    {
        book = new Book();
        user = new User();
    }

    public Borrowing (Book book, User user, String startDate, String endDate, boolean activeBorrowing)
    {
        this.book = book;
        this.user = user;
        this.startDate = startDate;
        this.endDate = endDate;
        this.activeBorrowing = activeBorrowing;
    }

    public Borrowing (Book book, User user, Timestamp startDate, Timestamp endDate, boolean activeBorrowing)
    {
        this.book = book;
        this.user = user;
        if (startDate != null) this.startDate = startDate.toString();
        else this.startDate = "";
        if (endDate != null) this.endDate = endDate.toString();
        else this.endDate = "";
        this.activeBorrowing = activeBorrowing;
    }

    public Borrowing (Book book, User user)
    {
        this.book = book;
        this.user = user;
        this.startDate = "";
        this.endDate = "";
        this.activeBorrowing = false;
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


    public void setStartDate (String startDate)
    {
        this.startDate = startDate;
    }

    public String getStartDate ()
    {
        if (startDate == null || startDate.equals("")) return "No start date";
        if (startDate.length() > 10) return startDate.substring(0, 10);
        else return startDate;
    }

    public void setEndDate (String endDate)
    {
        this.endDate = endDate;
    }

    public String getEndDate ()
    {
        if (endDate == null || endDate.equals("")) return "No return date";
        if (endDate.length() > 10) return endDate.substring(0, 10);
        else return endDate;
    }

    public void setActiveBorrowing (boolean activeBorrowing)
    {
        this.activeBorrowing = activeBorrowing;
    }

    public boolean getActiveBorrowing ()
    {
        return activeBorrowing;
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
    public String getBorrowingDate ()
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
    public String getReturnDate ()
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate today = LocalDate.now();
        LocalDate returnDate = today.plusDays(30);
        return returnDate.format(formatter);
    }

    public int getUserID ()
    {
        return this.user.getUserID();
    }

    public int getBookID ()
    {
        return this.book.getBookID();
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
}

