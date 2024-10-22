package LibraryManagement.patrons;

import java.time.LocalDate;

public class Student extends Patron {
    // Private instance variables
    private int id;
    private LocalDate enrollmentDate;

    // Constructor
    public Student(String name, String address, String phoneNum, int id, LocalDate enrollmentDate) {
        super(name, address, phoneNum);
        this.id = id;
        this.enrollmentDate = enrollmentDate;
    }

    // Getters and Setters
    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public LocalDate getEnrollmentDate() {

        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {

        this.enrollmentDate = enrollmentDate;
    }

    // toString method for description, adds type to LibraryItem toString
    @Override
    public String toString() {
        return super.toString() + ", ID: " + id + ", Enrollment Date: " + enrollmentDate;
    }

}
