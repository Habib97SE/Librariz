package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import Borrowing.Borrowing;
import Database.DatabaseHandling;
import Database.SecureData;


// this class is the model for the user class
public class Model
{

    private User user;


    public Model ()
    {
        user = new User();
    }

    public Model (User user)
    {
        this.user = user;
    }

    public Model (Model other)
    {
        this.user = other.user;
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public boolean addUser ()
    {
        user.setIdentifierNumber(createIdentifierNumber());
        // print out all the values of the user object

        String query = "INSERT INTO users (firstName, lastName, emailAddress, phoneNumber, homeAddress, zipCode, city, identifierNumber, currentFine, password, userType, personalNumber) VALUES ('" + user.getFirstName() + "', '" + user.getLastName() + "', '" + user.getEmailAddress() + "', '" + user.getPhoneNumber() + "', '" + user.getHomeAddress() + "', '" + user.getZipCode() + "', '" + user.getCity() + "', '" + user.getIdentifierNumber() + "', '" + user.getCurrentFine() + "', '" + user.getPassword() + "', '" + user.getUserType() + "', '" + user.getPersonalNumber() + "')";
        return DatabaseHandling.insertNewRow(query);
    }

    public boolean updateUser ()
    {
        String query = "UPDATE users SET firstName = '" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', emailAddress = '" + user.getEmailAddress() + "', phoneNumber = '" + user.getPhoneNumber() + "', homeAddress = '" + user.getHomeAddress() + "', zipCode = '" + user.getZipCode() + "', city = '" + user.getCity() + "', identifierNumber = '" + user.getIdentifierNumber() + "', currentFine = '" + user.getCurrentFine() + "', password = '" + user.getPassword() + "', userType = '" + user.getUserType() + "', personalNumber = '" + user.getPersonalNumber() + "' WHERE identifierNumber = '" + user.getIdentifierNumber() + "'";
        return DatabaseHandling.insertNewRow(query);
    }

    public boolean deleteUser ()
    {
        String query = "DELETE FROM users WHERE identifierNumber = '" + user.getIdentifierNumber() + "'";
        return DatabaseHandling.insertNewRow(query);
    }

    public User getUser (String personalNumber) throws Exception
    {
        try
        {
            String query = "SELECT * FROM users WHERE personalNumber = '" + personalNumber + "'";
            ResultSet rs = DatabaseHandling.getRow(query);
            assert rs != null;
            if (rs.next())
            {
                return new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("emailAddress"), rs.getString("phoneNumber"), rs.getString("homeAddress"), rs.getString("zipCode"), rs.getString("city"), rs.getString("identifierNumber"), rs.getDouble("currentFine"), rs.getString("password"), rs.getString("userType"), Database.SecureData.decrypt(rs.getString("personalNumber")));
            } else
            {
                return null;
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        } catch (Exception e)
        {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<User> getAllUsers ()
    {
        ArrayList<User> users = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM users";
            ResultSet rs = DatabaseHandling.getRows(query);
            assert rs != null;
            while (rs.next())
            {
                users.add(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("emailAddress"), rs.getString("phoneNumber"), rs.getString("homeAddress"), rs.getString("zipCode"), rs.getString("city"), rs.getString("identifierNumber"), rs.getDouble("currentFine"), rs.getString("password"), rs.getString("userType"), rs.getString("personalNumber")));
            }
            return users;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<User> getAllUsers (String userType)
    {
        ArrayList<User> users = new ArrayList<>();
        try
        {
            String query = "SELECT * FROM users WHERE userType = '" + userType + "'";
            ResultSet rs = DatabaseHandling.getRows(query);
            assert rs != null;
            while (rs.next())
            {
                users.add(new User(rs.getString("firstName"), rs.getString("lastName"), rs.getString("emailAddress"), rs.getString("phoneNumber"), rs.getString("homeAddress"), rs.getString("zipCode"), rs.getString("city"), rs.getString("identifierNumber"), rs.getDouble("currentFine"), rs.getString("password"), rs.getString("userType"), rs.getString("personalNumber")));
            }
            return users;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public boolean checkIfUserExists (String personalNumber)
    {
        try
        {
            String query = "SELECT * FROM users WHERE personalNumber = '" + personalNumber + "'";
            ResultSet rs = DatabaseHandling.getRow(query);
            assert rs != null;
            // if user exists return true else return false
            return rs.next();
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * This method is used to get the last identifier number from the database
     *
     * @param personalNumber the personal number of the user
     * @return the last identifier number
     */
    private String getYear (String personalNumber)
    {
        if (personalNumber.startsWith("19") || personalNumber.startsWith("20"))
        {
            return personalNumber.substring(2, 4);
        }
        return personalNumber.substring(0, 2);
    }

    public String createIdentifierNumber ()
    {
        // 3 char first name + 3 char last name + birthyear in YY format
        String identifierNumber = "";
        identifierNumber += user.getFirstName().substring(0, 3);
        identifierNumber += user.getLastName().substring(0, 3);
        identifierNumber += getYear(user.getPersonalNumber());
        return identifierNumber;
    }

    public ArrayList<Borrowing> getBorrowingsHistory ()
    {
        return DatabaseHandling.getBorrowingsHistory(user.getUserID());
    }
}
