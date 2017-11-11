package gui;

import driver.Interpreter;
import javafx.scene.layout.Pane;

public class InstructorInnerScreenManager extends Manager {
  private InstructorInnerScreenController controller;

  private CreateNewQuestionScreenManager createNewQuestionScreenManager =
      new CreateNewQuestionScreenManager();


  //public InstructorInnerScreenManager(Interpreter interpreter) {
  //  setInterpreter(interpreter);
  //}

  public void showScreen(Pane innerPane) {
    // load the new inner pane from fxml file
    loader = loadNewPane(loader, innerPane, "InstructorInnerScreen.fxml");
    // Load the controller and start the controller
    controller = loader.<InstructorInnerScreenController>getController();
    controller.start(this);
  }

  public void createNewQuestion(Pane innerPane) {
    showCreateNewQuestionScreen(innerPane);
  }

  private void showCreateNewQuestionScreen(Pane innerPane) {
    createNewQuestionScreenManager.showScreen(innerPane);
  }
}
