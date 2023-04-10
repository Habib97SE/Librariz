package Borrowing;

import Book.*;
import User.*;
import Database.DatabaseHandling;
import Database.SecureData;

import java.text.SimpleDateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;

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

    public int getUserId () throws Exception
    {
        String encryptedPersonalNumber = SecureData.encrypt(borrowing.getUser().getPersonalNumber());
        int userId = -1;
        String query = "SELECT * FROM users WHERE personalNumber = " + encryptedPersonalNumber + "";
        userId = DatabaseHandling.getUserIdByPersonalNumber(query);
        return userId;
    }

    public int getBookId () throws Exception
    {
        String encryptedISBN = SecureData.encrypt(borrowing.getBook().getIsbn());
        int bookId = -1;
        String query = "SELECT * FROM books WHERE ISBN = " + encryptedISBN + "";
        bookId = DatabaseHandling.getBookIdByISBN(query);
        return bookId;
    }

    public boolean setBorrowRecord (Borrowing borrowing) throws Exception
    {
        return DatabaseHandling.insertNewBorrowing(borrowing);
    }

    public User getUserByPersonalNumber (String encryptedPersonalNumber)
    {
        return DatabaseHandling.getUserByPersonalNumber(encryptedPersonalNumber);
    }

    public Book getBookByISBN (String isbn)
    {
        return DatabaseHandling.getBookByISBN(isbn);
    }


    /**
     * This method will compare and calculate date1 and date2 and return date difference, if date1 is greater than
     * date2 it will return a negative number, if date1 is less than date2 it will return a positive number, if date1
     *
     * @param date1Str : date1 is the date that the book was borrowed
     * @param date2Str : date2 is the date that the book was returned
     * @return : the difference between date1 and date2 in integer format (days)
     */
    private int getDateDifference (String date1Str, String date2Str) throws ParseException
    {
        SimpleDateFormat obj = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = obj.parse(date1Str);
        Date date2 = obj.parse(date2Str);

        long diff = date1.getTime() - date2.getTime();

        return Integer.parseInt(String.valueOf(diff / (24 * 60 * 60 * 1000)));
    }

    public HashMap<String, String> returnBook (Book book, User user) throws ParseException
    {
        HashMap<String, String> bookInfo = new HashMap<>();
        bookInfo.put("title", book.getTitle());

        try
        {
            int borrowingID = DatabaseHandling.getBorrowingID(book.getBookID(), user.getUserID());
            String lastReturnDate = DatabaseHandling.getLastReturnDate(borrowingID);

            // first parameter is the date the book should be returned and the second parameter is today's date (current date)
            int dateDifference = getDateDifference(lastReturnDate, LocalDate.now().toString());

            if (dateDifference > 0)
            {
                // if the book is returned after the due date, the user will be charged 10 SEK per day
                int fine = dateDifference * 10;
                bookInfo.put("fine", String.valueOf(fine));
                // register the fine in the database users table
                boolean result = DatabaseHandling.updateUserFine(user.getUserID(), fine);
            } else {
                bookInfo.put("fine", "0");
            }


            // update actualReturnedDate in borrowings table
            boolean result = DatabaseHandling.updateActualReturnedDate(borrowingID, LocalDate.now().toString());

            if (result)
                bookInfo.put("actualReturnedDate", LocalDate.now().toString());



            // update book's numberOfAvailableCopies
            result = DatabaseHandling.updateBookAvailableCopies(book.getBookID(), book.getNumberOfAvailableCopies() + 1);
            bookInfo.put("numberOfAvailableCopies", String.valueOf(book.getNumberOfAvailableCopies() + 1));

            return bookInfo;

        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
