package gui;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class InstructorMainScreenManager extends Manager {
  private Scene scene;
  private InstructorMainScreenController controller;
  private InstructorInnerScreenManager instructorInnerScreenManager =
      new InstructorInnerScreenManager();

  /**
   * Default constructor
   * 
   * @param scene
   */
  public InstructorMainScreenManager(Scene scene) {
    this.scene = scene;
  }

  /**
   * Go to home screen
   * 
   * @param innerPane The pane that needs update
   */
  public void home(Pane innerPane) {
    showHomeScreen(innerPane);
  }

  /**
   * Load and display the main screen for instructor
   * 
   * @param loginManager
   * @param user The user name
   */
  public void showScreen(LoginManager loginManager, String user) {
    loader = loadNewScreen(loader, scene, "InstructorMainScreen.fxml");
    // load the controller of the screen
    controller = loader.<InstructorMainScreenController>getController();
    // set the user name for
    controller.setUserName(user);
    controller.setLoginManager(loginManager);
    // Start the controller
    controller.start(this);
  }

  /**
   * Load and display the home screen for instructor
   * 
   * @param innerPane The pane that needs update
   */
  private void showHomeScreen(Pane innerPane) {
    instructorInnerScreenManager.showScreen(innerPane);
  }


}
