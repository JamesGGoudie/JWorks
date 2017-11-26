package gui;

import javafx.scene.layout.Pane;
import models.Problem;

import java.util.ArrayList;
import java.util.List;

public abstract class ViewProblemsManager extends Manager {
    /**
     * Load and display the screen to view all problems.
     *
     * @param innerPane The pane that new contents are loading into
     */
    public void showScreen(Pane innerPane) {
        loader = loadNewPane(loader, innerPane, "ViewAllProblemsScreen.fxml");
        ViewProblemsController controller =
                loader.getController();
        controller.start(this);
    }

    public abstract List<Problem> getProblems();

    /**
     * Gets the problems that match the given tag string. Tags are separated by spaces, and are not case sensitive.
     * @param tags a space separated list of tags
     * @return all problems that match the tag string
     */
    public List<Problem> getProblems(String tags) {
        List<Problem> unfilteredProblems = getProblems();
        List<Problem> filteredProblems = new ArrayList<>();

        for (Problem problem : unfilteredProblems) {
            if (problem.matchesSearchString(tags)) {
                filteredProblems.add(problem);
            }
        }

        return filteredProblems;
    }
}
