package LibraryManagement.items;

import LibraryManagement.authors.Author;

// Book class set up as a subclass to LibraryItem
public class Book extends LibraryItem{

    // Private instance variables
    private String format; // Format of the book (Printed, Electronic, or Audio)

    // Constructor
    public Book(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, String format) {
        super(id, title, author, isbn, publisher, numberOfCopies);
        this.format = format;
    }

    // Getters and Setters
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    // toString method for description that adds on to LibraryItem's toString method
    @Override
    public String toString() {
        return super.toString() + ", Format: " + format;
    }
}
