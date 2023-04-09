package Borrowing;
import Book.*;
import User.*;
import Database.DatabaseHandling;
import Database.SecureData;



public class Model
{
    private Borrowing borrowing;

    public Model ()
    {
        borrowing = new Borrowing();
    }

    public Model (Borrowing borrowing)
    {
        this.borrowing = borrowing;
    }

    public void setBorrowing (Borrowing borrowing)
    {
        this.borrowing = borrowing;
    }

    public Borrowing getBorrowing ()
    {
        return borrowing;
    }

    public int getUserId() throws Exception
    {
        String encryptedPersonalNumber = SecureData.encrypt(borrowing.getUser().getPersonalNumber());
        int userId= -1;
        String query = "SELECT * FROM users WHERE personalNumber = " + encryptedPersonalNumber + "";
        userId = DatabaseHandling.getUserIdByPersonalNumber(query);
        return userId;
    }

    public int getBookId() throws Exception
    {
        String encryptedISBN = SecureData.encrypt(borrowing.getBook().getIsbn());
        int bookId= -1;
        String query = "SELECT * FROM books WHERE ISBN = " + encryptedISBN + "";
        bookId = DatabaseHandling.getBookIdByISBN(query);
        return bookId;
    }

    public boolean setBorrowRecord () throws Exception
    {
        return DatabaseHandling.insertNewBorrowing(borrowing);
    }
}
