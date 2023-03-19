package User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

// this class is the model for the user class
public class Model
{
    private String userType;
    private String emailAddress;
    private String password;
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String phoneNumber;
    private String homeAddress;
    private int zipCode;
    private String city;
    private String libraryCardNumber;
    private double currentDebt;
    private static String dbUrl = "jdbc:mysql://localhost:3306/librariz";
    private static String dbUserName = "root";
    private static String dbPassword = "";

    public Model (String userType, String emailAddress, String password)
    {
        this.userType = userType;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public Model (String userType, String emailAddress, String password, String firstName, String lastName, String personalNumber, String phoneNumber, String homeAddress, int zipCode, String city, String libraryCardNumber, double currentDebt)
    {
        this.userType = userType;
        this.emailAddress = emailAddress;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.libraryCardNumber = libraryCardNumber;
        this.currentDebt = currentDebt;
    }

    public String getUserType ()
    {
        return userType;
    }

    public void setUserType (String userType)
    {
        this.userType = userType;
    }

    public String getEmailAddress ()
    {
        return emailAddress;
    }

    public void setEmailAddress (String emailAddress)
    {
        this.emailAddress = emailAddress;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getPersonalNumber ()
    {
        return personalNumber;
    }

    public void setPersonalNumber (String personalNumber)
    {
        this.personalNumber = personalNumber;
    }

    public String getPhoneNumber ()
    {
        return phoneNumber;
    }

    public void setPhoneNumber (String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
    }

    public String getHomeAddress ()
    {
        return homeAddress;
    }

    public void setHomeAddress (String homeAddress)
    {
        this.homeAddress = homeAddress;
    }

    public int getZipCode ()
    {
        return zipCode;
    }

    public void setZipCode (int zipCode)
    {
        this.zipCode = zipCode;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }

    public String getLibraryCardNumber ()
    {
        return libraryCardNumber;
    }

    public void setLibraryCardNumber (String libraryCardNumber)
    {
        this.libraryCardNumber = libraryCardNumber;
    }

    public double getCurrentDebt ()
    {
        return currentDebt;
    }

    public void setCurrentDebt (double currentDebt)
    {
        this.currentDebt = currentDebt;
    }


    /**
     * <p>
     * This method creates a new user in the database and returns true if the user was created successfully.
     * </p>
     *
     * @return boolean true if the user was created successfully, false if not.
     */
    public boolean createUser ()
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "INSERT INTO users (userType, emailAddress, password, firstName, lastName, personalNumber," +
                    " phoneNumber, homeAddress, zipCode, city, identifiersNumber, currentFine) VALUES (?, ?, ?, ?, " +
                    "?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, this.userType);
            statement.setString(2, this.emailAddress);
            statement.setString(3, this.password);
            statement.setString(4, this.firstName);
            statement.setString(5, this.lastName);
            statement.setString(6, this.personalNumber);
            statement.setString(7, this.phoneNumber);
            statement.setString(8, this.homeAddress);
            statement.setInt(9, this.zipCode);
            statement.setString(10, this.city);
            statement.setString(11, this.libraryCardNumber);
            statement.setDouble(12, this.currentDebt);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e)
        {
            return false;
        }

    }


