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

  public void viewProblemSets(Pane innerPane) {
    ViewProblemSetScreenManager manager = new ViewProblemSetScreenManager();
    manager.showScreen(innerPane);
  }

  public int getNumUncompletedAssignments() {
    // Re-use existing code from problem set screen
    ViewProblemSetScreenManager psScreenManager = new ViewProblemSetScreenManager();
    int numUnattempted = 0;

    // Parse through filtered list and count how many have a max score of -1
    for (ProblemSet ps : psScreenManager.getVisibleProblemSets()) {
      if (psScreenManager.getBestScore(ps) == -1) {
        numUnattempted++;
      }
    }

    return numUnattempted;
  }
}
