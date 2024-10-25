package LibraryManagement.items;

import LibraryManagement.authors.Author;

// Book class set up as a subclass to LibraryItem
public class Book extends LibraryItem{

    // Enum for the format of the book
    public enum Format {
        PRINTED,
        ELECTRONIC,
        AUDIO
    }

    // Private instance variables
    private Format format; // Format of the book (Printed, Electronic, or Audio)

    // Constructor
    public Book(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, Format format) {
        super(id, title, author, isbn, publisher, numberOfCopies, ItemType.BOOK);
        this.format = format;
    }

    // Getters and Setters
    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    // toString method for description that adds on to LibraryItem's toString method
    @Override
    public String toString() {
        return super.toString() + ", Format: " + format;
    }
}
