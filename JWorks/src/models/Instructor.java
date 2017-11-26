package models;

public class Instructor  extends User {
    protected int instructorID;

    public Instructor(){
        super();
    }

    public Instructor(int instructorID){
        super();
        this.setInstructorID(instructorID);
    }

    public Instructor(String name, String emailAddress, String password, int instructorID) {
        super(name, emailAddress, password);
        this.instructorID = instructorID;
    }

    public int getInstructorID() {
        return instructorID;
    }

    public void setInstructorID(int studentNumber) {
        this.instructorID = studentNumber;
    }
}
