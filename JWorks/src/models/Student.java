package models;

/**
 * Class to represent a Student user.
 */
public class Student extends User {
    protected int studentNumber;

    public Student(){
        super();
    }

    public Student(int studentNumber){
        super();
        this.setStudentNumber(studentNumber);
    }

    public Student(String name, String emailAddress, String password, int studentNumber) {
        super(name, emailAddress, password);
        this.studentNumber = studentNumber;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(int studentNumber) {
        this.studentNumber = studentNumber;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;

        return this.studentNumber == otherStudent.getStudentNumber();
    }
}
