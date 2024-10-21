package LibraryManagement.authors;

// Imports
import java.time.LocalDate; // import LocalDate to properly handle date object for "dob"
import java.time.format.DateTimeFormatter; // import DateTimeFormatter to format author's date of birth(dob)
import java.util.*; // import all classes in java.util package to access ArrayList and List for list of items author has written(writtenItems)

// Public class that represents the Author - includes name, date of birth(dob), and list of items they have written as well as getters and setters and description(toString)
public class Author {

    // Private instance variable(s)
    private String name; 
    private LocalDate dob; // date of birth in yyyy-mm-dd format
    private List<String> writtenItems; // list of items author has written

    // Constructor(s)
    public Author(String name) { // parameterized constructor
        this.name = name;
        this.writtenItems = new ArrayList<>(); // initializes the list
    }

    public Author() { // default constructor
        this.name = null; // sets name to null to indicate no value
        this.writtenItems = new ArrayList<>(); // initializes the list as empty
    }

    // Getters and Setters
    public String getName() { // method to get author's name
        return this.name;
    }

    public void setName(String name) { // method to set author's name
        this.name = name;
    }

    public LocalDate getDob() { // method to get author's date of birth(dob)
        return this.dob;
    }

    public void setDob(LocalDate dob) { // method to set the author's date of birth
        this.dob = dob;                 // yyyy-mm-dd format
    }

    public List<String> getWrittenItems() { // method to get a list of author's written items
        return writtenItems;
    }

    public void addWrittenItem(String item) { // method to an item to the authors written item list
        writtenItems.add(item);               // uses add method of the List interface to append item 
    }

    // Method to make the author's date of birth format prettier
    public String formatDob() {
        if (dob == null) {
            return "Date of birth not set. Input required information in YYYY-MM-DD format.";
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy");
        return this.dob.format(formatter);
    }

    // toString method to provide description
    @Override
    public String toString() {
        return "Author: " + this.name + ", Date of birth: " + this.formatDob() + ", Items written: " + this.writtenItems;
    }

}
