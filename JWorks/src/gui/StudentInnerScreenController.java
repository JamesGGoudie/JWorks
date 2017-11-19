package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class StudentInnerScreenController extends Controller{
  @FXML
  private Pane innerScreen;

  @FXML
  private Button viewProblemSetsButton;

  public void start(StudentInnerScreenManager studentInnerScreenManager) {
    viewProblemSetsButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        studentInnerScreenManager.viewProblemSets(innerScreen);
      }
    });
  }

}
