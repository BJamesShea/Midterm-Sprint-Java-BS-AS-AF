package LibraryManagement;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ArrayList;

import LibraryManagement.authors.Author;
import LibraryManagement.items.Book;
import LibraryManagement.items.LibraryItem;
import LibraryManagement.items.Periodical;

// Demo class is Users landing point with Library Menu
public class Demo_LibraryMenu extends Library {

    // Method to get the current date formatted as "Month Day, Year"
    private static String getCurrentFormattedDate() {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        return currentDate.format(formatter);
    }

    // Create lists to store data
    private static List<LibraryItem> libraryItems = new ArrayList<>();
    private static List<Author> authors = new ArrayList<>();
    
    public static void main(String[] args) {

        // Create scanner for Library Menu input
        Scanner scanner = new Scanner(System.in);

        // Initialize choice object
        int choice;

        // Call function to get a formatted current date
        String currentDate = getCurrentFormattedDate();

        // Library Menu
        do {
            System.out.println();
            System.out.println("Welcome to the Library Management System");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            System.out.println("Todays Date: " + currentDate);
            System.out.println("----------------------------------------");
            System.out.println("Please Review The Following Options:");
            System.out.println();
            System.out.println("1.  Add Library Item");
            System.out.println("2.  Edit Library Item");
            System.out.println("3.  Delete Library Item");
            System.out.println("4.  Borrow Library Item");
            System.out.println("5.  Return Library Item");
            System.out.println("6.  Add Author");
            System.out.println("7.  Edit Author");
            System.out.println("8.  Delete Author");
            System.out.println("9.  Add Patron");
            System.out.println("10. Edit Patron");
            System.out.println("11. Delete Patron");
            System.out.println("12. Exit");
            System.out.println();
            System.out.println("Enter your choice (1-12): ");
            choice = scanner.nextInt();

             switch (choice) {

                case 1:
                    addLibraryItem(scanner);
                    break;

                case 2:
                    System.out.println("Editing an existing library item...");
                    // Add logic to edit a library item
                    break;

                case 3:
                    System.out.println("Deleting a library item...");
                    // Add logic to delete a library item
                    break;

                case 4:
                    System.out.println("Borrowing a library item...");
                    // Add logic to borrow a library item
                    break;
                    
                case 5:
                    System.out.println("Returning a library item...");
                    // Add logic to return a library item
                    break;

                case 6:
                    addAuthor(scanner);
                    break;

                case 7:
                    System.out.println("Editing an existing author...");
                    // Add logic to edit an author
                    break;

                case 8:
                    System.out.println("Deleting an author...");
                    // Add logic to delete an author
                    break;

                case 9:
                    System.out.println("Adding a new patron...");
                    // Add logic to add a patron
                    break;

                case 10:
                    System.out.println("Editing an existing patron...");
                    // Add logic to edit a patron
                    break;

                case 11:
                    System.out.println("Deleting a patron...");
                    // Add logic to delete a patron
                    break;

                case 12:
                    System.out.println("Exiting the system. Thank you for visiting!");
                    break;

                default:
                    System.out.println("Invalid choice. Please choose a valid option (1-12).");
                    break;
            }

        } while (choice != 12);

        // Close the scanner
        scanner.close();    
        
    }

    // Method to add a library item
    private static void addLibraryItem(Scanner scanner) {
        System.out.println("Adding a new library item...");
        System.out.println("Enter the type of library item (1 for Book, 2 for Periodical): ");
        int itemTypeChoice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        // Input fields for both Book and Periodical items
        System.out.print("Enter ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Title: ");
        String title = scanner.nextLine();

        // User must choose an existing author or create a new author
        Author author = selectOrCreateAuthor(scanner);

        System.out.print("Enter ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Enter Publisher: ");
        String publisher = scanner.nextLine();
        System.out.print("Enter Number of Copies: ");
        int numberOfCopies = scanner.nextInt();
        scanner.nextLine(); 

        LibraryItem libraryItem;

        if (itemTypeChoice == 1) {
            // Creating a book item
            Book.Format format = null;
            while (format == null) {
                System.out.println("Enter the format of the Book (1 for Printed, 2 for Electronic, 3 for Audio): ");
                int formatChoice = scanner.nextInt();
                scanner.nextLine();

                switch (formatChoice) {

                    case 1:
                        format = Book.Format.PRINTED;
                        break;

                    case 2: 
                        format = Book.Format.ELECTRONIC;
                        break;

                    case 3:
                        format = Book.Format.AUDIO;
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
                }
            }

            libraryItem = new Book(id, title, author, isbn, publisher, numberOfCopies, format);
            System.out.println("Book added successfully.");
        } else if (itemTypeChoice == 2) {
            // Creating a periodical item
            Periodical.Type type = null;
            while (type == null) {
                System.out.println("Enter the type of the Periodical (1 for Printed, 2 for Electronic): ");
                int typeChoice = scanner.nextInt();
                scanner.nextLine();

                switch (typeChoice) {

                    case 1:
                        type = Periodical.Type.PRINTED;
                        break;

                    case 2:
                        type = Periodical.Type.ELECTRONIC;
                        break;

                    default:
                        System.out.println("Invalid choice. Please enter 1 or 2.");
                }
            }

            libraryItem = new Periodical(id, title, author, isbn, publisher, numberOfCopies, type);
            System.out.println("Periodical added successfully.");

        } else {
            System.out.println("Invalid item type choice. Please select 1 for Book or 2 for Periodical.");
            return;
        }

        // Add the new library item to the list
        libraryItems.add(libraryItem);
        // Add the library item to the author's list of written items
        author.addWrittenItem(libraryItem);

        System.out.println("Library item added successfully.");
    }

    // Method to add an author
    private static void addAuthor(Scanner scanner) {
        System.out.print("Enter author's name: ");
        String name = scanner.nextLine();
        System.out.print("Enter author's date of birth (YYYY-MM-DD): ");
        String dobInput = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dobInput);

        // Create the author
        Author author = new Author(name);
        author.setDob(dob);
        authors.add(author);

        System.out.println("Author added successfully.");
    }

    // Method to either select an existing author or create a new one
    private static Author selectOrCreateAuthor(Scanner scanner) {
        System.out.println("Select an existing author or create a new one: ");
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, authors.get(i).getName());
        }
        System.out.println((authors.size() + 1) + ". Create a new author.");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= authors.size()) {
            // Select existing author
            return authors.get(choice - 1);
        } else {
            // Create new author and return 
            addAuthor(scanner);
            return authors.get(authors.size() - 1); 
        }
    }
}