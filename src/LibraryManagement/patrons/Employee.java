package LibraryManagement.patrons;


public class Employee extends Patron{
    // Private instance variables
    private int id;
    private String department;

    // Constructor
    public Employee(String name, String address, String phoneNum, int id, String department) {
        super(name, address, phoneNum);
        this.id = id;
        this.department = department;
    }

    // Getters and Setters
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getDepartment() {

        return department;
    }

    public void setDepartment(String department) {

        this.department = department;
    }

    // toString method for description, adds type to LibraryItem toString
    @Override
    public String toString() {
        return super.toString() + ", ID: " + id + ", Department: " + department;
    }

}
