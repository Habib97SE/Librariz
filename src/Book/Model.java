package Book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class Model
{
    private String title;
    private String isbn;
    private String author;
    private String genre;
    private String shortDescription;
    private String bookFormat;
    private boolean available;
    private String bookImage;

    public Model (String title, String isbn, String author, String genre, String shortDescription, String bookFormat,
                  boolean available, String bookImage)
    {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
        this.genre = genre;
        this.shortDescription = shortDescription;
        this.bookFormat = bookFormat;
        this.available = available;
        this.bookImage = bookImage;
    }

    public Model ()
    {
        this.title = "";
        this.isbn = "";
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

    public void setAvailable (boolean available)
    {
        this.available = available;
    }

    public String getBookImage ()
    {
        return bookImage;
    }

    public void setBookImage (String bookImage)
    {
        this.bookImage = bookImage;
    }

    public boolean addBook ()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            PreparedStatement ps = conn.prepareStatement("INSERT INTO books VALUES (?,?,?,?,?,?,?,?)");

            ps.setString(1, title);
            ps.setString(2, isbn);
            ps.setString(3, author);
            ps.setString(4, genre);
            ps.setString(5, shortDescription);
            ps.setString(6, bookFormat);
            ps.setBoolean(7, available);
            ps.setString(8, bookImage);

            return ps.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editBook ()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            PreparedStatement ps = conn.prepareStatement("UPDATE books SET title = ?, isbn = ?, author = ?, genre = ?, shortDescription = ?, bookFormat = ?, available = ?, bookImage = ? WHERE isbn = ?");
            ps.setString(1, title);
            ps.setString(2, isbn);
            ps.setString(3, author);
            ps.setString(4, genre);
            ps.setString(5, shortDescription);
            ps.setString(6, bookFormat);
            ps.setBoolean(7, available);
            ps.setString(8, bookImage);
            ps.setString(9, isbn);

            return ps.executeUpdate() > 0;
        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public static Book getBook (String isbn)
    {
        Book book = null;
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE isbn = ?");
            ps.setString(1, isbn);

            ResultSet rs = ps.executeQuery();
            if (rs.next())
            {
                book = new Book(rs.getString("title"), rs.getString("isbn"), rs.getString("author"), rs.getString(
                        "genre"), rs.getString("shortDescription"), rs.getString("bookFormat"), rs.getBoolean("available"), rs.getString("bookImage"));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return book;
    }

    public static ArrayList<Book> getAllBooks ()
    {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM books");

            ResultSet rs = ps.executeQuery();
            while (rs.next())
            {
                books.add(new Book(rs.getString("title"), rs.getString("isbn"), rs.getString("author"), rs.getString(
                        "genre"), rs.getString("shortDescription"), rs.getString("bookFormat"), rs.getBoolean("available"), rs.getString("bookImage")));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return books;
    }

    public static ArrayList<Book> searchBooks (String searchPhrase)
    {
        ArrayList<Book> books = new ArrayList<>();
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

            PreparedStatement ps = conn.prepareStatement("SELECT * FROM books WHERE title LIKE ? OR isbn LIKE ? OR author LIKE ? OR genre LIKE ? OR shortDescription LIKE ? OR bookFormat LIKE ?");

            ps.setString(1, "%" + searchPhrase + "%");
            ps.setString(2, "%" + searchPhrase + "%");
            ps.setString(3, "%" + searchPhrase + "%");
            ps.setString(4, "%" + searchPhrase + "%");
            ps.setString(5, "%" + searchPhrase + "%");
            ps.setString(6, "%" + searchPhrase + "%");

            ResultSet rs = ps.executeQuery();

            while (rs.next())
            {
                books.add(new Book(rs.getString("title"), rs.getString("isbn"), rs.getString("author"), rs.getString(
                        "genre"), rs.getString("shortDescription"), rs.getString("bookFormat"), rs.getBoolean(
                        "availability"),
                        rs.getString("bookImage")));
            }

        } catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
        return books;
    }

    public boolean returnBook ()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

            PreparedStatement ps = conn.prepareStatement("UPDATE books SET availability = ? WHERE isbn = ?");

            ps.setBoolean(1, true);
            ps.setString(2, isbn);

            return ps.executeUpdate() > 0;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }


    public boolean borrowBook ()
    {
        try
        {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root", "");

            PreparedStatement ps = conn.prepareStatement("UPDATE books SET availability = ? WHERE isbn = ?");
            ps.setBoolean(1, false);
            ps.setString(2, isbn);

            return ps.executeUpdate() > 0;

        } catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean equals (Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Model model = (Model) o;

        if (available != model.available) return false;
        if (!Objects.equals(title, model.title)) return false;
        if (!Objects.equals(isbn, model.isbn)) return false;
        if (!Objects.equals(author, model.author)) return false;
        if (!Objects.equals(genre, model.genre)) return false;
        if (!Objects.equals(shortDescription, model.shortDescription))
            return false;
        if (!Objects.equals(bookFormat, model.bookFormat)) return false;
        return Objects.equals(bookImage, model.bookImage);
    }

    @Override
    public int hashCode ()
    {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (bookFormat != null ? bookFormat.hashCode() : 0);
        result = 31 * result + (available ? 1 : 0);
        result = 31 * result + (bookImage != null ? bookImage.hashCode() : 0);
        return result;
    }

    @Override
    public String toString ()
    {
        return "Model{" +
                "title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", bookFormat='" + bookFormat + '\'' +
                ", available=" + available +
                ", bookImage='" + bookImage + '\'' +
                '}';
    }
}
