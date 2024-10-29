package LibraryManagement.items;

import LibraryManagement.authors.Author; // Import the Author class
import LibraryManagement.util.Status; // Import the Status class
import LibraryManagement.patrons.Patron; // Import the Patron class
public class LibraryItem {

    // Create enum to define item type
    public enum ItemType {
        BOOK,
        PERIODICAL
    }

    // Private instance variables
    private String id;
    private String title;
    private Author author; 
    private String isbn;
    private String publisher;
    private int numberOfCopies;
    private ItemType itemType; // BOOK or PERIODICAL
    private Status status;

    // Constructors
    public LibraryItem(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, ItemType itemType) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publisher = publisher;
        this.numberOfCopies = numberOfCopies;
        this.itemType = itemType;
        this.status = new Status(); // Initialized as AVAILABLE
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Status getStatus() {
        return status;
    }

    // Methods to delegate borrowing and returning to Status class
    public void borrowItem(Patron patron) {
        status.borrowItem(patron, this); // Pass "this" allowing updated to item
    }

    public void returnItem() {
        status.returnItem(this);
    }

    // toString method for description
    @Override
    public String toString() {
        return String.format("ID: %s, Title: %s, Author: %s, ISBN: %s, Publisher: %s, Copies Available: %d, Item Type: %s, Status: %s",
        id, title, author != null ? author.getName() : "Unknown", isbn, publisher, numberOfCopies, itemType, status.getStatus());
    }

}
