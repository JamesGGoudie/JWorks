package gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Problem;

import java.util.List;

public class ViewProblemsController extends Controller {
    @FXML
    private TableView<Problem> questionTable;

    @FXML
    private TableColumn<Problem, Integer> idColumn;

    @FXML
    private TableColumn<Problem, String> questionColumn;

    @FXML
    private TableColumn<Problem, String> answerColumn;

    private ViewProblemsManager manager;

    /**
     *
     * @param manager The scene manager to use for this UI controller
     */
    public void start(ViewProblemsManager manager) {
        this.manager = manager;
        questionTable.getItems().setAll(getProblems());
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
    private List<Problem> getProblems() {
        return manager.getProblems();
    }
}
