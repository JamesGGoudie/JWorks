package models;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to keep track of user answers for a particular problem set
 */
public class ProblemSetAttempt extends DatabaseObject implements Serializable {
    // Properties
    private ProblemSet problemSet;
    private Student student;
    private Date timeAttempted;

    private List<String> answers;

    /**
     * Creates a new ProblemSetAttempt object, given the student and problem set, with a current timestamp.
     * @param student the Student attempting the Problem Set
     * @param problemSet the Problem Set being attempted
     */
    public ProblemSetAttempt(Student student, ProblemSet problemSet) {
        this.student = student;
        this.problemSet = problemSet;
        this.timeAttempted = Date.from(Instant.now());

        this.answers = new ArrayList<>(problemSet.getQuestions().size());
        // Fill answers with blanks
        for (int i = 0; i < problemSet.getQuestions().size(); i++) {
            answers.add("");
        }
    }

    /**
     * Stores the answer that the user enters for the given problem index. Strips excess leading/trailing spaces.
     * @param problemIndex the index of the problem in the problem set that the user answered
     * @param answer the answer from user input
     */
    public void setAnswer(int problemIndex, String answer) {
        answers.set(problemIndex, answer.trim());
    }

    /**
     * Returns whether or not the student inputted the correct answer for the given problem. Ignores casing.
     * @param problemIndex the index of the problem in the problem set to check answers for
     * @return whether or not the student inputted the correct answer, ignoring casing.
     */
    public boolean isAnswerCorrect(int problemIndex) {
        String expectedAnswer = problemSet.getQuestions().get(problemIndex).getAnswer();
        String actualAnswer = answers.get(problemIndex);

        return expectedAnswer.equalsIgnoreCase(actualAnswer);
    }

    /**
     * Counts and returns the number of correct answers the user has entered for this attempt.
     * @return the total number of answers that the user has entered correctly
     */
    public int getNumberofCorrectAnswers() {
        int score = 0;
        for (int i = 0; i < problemSet.getQuestions().size(); i++) {
            if (isAnswerCorrect(i)) {
                score++;
            }
        }

        return score;
    }

    // Auto-generated getters;
    public ProblemSet getProblemSet() {
        return problemSet;
    }

    public Student getStudent() {
        return student;
    }

    public Date getTimeAttempted() {
        return timeAttempted;
    }

    public List<String> getAnswers() {
        return answers;
    }

    /**
     * Serializes this problem set and returns a string representation.
     * @return the string representation of the serialized object. Empty string if failed.
     */
    public String serialize() {
        String result;
        try {
            ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
            ObjectOutputStream outputStream = new ObjectOutputStream(byteOut);
            outputStream.writeObject(this);
            result = byteOut.toString();
        } catch (IOException e){
            result = "";
        }

        return result;
    }

    /**
     * Deserializes the given problem set attempt object.
     * @param serial the string representation of the problem set attempt to convert to an object
     * @return the deserialized problem set attempt object
     */
    public static ProblemSetAttempt deserialize(String serial) {
        ProblemSetAttempt attempt;
        // Deserialize string argument to get problem set attempt
        try {
            byte[] byteArray = serial.getBytes();
            ByteArrayInputStream byteInput = new ByteArrayInputStream(byteArray);
            ObjectInputStream in = new ObjectInputStream(byteInput);
            attempt = (ProblemSetAttempt) in.readObject();
            in.close();
            byteInput.close();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }

        return attempt;
    }
}
