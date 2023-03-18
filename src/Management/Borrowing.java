package Management;
import Book.Book;
import Database.DatabaseHandling;
import User.Borrower;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Borrowing
{
    private Book book;
    private Borrower borrower;
    private String dateOfBorrowing;
    private String dateOfReturn;
    private boolean returned;
    private String dueDate;
    private Double lateFee;
    private Integer numberOfRenewals;
    private String lastRenewalDate;

    public Borrowing(Book book, Borrower borrower, String dateOfBorrowing, String dateOfReturn, boolean returned,
                     String dueDate, Double lateFee, String lastRenewalDate)
    {
        this.book = book;
        this.borrower = borrower;
        this.dateOfBorrowing = dateOfBorrowing;
        this.dateOfReturn = dateOfReturn;
        this.returned = returned;
        this.dueDate = dueDate;
        this.lateFee = lateFee;
        this.numberOfRenewals = 3;
        this.lastRenewalDate = lastRenewalDate;
    }

    public Book getBook ()
    {
        return book;
    }

    public void setBook (Book book)
    {
        this.book = book;
    }

    public Borrower getBorrower ()
    {
        return borrower;
    }

    public void setBorrower (Borrower borrower)
    {
        this.borrower = borrower;
    }

    public String getDateOfBorrowing ()
    {
        return dateOfBorrowing;
    }

    public void setDateOfBorrowing (String dateOfBorrowing)
    {
        this.dateOfBorrowing = dateOfBorrowing;
    }

    public String getDateOfReturn ()
    {
        return dateOfReturn;
    }

    public void setDateOfReturn (String dateOfReturn)
    {
        this.dateOfReturn = dateOfReturn;
    }

    public boolean isReturned ()
    {
        return returned;
    }

    public void setReturned (boolean returned)
    {
        this.returned = returned;
    }

    public String getDueDate ()
    {
        return dueDate;
    }

    public void setDueDate (String dueDate)
    {
        this.dueDate = dueDate;
    }

    public Double getLateFee ()
    {
        return lateFee;
    }

    public void setLateFee (Double lateFee)
    {
        this.lateFee = lateFee;
    }

    public Integer getNumberOfRenewals ()
    {
        return numberOfRenewals;
    }

    public void setNumberOfRenewals (Integer numberOfRenewals)
    {
        this.numberOfRenewals = numberOfRenewals;
    }

    public String getLastRenewalDate ()
    {
        return lastRenewalDate;
    }

    public void setLastRenewalDate (String lastRenewalDate)
    {
        this.lastRenewalDate = lastRenewalDate;
    }

    public boolean addBorrowingToDatabase()
    {
        String query = String.format("INSERT INTO Borrowing (bookID, borrowerID, dateOfBorrowing, dateOfReturn, returned, dueDate, " +
                "lateFee, numberOfRenewals, lastRenewalDate) VALUES (%s, %s, %s, %s, %s, %s, %s, %s, %s)",
                this.book.getBookId(), this.borrower.getBorrowerID(), this.dateOfBorrowing, this.dateOfReturn,
                this.returned, this.dueDate, this.lateFee, this.numberOfRenewals, this.lastRenewalDate);

        return DatabaseHandling.InsertIntoTable(query);
    }

    public String renewalBorrowing()
    {
        if (this.numberOfRenewals > 0)
        {
            this.numberOfRenewals--;
            this.lastRenewalDate = DatabaseHandling.getCellValue("borrowinghistory", "lastRenewalDate",new String[]{"bookID", "borrowerID"},
                    new String[]{this.book.getBookId().toString(), this.borrower.getBorrowerID().toString()});

            this.dueDate = DatabaseHandling.addDaysToCurrentDate(30);
            String query = String.format("UPDATE Borrowing SET numberOfRenewals = %s, lastRenewalDate = %s, dueDate = %s " +
                            "WHERE bookID = %s AND borrowerID = %s",
                    this.numberOfRenewals, this.lastRenewalDate, this.dueDate, this.book.getBookId(),
                    this.borrower.getBorrowerID());

            if (DatabaseHandling.UpdateTable(query))
            {
                return "Renewal successful";
            }
            else
            {
                return "Renewal failed";
            }
        }
        else
        {
            return "You have reached the maximum number of renewals";
        }
    }

    private int getDaysBetweenDates(String startDate, String endDate)
    {
        int days = 0;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        // check that the start date is before the end date
        if (start.isAfter(end))
            return days;
        days = (int) ChronoUnit.DAYS.between(start, end);
        return days;
    }

    private double calculateLateFee(int days)
    {
        return days * 10.0;
    }


    public boolean returnBook()
    {
        this.dateOfReturn = DatabaseHandling.getCellValue("borrowinghistory", "dateOfReturn",new String[]{"bookID", "borrowerID"},
                new String[]{this.book.getBookId().toString(), this.borrower.getBorrowerID().toString()});
        this.returned = true;
        this.lateFee = calculateLateFee(getDaysBetweenDates(this.dueDate, this.dateOfReturn));
        String query = String.format("UPDATE Borrowing SET dateOfReturn = %s, returned = %s, lateFee = %s " +
                        "WHERE bookID = %s AND borrowerID = %s",
                this.dateOfReturn, this.returned, this.lateFee, this.book.getBookId(),
                this.borrower.getBorrowerID());
        return DatabaseHandling.UpdateTable(query);
    }

    public boolean equals(Borrowing other)
    {
        return this.getBook().equals(other.getBook()) && this.getBorrower().equals(other.getBorrower());
    }

    @Override
    public String toString()
    {
        return String.format("%s %s %s %s %s %s %s %s", this.getBook().getTitle(), this.getBorrower().getFirstName(),
                this.getBorrower().getLastName(), this.getDateOfBorrowing(), this.getDateOfReturn(), this.isReturned(),
                this.getDueDate(), this.getLateFee());
    }


}
