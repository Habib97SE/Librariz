package Book;

import java.util.ArrayList;

public class Controller
{
    private Book book;
    private Model model;

    public Controller ()
    {
        book = new Book();
        model = new Model();
    }

    public Controller (Book book)
    {
        this.book = book;
        model = new Model();
    }

    public void setBook (Book book)
    {
        this.book = book;
    }

    public Book getBook ()
    {
        return book;
    }

    public Book getBook (String ISBN)
    {
        return model.getBook(ISBN);
    }

    public ArrayList<Book> getBooks ()
    {
        return model.getBooks();
    }

    public boolean addBook ()
    {
        return model.addBook(book);
    }

    public boolean addBook (Book newBook)
    {
        return model.addBook(newBook);
    }

    public boolean updateBook ()
    {
        return model.updateBook(book);
    }

    public boolean updateBook (Book newBook)
    {
        return model.updateBook(newBook);
    }

    public boolean deleteBook ()
    {
        return model.deleteBook(book);
    }

    public boolean deleteBook (Book newBook)
    {
        return model.deleteBook(newBook);
    }

    public boolean deleteBook (String ISBN)
    {
        return model.deleteBook(ISBN);
    }

    public boolean deleteBook (int ISBN)
    {
        return model.deleteBook(Integer.toString(ISBN));
    }


    public ArrayList<Book> searchBook (String searchPhrase)
    {
        return model.searchBook(searchPhrase);
    }
}
