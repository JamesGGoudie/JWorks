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
        // Mock implementation
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
        return problemList;
        /*
        Actual implementation - once API is complete
        String[] args = {"2"};
        interpreter.executeAction(args);
        return (List<Problem>) interpreter.getOutputGenerator().getLastResult();
         */
    }
}
