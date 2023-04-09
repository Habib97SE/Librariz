package Book;

public class Book
{
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String genre;
    private String language;
    private String description;
    private String publicationDate;
    private String edition;
    private String numberOfPages;
    private String numberOfCopies;
    private String numberOfAvailableCopies;

    public Book (String title, String author, String publisher, String isbn, String genre, String language, String description, String publicationDate, String edition, String numberOfPages, String numberOfCopies, String numberOfAvailableCopies)
    {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.isbn = isbn;
        this.genre = genre;
        this.language = language;
        this.description = description;
        this.publicationDate = publicationDate;
        this.edition = edition;
        this.numberOfPages = numberOfPages;
        this.numberOfCopies = numberOfCopies;
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }

    public Book ()
    {
        this.title = "";
        this.author = "";
        this.publisher = "";
        this.isbn = "";
        this.genre = "";
        this.language = "";
        this.description = "";
        this.publicationDate = "";
        this.edition = "";
        this.numberOfPages = "";
        this.numberOfCopies = "";
        this.numberOfAvailableCopies = "";

    }

    public Book (Book other)
    {
        this.title = other.title;
        this.author = other.author;
        this.publisher = other.publisher;
        this.isbn = other.isbn;
        this.genre = other.genre;
        this.language = other.language;
        this.description = other.description;
        this.publicationDate = other.publicationDate;
        this.edition = other.edition;
        this.numberOfPages = other.numberOfPages;
        this.numberOfCopies = other.numberOfCopies;
        this.numberOfAvailableCopies = other.numberOfAvailableCopies;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public String getAuthor ()
    {
        return author;
    }

    public void setAuthor (String author)
    {
        this.author = author;
    }

    public String getPublisher ()
    {
        return publisher;
    }

    public void setPublisher (String publisher)
    {
        this.publisher = publisher;
    }

    public String getIsbn ()
    {
        return isbn;
    }

    public void setIsbn (String isbn)
    {
        this.isbn = isbn;
    }

    public String getGenre ()
    {
        return genre;
    }

    public void setGenre (String genre)
    {
        this.genre = genre;
    }

    public String getLanguage ()
    {
        return language;
    }

    public void setLanguage (String language)
    {
        this.language = language;
    }

    public String getDescription ()
    {
        return description;
    }

    public void setDescription (String description)
    {
        this.description = description;
    }

    public String getPublicationDate ()
    {
        return publicationDate;
    }

    public void setPublicationDate (String publicationDate)
    {
        this.publicationDate = publicationDate;
    }

    public String getEdition ()
    {
        return edition;
    }

    public void setEdition (String edition)
    {
        this.edition = edition;
    }

    public String getNumberOfPages ()
    {
        return numberOfPages;
    }

    public void setNumberOfPages (String numberOfPages)
    {
        this.numberOfPages = numberOfPages;
    }

    public String getNumberOfCopies ()
    {
        return numberOfCopies;
    }

    public void setNumberOfCopies (String numberOfCopies)
    {
        this.numberOfCopies = numberOfCopies;
    }

    public String getNumberOfAvailableCopies ()
    {
        return numberOfAvailableCopies;
    }

    public void setNumberOfAvailableCopies (String numberOfAvailableCopies)
    {
        this.numberOfAvailableCopies = numberOfAvailableCopies;
    }


    public boolean equals (Book other)
    {
        return this.getIsbn().equals(other.getIsbn());
    }

    public boolean equals (String isbn)
    {
        return this.getIsbn().equals(isbn);
    }

    @Override
    public String toString ()
    {
        return "Title: " + title + " Author: " + author + " Publisher: " + publisher + " ISBN: " + isbn + " Genre: " + genre + " Language: " + language + " Description: " + description + " Publication Date: " + publicationDate + " Edition: " + edition + " Number of Pages: " + numberOfPages + " Number of Copies: " + numberOfCopies + " Number of Available Copies: " + numberOfAvailableCopies;
    }
}