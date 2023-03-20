package User;

public class User
{
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String homeAddress;
    private String zipCode;
    private String city;
    private String identifierNumber;
    private double currentFine;
    private String password;
    private String userType;
    private String personalNumber;

    public User(String firstName, String lastName, String emailAddress, String phoneNumber, String homeAddress, String zipCode, String city, String identifierNumber, double currentFine, String password, String userType, String personalNumber)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.zipCode = zipCode;
        this.city = city;
        this.identifierNumber = identifierNumber;
        this.currentFine = currentFine;
        this.password = password;
        this.userType = userType;
        this.personalNumber = personalNumber;
    }

    public User()
    {
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.phoneNumber = "";
        this.homeAddress = "";
        this.zipCode = "";
        this.city = "";
        this.identifierNumber = "";
        this.currentFine = 0;
        this.password = "";
        this.userType = "";
        this.personalNumber = "";
    }

    public User(User other)
    {
        this.firstName = other.firstName;
        this.lastName = other.lastName;
        this.emailAddress = other.emailAddress;
        this.phoneNumber = other.phoneNumber;
        this.homeAddress = other.homeAddress;
        this.zipCode = other.zipCode;
        this.city = other.city;
        this.identifierNumber = other.identifierNumber;
        this.currentFine = other.currentFine;
        this.password = other.password;
        this.userType = other.userType;
        this.personalNumber = other.personalNumber;
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

    public String getEmailAddress ()
    {
        return emailAddress;
    }

    public void setEmailAddress (String emailAddress)
    {
        this.emailAddress = emailAddress;
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

    public String getZipCode ()
    {
        return zipCode;
    }

    public void setZipCode (String zipCode)
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

    public String getIdentifierNumber ()
    {
        return identifierNumber;
    }

    public void setIdentifierNumber (String identifierNumber)
    {
        this.identifierNumber = identifierNumber;
    }

    public double getCurrentFine ()
    {
        return currentFine;
    }

    public void setCurrentFine (double currentFine)
    {
        this.currentFine = currentFine;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getUserType ()
    {
        return userType;
    }

    public void setUserType (String userType)
    {
        this.userType = userType;
    }

    public String getPersonalNumber ()
    {
        return personalNumber;
    }

    public void setPersonalNumber (String personalNumber)
    {
        this.personalNumber = personalNumber;
    }

    public boolean equals(User other)
    {
        return this.getPersonalNumber().equals(other.getPersonalNumber());
    }


    @Override
    public String toString()
    {
        return "User{" + "firstName=" + firstName + ", lastName=" + lastName + ", emailAddress=" + emailAddress + ", phoneNumber=" + phoneNumber + ", homeAddress=" + homeAddress + ", zipCode=" + zipCode + ", city=" + city + ", identifierNumber=" + identifierNumber + ", currentFine=" + currentFine + ", password=" + password + ", userType=" + userType + ", personalNumber=" + personalNumber + '}';
    }
}
