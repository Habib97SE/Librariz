package Book;

import Database.DatabaseHandling;

import java.sql.ResultSet;
import java.util.ArrayList;


/**
 * This class is used to handle all database related tasks for book.
 * It is used to get data from the database and to insert data into the database.
 * It is also used to update and delete data from the database.
 *
 * @author Habib
 * @version 1.0
 * @since 2023-04-04
 */
public class Model
{
    private Book book;
    private DatabaseHandling db;

    public Model ()
    {
        book = new Book();
    }

    public Model (Book book)
    {
        this.book = book;
    }

    public void setBook (Book book)
    {
        this.book = book;
    }

    public Book getBook ()
    {
        return book;
    }

    /**
     * Returns a book object with the given bookID
     *
     * @param ISBN the bookID of the book to be returned
     * @return a book object with the given bookID
     */
    public Book getBook (String ISBN)
    {
        String query = "SELECT * FROM books WHERE isbn = " + ISBN;
        ResultSet resultSet = DatabaseHandling.getRows(query);

        // convert resultSet to Book object
        try
        {
            while (true)
            {
                if (resultSet == null) return null;
                if (!resultSet.next()) break;
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setGenre(resultSet.getString("genre"));
                book.setLanguage(resultSet.getString("language"));
                book.setDescription(resultSet.getString("description"));
                book.setPublicationDate(resultSet.getString("publicationDate"));
                book.setEdition(resultSet.getString("edition"));
                book.setNumberOfPages(resultSet.getString("numberOfPages"));
                book.setNumberOfCopies(resultSet.getString("numberOfCopies"));
                book.setNumberOfAvailableCopies(resultSet.getString("numberOfAvailableCopies"));

            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return book;
    }

    /**
     * Returns an ArrayList of all the books in the database
     *
     * @return an ArrayList of all the books in the database, if no book found returns an empty ArrayList
     */
    public ArrayList<Book> getBooks ()
    {
        ArrayList<Book> books = new ArrayList<>();
        String query = "SELECT * FROM books";
        ResultSet resultSet = DatabaseHandling.getRows(query);

        try
        {
            while (resultSet.next())
            {
                Book book = new Book();
                book.setTitle(resultSet.getString("title"));
                book.setAuthor(resultSet.getString("author"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setIsbn(resultSet.getString("isbn"));
                book.setGenre(resultSet.getString("genre"));
                book.setLanguage(resultSet.getString("language"));
                book.setDescription(resultSet.getString("description"));
                book.setPublicationDate(resultSet.getString("publicationDate"));
                book.setEdition(resultSet.getString("edition"));
                book.setNumberOfPages(resultSet.getString("numberOfPages"));
                book.setNumberOfCopies(resultSet.getString("numberOfCopies"));
                book.setNumberOfAvailableCopies(resultSet.getString("numberOfAvailableCopies"));

                books.add(book);
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return books;
    }

    /**
     * Add the book object to the database
     *
     * @param newBook the book object to be added to the database
     * @return Returns true if the book was added successfully, false otherwise
     */
    private boolean addBookQuery (Book newBook)
    {
        String query = "INSERT INTO books (title, author, publisher, isbn, genre, language, description, " +
                "publicationDate, edition, numberOfCopies, numberOfPages, numberOfAvailableCopies) " + "VALUES ('" +
                newBook.getTitle() + "', '" + newBook.getAuthor() + "', '" + newBook.getPublisher() + "', '" +
                newBook.getIsbn() + "', '" + newBook.getGenre() + "', '" + newBook.getLanguage() + "', '" +
                newBook.getDescription() + "', '" + newBook.getPublicationDate() + "', '" + newBook.getEdition() + "', '" +
                newBook.getNumberOfCopies() + "', '" + newBook.getNumberOfPages() + "', '" + newBook.getNumberOfAvailableCopies() + "')";
        return DatabaseHandling.insertNewRow(query);
    }

    /**
     * Add the book object to the database
     *
     * @return Returns true if the book was added successfully, false otherwise
     */
    public boolean addBook ()
    {
        return addBookQuery(book);
    }

    /**
     * Add the book object to the database
     *
     * @param newBook the book object to be added to the database
     * @return Returns true if the book was added successfully, false otherwise
     */
    public boolean addBook (Book newBook)
    {
        return addBookQuery(newBook);
    }

    public boolean addBook (String title, String author, String publisher, String isbn, String genre, String language, String description, String publicationDate, String edition, String numberOfPages, String numberOfCopies, String numberOfAvailableCopies )
    {
        Book newBook = new Book(title, author, publisher, isbn, genre, language, description, publicationDate, edition, numberOfPages, numberOfCopies, numberOfAvailableCopies);
        return addBookQuery(newBook);
    }

    /**
     * Update an existing book in the database
     *
     * @param ISBN       the ISBN of the book to be updated
     * @param editedBook the book object with the updated information
     * @return Returns true if the book was updated successfully, false otherwise
     */
    public boolean updateBook (String ISBN, Book editedBook)
    {
        String query =
                "UPDATE books SET title = '" + editedBook.getTitle() + "', author = '" + editedBook.getAuthor() + "'," +
                        " publisher = '" + editedBook.getPublisher() + "', isbn = '" + editedBook.getIsbn() + "', genre = '" + editedBook.getGenre() + "', language = '" + editedBook.getLanguage() + "', description = '" + editedBook.getDescription() + "', publicationDate = '" + editedBook.getPublicationDate() + "', edition = '" + editedBook.getEdition() + "', numberOfPages = '" + editedBook.getNumberOfPages() + "', numberOfCopies = '" + editedBook.getNumberOfCopies() + "', numberOfAvailableCopies = '" + editedBook.getNumberOfAvailableCopies() +"' WHERE isbn = '" + ISBN + "'";
        return DatabaseHandling.updateRow(query);
    }

    public boolean updateBook (Book editedBook)
    {
        return updateBook(book.getIsbn(), editedBook);
    }

    public boolean updateBook (String ISBN)
    {
        Book newBook = getBook(ISBN);
        return updateBook(ISBN, newBook);
    }

    /**
     * Delete a book from the database
     *
     * @param ISBN the ISBN of the book to be deleted
     * @return Returns true if the book was deleted successfully, false otherwise
     */
    public boolean deleteBook (String ISBN)
    {
        String query = "DELETE FROM books WHERE isbn = '" + ISBN + "'";
        return DatabaseHandling.deleteRow(query);
    }

    /**
     * Delete a book from the database
     *
     * @return Returns true if the book was deleted successfully, false otherwise
     */
    public boolean deleteBook ()
    {
        return deleteBook(book.getIsbn());
    }

    /**
     * Delete a book from the database
     *
     * @param newBook the book object to be deleted
     * @return Returns true if the book was deleted successfully, false otherwise
     */
    public boolean deleteBook (Book newBook)
    {
        return deleteBook(newBook.getIsbn());
    }


    /**
     * Checks if a book is available in the library to be borrowed
     *
     * @param ISBN the ISBN of the book to be checked
     * @return Returns true if the book is available, false otherwise
     */
    public boolean isBookAvailable (String ISBN)
    {
        String query = "SELECT numberOfAvailableCopies FROM books WHERE isbn = '" + ISBN + "'";
        ResultSet resultSet = DatabaseHandling.getRows(query);
        try
        {
            while (true)
            {
                assert resultSet != null;
                if (!resultSet.next()) break;
                if (resultSet.getInt("numberOfAvailableCopies") > 0)
                {
                    return true;
                }
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
        return false;
    }


    /**
     * Checks if a book is available in the library to be borrowed
     *
     * @return Returns true if the book is available, false otherwise
     */
    public boolean isBookAvailable ()
    {
        return isBookAvailable(book.getIsbn());
    }

    /**
     * Checks if a book is available in the library to be borrowed
     *
     * @param newBook the book object to be checked
     * @return Returns true if the book is available, false otherwise
     */
    public boolean isBookAvailable (Book newBook)
    {
        return isBookAvailable(newBook.getIsbn());
    }


    public ArrayList<Book> searchBook (String searchPhrase)
    {
        String query = "SELECT * FROM books WHERE title LIKE '%" + searchPhrase + "%' OR author LIKE '%" + searchPhrase + "%' OR publisher LIKE '%" + searchPhrase + "%' OR isbn LIKE '%" + searchPhrase + "%' OR genre LIKE '%" + searchPhrase + "%' OR language LIKE '%" + searchPhrase + "%' OR description LIKE '%" + searchPhrase + "%' OR publicationDate LIKE '%" + searchPhrase + "%' OR edition LIKE '%" + searchPhrase + "%' OR numberOfPages LIKE '%" + searchPhrase + "%' OR numberOfCopies LIKE '%" + searchPhrase + "%' OR numberOfAvailableCopies LIKE '%" + searchPhrase + "%'";
        return DatabaseHandling.searchBooks(query);
    }
}
