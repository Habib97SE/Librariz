package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Book.History;
import Book.Book;

/**
 * This class is used to handle all database related tasks.
 * It is used to get data from the database and to insert data into the database.
 * It is also used to update and delete data from the database.
 *
 * @author Habib
 * @version 1.0
 * @since 2023-03-15
 */
public class DatabaseHandling
{
    private static String url = "jdbc:mysql://localhost:3306/librariz";
    private static String userName = "root";
    private static String password = "";


    /**
     * This method is used to get the value of a cell in a table. It takes in the table name, the column name, the column to check and the value to check.
     * Example: getCellValue("books", "title", "id", "1") will return the title of the book with the id 1.
     *
     * @param tableName   - The name of the table to check.
     *                    Example: "books", "borrowers", "staff"
     * @param columnName  - The name of the column to check.
     *                    Example: "id", "title", "author", "isbn", "publisher", "year", "genre", "language", "pages", "borrowerId", "staffId"
     * @param columnCheck - The name of the column to check.
     *                    Example: "id", "title", "author", "isbn", "publisher", "year", "genre", "language", "pages", "borrowerId", "staffId"
     * @param valueCheck  - The value to check.
     *                    Example: "1", "Harry Potter", "J.K. Rowling", "978-3-16-148410-0", "Bloomsbury", "1997", "Fantasy", "English", "223", "1", "1"
     * @return - The value of the cell.
     */
    public static String getCellValue (String tableName, String columnName, String columnCheck, String valueCheck)
    {
        String query = String.format("SELECT %s FROM %s WHERE %s = '%s'", columnName, tableName, columnCheck, valueCheck);
        String cellValue = null;
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                cellValue = result.getString(columnName);
            }
            conn.close();
        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return cellValue;
    }

    public static String getCellValue (String tableName, String columnName, String[] columnsCheck, String[] valuesCheck)
    {
        StringBuilder query = new StringBuilder(String.format("SELECT %s FROM %s WHERE %s = '%s'", columnName,
                tableName, columnsCheck[0], valuesCheck[0]));
        for (int i = 1; i < columnsCheck.length; i++)
            query.append(String.format(" AND %s = '%s'", columnsCheck[i], valuesCheck[i]));

        String cellValue = null;
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = conn.prepareStatement(query.toString());
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                cellValue = result.getString(columnName);
            }


        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return cellValue;
    }


    /**
     * This method is used to get the borrowing history of a borrower. It takes in a borrower ID and returns an ArrayList of History objects.
     *
     * @param tableName  - The name of the table to be queried.
     *                   - Examples: "borrower", "book", "staff"
     * @param columnName - The name of the column to be queried.
     *                   - Examples: "id", "firstName", "lastName", "personalNumber", "emailAddress", "phoneNumber", "homeAddress",
     * @param value      - The value to be queried.
     *                   - Examples: "1", "John", "Doe", "1990-01-01", "
     * @return - Returns the row ID of the row that matches the query.
     */
    public static Integer getRowID (String tableName, String columnName, String value)
    {
        Integer rowID = null;
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String query = String.format("SELECT id FROM %s WHERE %s = '%s'", tableName, columnName, value);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                rowID = rs.getInt("id");
            }
        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return rowID;
    }


    /**
     * This method is used to insert data into a table in the database. It takes in a table name, a query and an array of values.
     * Examples of using this method:
     * - InsertIntoTable("INSERT INTO book (title, isbn, author, genre, shortDescription, bookFormat,
     * availability) VALUES (value, value, value, value, value, value, value)");
     *
     * @param query - The query to be executed.
     *              - Examples: "INSERT INTO book (title, isbn, author, genre, shortDescription, bookFormat, availability)
     * @return - Returns true if the query was executed successfully, otherwise false.
     */
    public static boolean InsertIntoTable (String query)
    {
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.executeUpdate();
            return true;
        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
            return false;
        }
    }

    public static Book getBook (int bookID)
    {
        Book newBook = new Book();
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String query = String.format("SELECT * FROM book WHERE id = %s", bookID);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                newBook = new Book(rs.getString("title"), rs.getString("isbn"), rs.getString("author"),
                        rs.getString("genre"), rs.getString("shortDescription"), rs.getString("bookFormat"),
                        rs.getBoolean("availability"));
            }
        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return newBook;
    }

    public static ArrayList<History> getBorrowingHistory (int borrowerID)
    {
        ArrayList<History> bookHistory = new ArrayList<History>();
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String query = String.format("SELECT * FROM borrowinghistory WHERE borrowerID = %s",
                    Integer.toString(borrowerID));
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                Book newBook = getBook(rs.getInt("bookID"));
                History newBookHistory = new History(newBook, rs.getString("borrowingDate"), rs.getString("returnDate"));
                bookHistory.add(newBookHistory);
            }

        } catch (SQLException e)

        {

            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return bookHistory;
    }

