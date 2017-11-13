package gui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import models.Problem;

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

    public void start(AddProblemSetScreenManager manager) {
        this.manager = manager;
        problemTableViewController.start(new ViewAllProblemsManager());
    }

    @Override
    public void initialize() {
        super.initialize();
    }
}
