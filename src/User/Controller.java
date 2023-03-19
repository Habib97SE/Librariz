package User;

import java.util.ArrayList;

public class Controller
{
    private String userType;
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String emailAdress;
    private String phoneNumber;
    private String homeAddress;
    private String zipCode;
    private String city;
    private String identifiersNumber;
    private String password;
    private String confirmPassword;

    public Controller (String userType)
    {
        this.userType = userType;
    }

    /**
     * This method is used to register a new user in the system.
     *
     * @param firstName       - the first name of the user
     *                        <p>- Example: "John"</p>
     * @param lastName        - the last name of the user
     *                        <p>- Example: "Doe"</p>
     * @param personalNumber  - the personal number of the user
     *                        <p>- Example: "19900101-1234"</p>
     *                        <p>- Example: "199001011234"</p>
     *                        <p>- Example: "900101-1234"</p>
     *                        <p>- Example: "9001011234"</p>
     * @param emailAdress     - the email address of the user
     *                        <p>- Example: "mail@mail.com"</p>
     * @param phoneNumber     - the phone number of the user
     *                        <p>- Example: "0701234567"</p>
     *                        <p>- Example: "070-1234567"</p>
     *                        <p>- Example: "070 1234567"</p>
     *                        <p>- Example: "070-123 45 67"</p>
     * @param homeAddress     - the home address of the user
     *                        <p>- Example: "Street 1"</p>
     * @param zipCode         - the zip code of the user
     *                        <p>- Example: "12345"</p>
     *                        <p>- Example: "123 45"</p>
     * @param city            - the city of the user
     *                        <p>- Example: "Stockholm"</p>
     * @param password        - the password of the user
     *                        <p>- Example: "password"</p>
     * @param confirmPassword - the confirm password of the user
     *                        <p>- Example: "password"</p>
     * @return returns true if the user is registered, false if the user is not registered
     * @throws IllegalArgumentException if the user is not registered
     */
    public boolean register (String firstName, String lastName, String personalNumber, String emailAdress,
                             String phoneNumber, String homeAddress, String zipCode, String city, String password,
                             String confirmPassword)
    {

        // check for the IllegalArgumentException
        if (firstName == null || firstName.isEmpty())
        {
            throw new IllegalArgumentException("First name is empty");
        }
        if (lastName == null || lastName.isEmpty())
        {
            throw new IllegalArgumentException("Last name is empty");
        }
        if (personalNumber == null || personalNumber.isEmpty())
        {
            throw new IllegalArgumentException("Personal number is empty");
        }
        if (emailAdress == null || emailAdress.isEmpty())
        {
            throw new IllegalArgumentException("Email address is empty");
        }
        if (phoneNumber == null || phoneNumber.isEmpty())
        {
            throw new IllegalArgumentException("Phone number is empty");
        }
        if (homeAddress == null || homeAddress.isEmpty())
        {
            throw new IllegalArgumentException("Home address is empty");
        }
        if (zipCode == null || zipCode.isEmpty())
        {
            throw new IllegalArgumentException("Zip code is empty");
        }
        if (city == null || city.isEmpty())
        {
            throw new IllegalArgumentException("City is empty");
        }
        if (password == null || password.isEmpty())
        {
            throw new IllegalArgumentException("Password is empty");
        }
        if (confirmPassword == null || confirmPassword.isEmpty())
        {
            throw new IllegalArgumentException("Confirm password is empty");
        }


        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.password = password;
        this.confirmPassword = confirmPassword;

        // communicate with the model to register the user
        Model model = new Model(this.userType, this.emailAdress, this.password, this.firstName, this.lastName,
                this.personalNumber, this.phoneNumber, this.homeAddress, Integer.parseInt(this.zipCode), this.city,
                this.identifiersNumber, 0.0);

        return model.createUser();

    }


