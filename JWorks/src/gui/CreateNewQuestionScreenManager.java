package gui;

import driver.Interpreter;
import javafx.scene.layout.Pane;

public class CreateNewQuestionScreenManager extends Manager{
  
  //public CreateNewQuestionScreenManager(Interpreter interpreter) {
   // setInterpreter(interpreter);
  //}
  
  public void showScreen(Pane innerPane) {
    loader = loadNewPane(loader, innerPane, "CreateNewQuestionScreen.fxml");
    CreateNewQuestionScreenController controller =
        loader.<CreateNewQuestionScreenController>getController();
    controller.start(this);
  }
  
  public void createNewQuestion(String[] inputValues) {
    interpreter.executeAction(inputValues);
  }
  
  
}
