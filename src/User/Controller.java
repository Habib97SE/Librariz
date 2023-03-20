package User;

import java.util.ArrayList;

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
    }

    public User getUser ()
    {
        return user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public boolean addUser (User user)
    {
        Model model = new Model(user);
        return model.addUser();
    }

    public boolean updateUser ()
    {
        return model.updateUser();
    }

    public boolean deleteUser ()
    {
        return model.deleteUser();
    }

    public User getUser (String personalNumber)
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
}
