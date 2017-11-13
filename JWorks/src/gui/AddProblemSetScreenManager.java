package gui;

import javafx.scene.layout.Pane;

public class AddProblemSetScreenManager extends Manager {
    /**
     * Load and display the screen to view all problems.
     *
     * @param innerPane The pane that new contents are loading into
     */
    public void showScreen(Pane innerPane) {
        loader = loadNewPane(loader, innerPane, "AddProblemSetScreen.fxml");
        AddProblemSetScreenController controller =
                loader.getController();
        controller.start(this);
    }
}
