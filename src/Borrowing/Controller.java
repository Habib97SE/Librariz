package Borrowing;

import User.User;
import Book.Book;

import java.text.ParseException;
import java.util.HashMap;

public class Controller
{
    private Model model;
    public Controller ()
    {
        model = new Model();
    }

    public boolean borrowBook (Borrowing borrowing) throws Exception
    {
        return model.setBorrowRecord(borrowing);
    }


    public User getUserByPersonalNumber (String encryptedPersonalNumber)
    {
        return model.getUserByPersonalNumber(encryptedPersonalNumber);
    }

    public int getUserID (String encyrptedPersonalNumber) throws Exception
    {
        return model.getUserId();
    }

    public Book getBookByISBN (String encryptedISBN)
    {
        return model.getBookByISBN(encryptedISBN);
    }

    public HashMap<String, String> returnBook (Book book, User user) throws ParseException
    {
        return model.returnBook(book, user);
    }
}
