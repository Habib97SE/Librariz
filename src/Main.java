import Borrowing.Borrowing;

public class Main
{
    public static void main (String[] argh) throws Exception
    {

        Borrowing borrowing = new Borrowing();
        borrowing.borrowBook("1");


//        CustomMenuBar customMenuBar = new CustomMenuBar();
//        String[] menuNames = {"User", "Book"};
//        String[][] menuItems = {{"Add new user", "Edit user", "Delete user", "Show user",
//                "Show users"}, {"Find book", "Add new book", "Edit book", "Delete book", "Show books"}};
//        JMenuBar menuBar = customMenuBar.createMenuBar("Menu Bar", menuNames, menuItems);
//        JFrame frame = new JFrame("Library Management System");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//        frame.setLocationRelativeTo(null);
//        frame.setResizable(false);
//        frame.setJMenuBar(menuBar);
//        frame.setVisible(true);


    }
}