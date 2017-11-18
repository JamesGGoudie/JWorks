package gui;

import javafx.scene.layout.Pane;
import models.Problem;

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
}
