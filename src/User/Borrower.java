package User;

import Book.Book;
import Book.Reservation;

import java.util.ArrayList;
import java.util.Objects;

import Database.DatabaseHandling;

/**
 * <p>
 * The Borrower class extends the User class and represents a library borrower.
 * It contains borrower-specific attributes such as library card number, book borrowing history,
 * current debt, and a list of reservations.
 * </p>
 */
public class Borrower extends User
{

    /**
     * The library card number of the borrower.
     */
    private String libraryNumber;

    /**
     * The borrowing history of the borrower.
     */
    private ArrayList<Book> bookHistory;

    /**
     * The list of reservations made by the borrower.
     */
    private ArrayList<Reservation> reservations;

    /**
     * The current debt of the borrower.
     */
    private Double currentDebt;


    /**
     * The constructor for the Borrower class.
     *
     * @param firstName      The first name of the borrower
     * @param lastName       The last name of the borrower
     * @param personalNumber The personal number of the borrower
     * @param emailAddress   The email address of the borrower
     * @param phoneNumber    The phone number of the borrower
     * @param homeAddress    The home address of the borrower
     * @param zipCode        The zip code of the borrower
     * @param city           The city of the borrower
     * @param libraryNumber  The library card number of the borrower
     * @param bookHistory    The borrowing history of the borrower
     * @param reservations   The list of reservations made by the borrower
     * @param currentDebt    The current debt of the borrower
     */
    public Borrower (String firstName, String lastName, String personalNumber, String emailAddress, String phoneNumber,
                     String homeAddress, String zipCode, String city, String libraryNumber,
                     ArrayList<Book> bookHistory, ArrayList<Reservation> reservations, Double currentDebt)
    {
        super(firstName, lastName, personalNumber, emailAddress, phoneNumber, homeAddress, zipCode, city);
        this.bookHistory = bookHistory;
        this.libraryNumber = libraryNumber;
        this.reservations = reservations;
        this.currentDebt = currentDebt;
    }

    /**
     * The constructor for the Borrower class.
     *
     * @param firstName      The first name of the borrower
     * @param lastName       The last name of the borrower
     * @param personalNumber The personal number of the borrower
     * @param emailAddress   The email address of the borrower
     * @param phoneNumber    The phone number of the borrower
     * @param homeAddress    The home address of the borrower
     * @param zipCode        The zip code of the borrower
     * @param city           The city of the borrower
     */
    public Borrower (String firstName, String lastName, String personalNumber, String emailAddress, String phoneNumber,
                     String homeAddress, String zipCode, String city)
    {
        super(firstName, lastName, personalNumber, emailAddress, phoneNumber, homeAddress, zipCode, city);
        this.bookHistory = new ArrayList<>();
        this.libraryNumber = Integer.parseInt(DatabaseHandling.getLastLibraryCardNumber()) + 1 + "";
        this.reservations = new ArrayList<>();
        this.currentDebt = 0.0;
    }


    /**
     * Returns the last library card number from the database
     *
     * @return The last library card number
     */
    private String getLastLibraryNumber ()
    {
        String lastLibraryNumber = DatabaseHandling.getLastLibraryCardNumber();
        return Objects.requireNonNullElse(lastLibraryNumber, "0");
    }

    /**
     * Returns the library card number of the borrower.
     *
     * @return The library card number of the borrower
     */
    public String getLibraryNumber ()
    {
        return libraryNumber;
    }

    /**
     * Sets the library card number of the borrower.
     *
     * @param libraryNumber The library card number of the borrower
     */
    public void setLibraryNumber (String libraryNumber)
    {
        this.libraryNumber = libraryNumber;
    }

    /**
     * Returns the borrowing history of the borrower.
     *
     * @return The borrowing history of the borrower
     */
    public ArrayList<Book> getBookHistory ()
    {
        return bookHistory;
    }

    /**
     * Sets the borrowing history of the borrower.
     *
     * @param bookHistory The borrowing history of the borrower
     */
    public void setBookHistory (ArrayList<Book> bookHistory)
    {
        this.bookHistory = bookHistory;
    }

    /**
     * Returns the list of reservations made by the borrower.
     *
     * @return The list of reservations made by the borrower
     */
    public ArrayList<Reservation> getReservations ()
    {
        return reservations;
    }

    /**
     * Sets the list of reservations made by the borrower.
     *
     * @param reservations The list of reservations made by the borrower
     */
    public void setReservations (ArrayList<Reservation> reservations)
    {
        this.reservations = reservations;
    }

    /**
     * Returns the current debt of the borrower.
     *
     * @return The current debt of the borrower
     */
    public Double getCurrentDebt ()
    {
        return this.currentDebt;
    }

    /**
     * Sets the current debt of the borrower.
     *
     * @param currentDebt The current debt of the borrower
     */
    public void setCurrentDebt (Double currentDebt)
    {
        this.currentDebt = currentDebt;
    }

    /**
     * Gets the borrower's ID from the database.
     */
    public Integer getBorrowerID ()
    {
        return DatabaseHandling.getRowID("borrowers", "personalNumber", this.getPersonalNumber());
    }

    /**
     * Gets the borrower's library card number.
     *
     * @return The borrower's library card number
     */
    public String getLibraryCardNumber ()
    {
        return this.libraryNumber;
    }


    public void setLibraryCardNumber (String libraryCardNumber)
    {
        this.libraryNumber = libraryCardNumber;
    }

    public boolean equals (Borrower other)
    {
        return Objects.equals(this.getPersonalNumber(), other.getPersonalNumber());
    }

    /**
     * Corrects the personal number of the borrower in the right format which is 19YYMMDDXXXX or 20YYMMDDXXXX.
     *
     */
    private void correctPersonalNumber ()
    {
        // if there is any dash in the personal number, remove it
        if (this.getPersonalNumber().contains("-"))
            this.setPersonalNumber(this.getPersonalNumber().replace("-", ""));

        // check if it is 10 digits long if yes, add 19 or 20 to the beginning based on the first two digits
        if (this.getPersonalNumber().length() == 10)
        {
            if (this.getPersonalNumber().startsWith("19") || this.getPersonalNumber().startsWith("20"))
                this.setPersonalNumber(this.getPersonalNumber());
            else if (this.getPersonalNumber().charAt(0) == '0')
                this.setPersonalNumber("20" + this.getPersonalNumber());
            else
                this.setPersonalNumber("19" + this.getPersonalNumber());
        }
    }

    @Override
    public String toString ()
    {
        return String.format("%s %s", this.getFirstName(), this.getLastName());
    }
}
