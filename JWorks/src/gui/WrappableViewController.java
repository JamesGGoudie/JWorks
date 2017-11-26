package gui;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;
import models.Taggable;

import java.util.List;

public class WrappableViewController<T extends Taggable> extends Controller{

    @FXML
    protected TextField searchField;

    @FXML
    protected Button searchButton;

    @FXML
    protected Button clearButton;

    @FXML
    protected TableColumn<T, String> tagsColumn;

    @Override
    public void initialize() {
        super.initialize();

        tagsColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<T, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<T, String> param) {
                String tagString = "";
                List<String> tags = param.getValue().getTags();

                for (String tag : tags) {
                    tagString = tagString + tag + "; ";
                }

                return new ReadOnlyObjectWrapper<>(tagString);
            }
        });

        wrapColumnCells(tagsColumn);
    }

    /**
     * Sets the cell factory for the given string column such that the text is wrapped.
     * @param column the column to wrap
     */
    protected void wrapColumnCells(TableColumn<T, String> column) {
        column.setCellFactory(new Callback<TableColumn<T, String>, TableCell<T, String>>() {
            @Override
            public TableCell<T, String> call(TableColumn<T, String> param) {
                TableCell<T, String> cell = new TableCell<>();
                Text value = new Text();
                cell.setGraphic(value);
                value.wrappingWidthProperty().bind(cell.widthProperty());
                value.textProperty().bind(cell.itemProperty());
                return cell;
            }
        });
    }
}
