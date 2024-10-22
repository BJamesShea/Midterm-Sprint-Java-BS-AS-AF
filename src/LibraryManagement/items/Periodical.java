package LibraryManagement.items;

import LibraryManagement.authors.Author;

// Periodical class set up as a subclass to LibraryItem
public class Periodical extends LibraryItem {

    // Private instance variables
    private String type; // Type being either 'Printed' or 'Electronic'

    // Constructor
    public Periodical(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, String type) {
        super(id, title, author, isbn, publisher, numberOfCopies);
        this.type = type;
    }

    // Getters and Setters
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // toString method for description, adds type to LibraryItem toString 
    @Override
    public String toString() {
        return super.toString() + ", Type: " + type;
    }
}
