package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import driver.Interpreter;
import io.OutputGen;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class Manager {
  
  protected static Interpreter interpreter = Interpreter.createNewInterpreter(OutputGen.OutputMode.GUI);
  protected FXMLLoader loader;

  /**
   * Load a new layout to the screen
   * 
   * @param loader
   * @param scene
   * @param fxmlFile The fxml file name that contains the new layout
   * @return FXMLLoader object for controller uses
   */
  protected FXMLLoader loadNewScreen(FXMLLoader loader, Scene scene,
      String fxmlFile) {

    // get the layout of the screen from the fxml file
    loader = new FXMLLoader(getClass().getResource(fxmlFile));

    // load the new screen to the window(scene)
    try {
      scene.setRoot((Parent) loader.load());
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null,
          ex);
    }
    return loader;
  }

  /**
   * 
   * @param loader
   * @param innerPane
   * @param fxmlFile
   * @return
   */
  protected FXMLLoader loadNewPane(FXMLLoader loader, Pane innerPane,
      String fxmlFile) {
    
    loader = new FXMLLoader(getClass().getResource(fxmlFile));
    
    // replace the inner pane with the fxml file content
    innerPane.getChildren().clear();
    try {
      innerPane.getChildren().add(loader.load());
    } catch (IOException ex) {
      Logger.getLogger(LoginManager.class.getName()).log(Level.SEVERE, null,
          ex);
    }
    
    return loader;
  }
  
}
