package gui;

import models.ProblemSet;
import models.ProblemSetAttempt;
import models.Student;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a single row in the statistics table
 */
public class ViewProblemSetStatisticsRow {

    private Student student;
    private ProblemSet problemSet;
    private List<ProblemSetAttempt> attempts;

    /**
     * Creates a new row with the given student number and problem set number.
     * @param student the student  that attempted the problem set
     * @param problemSet the problem set that the student attempted
     */
    public ViewProblemSetStatisticsRow(Student student, ProblemSet problemSet) {
        this.student = student;
        this.problemSet = problemSet;
        attempts = new ArrayList<>();
    }

    /**
     * Returns the best score out of all the attempts stored in this row.
     * @return the best score out of all the attempts stored in this row.
     */
    public int getBestScore() {
        int max = 0;
        for (ProblemSetAttempt attempt : attempts) {
            if (attempt.getNumberofCorrectAnswers() > max) {
                max = attempt.getNumberofCorrectAnswers();
            }
        }

        return max;
    }

    /**
     * Adds the given problem set attempts into the object only if the attempt contains the correct student and problem
     * set.
     * @param attemptList the candidate list of attempts to add to this list
     */
    public void addAttempts(List<ProblemSetAttempt> attemptList) {
        for (ProblemSetAttempt attempt : attemptList) {
            if (attempt.getProblemSet().equals(problemSet)
                    && attempt.getStudent().equals(student)) {
                attempts.add(attempt);
            }
        }
    }

    /**
     * Get the number of attempts stored in this row.
     * @return the number of attempts stored in this row.
     */
    public int getNumberOfAttempts() {
        return attempts.size();
    }
}
