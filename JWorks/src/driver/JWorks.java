package driver;

import io.UI;

public class JWorks {

  private static UI ui = new UI();
  private static Interpreter interpreter = new Interpreter();
  private static UIParser parser = new UIParser();

  private static boolean exit = false;
  private static String input;
  private static String[][] formatted_input;


  public static void main(String[] args) {
    // Keep the program running
    while (!exit) {
      input = ui.userChoicePrompt();

      if (input.equals("exit")) {
        exit = true;
      } else {
         formatted_input = parser.parseInput(input);
         interpreter.executeAction(formatted_input);
      }
    }
  }
}
