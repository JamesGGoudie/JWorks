package gui;

import javafx.scene.layout.Pane;

public class CreateNewStudentAccountManager extends Manager{
  private CreateNewStudentAccountController controller;
  
  public void showScreen(Pane innerPane) {
    // load the new inner pane from fxml file
    loader = loadNewPane(loader, innerPane, "CreateNewStudentAccount.fxml");
    // Load the controller and start the controller
    controller = loader.<CreateNewStudentAccountController>getController();
    controller.start(this);
  }

}
