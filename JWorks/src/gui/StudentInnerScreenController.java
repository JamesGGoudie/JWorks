package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class StudentInnerScreenController extends Controller {
  @FXML
  private Button viewProblemSetsButton;
  @FXML
  private Pane innerScreen;
  @FXML
  private Label unattemptedLabel;

  public void start(StudentInnerScreenManager studentInnerScreenManager) {

    viewProblemSetsButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        studentInnerScreenManager.viewProblemSets(innerScreen);
      }
    });

    unattemptedLabel.setText(String.valueOf(studentInnerScreenManager.getNumUncompletedAssignments()));
  }
}
