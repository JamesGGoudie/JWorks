package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class InstructorInnerScreenController {
  @FXML
  private Button createNewQuestionButton;
  @FXML
  private Button viewQuestionsButton;
  @FXML
  private Pane innerScreen;
  
  private InstructorInnerScreenManager innerScreenManager = new InstructorInnerScreenManager();

  public void initialize() {}

  public void initSession(
      InstructorMainScreenManager instructorMainScreenManager) {

    createNewQuestionButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        innerScreenManager.createNewQuestion(innerScreen);
      }
    });

  }
}
