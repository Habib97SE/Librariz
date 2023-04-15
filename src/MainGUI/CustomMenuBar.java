package MainGUI;

import User.User;

import javax.swing.*;
import java.awt.event.*;

import Database.DatabaseHandling;
import Database.SecureData;

import Book.BookView;
import Borrowing.BorrowingView;
import User.UserView;


/**
 * CustomMenuBar class is used to create a menu bar with menus and menu items and add action listeners to the menu
 * items to handle the actions.
 *
 * @author : Habib
 * @version : 1.0.0
 * @since : 07/04/2023, Fri
 */
public class CustomMenuBar extends JMenuBar
{

    private User user;


    private final String borrowerMenuBarName = "Borrower Menu";
    private final String librarianMenuBarName = "Librarian Menu";
    private final String adminMenuBarName = "Admin Menu";


    private final String[] borrowerMenuNames = {"Profile", "Book", "Borrow & Return"};
    private final String[] librarianMenuNames = {"User", "Book", "Borrow & Return"};
    private final String[] adminMenuNames = {"Borrower", "Librarian", "Book", "Settings"};

    private final String[][] borrowerMenuItems = {
            {"Show user", "Edit user"},
            {"Find book", "Show books"},
            {"Borrow book", "Show borrowings", "Return book"}
    };

    private final String[][] librarianMenuItems = {
            {"Add new user", "Delete user", "Edit user", "Show user", "Show users"},
            {"Find book", "Add new book", "Edit book", "Delete book", "Show books"},
            {"Borrow book", "Show borrowings", "Return book"}
    };

    private final String[][] adminMenuItems = {
            {"Add new borrower", "Delete borrower", "Edit borrower", "Show borrower", "Show borrowers"},
            {"Add new librarian", "Delete librarian", "Edit librarian", "Show librarian", "Show librarians"},
            {"Find book", "Add new book", "Edit book", "Delete book", "Show books"},
            {"Settings"}
    };


    public CustomMenuBar (User user)
    {
        this.user = user;
    }

    public void setUser (User user)
    {
        this.user = user;
    }

    public User getUser ()
    {
        return user;
    }

    /*
     * Create a menu bar with menus and menu items
     * @param menuBarName : name of the menu bar
     * @param menuNames : array of menu names
     * @param menuItems : array of menu items
     * @return JMenuBar : return the menu bar with menus and menu items
     */
    public JMenuBar createMenuBar (String menuBarName, String[] menuNames, String[][] menuItems)
    {
        JMenuBar menuBar = new JMenuBar();
        for (int i = 0; i < menuNames.length; i++)
        {
            menuBar.add(createMenu(menuNames[i], menuItems[i]));
        }
        return menuBar;
    }

    /**
     * Create a menu with menu items and add action listeners to the menu items to handle the actions
     *
     * @param menuName  : name of the menu to be created
     * @param menuItems : array of menu items to be added to the menu
     * @return JMenu : return the menu with menu items
     */
    public JMenu createMenu (String menuName, String[] menuItems)
    {
        JMenu menu = new JMenu(menuName);
        for (String menuItem : menuItems)
        {
            menu.add(createMenuItem(menuItem));
        }
        return menu;
    }

    /**
     * Create a menu item and add action listener to the menu item to handle the action
     *
     * @param menuItemName : name of the menu item to be created
     * @return JMenuItem : return the menu item with added action listener
     */
    public JMenuItem createMenuItem (String menuItemName)
    {
        // add action listener
        JMenuItem menuItem = new JMenuItem(menuItemName);
        menuItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed (ActionEvent e)
            {
                try
                {
                    if (menuItemName.equals("Log out"))
                    {
                        user = null;
                        new Initial();
                    }
                    handleMenuItemAction(menuItem);
                } catch (Exception exception)
                {
                    exception.printStackTrace();
                }
            }
        });
        return menuItem; // return the menuItem with added ActionListener
    }

    /**
     * Handle the action of the menu item
     *
     * @param menuItem : menu item whose action is to be handled
     * @throws Exception : throw exception if any
     */
    public boolean handleMenuItemAction (JMenuItem menuItem) throws Exception
    {
        String menuItemName = menuItem.getText();
        UserView userView = new UserView(user);
        BookView bookView = new BookView(user);
        BorrowingView borrowingView = new BorrowingView(user);
        switch (menuItemName)
        {
            case "Add new user":
                // Call method to add new user
                // call method in User.View
                userView.addUser();

                break;
            case "Delete user":
                // Call method to delete user
                userView.deleteUser();

                break;
            case "Edit user":
                userView.editUser();

            case "Show user":
                userView.showUser();
                break;
            case "Show users":
                // Call method to show users
                userView.showUsers();
                break;
            case "Find book":
                // Call method to find book
                bookView.findBook();
                break;
            case "Add new book":
                // Call method to add new book
                bookView.addBook();
                break;
            case "Edit book":
                // Call method to edit book
                bookView.updateBook();
                break;
            case "Delete book":
                // Call method to delete book
                bookView.deleteBook();
                break;
            case "Show books":
                // Call method to show books
                bookView.viewAllBooks();
                break;
            case "Borrow book":
                // Call method to borrow book
                borrowingView.borrowBook(bookView.findBook());
                break;
            case "Show borrowings":
                // Call method to show borrowings
                userView.showBorrowingsHistory();
                break;
            default:
                JOptionPane.showMessageDialog(null, "Invalid menu item", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
        return true;
    }

    /**
     * Create menu bar for borrower user
     *
     * @return JMenuBar : return the menu bar for borrower user
     */
    public JMenuBar createLibrarianMenuBar ()
    {
        return createMenuBar(borrowerMenuBarName, borrowerMenuNames, borrowerMenuItems);
    }

    /**
     * Create menu bar for librarian user
     *
     * @return JMenuBar : return the menu bar for librarian user
     */
    public JMenuBar createBorrowerMenuBar ()
    {
        return createMenuBar(librarianMenuBarName, librarianMenuNames, librarianMenuItems);
    }

    /**
     * Create menu bar for admin user
     *
     * @return JMenuBar : return the menu bar for admin user
     */
    public JMenuBar createAdminMenuBar ()
    {
        return createMenuBar(adminMenuBarName, adminMenuNames, adminMenuItems);
    }
}
