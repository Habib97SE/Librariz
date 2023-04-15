package User;

import Borrowing.Borrowing;

import java.util.ArrayList;
import java.util.Objects;

public class Controller
{
    private User user;
    private Model model;

    public Controller ()
    {
        user = new User();
        model = new Model(user);
    }

    public Controller (User user)
    {
        this.user = user;
        model = new Model(user);
    }

    public User getUser ()
    {
        return user;
    }

    public User getUserById (String id)
    {
        return model.getUserById(id);
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public boolean addUser (User user) throws Exception
    {
        String encryptedPersonalNumber = Database.SecureData.encrypt(user.getPersonalNumber());
        String encryptedPassword = Database.SecureData.encrypt(user.getPassword());
        user.setPersonalNumber(encryptedPersonalNumber);
        user.setPassword(encryptedPassword);
        Model model = new Model(user);
        return model.addUser();
    }

    public boolean updateUser (User updatedUser)
    {
        return model.updateUser(updatedUser);
    }

    public boolean deleteUser ()
    {
        return model.deleteUser();
    }

    public User getUser (String personalNumber) throws Exception
    {
        return model.getUser(personalNumber);
    }

    public ArrayList<User> getAllUsers ()
    {
        return model.getAllUsers();
    }

    public ArrayList<User> getAllUsers (String userType)
    {
        return model.getAllUsers(userType);
    }

    private boolean checkIfUserExists (String personalNumber)
    {
        return model.checkIfUserExists(personalNumber);
    }

    private boolean isNameValid (String name)
    {
        return Database.SecureData.isValidName(name);
    }

    private boolean isPersonalNumberValid (String personalNumber)
    {
        return Database.SecureData.isValidPersonalNumber(personalNumber);
    }

    private boolean isEmailAddressValid (String emailAddress)
    {
        // check if the email address is already in the database
        if (model.checkIfUserExists(emailAddress))
        {
            return false;
        }
        return Database.SecureData.isValidEmail(emailAddress);
    }

    private boolean isPhoneNumberValid (String phoneNumber)
    {
        return Database.SecureData.isValidPhoneNumber(phoneNumber);
    }

    private boolean isPasswordValid (String password)
    {
        return Database.SecureData.isValidPassword(password);
    }

    private boolean isAddressValid (String address)
    {
        return Database.SecureData.isValidAddress(address);
    }

    private boolean isZipCodeValid (String zipCode)
    {
        return Database.SecureData.isValidZipCode(zipCode);
    }

    private boolean isCityValid (String city)
    {
        return Database.SecureData.isValidCity(city);
    }

    private String generateIdentifiersNumber ()
    {
        // get the last identifier number from the database
        String lastIdentifierNumber = model.createIdentifierNumber();
        String lastLibraryIdentifierNumber = model.createIdentifierNumber();
        if (lastLibraryIdentifierNumber == null)
        {
            return "0000000000000000";
        } else
        {
            // plus one to the last identifier digit and return it
            return String.valueOf(Integer.parseInt(lastLibraryIdentifierNumber) + 1);
        }
    }

    public boolean editUser (User user)
    {
        this.user = user;
        return updateUser(user);
    }

    public ArrayList<Borrowing> getBorrowingsHistory ()
    {
        return model.getBorrowingsHistory();
    }

    public void logOut ()
    {
        user = new User();

    }

    public enum Error
    {
        NONE,
        FIRST_NAME,
        LAST_NAME,
        EMAIL_ADDRESS,
        DUPLICATE_EMAIL_ADDRESS,
        PHONE_NUMBER,
        HOME_ADDRESS,
        ZIP_CODE,
        CITY,
        IDENTIFIER_NUMBER,
        CURRENT_FINE,
        PASSWORD,
        USER_TYPE,
        PERSONAL_NUMBER,
        USER_EXISTS
    }

    /**
     * @return
     */
    public Error validateInputs ()
    {
        if (checkIfUserExists(user.getPersonalNumber()))
        {
            return Error.USER_EXISTS;
        }

        if (!isNameValid(user.getFirstName()))
        {
            return Error.FIRST_NAME;
        }

        if (!isNameValid(user.getLastName()))
        {
            return Error.LAST_NAME;
        }

        if (!isEmailAddressValid(user.getEmailAddress()))
        {
            return Error.EMAIL_ADDRESS;
        }

        if (!isPhoneNumberValid(user.getPhoneNumber()))
        {
            return Error.PHONE_NUMBER;
        }

        if (!isAddressValid(user.getHomeAddress()))
        {
            return Error.HOME_ADDRESS;
        }

        if (!isZipCodeValid(user.getZipCode()))
        {
            return Error.ZIP_CODE;
        }

        if (!isCityValid(user.getCity()))
        {
            return Error.CITY;
        }

        if (!isPasswordValid(user.getPassword()))
        {
            return Error.PASSWORD;
        }

        if (!isPersonalNumberValid(user.getPersonalNumber()))
        {
            return Error.PERSONAL_NUMBER;
        }
        return Error.NONE;
    }


}
