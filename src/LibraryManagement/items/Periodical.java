package LibraryManagement.items;

import LibraryManagement.authors.Author;

// Periodical class set up as a subclass to LibraryItem
public class Periodical extends LibraryItem {

    // Enum for the type of the periodical
    public enum Type {
        PRINTED,
        ELECTRONIC
    }

    // Private instance variables
    private Type type; 

    // Constructor
    public Periodical(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, Type type) {
        super(id, title, author, isbn, publisher, numberOfCopies, ItemType.PERIODICAL);
        this.type = type;
    }

    // Getters and Setters
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    // toString method for description, adds type to LibraryItem toString 
    @Override
    public String toString() {
        return super.toString() + ", Type: " + type;
    }
}
