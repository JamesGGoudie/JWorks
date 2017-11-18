package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController extends Controller {
  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private Button loginButton;
  @FXML
  private Label errorMessage;

  /**
   * Start the handling of actions on screen
   * 
   * @param loginManager The manager that handles screen updates on login
   */
  public void start(LoginManager loginManager) {

    // action on login button
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        // get the uesr name from the user name tex field
        String user = username.getText();
        String pw = password.getText();
        // check for any empty field
        if (user.equals("") || pw.equals("")) {
          errorMessage.setText("Please enter all required fields");
        } else {
          // Show validation error and clear fields if login failed
          if (!loginManager.Login(user, pw)) {
            errorMessage.setText("Incorrect username or password");
            username.clear();
            password.clear();
          };
        }
      }
    });
  }
}
