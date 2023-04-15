package Borrowing;

import Database.SecureData;
import User.User;
import Book.Book;

import javax.swing.*;
import java.util.HashMap;
import java.util.Objects;

public class BorrowingView
{
    private Controller controller;
    private User user;

    public BorrowingView (User user)
    {
        this.user = user;
        controller = new Controller();
    }


    public void borrowBook (Borrowing borrowing)
    {
        try
        {
            if (controller.borrowBook(borrowing))
            {
                String message = String.format("""
                        Book borrowed successfully!
                        Book details: \n
                        Title: %s \n
                        Last returned date: %s \n
                        Please keep in mind that for each late day you will be charged by 10 SEK. \n
                        """, borrowing.getBook().getTitle(), borrowing.getReturnDate());
                JOptionPane.showMessageDialog(null, message);
            } else
            {
                JOptionPane.showMessageDialog(null, "Book not borrowed!");
            }
        } catch (Exception e)
        {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
        }
    }

    private User getUserByPersonalNumber () throws Exception
    {
        while (true)
        {
            String personalNumber = JOptionPane.showInputDialog("Enter personal number: ");
            String encryptedPersonalNumber = SecureData.encrypt(personalNumber);
            User user = controller.getUserByPersonalNumber(encryptedPersonalNumber);
            if (user == null)
            {
                int choice = JOptionPane.showConfirmDialog(null, "User not found! Do you want to try again?");
                if (choice == JOptionPane.NO_OPTION)
                {
                    return null;
                }
            } else
            {
                return user;
            }
        }
    }

    private Book getBookByISBN (String ISBN) throws Exception
    {
        return controller.getBookByISBN(ISBN);
    }

    private Book getBookByISBN () throws Exception
    {
        while (true)
        {
            String isbn = JOptionPane.showInputDialog("Enter ISBN: ");
            Book book = controller.getBookByISBN(isbn);
            System.out.println("Book isbn: " + book.getIsbn());
            if (book == null)
            {
                int choice = JOptionPane.showConfirmDialog(null, "Book not found! Do you want to try again?");
                if (choice == JOptionPane.NO_OPTION)
                {
                    return null;
                }
            } else
            {
                return book;
            }
        }
    }

    public void createPDF(String message)
    {
        // TODO: 2023-04-10
    }
    public void print (String message)
    {
        // TODO: 2023-04-10
    }

    public void borrowBook (String isbn) throws Exception
    {
        User user = getUserByPersonalNumber();
        if (user == null)
            return;
        Book book = getBookByISBN(isbn);
        if (book == null)
            return;


        Borrowing borrowing = new Borrowing(book, user);
        borrowBook(borrowing);
        return;
    }

    public boolean returnBook () throws Exception
    {
        User user = getUserByPersonalNumber();
        Book book = getBookByISBN();

        if (user == null || book == null)
            return false;

        HashMap<String, String> result = controller.returnBook(book, user);

        if (result != null)
        {
            String message = String.format("""
                    Book returned successfully!
                    Book details: \n
                    Title: %s \n
                    return date: %s \n
                    """, result.get("title"), result.get("returnDate"));
            if (!Objects.equals(result.get("fine"), "0"))
            {
                message += String.format("""
                        You have to pay a fine of %s SEK. \n
                        """, result.get("fine"));
            }
            JOptionPane.showMessageDialog(null, message);
        } else
        {
            JOptionPane.showMessageDialog(null, "Book not returned!");
        }

        return false;
    }
}
