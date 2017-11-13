package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Problem;
import models.SimpleProblemSet;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    private TextField maxAttemptsField;

    @FXML
    private Label errorLabel;

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

        saveProblemSetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // Refuse if any fields are empty
                if (problemSetNameField.getText().length() == 0
                        || maxAttemptsField.getText().length() == 0
                        || releaseDateField.getValue() == null
                        || dueDateField.getValue() == null) {
                    errorLabel.setText("Please ensure all fields are filled in.");
                } else if (problemList.isEmpty()) {
                    errorLabel.setText("Please select at least 1 problem.");
                } else {
                    saveProblemSet();
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

    private void saveProblemSet() {
        // Reset error
        errorLabel.setText("");

        // Check for valid options
        String problemSetName = problemSetNameField.getText();
        String maxAttemptsText = maxAttemptsField.getText();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate;
        Date endDate;
        int maxAttempts;


        try {
            startDate = dateFormat.parse(releaseDateField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
            endDate = dateFormat.parse(dueDateField.getValue().format(DateTimeFormatter.ISO_LOCAL_DATE));
            maxAttempts = Integer.parseInt(maxAttemptsText);
        } catch (ParseException e) {
            errorLabel.setText("Please ensure the dates are entered correctly.");
            return;
        } catch (NumberFormatException e) {
            errorLabel.setText("Please ensure the max attempts field is strictly numeric.");
            maxAttemptsField.clear();
            maxAttemptsField.requestFocus();
            return;
        }

        if (startDate.after(endDate)) {
            errorLabel.setText("Release date must be before due date.");
            return;
        }

        // Create the problem set object if possible
        SimpleProblemSet problemSet = new SimpleProblemSet(problemList);
        problemSet.setStartTime(startDate);
        problemSet.setEndTime(endDate);
        problemSet.setMaxAttempts(maxAttempts);

        List<Problem> problems = new ArrayList<>(problemList);

        for (Problem p : problems) {
            problemSet.addProblem(p);
        }

        manager.addProblemSet(problemSet);
    }
}
