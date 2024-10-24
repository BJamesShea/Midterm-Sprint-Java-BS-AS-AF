package LibraryManagement;

import LibraryManagement.items.*;
import LibraryManagement.authors.*;
import LibraryManagement.patrons.*;

import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<LibraryItem> libraryItems;
    private List<Author> authors;
    private List<Patron> patrons;

    public Library() {
        libraryItems = new ArrayList<>();
        authors = new ArrayList<>();
        patrons = new ArrayList<>();

    }

    public void addLibraryItem(LibraryItem item) {
        libraryItems.add(item);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public LibraryItem searchByTitle(String title) {
        for (LibraryItem item : libraryItems) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;

    }

    public LibraryItem searchByAuthor(String authorName) {
        for (LibraryItem item : libraryItems) {
            if (item.getAuthor().getName().equals(authorName)) {
                return item;
            }
        }
        return null;
    }

    public LibraryItem searchByISBN(String isbn) {
        for (LibraryItem item : libraryItems) {
            if (item.getIsbn().equals(isbn)) {
                return item;
            }
        }
        return null;
    }

    public boolean borrowItem(String title, Patron patron) {
        LibraryItem item = searchByTitle(title);

        if (item != null && item.getNumberOfCopies() > 0) {
            item.setNumberOfCopies(item.getNumberOfCopies() - 1);

            patron.addBorrowedItem(item);

            return true;
        } else {
            System.out.println("Item is currently checked out.");
        }
        return false;

    }

    public boolean returnItem(String title, Patron patron) {
        LibraryItem item = searchByTitle(title);

        if (item != null && patron.getBorrowedItem().contains(item)) {
            item.setNumberOfCopies(item.getNumberOfCopies() + 1);

            patron.getBorrowedItem().remove(item);

            return true;
        }
        return false;
    }

















}