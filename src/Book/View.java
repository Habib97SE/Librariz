package Book;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;


public class View
{
    private final Controller controller;

    public View ()
    {
        controller = new Controller();
    }

    /**
     * This method is used to add a book to the database
     */
    public void findBook ()
    {
        Book book = null;
        while (true)
        {
            String isbn = JOptionPane.showInputDialog("Enter the ISBN of the book you want to find");
            book = controller.getBook(isbn);

            if (Objects.equals(book.getIsbn(), ""))
            {
                int choice = JOptionPane.showConfirmDialog(null, "Book not found. Do you want to try again?", "Book not found", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.NO_OPTION) break;
            } else {
                viewBook(book);
                break;
            }
        }

        // view book in the GUI


    }

    public void viewBook (Book book)
    {
        JFrame frame = new JFrame("View Book");

        frame.setSize(800, 600);

        // create table with two columns, one for the attribute name and the other for the attribute value
        String[] columnNames = {"Attribute", "Value"};
        String[][] data = {{"Title", book.getTitle()}, {"Author", book.getAuthor()}, {"Publisher", book.getPublisher()}, {"ISBN", book.getIsbn()}, {"Genre", book.getGenre()}, {"Language", book.getLanguage()}, {"Description", book.getDescription()}, {"Publication Date", book.getPublicationDate()}, {"Edition", book.getEdition()}, {"Number of Pages", book.getNumberOfPages()}, {"Number of Copies", book.getNumberOfCopies()}, {"Number of Available Copies", book.getNumberOfAvailableCopies()}};
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane);


        frame.setVisible(true);
    }

    /**
     * This method is used to view all the books in the database
     */
    public void viewAllBooks ()
    {
        // get all the books from the controller

        // display the books in the GUI


        JFrame frame = new JFrame("View All Books");

        frame.setSize(800, 600);

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

        frame.setVisible(true);
    }

    /**
     * This method is used to add a book to the database
     */
    public void addBook ()
    {
        JFrame frame = new JFrame("Add Book");

        frame.setSize(800, 600);

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

        frame.setVisible(true);

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

        frame.setSize(800, 600);

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
        frame.setVisible(true);

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

        frame.setSize(800, 600);

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
        frame.setVisible(true);

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
        String searchPhrase = JOptionPane.showInputDialog("Enter the search phrase: ");
        ArrayList<Book> books = controller.searchBook(searchPhrase);
        JFrame frame = new JFrame("Search Book");
        JPanel panel = new JPanel();
        frame.setSize(800, 600);
        String[] columnNames = {"Title", "Author", "Publisher", "ISBN", "Genre", "Language", "Description", "Publication Date", "Edition", "Number of Pages", "Number of Copies", "Number of Available Copies"};
        Object[][] data = new Object[books.size()][12];
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
        frame.add(scrollPane);
        frame.add(panel);
        frame.setVisible(true);

    }
}
