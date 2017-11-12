package gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Problem;

import java.util.List;

public class ViewAllProblemsController extends Controller {
    @FXML
    private TableView<Problem> questionTable;

    @FXML
    private TableColumn<Problem, Integer> idColumn;

    @FXML
    private TableColumn<Problem, String> questionColumn;

    @FXML
    private TableColumn<Problem, String> answerColumn;

    private ViewAllProblemsManager manager;

    /**
     *
     * @param manager The scene manager to use for this UI controller
     */
    public void start(ViewAllProblemsManager manager) {
        this.manager = manager;
        questionTable.getItems().setAll(getAllProblems());
    }

    @Override
    public void initialize() {
        super.initialize();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("problem"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));
    }

    /**
     * Calls the Manager to get all Problems.
     * @return a list of all Problems
     */
    private List<Problem> getAllProblems() {
        return manager.getAllProblems();
    }
}
