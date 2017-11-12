package gui;

import javafx.scene.layout.Pane;

public class InstructorInnerScreenManager extends Manager {
  private InstructorInnerScreenController controller;
  private CreateNewQuestionScreenManager createNewQuestionScreenManager =
      new CreateNewQuestionScreenManager();
  private ViewAllProblemsManager viewAllProblemsManager =
      new ViewAllProblemsManager();

  /**
   * Go to the create new question screen
   * 
   * @param innerPane The pane that needs updates
   */
  public void createNewQuestion(Pane innerPane) {
    showCreateNewQuestionScreen(innerPane);
  }

  public void viewAllProblems(Pane innerPane) {
    showViewAllProblemsScreen(innerPane);
  }

  /**
   * Load and display the inner screen for instructor
   * 
   * @param innerPane
   */
  public void showScreen(Pane innerPane) {
    // load the new inner pane from fxml file
    loader = loadNewPane(loader, innerPane, "InstructorInnerScreen.fxml");
    // Load the controller and start the controller
    controller = loader.<InstructorInnerScreenController>getController();
    controller.start(this);
  }

  /**
   * Load and display the create new question screen
   * 
   * @param innerPane The pane that needs updates
   */
  private void showCreateNewQuestionScreen(Pane innerPane) {
    createNewQuestionScreenManager.showScreen(innerPane);
  }

  private void showViewAllProblemsScreen(Pane innerPane) {
    viewAllProblemsManager.showScreen(innerPane);
  }
}
