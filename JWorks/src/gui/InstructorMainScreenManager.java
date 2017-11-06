package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import driver.Interpreter;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class InstructorMainScreenManager {
  private InstructorInnerScreenController controller;
  private Interpreter interpreter;
  
  public InstructorMainScreenManager(Interpreter interpreter) {
    this.interpreter = interpreter;
  }

  public void homeScreen(Pane innerPane) {
    try {
      // load the new inner pane from fxml file
      FXMLLoader loader =
          new FXMLLoader(getClass().getResource("InstructorInnerScreen.fxml"));
      // replace the inner pane with the fxml file content
      innerPane.getChildren().clear();
      innerPane.getChildren().add(loader.load());
      // Load the controller and start the controller
      controller =
          loader.<InstructorInnerScreenController>getController();
      controller.initSession(this, interpreter);
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null,
          ex);
    }
  }

}
