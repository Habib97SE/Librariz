package Book;
import Database.DatabaseHandling;

public class Book
{
    private String title;
    private String isbn;
    private String author;
    private String genre;
    private String shortDescription;
    private String bookFormat;
    private boolean available;

    public Book (String title, String isbn, String author, String genre, String shortDescription, String bookFormat,
                 boolean available)
    {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.shortDescription = shortDescription;
        this.bookFormat = bookFormat;
        this.available = available;
    }

    public Book ()
    {
        this.title = "";
        this.isbn= "";
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getIsbn ()
    {
        return isbn;
    }

    public void setIsbn (String isbn)
    {
        this.isbn = isbn;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getGenre ()
    {
        return genre;
    }

    public void setGenre (String genre)
    {
        this.genre = genre;
    }

    public String getShortDescription ()
    {
        return shortDescription;
    }

    public void setShortDescription (String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public String getBookFormat ()
    {
        return bookFormat;
    }

    public void setBookFormat (String bookFormat)
    {
        this.bookFormat = bookFormat;
    }

    public boolean isAvailable ()
    {
        return available;
    }

    public void setAvailability (boolean available)
    {
        this.available = available;
    }

    private String getReturnDate ()
    {
        return "2023-03-25";
    }

    private String printAvailability ()
    {
        return this.isAvailable() ? "Yes" : this.getReturnDate();
    }

    public Integer getBookId()
    {
        return DatabaseHandling.getRowID("book", "isbn", this.getIsbn());
    }

    public boolean equals (Book other)
    {
        return this.getIsbn().equals(other.getIsbn());
    }

    @Override
    public String toString ()
    {
        return String.format("""
                        Title: %s\s
                        ISBN: %s\s
                        Author: %s\s
                        Genre: %s\s
                        Short description: %s\s
                        Format: %s\s
                        Available: %s""", this.getTitle(), this.getIsbn(), this.getAuthor(), this.getGenre(),
                this.getShortDescription(), this.getBookFormat(), this.printAvailability());
    }
}
