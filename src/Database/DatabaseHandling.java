package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Borrowing.*;
import Book.*;
import User.*;

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

    public static Connection connection = null;

    public DatabaseHandling () throws SQLException
    {
        connection = DriverManager.getConnection(url, userName, password);
    }

    public static boolean insertNewRow (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean checkIfRowExists (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static String getSingleValue (String query, String column)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return resultSet.getString(column);
            return null;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getRow (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getRows (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean deleteRow (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateRow (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static int getRowId (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("id");
            return -1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }

    public static ArrayList<Book> searchBooks (String query)
    {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                Book book = new Book();
                book.setIsbn(resultSet.getString("isbn"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setGenre(resultSet.getString("genre"));
                book.setLanguage(resultSet.getString("language"));
                book.setDescription(resultSet.getString("description"));
                book.setPublicationDate(resultSet.getString("publicationDate"));
                book.setEdition(resultSet.getString("edition"));
                book.setNumberOfCopies(resultSet.getString("numberOfCopies"));
                book.setNumberOfPages(resultSet.getString("numberOfPages"));
                book.setNumberOfAvailableCopies(resultSet.getString("numberOfAvailableCopies"));
                books.add(book);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        return books;
    }

    public static User getUser(String personalNumber)
    {
        User user = new User();
        try {
            String query = "SELECT * FROM users WHERE personalNumber = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, personalNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {

            }
        } catch (Exception e) {
            return null;
        }
    }

    public static int getUserIdByPersonalNumber (String personalNumber) throws Exception
    {
        int userId = -1;
        String query = "SELECT * FROM users WHERE personalNumber = ?";
        try (Connection conn = DriverManager.getConnection(url, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, personalNumber);
            ResultSet rs = stmt.executeQuery();
            if (rs.next())
            {
                userId = rs.getInt("id");
            }
        }
        return userId;
    }


    public static boolean insertNewBorrowing (Borrowing borrowing) throws Exception
    {
        String query = "INSERT INTO borrowings (userId, bookId, borrowed_at, returned_at) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, getUserIdByPersonalNumber(borrowing.getUser().getPersonalNumber()));
            stmt.setInt(2, getBookIdByISBN(borrowing.getBook().getIsbn()));
            stmt.setString(3, borrowing.getDateString());
            stmt.setString(4, borrowing.getReturnDateString());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static int getBookIdByISBN (String query)
    {
        try
        {
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("id");
            return -1;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return -1;
        }
    }
}
