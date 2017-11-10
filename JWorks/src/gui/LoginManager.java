package gui;

import driver.Interpreter;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

public class LoginManager extends Manager {
  private Scene scene;
  private Interpreter interpreter;
  private FXMLLoader loader;
  private InstructorMainScreenManager instructorMainScreenManager;

  /**
   * Default constructor
   * 
   * @param scene The scene of the screen
   * @param interpreter The interpreter for executing actions
   */
  public LoginManager(Scene scene, Interpreter interpreter) {
    this.scene = scene;
    this.interpreter = interpreter;
  }

  /**
   * Load the corresponding instructor/student screen based on the user's input
   * 
   * @param user The name of the user
   */
  public void Login(String user) {
    ShowInstructorMainScreen(user);
  }

  /**
   * Return the user to the login screen
   */
  public void logout() {
    showLoginScreen();
  }

  /**
   * Display the login screen
   */
  public void showLoginScreen() {
    // load the fxml file that contains the layout of the login screen
    loader = loadNewScreen(loader, scene, "LoginScreen.fxml");

    LoginController controller = loader.<LoginController>getController();
    controller.Start(this);
  }

  /**
   * Set the window to show the instructor main screen
   * 
   * @param user The name of the current user
   */
  private void ShowInstructorMainScreen(String user) {
    // create a new instance of instructor main screen manager
    instructorMainScreenManager = new InstructorMainScreenManager(interpreter);
    //
    loader = loadNewScreen(loader, scene, "InstructorMainScreen.fxml");
    // load the controller of the screen
    InstructorMainScreenController controller =
        loader.<InstructorMainScreenController>getController();
    // Start the controller
    controller.initSession(this, instructorMainScreenManager, user);
  }
}