    public Model getUSerByEmail (String emailAddress)
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "SELECT * FROM users WHERE emailAddress = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, emailAddress);
            ResultSet result = statement.executeQuery();
            if (result.next())
            {
                return new Model(result.getString("userType"), result.getString("emailAddress"), result.getString(
                        "password"), result.getString("firstName"), result.getString("lastName"), result.getString("personalNumber"), result.getString("phoneNumber"), result.getString("homeAddress"), result.getInt("zipCode"), result.getString("city"), result.getString("identifiersNumber"), result.getDouble("currentFine"));
            } else
            {
                return null;
            }
        } catch (SQLException e)
        {
            return null;
        }
    }

    public Model getUserByPersonalNumber (String personalNumber)
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "SELECT * FROM users WHERE personalNumber = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, personalNumber);
            ResultSet result = statement.executeQuery();
            if (result.next())
            {
                return new Model(result.getString("userType"), result.getString("emailAddress"),
                        result.getString("password"), result.getString("firstName"), result.getString("lastName"), result.getString("personalNumber"), result.getString("phoneNumber"), result.getString("homeAddress"), result.getInt("zipCode"), result.getString("city"), result.getString("identifiersNumber"), result.getDouble("currentFine"));
            } else
            {
                return null;
            }
        } catch (SQLException e)
        {
            return null;
        }
    }

    public Model getUserByIdentifierNumber (String identifierNumber)
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "SELECT * FROM users WHERE identifiersNumber = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, identifierNumber);
            ResultSet result = statement.executeQuery();
            if (result.next())
            {
                return new Model(result.getString("userType"), result.getString("emailAddress"),
                        result.getString("password"), result.getString("firstName"), result.getString("lastName"), result.getString("personalNumber"), result.getString("phoneNumber"), result.getString("homeAddress"), result.getInt("zipCode"), result.getString("city"), result.getString("identifiersNumber"), result.getDouble("currentFine"));
            } else
            {
                return null;
            }
        } catch (SQLException e)
        {
            return null;
        }
    }


    public boolean updateUser (Model user)
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "UPDATE users SET userType = ?, emailAddress = ?, password = ?, firstName = ?, lastName = ?, personalNumber = ?, phoneNumber = ?, homeAddress = ?, zipCode = ?, city = ?, identifiersNumber = ?, currentFine = ? WHERE emailAddress = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, user.getUserType());
            statement.setString(2, user.getEmailAddress());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getFirstName());
            statement.setString(5, user.getLastName());
            statement.setString(6, user.getPersonalNumber());
            statement.setString(7, user.getPhoneNumber());
            statement.setString(8, user.getHomeAddress());
            statement.setInt(9, user.getZipCode());
            statement.setString(10, user.getCity());
            statement.setString(11, user.getLibraryCardNumber());
            statement.setDouble(12, user.getCurrentDebt());
            statement.setString(13, user.getEmailAddress());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e)
        {
            return false;
        }
    }


    /**
     * <p>Deletes a user from the database based on the personal number.</p>
     * <strong>The user can only been deleted by personal number</strong>
     *
     * <p>Example:</p>
     * <code>
     * UserDAO userDAO = new UserDAO();
     * userDAO.deleteUser(YYMMDDxxxx);
     * </code>
     *
     * @param personalNumber The personal number of the user
     * @return true if the user was deleted, false if not
     */
    public boolean deleteUser (String personalNumber)
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "DELETE FROM users WHERE personalNumber = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, personalNumber);
            int rowsDeleted = statement.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e)
        {
            return false;
        }
    }

    private static ArrayList<Model> getBorrowers ()
    {
        ArrayList<Model> borrowers = new ArrayList<>();
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "SELECT * FROM users WHERE userType = 0";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                Model user = new Model(result.getString("userType"), result.getString("emailAddress"),
                        result.getString("password"), result.getString("firstName"), result.getString("lastName"), result.getString("personalNumber"), result.getString("phoneNumber"), result.getString("homeAddress"), result.getInt("zipCode"), result.getString("city"), result.getString("identifiersNumber"), result.getDouble("currentFine"));
                borrowers.add(user);
            }
        } catch (SQLException e)
        {
            return null;
        }
        return borrowers;
    }

    private static ArrayList<Model> getEmployees ()
    {
        ArrayList<Model> employees = new ArrayList<>();
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "SELECT * FROM users WHERE userType = 1";
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                Model user = new Model(result.getString("userType"), result.getString("emailAddress"), result.getString("password"), result.getString("firstName"), result.getString("lastName"), result.getString("personalNumber"), result.getString("phoneNumber"), result.getString("homeAddress"), result.getInt("zipCode"), result.getString("city"), result.getString("identifiersNumber"), result.getDouble("currentFine"));
                employees.add(user);
            }
        } catch (SQLException e)
        {
            return null;
        }
        return employees;
    }


    public static ArrayList<Model> getAllUsers (String userType)
    {
        if (userType.equalsIgnoreCase("borrowers"))
        {

            return getBorrowers();
        } else if (userType.equalsIgnoreCase("employees"))
        {
            return getEmployees();
        } else
        {
            return null;
        }
    }

    public boolean authenticate ()
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "SELECT * FROM users WHERE emailAddress = ? AND password = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, emailAddress);
            statement.setString(2, password);
            ResultSet result = statement.executeQuery();
            return result.next();
        } catch (SQLException e)
        {
            return false;
        }
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Model user = (Model) o;
        return Objects.equals(userType, user.userType) &&
                zipCode == user.zipCode &&
                Double.compare(user.currentDebt, currentDebt) == 0 &&
                Objects.equals(emailAddress, user.emailAddress) &&
                Objects.equals(password, user.password) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(personalNumber, user.personalNumber) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(homeAddress, user.homeAddress) &&
                Objects.equals(city, user.city) &&
                Objects.equals(libraryCardNumber, user.libraryCardNumber);
    }

    @Override
    public int hashCode ()
    {
        return Objects.hash(userType, emailAddress, password, firstName, lastName, personalNumber, phoneNumber, homeAddress, zipCode, city, libraryCardNumber, currentDebt);
    }

    @Override
    public String toString ()
    {
        return String.format("User{userType='%s', emailAddress='%s', password='%s', firstName='%s', lastName='%s', personalNumber='%s', phoneNumber='%s', homeAddress='%s', zipCode=%d, city='%s', libraryCardNumber='%s', currentDebt=%f}", userType, emailAddress, password, firstName, lastName, personalNumber, phoneNumber, homeAddress, zipCode, city, libraryCardNumber, currentDebt);
    }

    public boolean editUser ()
    {
        try
        {
            Connection conn = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            String query = "UPDATE users SET emailAddress = ?, password = ?, firstName = ?, lastName = ?, personalNumber = ?, phoneNumber = ?, homeAddress = ?, zipCode = ?, city = ?, identifiersNumber = ?, currentFine = ? WHERE emailAddress = ?";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, emailAddress);
            statement.setString(2, password);
            statement.setString(3, firstName);
            statement.setString(4, lastName);
            statement.setString(5, personalNumber);
            statement.setString(6, phoneNumber);
            statement.setString(7, homeAddress);
            statement.setInt(8, zipCode);
            statement.setString(9, city);
            statement.setString(10, libraryCardNumber);
            statement.setDouble(11, currentDebt);
            statement.setString(12, emailAddress);

            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e)
        {
            return false;
        }
    }


}
