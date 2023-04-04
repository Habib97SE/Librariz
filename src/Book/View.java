package Book;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class View
{
    private Controller controller;


    /**
     * This method is used to add a book to the database
     */
    public void findBook ()
    {
        String isbn = JOptionPane.showInputDialog("Enter the ISBN of the book you want to find");
        Book book = controller.getBook(isbn);

        // set the book variable to the book object returned by the controller

        // display the book object in the GUI

        JFrame frame = new JFrame("View Book");

        JPanel panel = new JPanel();

        JLabel title = new JLabel("Title: " + book.getTitle());
        JLabel author = new JLabel("Author: " + book.getAuthor());
        JLabel publisher = new JLabel("Publisher: " + book.getPublisher());
        JLabel isbnLabel = new JLabel("ISBN: " + book.getIsbn());
        JLabel genre = new JLabel("Genre: " + book.getGenre());
        JLabel language = new JLabel("Language: " + book.getLanguage());
        JLabel description = new JLabel("Description: " + book.getDescription());
        JLabel publicationDate = new JLabel("Publication Date: " + book.getPublicationDate());
        JLabel edition = new JLabel("Edition: " + book.getEdition());
        JLabel numberOfPages = new JLabel("Number of Pages: " + book.getNumberOfPages());
        JLabel numberOfCopies = new JLabel("Number of Copies: " + book.getNumberOfCopies());
        JLabel numberOfAvailableCopies = new JLabel("Number of Available Copies: " + book.getNumberOfAvailableCopies());

        panel.add(title);
        panel.add(author);
        panel.add(publisher);
        panel.add(isbnLabel);
        panel.add(genre);
        panel.add(language);
        panel.add(description);
        panel.add(publicationDate);
        panel.add(edition);
        panel.add(numberOfPages);
        panel.add(numberOfCopies);
        panel.add(numberOfAvailableCopies);

        frame.add(panel);

    }

    /**
     * This method is used to view all the books in the database
     */
    public void viewAllBooks ()
    {
        // get all the books from the controller

        // display the books in the GUI

        JFrame frame = new JFrame("View All Books");

        JPanel panel = new JPanel();


        // Create a table with the following columns: Title, Author, Publisher, ISBN, Genre, Language, Description,
        // Publication Date, Edition, Number of Pages, Number of Copies, Number of Available Copies in order to show all the books

        ArrayList<Book> books = controller.getBooks();

        String[] columnNames = {"Title", "Author", "Publisher", "ISBN", "Genre", "Language", "Description", "Publication Date", "Edition", "Number of Pages", "Number of Copies", "Number of Available Copies"};
        String[][] data = new String[books.size()][12];

        for (int i = 0; i < books.size(); i++)
        {
            data[i][0] = books.get(i).getTitle();
            data[i][1] = books.get(i).getAuthor();
            data[i][2] = books.get(i).getPublisher();
            data[i][3] = books.get(i).getIsbn();
            data[i][4] = books.get(i).getGenre();
            data[i][5] = books.get(i).getLanguage();
            data[i][6] = books.get(i).getDescription();
            data[i][7] = books.get(i).getPublicationDate();
            data[i][8] = books.get(i).getEdition();
            data[i][9] = books.get(i).getNumberOfPages();
            data[i][10] = books.get(i).getNumberOfCopies();
            data[i][11] = books.get(i).getNumberOfAvailableCopies();
        }

        JTable table = new JTable(data, columnNames);

        JScrollPane scrollPane = new JScrollPane(table);

        panel.add(scrollPane);

        frame.add(panel);
    }

    /**
     * This method is used to add a book to the database
     */
    public void addBook ()
    {
        JFrame frame = new JFrame("Add Book");

        JPanel panel = new JPanel();

        JLabel titleLabel = new JLabel("Title: ");
        JTextField title = new JTextField(20);
        JLabel authorLabel = new JLabel("Author: ");
        JTextField author = new JTextField(20);
        JLabel publisherLabel = new JLabel("Publisher: ");
        JTextField publisher = new JTextField(20);
        JLabel isbnLabel = new JLabel("ISBN: ");
        JTextField isbn = new JTextField(20);
        JLabel genreLabel = new JLabel("Genre: ");
        JTextField genre = new JTextField(20);
        JLabel languageLabel = new JLabel("Language: ");
        JTextField language = new JTextField(20);
        JLabel descriptionLabel = new JLabel("Description: ");
        JTextField description = new JTextField(20);
        JLabel publicationDateLabel = new JLabel("Publication Date: ");
        JTextField publicationDate = new JTextField(20);
        JLabel editionLabel = new JLabel("Edition: ");
        JTextField edition = new JTextField(20);
        JLabel numberOfPagesLabel = new JLabel("Number of Pages: ");
        JTextField numberOfPages = new JTextField(20);
        JLabel numberOfCopiesLabel = new JLabel("Number of Copies: ");
        JTextField numberOfCopies = new JTextField(20);
        JLabel numberOfAvailableCopiesLabel = new JLabel("Number of Available Copies: ");
        JTextField numberOfAvailableCopies = new JTextField(20);

        JButton addBookButton = new JButton("Add Book");
        JButton cancel = new JButton("Cancel");

        panel.add(titleLabel);
        panel.add(title);
        panel.add(authorLabel);
        panel.add(author);
        panel.add(publisherLabel);
        panel.add(publisher);
        panel.add(isbnLabel);
        panel.add(isbn);
        panel.add(genreLabel);
        panel.add(genre);
        panel.add(languageLabel);
        panel.add(language);
        panel.add(descriptionLabel);
        panel.add(description);
        panel.add(publicationDateLabel);
        panel.add(publicationDate);
        panel.add(editionLabel);
        panel.add(edition);
        panel.add(numberOfPagesLabel);
        panel.add(numberOfPages);
        panel.add(numberOfCopiesLabel);
        panel.add(numberOfCopies);
        panel.add(numberOfAvailableCopiesLabel);
        panel.add(numberOfAvailableCopies);
        panel.add(addBookButton);
        panel.add(cancel);

        frame.add(panel);

        // add the book to the database using the controller and display a message to the user that the book was
        // added successfully
        addBookButton.addActionListener(e ->
        {
            Book book = new Book(title.getText(), author.getText(), publisher.getText(), isbn.getText(), genre.getText(), language.getText(), description.getText(), publicationDate.getText(), edition.getText(), numberOfPages.getText(), numberOfCopies.getText(), numberOfAvailableCopies.getText());
            controller.addBook(book);
            JOptionPane.showMessageDialog(null, "Book added successfully");
        });

        cancel.addActionListener(e ->
        {
            frame.dispose();
        });
    }

    /**
     * This method is used to delete a book from the database
     */
    public void deleteBook ()
    {
        JFrame frame = new JFrame("Delete Book");

        JPanel panel = new JPanel();

        JLabel isbnLabel = new JLabel("ISBN: ");
        JTextField isbn = new JTextField(20);

        JButton deleteBookButton = new JButton("Delete Book");
        JButton cancel = new JButton("Cancel");

        panel.add(isbnLabel);
        panel.add(isbn);
        panel.add(deleteBookButton);
        panel.add(cancel);

        frame.add(panel);

        // delete the book from the database using the controller and display a message to the user that the book was
        // deleted successfully
        deleteBookButton.addActionListener(e ->
        {
            controller.deleteBook(isbn.getText());
            JOptionPane.showMessageDialog(null, "Book deleted successfully");
        });

        cancel.addActionListener(e ->
        {
            frame.dispose();
        });
    }

    /**
     * This method is used to update a book in the database
     */
    public void updateBook ()
    {
        JFrame frame = new JFrame("Update Book");

        JPanel panel = new JPanel();

        JLabel isbnLabel = new JLabel("ISBN: ");
        JTextField isbn = new JTextField(20);

        JButton updateBookButton = new JButton("Update Book");
        JButton cancel = new JButton("Cancel");

        panel.add(isbnLabel);
        panel.add(isbn);
        panel.add(updateBookButton);
        panel.add(cancel);

        frame.add(panel);

        // update the book in the database using the controller and display a message to the user that the book was
        // updated successfully
        updateBookButton.addActionListener(e ->
        {
            Controller controller = new Controller();
            Book book = controller.getBook(isbn.getText());
            boolean result = controller.updateBook(book);
            if (result)
            {
                JOptionPane.showMessageDialog(null, "Book updated successfully");
            } else
            {
                JOptionPane.showMessageDialog(null, "Book not updated");
            }
        });

        cancel.addActionListener(e ->
        {
            frame.dispose();
        });
    }

    /**
     * This method is used to search for a book in the database
     */
    public void searchBook ()
    {
        JFrame frame = new JFrame("Search Book");

        JPanel panel = new JPanel();

        JLabel isbnLabel = new JLabel("ISBN: ");
        JTextField isbn = new JTextField(20);

        JButton searchBookButton = new JButton("Search Book");
        JButton cancel = new JButton("Cancel");

        panel.add(isbnLabel);
        panel.add(isbn);
        panel.add(searchBookButton);
        panel.add(cancel);

        frame.add(panel);

        // search for the book in the database using the controller and display a message to the user that the book was
        // found successfully
        searchBookButton.addActionListener(e ->
        {
            controller.searchBook(isbn.getText());
            JOptionPane.showMessageDialog(null, "Book found successfully");
        });

        cancel.addActionListener(e ->
        {
            frame.dispose();
        });
    }
}
