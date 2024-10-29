package LibraryManagement;

import LibraryManagement.authors.Author;
import LibraryManagement.items.Book;
import LibraryManagement.items.LibraryItem;
import LibraryManagement.items.Periodical;
import LibraryManagement.patrons.Employee;
import LibraryManagement.patrons.Patron;
import LibraryManagement.patrons.Student;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
    private static List<Patron> patrons = new ArrayList<>();
    
    public static void main(String[] args) {

        // Create scanner for Library Menu input
        Scanner scanner = new Scanner(System.in);

        // Load initial data
        loadData();

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
                    System.out.println("Adding a new library item...");
                    addLibraryItem(scanner);
                    break;

                case 2:
                    System.out.println("Editing an existing library item...");
                    editLibraryItem(scanner);
                    break;

                case 3:
                    System.out.println("Deleting a library item...");
                    deleteLibraryItem(scanner);
                    break;

                case 4:
                    System.out.println("Borrowing a library item...");
                    borrowLibraryItem(scanner);
                    break;
                    
                case 5:
                    System.out.println("Returning a library item...");
                    returnLibraryItem(scanner);
                    break;

                case 6:
                    addAuthor(scanner);
                    break;

                case 7:
                    System.out.println("Editing an existing author...");
                    editAuthor(scanner);
                    break;

                case 8:
                    System.out.println("Deleting an author...");
                    deleteAuthor(scanner);
                    break;

                case 9:
                    System.out.println("Adding a new patron...");
                    addPatron(scanner);
                    break;

                case 10:
                    System.out.println("Editing an existing patron...");
                    editPatron(scanner);
                    break;

                case 11:
                    System.out.println("Deleting a patron...");
                    deletePatron(scanner);
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
        scanner.nextLine();

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

    // Method to edit an existing library item
    private static void editLibraryItem(Scanner scanner) {
        if (libraryItems.isEmpty()) {
            System.out.println("No library items available to edit.");
            return;
        }

        System.out.println("Select a library item to edit: ");
        for (int i = 0; i < libraryItems.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, libraryItems.get(i).getTitle());
        }
        System.out.print("Enter the number of the library item to edit: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= libraryItems.size()) {
            LibraryItem itemToEdit = libraryItems.get(choice - 1);

            System.out.print("Enter new title (or press Enter to keep current title: " + itemToEdit.getTitle() + "): ");
            String newTitle = scanner.nextLine();
            if (!newTitle.trim().isEmpty()) {
                itemToEdit.setTitle(newTitle);
            }

            System.out.print("Enter new ISBN (or press Enter to keep current ISBN: " + itemToEdit.getIsbn() + "): ");
            String newIsbn = scanner.nextLine();
            if (!newIsbn.trim().isEmpty()) {
                itemToEdit.setIsbn(newIsbn);
            }

            System.out.print("Enter new publisher (or press Enter to keep current publisher: " + itemToEdit.getPublisher() + "): ");
            String newPublisher = scanner.nextLine();
            if (!newPublisher.trim().isEmpty()) {
                itemToEdit.setPublisher(newPublisher);
            }

            System.out.print("Enter new number of copies (or press Enter to keep current number: " + itemToEdit.getNumberOfCopies() + "): ");
            String newCopiesInput = scanner.nextLine();
            if (!newCopiesInput.trim().isEmpty()) {
                int newCopies = Integer.parseInt(newCopiesInput);
                itemToEdit.setNumberOfCopies(newCopies);
            }

            // If the item is a Book, handle format updates
            if (itemToEdit instanceof Book) {
                Book bookToEdit = (Book) itemToEdit;
                System.out.print("Enter new format (1 for Printed, 2 for Electronic, 3 for Audio) (or press Enter to keep current format): ");
                String formatInput = scanner.nextLine();
                if (!formatInput.trim().isEmpty()) {
                    Book.Format format;
                    switch (formatInput) {
                        case "1":
                            format = Book.Format.PRINTED;
                            break;
                        case "2":
                            format = Book.Format.ELECTRONIC;
                            break;
                        case "3":
                            format = Book.Format.AUDIO;
                            break;
                        default:
                            System.out.println("Invalid format choice. Keeping current format.");
                            format = bookToEdit.getFormat();
                    }
                    bookToEdit.setFormat(format);
                }
            } else if (itemToEdit instanceof Periodical) {
                Periodical periodicalToEdit = (Periodical) itemToEdit;
                System.out.print("Enter new type (1 for Printed, 2 for Electronic) (or press Enter to keep current type): ");
                String typeInput = scanner.nextLine();
                if (!typeInput.trim().isEmpty()) {
                    Periodical.Type type;
                    switch (typeInput) {
                        case "1":
                            type = Periodical.Type.PRINTED;
                            break;
                        case "2":
                            type = Periodical.Type.ELECTRONIC;
                            break;
                        default:
                            System.out.println("Invalid type choice. Keeping current type.");
                            type = periodicalToEdit.getType();
                    }
                    periodicalToEdit.setType(type);
                }
            }

            System.out.println("Library item details updated successfully.");
        } else {
            System.out.println("Invalid choice. Please select a valid library item number.");
        }
    }


    // Method to delete a library item
    private static void deleteLibraryItem(Scanner scanner) {
        if (libraryItems.isEmpty()) {
            System.out.println("No library items available to delete.");
            return;
        }

        System.out.println("Select a library items to delete: ");
        for (int i = 0; i < libraryItems.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, libraryItems.get(i).getTitle());
        }
        System.out.print("Enter the number of the library item to delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= libraryItems.size()) {
            // Remove the author from the list
            LibraryItem removedLibraryItem = libraryItems.remove(choice - 1);
            System.out.println("Library Item '" + removedLibraryItem.getTitle() + "' has been deleted successfully.");
        } else {
            System.out.println("Invalid choice. Please select a valid library item number.");
        }
    }

    // Method to add an author
    private static void addAuthor(Scanner scanner) {
        scanner.nextLine(); // Clear the buffer before reading input
        System.out.print("Enter author's name: ");
        String name = scanner.nextLine(); // Read author's name

        // To avoid issues with lingering newlines
        System.out.print("Enter author's date of birth (YYYY-MM-DD): ");
        String dobInput = scanner.nextLine(); // Read date of birth
        LocalDate dob = LocalDate.parse(dobInput); // Parse date of birth

        // Create the author
        Author author = new Author(name);
        author.setDob(dob);
        authors.add(author); // Add the author to the list

        System.out.println("Author added successfully.");
    }

    // Method to edit an existing author
    private static void editAuthor(Scanner scanner) {
        if (authors.isEmpty()) {
            System.out.println("No authors available to edit.");
            return;
        }

        System.out.println("Select an author to edit: ");
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, authors.get(i).getName());
        }
        System.out.print("Enter the number of the author to edit: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= authors.size()) {
            Author authorToEdit = authors.get(choice - 1);

            System.out.print("Enter new name (or press Enter to keep current name: " + authorToEdit.getName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                authorToEdit.setName(newName);
            }

            System.out.print("Enter new date of birth (YYYY-MM-DD) (or press Enter to keep current date of birth): ");
            String dobInput = scanner.nextLine();
            if (!dobInput.trim().isEmpty()) {
                LocalDate dob = LocalDate.parse(dobInput);
                authorToEdit.setDob(dob);
            }

            System.out.println("Author details updated successfully.");
        } else {
            System.out.println("Invalid choice. Please select a valid author number.");
        }
    }


    // Method to delete an author
    private static void deleteAuthor(Scanner scanner) {
        if (authors.isEmpty()) {
            System.out.println("No authors available to delete.");
            return;
        }

        System.out.println("Select an author to delete: ");
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, authors.get(i).getName());
        }
        System.out.print("Enter the number of the author to delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= authors.size()) {
            // Remove the author from the list
            Author removedAuthor = authors.remove(choice - 1);
            System.out.println("Author '" + removedAuthor.getName() + "' has been deleted successfully.");
        } else {
            System.out.println("Invalid choice. Please select a valid author number.");
        }
    }

    // Method to either select an existing author or create a new one
    private static Author selectOrCreateAuthor(Scanner scanner) {
        System.out.println("Select an existing author or create a new one: ");
        for (int i = 0; i < authors.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, authors.get(i).getName());
        }
        System.out.println((authors.size() + 1) + ". Press 1 to create a new Author.");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        if (choice > 0 && choice <= authors.size()) {
            // Select existing author
            return authors.get(choice - 1);
        } else {
            // Create new author and return 
            addAuthor(scanner);
            return authors.get(authors.size() - 1); 
        }
    }

    // Method to add a patron
    private static void addPatron(Scanner scanner) {
        System.out.println("Choose Patron Type (1 for Student, 2 for Employee): ");
        int patronChoice = scanner.nextInt();
        scanner.nextLine();

        // Input fields for both patrons
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Address: ");
        String address = scanner.nextLine();
        System.out.print("Enter Phone Number: ");
        String phoneNum = scanner.nextLine();

        // Input fields for chosen patron type
        if (patronChoice == 1) {
            // Add student
            System.out.print("Enter Student ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Enrollment Date (YYYY-MM-DD): ");
            String enrollmentDateInput = scanner.nextLine();
            LocalDate enrollmentDate = LocalDate.parse(enrollmentDateInput);
            // Create student object
            Student student = new Student(name, address, phoneNum, id, enrollmentDate);
            patrons.add(student); // add new student to list of patrons
            System.out.println("Student added successfully.");
        } else if (patronChoice == 2) {
            // Add employee
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            System.out.print("Enter Department: ");
            String department = scanner.nextLine();
            // Create employee object
            Employee employee = new Employee(name, address, phoneNum, id, department);
            patrons.add(employee); // add new employee to list of patrons
            System.out.println("Employee added successfully.");
        } else {
            System.out.println("Invalid choice. Please select 1 for Student or 2 for Employee.");
        }
    }

    // Method to delete a patron
    private static void deletePatron(Scanner scanner) {
        if (patrons.isEmpty()) {
            System.out.println("No patrons available to delete.");
            return;
        }

        System.out.println("Select an patron to delete: ");
        for (int i = 0; i < patrons.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, patrons.get(i).getName());
        }
        System.out.print("Enter the number of the patron to delete: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        if (choice > 0 && choice <= patrons.size()) {
            // Remove the author from the list
            Patron removedPatron = patrons.remove(choice - 1);
            System.out.println("Patron '" + removedPatron.getName() + "' has been deleted successfully.");
        } else {
            System.out.println("Invalid choice. Please select a valid author number.");
        }
    }

    // Method to edit an existing patron
    private static void editPatron(Scanner scanner) {
        if (patrons.isEmpty()) {
            System.out.println("No patrons available to edit.");
            return;
        }

        System.out.println("Select a patron to edit: ");
        for (int i = 0; i < patrons.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, patrons.get(i).getName());
        }
        System.out.print("Enter the number of the patron to edit: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Clear the buffer

        if (choice > 0 && choice <= patrons.size()) {
            Patron patronToEdit = patrons.get(choice - 1);

            System.out.print("Enter new name (or press Enter to keep current name: " + patronToEdit.getName() + "): ");
            String newName = scanner.nextLine();
            if (!newName.trim().isEmpty()) {
                patronToEdit.setName(newName); // Assuming you have a setName method in Patron class
            }

            System.out.print("Enter new address (or press Enter to keep current address: " + patronToEdit.getAddress() + "): ");
            String newAddress = scanner.nextLine();
            if (!newAddress.trim().isEmpty()) {
                patronToEdit.setAddress(newAddress); // Assuming you have a setAddress method in Patron class
            }

            System.out.print("Enter new phone number (or press Enter to keep current number: " + patronToEdit.getPhoneNum() + "): ");
            String newPhoneNum = scanner.nextLine();
            if (!newPhoneNum.trim().isEmpty()) {
                patronToEdit.setPhoneNum(newPhoneNum); // Assuming you have a setPhoneNumber method in Patron class
            }

            // Additional patron-specific fields
            if (patronToEdit instanceof Student) {
                System.out.print("Enter new Student ID (or press Enter to keep current ID: " + ((Student) patronToEdit).getId() + "): ");
                String newStudentId = scanner.nextLine();
                if (!newStudentId.trim().isEmpty()) {
                    ((Student) patronToEdit).setId(Integer.parseInt(newStudentId)); // Assuming you have a setId method in Student class
                }
                System.out.print("Enter new Enrollment Date (YYYY-MM-DD) (or press Enter to keep current date): ");
                String newEnrollmentDateInput = scanner.nextLine();
                if (!newEnrollmentDateInput.trim().isEmpty()) {
                    LocalDate newEnrollmentDate = LocalDate.parse(newEnrollmentDateInput);
                    ((Student) patronToEdit).setEnrollmentDate(newEnrollmentDate); // Assuming you have a setEnrollmentDate method in Student class
                }
            } else if (patronToEdit instanceof Employee) {
                System.out.print("Enter new Employee ID (or press Enter to keep current ID: " + ((Employee) patronToEdit).getId() + "): ");
                String newEmployeeId = scanner.nextLine();
                if (!newEmployeeId.trim().isEmpty()) {
                    ((Employee) patronToEdit).setId(Integer.parseInt(newEmployeeId)); // Assuming you have a setId method in Employee class
                }
                System.out.print("Enter new Department (or press Enter to keep current department: " + ((Employee) patronToEdit).getDepartment() + "): ");
                String newDepartment = scanner.nextLine();
                if (!newDepartment.trim().isEmpty()) {
                    ((Employee) patronToEdit).setDepartment(newDepartment); // Assuming you have a setDepartment method in Employee class
                }
            }

            System.out.println("Patron details updated successfully.");
        } else {
            System.out.println("Invalid choice. Please select a valid patron number.");
        }
    }

    // Method to borrow a library item
    private static void borrowLibraryItem(Scanner scanner) {
        // First check if the library had items and patrons
        if (libraryItems.isEmpty() || patrons.isEmpty()) {
            System.out.println("No library items available or patrons available to borrow items");
            return;
        }

        // If items and patrons available select an item to borrow
        LibraryItem itemToBorrow = selectLibraryItem(scanner, "borrow");
        if (itemToBorrow == null) return;

        // Select the patron borrowing the item
        Patron patron = selectPatron(scanner, "borrow");
        if (patron == null) return;

        // Borrow the item using the Status class
        itemToBorrow.getStatus().borrowItem(patron, itemToBorrow);
    }

    // Helper method to select a library item
    private static LibraryItem selectLibraryItem(Scanner scanner, String action) {
        System.out.println("Select a library item to " + action + ":");
        for (int i = 0; i < libraryItems.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, libraryItems.get(i).getTitle());
        } 
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= libraryItems.size()) {
            return libraryItems.get(choice - 1);
        } else {
            System.out.println("Invalid choice. Please select a valid item number.");
            return null;
        }
    }

    // Helper method to select a patron
    private static Patron selectPatron(Scanner scanner, String action) {
        System.out.println("Select a patron to " + action + ":");
        for (int i = 0; i < patrons.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, patrons.get(i).getName());
        }
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice > 0 && choice <= patrons.size()) {
            return patrons.get(choice - 1);
        } else {
            System.out.println("Invalid choice. Please select a valid patron number.");
            return null;
        }
    }

    // Method to return a library item
    private static void returnLibraryItem(Scanner scanner) {
        if (libraryItems.isEmpty()) {
            System.out.println("No library items available for returning.");
            return;
        }

        // Select a library item to return
        LibraryItem itemToReturn = selectLibraryItem(scanner, "return");
        if (itemToReturn == null) return;

        // Check if the item is currently borrowed
        if (itemToReturn.getStatus().getCurrentHolder() == null) {
            System.out.println("This item is not currently checked out.");
            return;
        }

        // Return the item using its status method
        itemToReturn.getStatus().returnItem(itemToReturn);
        System.out.println("Item returned successfully.");
    }

    // Method to load data every time code is run
    private static void loadData() {
    
        // Authors data
        Author a1 = new Author("Jane Austen");
        a1.setDob(LocalDate.of(1775,12,16));
        Author a2 = new Author("George Orwell");
        a2.setDob(LocalDate.of(1903,6,25));
        Author a3 = new Author("J.R.R. Tolkien");
        a3.setDob(LocalDate.of(1892,1,3));
        Author a4 = new Author("Mark Twain");
        a4.setDob(LocalDate.of(1835,11,30));
        Author a5 = new Author("Agatha Christie");
        a5.setDob(LocalDate.of(1890,9,15));

        authors.add(a1);
        authors.add(a2);
        authors.add(a3);
        authors.add(a4);
        authors.add(a5);

        // Book data
        Book b1 = new Book("B001","Pride and Prejudice",a1,"1012354","Vintage",5,Book.Format.PRINTED);
        Book b2 = new Book("B002","1984",a2,"1111111","Penguin",3,Book.Format.ELECTRONIC);
        Book b3 = new Book("B003", "The Hobbit", a3, "3333333333", "Allen & Unwin", 8, Book.Format.PRINTED);
        Book b4 = new Book("B004", "The Adventures of Huckleberry Finn", a4, "4444444444", "Harper & Brothers", 4, Book.Format.AUDIO);
        Book b5 = new Book("B005", "Murder on the Orient Express", a5, "5555555555", "Collins Crime Club", 6, Book.Format.PRINTED);
        Book b6 = new Book("B006", "Emma", a1, "1528754", "Penguin Classics", 3, Book.Format.ELECTRONIC);
        Book b7 = new Book("B007", "Animal Farm", a2, "7777777777", "Secker & Warburg", 10, Book.Format.PRINTED);

        libraryItems.add(b1);
        libraryItems.add(b2);
        libraryItems.add(b3);
        libraryItems.add(b4);
        libraryItems.add(b5);
        libraryItems.add(b6);
        libraryItems.add(b7);

        a1.addWrittenItem(b1);
        a1.addWrittenItem(b6);
        a2.addWrittenItem(b2);
        a2.addWrittenItem(b7);
        a3.addWrittenItem(b3);
        a4.addWrittenItem(b4);
        a5.addWrittenItem(b5);

        // Periodical data
        Periodical p1 = new Periodical("P001", "National Geographic", a2, "8888888888", "NatGeo", 7, Periodical.Type.PRINTED);
        Periodical p2 = new Periodical("P002", "The New Yorker", a5, "9999999999", "Condé Nast", 5, Periodical.Type.PRINTED);
        Periodical p3 = new Periodical("P003", "Scientific American", a4, "1010101010", "Springer Nature", 4, Periodical.Type.ELECTRONIC);
        Periodical p4 = new Periodical("P004", "TIME Magazine", a3, "1112131415", "Time USA, LLC", 6, Periodical.Type.PRINTED);
        Periodical p5 = new Periodical("P005", "Nature", a1, "1213141516", "Nature Publishing Group", 3, Periodical.Type.ELECTRONIC);

        libraryItems.add(p1);
        libraryItems.add(p2);
        libraryItems.add(p3);
        libraryItems.add(p4);
        libraryItems.add(p5);

        a2.addWrittenItem(p1);
        a5.addWrittenItem(p2);
        a4.addWrittenItem(p3);
        a3.addWrittenItem(p4);
        a1.addWrittenItem(p5);

        // Patron data (Students and Employees)
        Student s1 = new Student("Angela Flynn-Smith", "123 Maple St", "555-1234", 1001, LocalDate.of(2021, 9, 1));
        Student s2 = new Student("Adam Stevenson", "456 Elm St", "555-5678", 1002, LocalDate.of(2020, 9, 1));
        Student s3 = new Student("Brandon Shea", "789 Pine St", "555-8765", 1003, LocalDate.of(2022, 1, 15));

        Employee e1 = new Employee("Stevie Nicks", "321 Oak St", "555-4321", 2001, "Science Department");
        Employee e2 = new Employee("Lady Gaga", "654 Cedar St", "555-6543", 2002, "Literature Department");
        Employee e3 = new Employee("Taylor Swift", "987 Willow St", "555-6789", 2003, "History Department");

        patrons.add(s1);
        patrons.add(s2);
        patrons.add(s3);
        patrons.add(e1);
        patrons.add(e2);
        patrons.add(e3);

        System.out.println("Sample data loaded successfully.");
    }

}
