package gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import models.ProblemSet;

import java.text.SimpleDateFormat;

public class ViewProblemSetScreenController extends WrappableViewController<ProblemSet> {
    @FXML
    private TableView<ProblemSet> problemSetTable;

    @FXML
    private TableColumn<ProblemSet, Integer> idColumn;

    @FXML
    private TableColumn<ProblemSet, Integer> numQuestionsColumn;

    @FXML
    private TableColumn<ProblemSet, String> releaseDateColumn;

    @FXML
    private TableColumn<ProblemSet, String> dueDateColumn;

    @FXML
    private TableColumn<ProblemSet, String> bestAttemptColumn;

    @FXML
    private Button viewProblemsButton;

    @FXML
    private Button attemptProblemsButton;

    @FXML
    private Button viewStatsButton;

    @FXML
    private Pane innerScreen;

    private ViewProblemSetScreenManager manager = new ViewProblemSetScreenManager();

    /**
     *
     * @param manager The scene manager to use for this UI controller
     */
    public void start(ViewProblemSetScreenManager manager) {
        this.manager = manager;
        problemSetTable.getItems().setAll(manager.getVisibleProblemSets());
    }

    /**
     * Setup the listeners and factories for each table column
     */
    @Override
    public void initialize() {
        super.initialize();

        // Wrap columns
        wrapColumnCells(releaseDateColumn);
        wrapColumnCells(dueDateColumn);

        // Direct property mapping
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));

        // Custom callbacks for table columns
        numQuestionsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProblemSet, Integer>, ObservableValue<Integer>>() {
            @Override
            public ObservableValue<Integer> call(TableColumn.CellDataFeatures<ProblemSet, Integer> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().getQuestions().size());
            }
        });

        SimpleDateFormat dateFormatter = new SimpleDateFormat();

        releaseDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProblemSet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProblemSet, String> param) {
                return new ReadOnlyObjectWrapper<>(dateFormatter.format(param.getValue().getStartTime()));
            }
        });

        dueDateColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProblemSet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProblemSet, String> param) {
                return new ReadOnlyObjectWrapper<>(dateFormatter.format(param.getValue().getEndTime()));
            }
        });

        bestAttemptColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ProblemSet, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<ProblemSet, String> param) {
                int score = manager.getBestScore(param.getValue());
                if (score == -1) {
                    return new ReadOnlyObjectWrapper<>("N/A");
                } else {
                    return new ReadOnlyObjectWrapper<>(String.valueOf(score) + "%");
                }
            }
        });

        // Handle button behaviour
        viewProblemsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get active problem set
                ProblemSet activeProblemSet = problemSetTable.getSelectionModel().getSelectedItem();
                // Change to new page if selected
                if (activeProblemSet != null) {
                    ViewProblemSetProblemsManager viewManager = new ViewProblemSetProblemsManager(activeProblemSet);
                    viewManager.showScreen(innerScreen);
                }
            }
        });

        attemptProblemsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Get active problem set
                ProblemSet activeProblemSet = problemSetTable.getSelectionModel().getSelectedItem();
                // Change to new page if selected
                if (activeProblemSet != null) {
                    CompleteProblemSetScreenManager viewManager = new CompleteProblemSetScreenManager();
                    viewManager.showScreen(innerScreen, activeProblemSet);
                }
            }
        });

        viewStatsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Navigate to statistics screen for the given problem set
                ProblemSet activeProblemSet = problemSetTable.getSelectionModel().getSelectedItem();
                if (activeProblemSet != null) {
                    ViewProblemSetStatisticsScreenManager statsViewManager = new ViewProblemSetStatisticsScreenManager(activeProblemSet);
                    statsViewManager.showScreen(innerScreen);
                }
            }
        });

        // Set the button state dependent on user permissions
        viewProblemsButton.setVisible(!manager.isUserStudent());
        attemptProblemsButton.setVisible(manager.isUserStudent());
        viewStatsButton.setVisible(!manager.isUserStudent());

        // Setup tag filters
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchField.clear();
                problemSetTable.getItems().setAll(manager.getVisibleProblemSets());
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                problemSetTable.getItems().setAll(manager.getVisibleProblemSets(searchField.getText()));
            }
        });
    }
}
