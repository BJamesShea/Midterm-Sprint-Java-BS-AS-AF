package LibraryManagement.util;

import LibraryManagement.authors.Author;
import LibraryManagement.items.LibraryItem;

public class Status extends LibraryItem {
    public Status(String id, String title, Author author, String isbn, String publisher, int numberOfCopies, ItemType itemType) {
        super(id, title, author, isbn, publisher, numberOfCopies, itemType);
    }
}
