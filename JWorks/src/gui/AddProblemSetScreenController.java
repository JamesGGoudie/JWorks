package gui;

import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddProblemSetScreenController extends Controller {

    @FXML
    private TableView<Problem> problemListTable;

    @FXML
    private TableColumn<Problem, Integer> problemListColumn;

    @FXML
    private TextField problemSetNameField;

    @FXML
    private DatePicker releaseDateField;

    @FXML
    private DatePicker dueDateField;

    @FXML
    private Button saveProblemSetButton;

    @FXML
    private ViewProblemsController problemTableViewController;

    private AddProblemSetScreenManager manager;

    private List<Problem> problemList;

    public void start(AddProblemSetScreenManager manager) {
        this.manager = manager;

        // Setup nested screen
        problemTableViewController.start(new ViewAllProblemsManager());

        problemTableViewController.questionTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Double click to add to problem set
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Problem selected = problemTableViewController.questionTable.getSelectionModel().getSelectedItem();
                    addProblemToModel(selected);
                }
            }
        });

    }

    @Override
    public void initialize() {
        super.initialize();

        // Initialize problem set UI components
        problemList = new ArrayList<>();
        problemListColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        problemListTable.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // Double click to remove from problem set
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    Problem selected = problemListTable.getSelectionModel().getSelectedItem();
                    removeProblemFromModel(selected);
                }
            }
        });

        updateTableList();
    }

    /**
     * Adds the given Problem to the Problem set list. Does nothing if the element already exists.
     * @param problem the Problem object to add
     */
    public void addProblemToModel(Problem problem) {
        if (!problemList.contains(problem)) {
            problemList.add(problem);
            updateTableList();
        }
    }

    /**
     * Removes the given Problem from the Problem Set list.
     * @param problem the Problem to remove.
     */
    public void removeProblemFromModel(Problem problem) {
        if (problemList.contains(problem)) {
            problemList.remove(problem);
            updateTableList();
        }
    }

    private void updateTableList() {
        problemListTable.getItems().setAll(problemList);
    }
}
