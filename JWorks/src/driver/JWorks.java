package driver;

import io.OutputGen;
import io.UI;

public class JWorks {

  private static UI ui = new UI();
  private static Interpreter interpreter = new Interpreter(OutputGen.OutputMode.COMMAND_LINE);
  private static UIParser parser = new UIParser();

  private static boolean exit = false;
  private static String input;
  private static String[] parameters;


  public static void main(String[] args) {
    // Keep the program running
    while (!exit) {
      input = ui.userChoicePrompt();

      if (input.trim().equals("exit")) {
        exit = true;
      } else {
        parameters = parser.parseInput(input.trim());
        interpreter.executeAction(parameters);
      }
    }
  }
}
