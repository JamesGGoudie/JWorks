package gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ViewProblemSetStatisticsScreenController extends Controller {
    @FXML
    private TableView<ViewProblemSetStatisticsRow> statisticsTable;
    @FXML
    private TableColumn<ViewProblemSetStatisticsRow, Integer> studentNumberColumn;
    @FXML
    private TableColumn<ViewProblemSetStatisticsRow, Integer> problemSetColumn;
    @FXML
    private TableColumn<ViewProblemSetStatisticsRow, Integer> numAttemptsColumn;
    @FXML
    private TableColumn<ViewProblemSetStatisticsRow, String> bestScoreColumn;
    @FXML
    private Label titleLabel;
    @FXML
    private Label participationLabel;
    @FXML
    private Label bestAttemptLabel;
    @FXML
    private Label numAttemptsLabel;

    private ViewProblemSetStatisticsScreenManager manager;

    public void start(ViewProblemSetStatisticsScreenManager manager) {
        this.manager = manager;

        // Populate data
        statisticsTable.getItems().setAll(manager.getRows());

        // Set static labels
        titleLabel.setText("Statistics for Problem Set " + String.valueOf(manager.getProblemSet().getId()));
        participationLabel.setText(String.valueOf(getNumberOfStudentsParticipated()));
        bestAttemptLabel.setText(String.format("%1$,.2f%%", getBestAttemptAverage()));
        numAttemptsLabel.setText(String.format("%1$,.2f", getAverageAttemptsPerStudent()));
    }

    @Override
    public void initialize() {
        super.initialize();

        // Setup data methods for each table column:
        studentNumberColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()
                .getStudent()
                .getStudentNumber()));
        problemSetColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()
                .getProblemSet()
                .getId()));
        numAttemptsColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()
                .getNumberOfAttempts()));
        bestScoreColumn.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()
                .getBestScore() + "%"));
    }

    /**
     * Returns the average of the best attempts of all students.
     * @return the average of the best attempts of all students.
     */
    public double getBestAttemptAverage() {
        int bestAttemptsTotal = 0;
        for (ViewProblemSetStatisticsRow row : statisticsTable.getItems()) {
            bestAttemptsTotal += row.getBestScore();
        }

        return (double) bestAttemptsTotal / (double) getNumberOfStudentsParticipated();
    }

    /**
     * Returns the average number of attempts for each student.
     * @return the average number of attempts for each student.
     */
    public double getAverageAttemptsPerStudent() {
        int totalAttempts = 0;
        for (ViewProblemSetStatisticsRow row : statisticsTable.getItems()) {
            totalAttempts += row.getNumberOfAttempts();
        }

        return (double) totalAttempts / (double) getNumberOfStudentsParticipated();
    }

    /**
     * Returns the number of students that attempted this problem set.
     * @return the number of students that attempted this problem set.
     */
    public int getNumberOfStudentsParticipated() {
        return statisticsTable.getItems().size();
    }
}
