package Book;

public class History extends Book
{
    private String borrowingDate;
    private String returnDate;

    public History (String title, String isbn, String author, String genre, String shortDescription, String bookFormat,
                    boolean available, String borrowingDate, String returnDate)
    {
        super(title, isbn, author, genre, shortDescription, bookFormat, available);
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public History (Book book, String borrowingDate, String returnDate)
    {
        super(book.getTitle(), book.getIsbn(), book.getAuthor(), book.getGenre(), book.getShortDescription(),
                book.getBookFormat(), book.isAvailable());
        this.borrowingDate = borrowingDate;
        this.returnDate = returnDate;
    }

    public String getBorrowingDate ()
    {
        return borrowingDate;
    }

    public void setBorrowingDate (String borrowingDate)
    {
        this.borrowingDate = borrowingDate;
    }

    public String getReturnDate ()
    {
        return returnDate;
    }

    public void setReturnDate (String returnDate)
    {
        this.returnDate = returnDate;
    }

    @Override
    public String toString ()
    {
        return String.format("%s %s %s", this.getTitle(), this.getBorrowingDate(), this.getReturnDate());
    }
}
