package LibraryManagement.patrons;

import LibraryManagement.Library;
import LibraryManagement.items.LibraryItem;

import java.util.ArrayList;
import java.util.List;

public class Patron extends Library {

    // Enum for PatronType
    public enum PatronType {
        EMPLOYEE,
        STUDENT
    }

    // Private instance variable(s)
    private String name;
    private String address;
    private String phoneNum;
    private List<LibraryItem> borrowedItems; // list of items patron has borrowed
    private PatronType patronType; // Enum for Employee or Student

    // Constructor(s)
    public Patron(String name, String address, String phoneNum, PatronType patronType) { // parameterized constructor
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.borrowedItems = new ArrayList<>(); // initializes the list
        this.patronType = patronType;
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

    public String getAddress() { // method to get patron's address
        return this.address;
    }

    public void setAddress(String address) { // method to set the patron's address
        this.address = address;
    }

    public String getPhoneNum() { // method to get patron's phone number
        return this.phoneNum;
    }

    public void setPhoneNum(String phoneNum) { // method to set the patron's phone number
        this.phoneNum = phoneNum;
    }

    public List<LibraryItem> getBorrowedItem() { // method to get a list of patron's borrowed items
        return borrowedItems;
    }

    public void addBorrowedItem(LibraryItem item) { // method to an item to the patrons borrowed item list
        borrowedItems.add(item);               // uses add method of the List interface to append item
    }

    public PatronType getPatronType() {
        return this.patronType;
    }

    public void setPatronType(PatronType patronType) {
        this.patronType = patronType;
    }

    // toString method to provide description
    @Override
    public String toString() {
        String itemsBorrowed = borrowedItems.isEmpty() ? "None" :
                String.join(", ", borrowedItems.stream().map(LibraryItem::getTitle).toList());

        return String.format("Patron: %s, Address: %s, Phone Number: %s, Items written: %s", name, address, phoneNum, itemsBorrowed);
    }
}
