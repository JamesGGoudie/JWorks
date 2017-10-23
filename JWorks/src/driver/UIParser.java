package driver;

/**
 * Parse user input strings into readable parameters for the Interpreter to
 * execute commands
 * 
 * @author sin
 */

import java.util.ArrayList;
import java.util.Arrays;

public class UIParser {
  private String[] formattedInput;

  /**
   * Parse the user input into a format that can be used by the actions
   * 
   * @param input The user's input
   * @return formatted_input A string array in the form of [action,
   *         parameters...]
   */
  public String[] parseInput(String input) {
    // if the action has no parameters,, just return the action
    if (input.length() == 1) {
      return new String[] {input};
    } else {

      ArrayList<String> tempFormattedInput = new ArrayList<String>();
      String[] parameters;
      // add the action from the string to the formatted input
      tempFormattedInput.add(input.substring(0, input.indexOf(' ')));

      parameters = formatParameters(input.substring(input.indexOf(' ')));

      // add the parameters to the formatted input
      for (int i = 0; i < parameters.length; i++) {
        tempFormattedInput.add(parameters[i]);
      }


      formattedInput = new String[tempFormattedInput.size()];
      return tempFormattedInput.toArray(formattedInput);
    }
  }

  /**
   * 
   * @param parameters The parameters input by the user
   * @return formatted_parameters The formatted parameters
   */
  private String[] formatParameters(String parameters) {
    ArrayList<String> paramList = new ArrayList<String>();

    // check for any string parameter in the input
    int stringBegin = parameters.indexOf('"');
    int stringEnd = parameters.lastIndexOf('"');


    if (stringBegin != stringEnd) {
      String stringParam = parameters.substring(stringBegin + 1, stringEnd);
      // take whatever is in the warper as the first parameter
      paramList.add(stringParam);
      if (parameters.length() > stringParam.length() + 3) {
        // remove the first parameter from parameters
        parameters = parameters.substring(stringEnd + 2);
      }
    }

    // split all the parameters and add them to the list
    paramList.addAll(Arrays.asList(parameters.split(" ", 0)));

    // return the parameters as a string array
    String[] formatted_parameters = new String[paramList.size()];
    return paramList.toArray(formatted_parameters);
  }


}
