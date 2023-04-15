package Database;

import java.sql.*;
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

    public static User getUser (String personalNumber)
    {
        User user = new User();
        try
        {
            String query = "SELECT * FROM users WHERE personalNumber = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, personalNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                user.setUserID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmailAddress(resultSet.getString("emailAddress"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setHomeAddress(resultSet.getString("homeAddress"));
                user.setZipCode(resultSet.getString("zipCode"));
                user.setCity(resultSet.getString("city"));
                user.setIdentifierNumber(resultSet.getString("identifierNumber"));
                user.setCurrentFine(resultSet.getDouble("currentFine"));
                user.setPassword(resultSet.getString("password"));
                user.setPersonalNumber(resultSet.getString("personalNumber"));

                return user;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
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
        String query = "INSERT INTO borrowings (bookId, userId, borrowingDate, returnDate) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, userName, password);
             PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, borrowing.getBookID());
            stmt.setInt(2, borrowing.getUserID());
            stmt.setString(3, borrowing.getBorrowingDate());
            stmt.setString(4, borrowing.getReturnDate());
            return stmt.executeUpdate() > 0;
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

    public static User getUserByPersonalNumber (String encryptedPersonalNumber)
    {
        try
        {
            String query = "SELECT * FROM users WHERE personalNumber = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, encryptedPersonalNumber);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                User user = new User();
                user.setUserID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmailAddress(resultSet.getString("emailAddress"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setHomeAddress(resultSet.getString("homeAddress"));
                user.setZipCode(resultSet.getString("zipCode"));
                user.setCity(resultSet.getString("city"));
                user.setIdentifierNumber(resultSet.getString("identifierNumber"));
                user.setCurrentFine(resultSet.getDouble("currentFine"));
                user.setPassword(resultSet.getString("password"));
                user.setPersonalNumber(resultSet.getString("personalNumber"));
                return user;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }

    public static Book getBookByISBN (String isbn)
    {
        try
        {
            String query = "SELECT * FROM books WHERE isbn = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, isbn);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                Book book = new Book();
                book.setBookID(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setGenre(resultSet.getString("genre"));
                book.setLanguage(resultSet.getString("language"));
                book.setDescription(resultSet.getString("description"));
                book.setPublicationDate(resultSet.getString("publicationDate"));
                book.setEdition(resultSet.getString("edition"));
                book.setNumberOfCopies(resultSet.getString("numberOfCopies"));
                book.setNumberOfPages(resultSet.getString("numberOfPages"));
                book.setNumberOfAvailableCopies(resultSet.getString("numberOfAvailableCopies"));
                return book;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }

    public static String getLastReturnDate (int borrowingID)
    {
        // get the last record where both userID and bookID are the same
        try
        {
            String query = "SELECT * FROM borrowings WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, borrowingID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getString("returnDate");
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }

    public static boolean updateUserFine (int userID, int fine)
    {
        try
        {
            String query = "UPDATE users SET currentFine = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, fine);
            statement.setInt(2, userID);
            return statement.executeUpdate() > 0;
        } catch (Exception e)
        {
            return false;
        }
    }

    public static boolean updateBookAvailableCopies (int bookID, String s)
    {
        try
        {
            String query = "UPDATE books SET numberOfAvailableCopies = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, s);
            statement.setInt(2, bookID);
            return statement.executeUpdate() > 0;

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    public static int getBorrowingID (int bookID, int userID)
    {
        try
        {
            String query = "SELECT * FROM borrowings WHERE bookId = ? AND userId = ? ORDER BY id DESC LIMIT 1";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, bookID);
            statement.setInt(2, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                return resultSet.getInt("id");
            } else
            {
                return -1;
            }
        } catch (Exception e)
        {
            return -1;
        }
    }

    public static boolean updateActualReturnedDate (int borrowingID, String toString)
    {
        try
        {
            String query = "UPDATE borrowings SET actualReturnedDate = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, toString);
            statement.setInt(2, borrowingID);
            return statement.executeUpdate() > 0;

        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Borrowing> getBorrowingsHistory (int userID)
    {
        ArrayList<Borrowing> borrowings = new ArrayList<>();

        try
        {
            String query = "SELECT * FROM borrowings WHERE userId = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                boolean activeBorrowing = resultSet.getInt("activeBorrowing") == 1;
                if (activeBorrowing)
                    continue;
                Timestamp borrowingDate = resultSet.getTimestamp("borrowingDate");
                Timestamp returnDate = resultSet.getTimestamp("returnDate");
                Borrowing borrowing = new Borrowing(getBookByID(resultSet.getString("bookId")),
                        getUserByID(resultSet.getInt("userId")), borrowingDate, returnDate,
                        activeBorrowing);

                borrowings.add(borrowing);
            }
            return borrowings;
        } catch (Exception e)
        {
            e.printStackTrace();
            return borrowings;
        }
    }

    private static User getUserByID (int userId)
    {
        try
        {
            String query = "SELECT * FROM users WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                User user = new User();
                user.setUserID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmailAddress(resultSet.getString("emailAddress"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setHomeAddress(resultSet.getString("homeAddress"));
                user.setZipCode(resultSet.getString("zipCode"));
                user.setCity(resultSet.getString("city"));
                user.setIdentifierNumber(resultSet.getString("identifierNumber"));
                user.setCurrentFine(resultSet.getDouble("currentFine"));
                user.setPassword(resultSet.getString("password"));
                user.setPersonalNumber(resultSet.getString("personalNumber"));
                return user;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }

    private static Book getBookByID (String bookId)
    {
        try
        {
            String query = "SELECT * FROM books WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, bookId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                Book book = new Book();
                book.setBookID(resultSet.getInt("id"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setGenre(resultSet.getString("genre"));
                book.setLanguage(resultSet.getString("language"));
                book.setDescription(resultSet.getString("description"));
                book.setPublicationDate(resultSet.getString("publicationDate"));
                book.setEdition(resultSet.getString("edition"));
                book.setNumberOfCopies(resultSet.getString("numberOfCopies"));
                book.setNumberOfPages(resultSet.getString("numberOfPages"));
                book.setNumberOfAvailableCopies(resultSet.getString("numberOfAvailableCopies"));
                return book;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static int checkLogin (String emailAddress, String userPassword) throws Exception
    {
        int userID = -1;
        userPassword = SecureData.encrypt(userPassword);

        String query = "SELECT id FROM users WHERE emailAddress = ? AND password = ?";
        Connection connection = DriverManager.getConnection(url, userName, password);
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, emailAddress);
        statement.setString(2, userPassword);


        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next())
        {
            userID = resultSet.getInt("id");
        }
        return userID;
    }

    public static User getUserById (int userID)
    {
        try
        {
            String query = "SELECT * FROM users WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
            {
                User user = new User();
                user.setUserID(resultSet.getInt("id"));
                user.setFirstName(resultSet.getString("firstName"));
                user.setLastName(resultSet.getString("lastName"));
                user.setEmailAddress(resultSet.getString("emailAddress"));
                user.setPhoneNumber(resultSet.getString("phoneNumber"));
                user.setHomeAddress(resultSet.getString("homeAddress"));
                user.setZipCode(resultSet.getString("zipCode"));
                user.setCity(resultSet.getString("city"));
                user.setIdentifierNumber(resultSet.getString("identifierNumber"));
                user.setCurrentFine(resultSet.getDouble("currentFine"));
                user.setPassword(resultSet.getString("password"));
                user.setPersonalNumber(resultSet.getString("personalNumber"));
                user.setUserType(resultSet.getString("userType"));
                return user;
            } else
            {
                return null;
            }
        } catch (Exception e)
        {
            return null;
        }
    }

    public static boolean updateUser (User editedUser)
    {
        try
        {
            String query = "UPDATE users SET firstName = ?, lastName = ?, emailAddress = ?, phoneNumber = ?, homeAddress = ?, zipCode = ?, city = ?, identifierNumber = ?, currentFine = ?, password = ?, personalNumber = ? WHERE id = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, editedUser.getFirstName());
            statement.setString(2, editedUser.getLastName());
            statement.setString(3, editedUser.getEmailAddress());
            statement.setString(4, editedUser.getPhoneNumber());
            statement.setString(5, editedUser.getHomeAddress());
            statement.setString(6, editedUser.getZipCode());
            statement.setString(7, editedUser.getCity());
            statement.setString(8, editedUser.getIdentifierNumber());
            statement.setDouble(9, editedUser.getCurrentFine());
            statement.setString(10, SecureData.encrypt(editedUser.getPassword()));
            statement.setString(11, editedUser.getPersonalNumber());
            statement.setInt(12, editedUser.getUserID());
            return statement.executeUpdate() > 0;
        } catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public static ArrayList<Borrowing> getCurrentBorrowings (int userID)
    {
        ArrayList<Borrowing> borrowings = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM borrowings WHERE userID = ?";
            Connection connection = DriverManager.getConnection(url, userName, password);
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, userID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next())
            {
                if (resultSet.getInt("activeBorrowing") == 0)
                    continue;

                Borrowing borrowing = new Borrowing();
                borrowing.setBook(getBookByID(resultSet.getString("bookID")));
                borrowing.setStartDate(resultSet.getString("borrowingDate"));
                borrowing.setEndDate(resultSet.getString("returnDate"));
                borrowing.setActiveBorrowing(true);
                borrowings.add(borrowing);
            }
            return borrowings;
        } catch (Exception e)
        {
            e.printStackTrace();
            return borrowings;
        }
    }
}
