package LibraryManagement.patrons;

import LibraryManagement.Library;
import LibraryManagement.items.LibraryItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Patron extends Library {
    // Private instance variable(s)
    private String name;
    private String address;
    private String phoneNum;
    private List<LibraryItem> borrowedItems; // list of items patron has borrowed

    // Constructor(s)
    public Patron(String name, String address, String phoneNum) { // parameterized constructor
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.borrowedItems = new ArrayList<>(); // initializes the list
    }

    public Patron() { // default constructor
        this.name = null; // sets name to null to indicate no value
        this.address = null; // sets address to null to indicate no value
        this.phoneNum = null; //sets phone number to null to indicate no value
        this.borrowedItems = new ArrayList<>(); // initializes the list as empty
    }

    // Getters and Setters
    public String getName() { // method to get patron's name
        return this.name;
    }

    public void setName(String name) { // method to set patron's name
        this.name = name;
    }

    public String getAddress() { // method to get patron's address)
        return this.address;
    }

    public void setAddress(String address) { // method to set the patron's address
        this.address = address;
    }

    public List<LibraryItem> getBorrowedItemsItems() { // method to get a list of patron's borrowed items
        return borrowedItems;
    }

    public void addBorrowedItem(LibraryItem item) { // method to an item to the patrons borrowed item list
        borrowedItems.add(item);               // uses add method of the List interface to append item
    }

    // toString method to provide description
    @Override
    public String toString() {
        String itemsBorrowed = borrowedItems.isEmpty() ? "None" :
                String.join(", ", borrowedItems.stream().map(LibraryItem::getTitle).toList());

        return String.format("Patron: %s, Address: %s, Items written: %s", name, address, itemsBorrowed);
    }
}
