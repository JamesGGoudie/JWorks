package gui;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import models.Problem;
import models.ProblemSet;
import models.ProblemSetAttempt;
import models.Student;

public class CompleteProblemSetScreenController extends Controller {
    // Bottom pane
    @FXML
    private ProgressBar progressBar;

    @FXML
    private Button nextQuestionButton;

    @FXML
    private Button prevQuestionButton;

    @FXML
    private Button submitAnswersButton;

    // Central pane
    @FXML
    private Label problemText;

    @FXML
    private TextField problemAnswer;

    // Top pane
    @FXML
    private Label problemSetName;

    // Model
    private ProblemSetAttempt currentAttempt;
    private int currentProblemIndex = 0;
    private Problem currentProblem;
    private CompleteProblemSetScreenManager manager;

    // Methods

    /**
     * Starts an instance of this screen such that the given student is attempting to answer the first question.
     * @param manager the manager for this screen.
     * @param problemSet the Problem Set being attempted.
     * @param student the Student that is attempting the Problem Set
     */
    public void start(CompleteProblemSetScreenManager manager, ProblemSet problemSet, Student student) {
        this.manager = manager;

        // Create problem set attempt model
        currentAttempt = new ProblemSetAttempt(student, problemSet);

        // Initialize text for problem set name
        problemSetName.setText("Problem Set " + problemSet.getId());

        // Initialize text for first question
        setupQuestion(currentProblemIndex);

        // Initialize visible state in edge case of 1 problem, submit is shown instead of next
        if (problemSet.getQuestions().size() == 1) {
            submitAnswersButton.setVisible(true);
            nextQuestionButton.setVisible(false);
        }
    }

    /**
     * Sets up the on click listeners for each of the GUI components.
     */
    @Override
    public void initialize() {
        nextQuestionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Move to the next question only if there is an answer entered
                if (problemAnswer.getText().trim().length() > 0) {
                    // Save and move on!
                    currentAttempt.setAnswer(currentProblemIndex, problemAnswer.getText());
                    setupQuestion(++currentProblemIndex);
                } else {
                    showNoAnswerError();
                }
            }
        });

        prevQuestionButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Save current answer and go back to previous
                currentAttempt.setAnswer(currentProblemIndex, problemAnswer.getText());
                setupQuestion(--currentProblemIndex);
            }
        });

        submitAnswersButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Submit if there is an answer entered
                if (problemAnswer.getText().trim().length() > 0) {
                    currentAttempt.setAnswer(currentProblemIndex, problemAnswer.getText());
                    manager.submitAttempt(currentAttempt);
                } else {
                    showNoAnswerError();
                }
            }
        });
    }

    /**
     * Handles the state change between questions. Changes the corresponding labels, answers
     * and updates the progress bar.
     * @param index the index of the next problem to switch to
     */
    private void setupQuestion(int index) {
        currentProblem = currentAttempt.getProblemSet().getQuestions().get(index);
        int numProblems = currentAttempt.getProblemSet().getQuestions().size();

        // Change labels
        problemText.setText(currentProblem.getProblem());
        problemAnswer.setText(currentAttempt.getAnswers().get(index));

        // Update progress bar
        progressBar.setProgress((index + 1) / numProblems);

        // Update button disable state if necessary
        // Prev button disabled on first question
        prevQuestionButton.setDisable(index == 0);
        // Submit visible on last question
        nextQuestionButton.setVisible(index != numProblems - 1);
        submitAnswersButton.setVisible(index == numProblems - 1);
    }

    /**
     * Shows an error informing the user that they need to answer the problem.
     */
    private void showNoAnswerError() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText("Enter an answer before proceeding!");
        alert.show();
        problemAnswer.requestFocus();
    }
}
