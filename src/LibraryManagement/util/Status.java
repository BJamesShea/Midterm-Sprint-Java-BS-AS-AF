package LibraryManagement.util;

import LibraryManagement.authors.Author;
import LibraryManagement.items.LibraryItem;
import LibraryManagement.patrons.Patron;
import java.time.LocalDate;

public class Status extends LibraryItem {

    public enum AvailabilityStatus {
        AVAILABLE,
        CHECKED_OUT,
        OVERDUE
    }


    // Private instance variables

    private AvailabilityStatus status;
    private LocalDate dueDate;
    private Patron currentHolder;


    // Constructor

    public Status(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, ItemType itemType) {
        super(id, title, author, isbn, publisher, numberOfCopies, itemType);
        this.status = AvailabilityStatus.AVAILABLE;
        this.dueDate = null;
        this.currentHolder = null;
    }


    // Getters and Setters

    // Get the status
    public AvailabilityStatus getStatus() {
        // Automatically update the status based on the due date
        updateStatus();
        return status;
    }

    // Set the status
    // setStatus is set to private to restrict direct modification from outside the class as the Status class itself will internally control when and how status is updated
    private void setStatus(AvailabilityStatus status) {
        this.status = status;
    }

    // Get the due date
    public LocalDate getDueDate() {
        return dueDate;
    }

    // Set the due date
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        // Update the status based on new due date
        updateStatus();
    }

    // Get the current holder of the item
    public Patron getCurrentHolder() {
        return currentHolder;
    }

    // Set the current holder of the item
    public void setCurrentHolder(Patron currentHolder) {
        this.currentHolder = currentHolder;
    }


    // Methods

    // Method to borrow an item, setting status and due date
    public void borrowItem(Patron patron) {
        if (status == AvailabilityStatus.AVAILABLE) {
            setStatus(AvailabilityStatus.CHECKED_OUT);
            setDueDate(LocalDate.now().plusDays(6)); // Sets the due date to the borrowed date plus 6 days
            this.currentHolder = patron;
            setNumberOfCopies(getNumberOfCopies() - 1);
            System.out.println("Item borrowed successfully.");
        } else {
            System.out.println("Item is not available for borrowing.");
        }
    }

    // Method to return an item, updating status and clearing due date
    @Override
    public void returnItem() {
        if (status == AvailabilityStatus.CHECKED_OUT || status == AvailabilityStatus.OVERDUE) {
            setStatus(AvailabilityStatus.AVAILABLE);
            this.dueDate = null;
            this.currentHolder = null;
            setNumberOfCopies(getNumberOfCopies() + 1);
            System.out.println("Item returned successfully.");
        } else {
            System.out.println("Item was not checked out.");
        }
    }

    // Method to update status based on due date
    // Set to private to restrict direct modification
    private void updateStatus() {
        if (status == AvailabilityStatus.CHECKED_OUT && dueDate != null && LocalDate.now().isAfter(dueDate)) {
            setStatus(AvailabilityStatus.OVERDUE);
        } else if (status == AvailabilityStatus.OVERDUE && dueDate != null && LocalDate.now().isBefore(dueDate)) {
            setStatus(AvailabilityStatus.CHECKED_OUT);
        }
    }

    // toString method for description
    @Override
    public String toString() {
        return super.toString() + String.format(", Status: %s, Due Date: %s, Current Holder: %s",
        status, dueDate != null ? dueDate : "None",
        currentHolder != null ? currentHolder.getName() : "None");
    }
}
