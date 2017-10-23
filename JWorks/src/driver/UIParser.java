package driver;

import java.util.ArrayList;
import java.util.Arrays;

public class UIParser {
  private String[] formatted_input;

  /**
   * Parse the user input into a format that can be used by the actions
   * 
   * @param input The user's input
   * @return formatted_input A string array in the form of [action,
   *         parameters...]
   */
  public String[] parseInput(String input) {

    ArrayList<String> temp_formatted_input = new ArrayList<String>();

    // add the action from the string to the formatted input
    temp_formatted_input.add(input.substring(0, input.indexOf(' ')));
    // add the parameters to the formatted input
    temp_formatted_input.addAll(
        Arrays.asList(formatParameters(input.substring(input.indexOf(' ')))));

    formatted_input = new String[temp_formatted_input.size()];
    return temp_formatted_input.toArray(formatted_input);
  }

  /**
   * 
   * @param parameters The parameters input by the user
   * @return formatted_parameters The formatted parameters
   */
  private String[] formatParameters(String parameters) {
    ArrayList<String> param_list = new ArrayList<String>();

    // check for any string parameter in the input
    int stringBegin = parameters.indexOf('"');
    int stringEnd = parameters.lastIndexOf('"');


    if (stringBegin != stringEnd) {
      // take whatever is in the warper as the first parameter
      param_list.add(parameters.substring(stringBegin + 1, stringEnd));
      // remove the first parameter from parameters
      parameters = parameters.substring(stringEnd);
    }

    // split all the parameters and add them to the list
    param_list.addAll(Arrays.asList(parameters.split(" ", 0)));

    // return the parameters as a string array
    String[] formatted_parameters = new String[param_list.size()];
    return param_list.toArray(formatted_parameters);
  }


}
