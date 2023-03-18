package User;

public class User
{
    private String firstName;
    private String lastName;
    private String personalNumber;
    private String emailAddress;
    private String phoneNumber;
    private String homeAddress;
    private String zipCode;
    private String city;
    public User(String firstName, String lastName, String personalNumber, String emailAddress, String phoneNumber,
                String homeAddress, String zipCode, String city)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.personalNumber = personalNumber;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.homeAddress = homeAddress;
        this.zipCode = zipCode;
        this.city = city;
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
}
