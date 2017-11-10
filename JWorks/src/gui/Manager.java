package gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Manager {
  
  /**
   * Load a new layout to the screen
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
  
  protected FXMLLoader loadNewPane() {
    return null;
  }
}
