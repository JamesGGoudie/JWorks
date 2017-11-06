package gui;

import driver.Interpreter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class CreateNewQuestionScreenController {
  @FXML
  private Button submitButton;
  @FXML
  private TextField questionInput;
  @FXML
  private TextField answerInput;
  @FXML
  private Label questionError;
  @FXML
  private Label answerError;


  private final String COMMAND = "1";
  private String[] inputValues;

  public void initialize() {}

  public void initSession(Pane innerPane, Interpreter interpreter) {

    // when the submitButton is clicked
    submitButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // check if any text field is empty
        if (questionInput.getText().equals("")
            || answerInput.getText().equals("")) {
          // set an error message to the user
          if (questionInput.getText().equals("")) {
            questionError.setText("Please enter the question");
          }
          if (answerInput.getText().equals("")) {
            answerError.setText("Please enter the answer");
          }

        } else {
          // set the input values for the interpreter
          inputValues = new String[] {COMMAND, questionInput.getText(),
              answerInput.getText()};

          // execute the action
          interpreter.executeAction(inputValues);
          
          // reset the input fields
          questionInput.setText("");
          answerInput.setText("");
        }
      }
    });
  }
}
