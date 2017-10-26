package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class LoginController {
  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private Button loginButton;
  @FXML
  private Label errorMessage;

  public void initialize() {}

  public void initManager(LoginManager loginManager) {

    // action on login button
    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        String user = username.getText();
        if (user.equals("")) {
          errorMessage.setText("Please enter the username");
        } else {
          loginManager.Login(user);
        }
      }
    });

  }


}
