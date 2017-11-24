package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import models.*;

import java.util.ArrayList;
import java.util.List;

public class StudentInnerScreenController extends Controller {
  @FXML
  private Button doProblemSetButton;
  @FXML
  private Button viewProblemSetsButton;
  @FXML
  private Pane innerScreen;

  public void start(StudentInnerScreenManager studentInnerScreenManager) {
    doProblemSetButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        // TODO: Replace fake problem set
        List<Problem> problemList = new ArrayList<>();
        Problem p1 = new SingleAnswerProblem("Given 2+2=x; what is x-1?", "3");
        p1.setId(1);
        Problem p2 = new SingleAnswerProblem("What is?", "42");
        p2.setId(2);
        Problem p3 = new SingleAnswerProblem("This is mock data", "just so you know");
        p3.setId(3);

        problemList.add(p1);
        problemList.add(p2);
        problemList.add(p3);

        ProblemSet problemSet = new SimpleProblemSet(problemList);

        // Do the problem set
        studentInnerScreenManager.attemptProblemSet(innerScreen, problemSet);
      }
    });

    viewProblemSetsButton.setOnAction(new EventHandler<ActionEvent>() {
      @Override
      public void handle(ActionEvent event) {
        studentInnerScreenManager.viewProblemSets(innerScreen);
      }
    });

  }
}
