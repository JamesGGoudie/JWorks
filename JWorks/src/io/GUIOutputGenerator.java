package io;

import javafx.scene.control.Alert;
import models.Problem;
import models.ProblemSet;

import java.util.List;

public class GUIOutputGenerator implements OutputGen {
    /**
     * Output a String out onto the UI by creating an alert dialog.
     *
     * @param out
     */
    @Override
    public void output(String out) {
        showInfoAlert(out);
    }

    /**
     * Output a Problem object onto the UI
     *
     * @param problem the Problem object to output
     */
    @Override
    public void output(Problem problem) {

    }

    /**
     * Output a Problem Set object onto the UI
     *
     * @param problemSet the Problem Set object to output
     */
    @Override
    public void output(ProblemSet problemSet) {

    }

    /**
     * Outputs a ArrayList of problems/problem sets.
     *
     * @param problemSet an ArrayList of problems in a problem set.
     */
    @Override
    public void problemSetOutput(List<String[]> problemSet) {

    }

    private void showInfoAlert(String description) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(description);
        alert.show();
    }
}
