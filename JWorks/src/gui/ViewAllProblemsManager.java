package gui;

import javafx.scene.layout.Pane;
import models.Problem;
import models.SingleAnswerProblem;

import java.util.ArrayList;
import java.util.List;


public class ViewAllProblemsManager extends ViewProblemsManager {

    /**
     * Retrieves a list of all available problems to display on the GUI.
     * @return a list of all available Problem objects
     */
    public List<Problem> getProblems() {
        String[] args = {"ViewProblemsCommand"};
        interpreter.executeAction(args);
        return (List<Problem>) interpreter.getOutputGenerator().getLastResult();
    }
}
