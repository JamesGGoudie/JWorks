package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import driver.Interpreter;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class InstructorInnerScreenManager {

  public void createNewQuestion(Pane innerPane, Interpreter interpreter) {
    showCreateNewQuestionScreen(innerPane, interpreter);
  }
  
  private void showCreateNewQuestionScreen(Pane innerPane, Interpreter interpreter) {
    try {
      // load the new inner pane from the new question screen fxml file
      FXMLLoader loader = new FXMLLoader(
          getClass().getResource("CreateNewQuestionScreen.fxml"));
      // clear the inner pane
      innerPane.getChildren().clear();
      // Add the new pane with question input to the main screen
      innerPane.getChildren().add(loader.load());
      CreateNewQuestionScreenController controller =
          loader.<CreateNewQuestionScreenController>getController();
      controller.initSession(innerPane, interpreter);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }
}
