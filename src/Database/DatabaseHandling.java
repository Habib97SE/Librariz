package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public static ResultSet getRow (String query)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getRows(String query)
    {
        try
        {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteRow(String query)
    {
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


}
