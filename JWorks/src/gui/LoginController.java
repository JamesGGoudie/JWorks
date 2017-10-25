package gui;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class LoginController {
  @FXML
  private TextField username;
  @FXML
  private TextField password;
  @FXML
  private Button loginButton;

  public void initialize() {}

  public void initManager(LoginManager loginManager) {

    loginButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent arg0) {
        String user = username.getText();
        loginManager.Login(user);
      }
    });

  }


}
