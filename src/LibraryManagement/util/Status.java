package LibraryManagement.util;

import LibraryManagement.items.LibraryItem;
import LibraryManagement.patrons.Patron;
import java.time.LocalDate;

public class Status {

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

    public Status() {
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

    // Method to borrow an item
    public void borrowItem(Patron patron, LibraryItem item) {
        if (status == AvailabilityStatus.AVAILABLE && item.getNumberOfCopies() > 0) {
            this.status = AvailabilityStatus.CHECKED_OUT;
            this.dueDate = LocalDate.now().plusDays(6);
            this.currentHolder = patron;
            item.setNumberOfCopies(item.getNumberOfCopies() - 1);
            System.out.println("Item borrowed successfully.");
        } else {
            System.out.println("Item is not available for borrowing.");
        }
    }

    // Method to return an item
    public void returnItem(LibraryItem item) {
        if (status == AvailabilityStatus.CHECKED_OUT || status == AvailabilityStatus.OVERDUE) {
            this.status = AvailabilityStatus.AVAILABLE;
            this.dueDate = null;
            this.currentHolder = null;
            item.setNumberOfCopies(item.getNumberOfCopies() + 1);
            System.out.println("Item returned successfully.");
        } else {
            System.out.println("Item was not checked out.");
        }
    }

    // Method to update status based on due date
    // Set to private to restrict direct modification
    private void updateStatus() {
        if (status == AvailabilityStatus.CHECKED_OUT && dueDate != null && LocalDate.now().isAfter(dueDate)) {
            this.status = AvailabilityStatus.OVERDUE;
        } else if (status == AvailabilityStatus.OVERDUE && dueDate != null && LocalDate.now().isBefore(dueDate)) {
            this.status = AvailabilityStatus.CHECKED_OUT;
        }
    }

    // toString method for description
    @Override
    public String toString() {
        return String.format("Status: %s, Due Date: %s, Current Holder: %s",
        status, dueDate != null ? dueDate : "None",
        currentHolder != null ? currentHolder.getName() : "None");
    }
}