    /**
     * This method is used to login a user in the system. It will check if the user is registered in the system.
     *
     * @param userType    - the type of user that is trying to login
     *                    <p>Example - "librarian"</p>
     *                    <p>Example - "borrower"</p>
     * @param emailAdress - the email address of the user
     *                    <p>Example - "mail@mail.com"</p>
     * @param password    <p>Example - "password"</p>
     * @return returns true if the user is logged in, false if the user is not logged in
     * @throws IllegalArgumentException If inappropiate arguments are passed to the method (null or empty)
     */
    public boolean login (String userType, String emailAdress, String password)
    {
        if (userType == null || userType.isEmpty())
        {
            throw new IllegalArgumentException("User type is empty");
        }
        if (emailAdress == null || emailAdress.isEmpty())
        {
            throw new IllegalArgumentException("Email address is empty");
        }
        if (password == null || password.isEmpty())
        {
            throw new IllegalArgumentException("Password is empty");
        }

        return new Model(userType, emailAdress, password).authenticate();

    }

    /**
     * This method is used to update the user information in the system. It will check if the user is registered in the
     *
     * @return returns true if the user is updated, false if the user is not updated.
     */
    public boolean deleteUser ()
    {
        return new Model(this.userType, emailAdress, password).deleteUser(this.personalNumber);
    }

    public boolean editUser ()
    {
        Model model = new Model(this.userType, this.emailAdress, this.password, this.firstName, this.lastName,
                this.personalNumber, this.phoneNumber, this.homeAddress, Integer.parseInt(this.zipCode), this.city,
                this.identifiersNumber, 0.0);
        return model.editUser();
    }


    public void reset ()
    {
        this.firstName = "";
        this.lastName = "";
        this.personalNumber = "";
        this.emailAdress = "";
        this.phoneNumber = "";
        this.homeAddress = "";
        this.zipCode = "";
        this.city = "";
        this.password = "";
        this.confirmPassword = "";
    }

    private static String starPersonalNumber (String personalNumber)
    {
        String star = "****";
        // star last 4 digits

        if (personalNumber.length() == 10)
        {
            // add - after sixth digit
            personalNumber = personalNumber.substring(0, 6) + "-" + personalNumber.substring(7);
            return personalNumber.substring(0, 7) + star;
        } else if (personalNumber.length() == 12)
        {
            // add - after eighth digit
            personalNumber = personalNumber.substring(0, 8) + "-" + personalNumber.substring(9);
            return personalNumber.substring(0, 9) + star;
        } else
        {
            return personalNumber;
        }
    }


    /**
     * <p>
     * This method is used to get all the users from the database. It will return a list of users with all the
     * information about the user.
     * </p>
     * <p>
     * Example:
     *      <ul>
     *          <li>First name</li>
     *          <li>Last name</li>
     *          <li>Personal number</li>
     *          <li>Email address</li>
     *          <li>Phone number</li>
     *          <li>Home address</li>
     *          <li>Zip code</li>
     *          <li>City</li>
     *          <li>Identifiers number</li>
     *          <li>Current fine</li>
     *      </ul>
     *      <pre>
     *          <code>
     *              {@code
     *                ArrayList<ArrayList<String>> users = User.Controller.getUsers();
     *              }
     *          </code>
     *      </pre>
     * </p>
     *
     * @return returns a list of users with all the information about the user.
     */
    public static ArrayList<ArrayList<String>> getUsers ()
    {
        ArrayList<Model> users = Model.getAllUsers("borrowers");
        ArrayList<ArrayList<String>> usersList = new ArrayList<ArrayList<String>>();
        assert users != null;
        for (Model user : users)
        {
            ArrayList<String> userInformation = new ArrayList<String>();
            userInformation.add(user.getFirstName());
            userInformation.add(user.getLastName());
            userInformation.add(starPersonalNumber(user.getPersonalNumber()));
            userInformation.add(user.getEmailAddress());
            userInformation.add(user.getPhoneNumber());
            userInformation.add(user.getHomeAddress());
            userInformation.add(String.valueOf(user.getZipCode()));
            userInformation.add(user.getCity());
            userInformation.add(String.valueOf(user.getLibraryCardNumber()));
            userInformation.add(String.valueOf(user.getCurrentDebt()));
            usersList.add(userInformation);
        }

        return usersList;
    }

}