//    public static boolean addEmployeeToDatabase (Staff staff)
//    {
//        boolean result = false;
//
//        try
//        {
//            Connection conn = DriverManager.getConnection(url, userName, password);
//            String query = String.format("INSERT INTO staff(firstName, lastName, personalNumber, emailAddress, " +
//                            "phoneNumber, homeAddress, zipCode, city, employmentNumber) VALUES (%s, %s, %s, %s, %s, %s, %s, " +
//                            "%s, %s)", staff.getFirstName(), staff.getLastName(), staff.getPersonalNumber(),
//                    staff.getEmailAddress(), staff.getPhoneNumber(), staff.getHomeAddress(), staff.getZipCode(),
//                    staff.getCity(), staff.getEmploymentNumber());
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//            result = pstmt.execute();
//
//        } catch (SQLException e)
//        {
//            System.out.println("Connection to database failed, try again!");
//            e.printStackTrace();
//
//        }
//        return result;
//    }
//
//    public static boolean addBorrower (Borrower borrower)
//    {
//        boolean result = false;
//        try
//        {
//            Connection conn = DriverManager.getConnection(url, userName, password);
//            String query = "INSERT INTO borrowers(firstName, lastName, personalNumber, emailAddress, phoneNumber, " +
//                    "homeAddress, zipCode, city, libraryCardNumber, currentDebt) VALUES (?, ?, ?, ? , ? , ? , ? , ? , ? , ?)";
//
//
//            PreparedStatement pstmt = conn.prepareStatement(query);
//
//            pstmt.setString(1, borrower.getFirstName());
//            pstmt.setString(2, borrower.getLastName());
//            pstmt.setString(3, borrower.getPersonalNumber());
//            pstmt.setString(4, borrower.getEmailAddress());
//            pstmt.setString(5, borrower.getPhoneNumber());
//            pstmt.setString(6, borrower.getHomeAddress());
//            pstmt.setString(7, borrower.getZipCode());
//            pstmt.setString(8, borrower.getCity());
//            pstmt.setString(9, borrower.getLibraryCardNumber());
//            pstmt.setDouble(10, borrower.getCurrentDebt());
//
//
//            int rowsInserted = pstmt.executeUpdate();
//            if (rowsInserted > 0)
//                result = true;
//        } catch (Exception e)
//        {
//            System.out.println("Connection to database failed, try again!");
//            e.printStackTrace();
//        }
//
//        return result;
//    }


    public static String addDaysToCurrentDate (int i)
    {
        String date = null;
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String query = String.format("SELECT DATE_ADD(CURDATE(), INTERVAL %s DAY)", i);
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                date = rs.getString(1);
            }
        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return date;
    }

    public static boolean UpdateTable (String query)
    {
        boolean result = false;
        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            PreparedStatement pstmt = conn.prepareStatement(query);
            result = pstmt.execute();
        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<Book> getBooks ()
    {
        ArrayList<Book> books = new ArrayList<Book>();

        try
        {
            Connection conn = DriverManager.getConnection(url, userName, password);
            String query = "SELECT * FROM books";
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next())
            {
                Book newBook = new Book(rs.getString("title"), rs.getString("isbn"), rs.getString("author"),
                        rs.getString("genre"), rs.getString("shortDescription"), rs.getString("bookFormat"),
                        rs.getBoolean("availability"));
                books.add(newBook);
            }
        } catch (SQLException e)
        {
            System.out.println("Failed to connect, try again.");
            e.printStackTrace();
        }
        return books;
    }
}

//    public static String getLastLibraryCardNumber ()
//    {
//        String lastLibraryCardNumber = null;
//        try
//        {
//            Connection conn = DriverManager.getConnection(url, userName, password);
//            String query = "SELECT libraryCardNumber FROM borrowers ORDER BY libraryCardNumber DESC LIMIT 1";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next())
//            {
//                lastLibraryCardNumber = rs.getString(1);
//            }
//        } catch (SQLException e)
//        {
//            System.out.println("Failed to connect, try again.");
//            e.printStackTrace();
//        }
//        return lastLibraryCardNumber;
//    }
//
//    public static boolean editBorrower (Borrower borrower)
//    {
//        boolean result = false;
//        try
//        {
//            Connection conn = DriverManager.getConnection(url, userName, password);
//            String query = "UPDATE borrowers SET firstName = ?, lastName = ?, emailAddress = ?, " +
//                    "phoneNumber = ?, homeAddress = ?, zipCode = ?, city = ?, currentDebt = ? " +
//                    "WHERE personalNumber = ?";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, borrower.getFirstName());
//            pstmt.setString(2, borrower.getLastName());
//            pstmt.setString(3, borrower.getEmailAddress());
//            pstmt.setString(4, borrower.getPhoneNumber());
//            pstmt.setString(5, borrower.getHomeAddress());
//            pstmt.setString(6, borrower.getZipCode());
//            pstmt.setString(7, borrower.getCity());
//            pstmt.setDouble(9, borrower.getCurrentDebt());
//
//            int rowsUpdated = pstmt.executeUpdate();
//            if (rowsUpdated > 0)
//                result = true;
//
//        } catch (SQLException e)
//        {
//            System.out.println("Failed to connect, try again.");
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    public static Borrower findBorrowerByPersonalNumber (String personalNumber)
//    {
//        Borrower borrower = null;
//        try
//        {
//            Connection conn = DriverManager.getConnection(url, userName, password);
//            String query = "SELECT * FROM borrowers WHERE personalNumber = ?";
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, personalNumber);
//            ResultSet rs = pstmt.executeQuery();
//            while (rs.next())
//            {
//                borrower = new Borrower(rs.getString("firstName"), rs.getString("lastName"),
//                        rs.getString("personalNumber"), rs.getString("emailAddress"), rs.getString("phoneNumber"),
//                        rs.getString("homeAddress"), rs.getString("zipCode"), rs.getString("city"));
//            }
//            return borrower;
//        } catch (SQLException e)
//        {
//            System.out.println("Failed to connect, try again.");
//            e.printStackTrace();
//        }
//        return borrower;
//    }
//}
