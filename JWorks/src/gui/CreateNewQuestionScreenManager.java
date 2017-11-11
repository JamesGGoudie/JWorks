package gui;

import javafx.scene.layout.Pane;

public class CreateNewQuestionScreenManager extends Manager {

  /**
   * Create a new question with the given parameters
   * 
   * @param inputValues The parameters for the action
   */
  public void createNewQuestion(String[] inputValues) {
    // execute the action in interpreter
    interpreter.executeAction(inputValues);
  }

  /**
   * Load and display the create new question screen
   * 
   * @param innerPane The pane that new contents are loading into
   */
  public void showScreen(Pane innerPane) {
    loader = loadNewPane(loader, innerPane, "CreateNewQuestionScreen.fxml");
    CreateNewQuestionScreenController controller =
        loader.<CreateNewQuestionScreenController>getController();
    controller.start(this);
  }
}
