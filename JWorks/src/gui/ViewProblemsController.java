package gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.Problem;

import java.util.List;

public class ViewProblemsController extends WrappableViewController<Problem> {
    @FXML
    public TableView<Problem> questionTable;

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
        questionTable.getItems().setAll(manager.getProblems());
    }

    @Override
    public void initialize() {
        super.initialize();
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        questionColumn.setCellValueFactory(new PropertyValueFactory<>("problem"));
        answerColumn.setCellValueFactory(new PropertyValueFactory<>("answer"));

        // Wrap text
        wrapColumnCells(questionColumn);
        wrapColumnCells(answerColumn);

        // Setup tag filters
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                searchField.clear();
                questionTable.getItems().setAll(manager.getProblems());
            }
        });

        searchButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                questionTable.getItems().setAll(manager.getProblems(searchField.getText()));
            }
        });
    }
}
