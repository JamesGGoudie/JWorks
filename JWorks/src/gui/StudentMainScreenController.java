package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class StudentMainScreenController extends Controller{
  @FXML
  private Button logoutButton;
  @FXML
  private Label welcomeLabel;
  @FXML
  private Pane innerScreen;
  @FXML
  private Button homeButton;

  private LoginManager loginManager;
  
  /**
   * Set the user name label on the main screen
   * 
   * @param user The user name
   */
  public void setUserName(String user) {
    welcomeLabel.setText("Welcome user " + user);
  }
  
  /**
   * Set the manager for login actions
   * 
   * @param loginManager
   */
  public void setLoginManager(LoginManager loginManager) {
    this.loginManager = loginManager;
  }
  
  /**
   * Start the handling of actions on screen
   * 
   * @param StudentMainScreenManager Manager that handles screen updates
   */
  public void start(StudentMainScreenManager studentMainScreenManager) {
    studentMainScreenManager.home(innerScreen);
    
    // logout button action
    logoutButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // load the logout screen
        loginManager.logout();
      }
    });
    
    // home button action
    homeButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // load the home screen
        studentMainScreenManager.home(innerScreen);
      }
    });
  }
}
