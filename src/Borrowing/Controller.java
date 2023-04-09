package Borrowing;

public class Controller
{
    public Controller ()
    {
    }

    public boolean borrowBook (Borrowing borrowing) throws Exception
    {
        Model model = new Model(borrowing);

        return model.setBorrowRecord();
    }


}
