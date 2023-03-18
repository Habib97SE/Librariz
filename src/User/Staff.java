package User;

public class Staff extends User
{
    private String userName;
    private String password;
    private String employmentNumber;



    public Staff(String firstName, String lastName, String personalNumber, String emailAddress, String phoneNumber,
                 String homeAddress, String zipCode, String city, String userName, String password, String employmentNumber)
    {
        super(firstName, lastName, personalNumber, emailAddress, phoneNumber, homeAddress, zipCode, city);
        this.userName = userName;
        this.password = password;
        this.employmentNumber = employmentNumber;
    }

    public String getUserName ()
    {
        return userName;
    }

    public void setUserName (String userName)
    {
        this.userName = userName;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    public String getEmploymentNumber ()
    {
        return employmentNumber;
    }

    public void setEmploymentNumber (String employmentNumber)
    {
        this.employmentNumber = employmentNumber;
    }

}
