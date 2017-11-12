package io;

import javafx.scene.control.Alert;
import models.Problem;
import models.ProblemSet;

import java.util.List;

public class GUIOutputGenerator implements OutputGen {

    private Object lastResult;
    /**
     * Output a String out onto the UI by creating an alert dialog.
     *
     * @param out
     */
    @Override
    public void output(String out) {
        showInfoAlert(out);
        lastResult = out;
    }

    /**
     * Output a Problem object onto the UI
     *
     * @param problem the Problem object to output
     */
    @Override
    public void output(Problem problem) {
        lastResult = problem;
    }

    /**
     * Output a Problem Set object onto the UI
     *
     * @param problemSet the Problem Set object to output
     */
    @Override
    public void output(ProblemSet problemSet) {
        lastResult = problemSet;
    }

    /**
     * Outputs a ArrayList of problems/problem sets.
     *
     * @param problemSet an ArrayList of problems in a problem set.
     */
    @Override
    public void problemSetOutput(List<String[]> problemSet) {
        lastResult = problemSet;
    }

    /**
     * Returns the last Object that was sent to this OutputGenerator.
     *
     * @return the last Object that was sent to this OutputGenerator
     */
    @Override
    public Object getLastResult() {
        return lastResult;
    }

    private void showInfoAlert(String description) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(description);
        alert.show();
    }
}
