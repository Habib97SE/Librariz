import Borrowing.BorrowingView;
import Database.DatabaseHandling;
import Database.SecureData;
import MainGUI.CustomMenuBar;
import User.User;

import javax.swing.*;

import MainGUI.Initial;
import User.UserView;

public class Main
{
    public static void main (String[] argh) throws Exception
    {
//
//        User user = DatabaseHandling.getUserById(6);
//
//        UserView userView = new UserView(user);
//
//        userView.showUser();

        new Initial();
    }
}