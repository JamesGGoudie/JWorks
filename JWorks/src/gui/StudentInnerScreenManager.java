package gui;

import javafx.scene.layout.Pane;
import models.ProblemSet;
import models.Student;

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

  /**
   * Creates a new instance of the complete problem set screen, with the given problem set.
   * @param innerPane The inner screen to load onto
   * @param problemSet the problem set to attempt
   */
  public void attemptProblemSet(Pane innerPane, ProblemSet problemSet) {
    CompleteProblemSetScreenManager manager = new CompleteProblemSetScreenManager();
    manager.showScreen(innerPane, problemSet);
  }

  public void viewProblemSets(Pane innerPane) {
    ViewProblemSetScreenManager manager = new ViewProblemSetScreenManager();
    manager.showScreen(innerPane);
  }
}
