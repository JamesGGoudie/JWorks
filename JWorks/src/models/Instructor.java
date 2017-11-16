package models;

public class Instructor  extends User {
    protected int instructorID;

    public Instructor(String name, String emailAddress, String password, int instructorID) {
        super(name, emailAddress, password);
        this.instructorID = instructorID;
    }

    public int getStudentNumber() {
        return instructorID;
    }

    public void setStudentNumber(int studentNumber) {
        this.instructorID = studentNumber;
    }
}
