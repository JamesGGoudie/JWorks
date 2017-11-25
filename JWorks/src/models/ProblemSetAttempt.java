package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class to keep track of user answers for a particular problem set
 */
public class ProblemSetAttempt extends DatabaseObject {
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
     * Creates a new ProblemSetAttempt object, given a student, problem set, and attempt time. This
     * constructor assumes that the attempt was made some time ago, and we need to reinstantiate
     * the attempt object.
     * @param student The student who completed the problem set.
     * @param problemSet The problem set which was completed.
     * @param attemptTime The time the problem set was attempted.
     */
    public ProblemSetAttempt(Student student, ProblemSet problemSet, Date attemptTime) {
        this(student, problemSet);
        this.timeAttempted = attemptTime;
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
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ProblemSet.class, new AbstractModelAdapter<ProblemSet>())
                .registerTypeAdapter(Problem.class, new AbstractModelAdapter<Problem>())
                .create();
        return gson.toJson(this);
    }

    /**
     * Deserializes the given problem set attempt object.
     * @param serial the string representation of the problem set attempt to convert to an object
     * @return the deserialized problem set attempt object
     */
    public static ProblemSetAttempt deserialize(String serial) {
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(ProblemSet.class, new AbstractModelAdapter<ProblemSet>())
                .registerTypeAdapter(Problem.class, new AbstractModelAdapter<Problem>())
                .create();
        return gson.fromJson(serial, ProblemSetAttempt.class);
    }
}
