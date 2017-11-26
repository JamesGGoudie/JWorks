package gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.Problem;

import java.util.List;

public class ViewProblemsController extends Controller {
    @FXML
    public TableView<Problem> questionTable;

    @FXML
    private TableColumn<Problem, Integer> idColumn;

    @FXML
    private TableColumn<Problem, String> questionColumn;

    @FXML
    private TableColumn<Problem, String> answerColumn;

    @FXML
    private TextField filterField;

    @FXML
    private Button filterButton;

    @FXML
    private Button clearButton;

    @FXML
    private TableColumn<Problem, String> tagsColumn;

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
        wrapColumnCells(tagsColumn);

        tagsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Problem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Problem, String> param) {
                String tagString = "";
                List<String> tags = param.getValue().getTags();

                for (String tag : tags) {
                    tagString = tagString + tag + "; ";
                }

                return new ReadOnlyObjectWrapper<>(tagString);
            }
        });

        // Setup tag filters
        clearButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                filterField.clear();
                questionTable.getItems().setAll(manager.getProblems());
            }
        });

        filterButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                questionTable.getItems().setAll(manager.getProblems(filterField.getText()));
            }
        });
    }

    private void wrapColumnCells(TableColumn<Problem, String> column) {
        column.setCellFactory(new Callback<TableColumn<Problem, String>, TableCell<Problem, String>>() {
            @Override
            public TableCell<Problem, String> call(TableColumn<Problem, String> param) {
                TableCell<Problem, String> cell = new TableCell<>();
                Text value = new Text();
                cell.setGraphic(value);
                value.wrappingWidthProperty().bind(cell.widthProperty());
                value.textProperty().bind(cell.itemProperty());
                return cell;
            }
        });
    }
}
