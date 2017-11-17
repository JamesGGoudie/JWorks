package gui;

import javafx.scene.layout.Pane;

public class StudentInnerScreenManager extends Manager{
  private StudentInnerScreenController controller;
  
  /**
   * Load and display the inner screen for student
   * 
   * @param innerPane
   */
  public void showScreen(Pane innerPane) {
    // load the new inner pane from fxml file
    loader = loadNewPane(loader, innerPane, "StudentInnerScreen.fxml");
    // Load the controller and start the controller
    controller = loader.<StudentInnerScreenController>getController();
    controller.start(this);
  }
}
