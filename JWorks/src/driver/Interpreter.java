package driver;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Hashtable;

import command.AddSimpleProblemSetCommand;
import command.Command;
import command.AddSimpleProblemCommand;
import command.ViewProblemsCommand;

import databaseAPI.DatabaseDriverAPI;
import databaseAPI.DatabaseExtractAPI;
import databaseAPI.DatabaseStoreAPI;
import io.GUIOutputGenerator;
import io.OutputGen;
import io.OutputGen.OutputMode;
import io.OutputGenerator;

/**
 * Interprets the user input and execute the execute the action
 * 
 * @author Sin
 *
 */
public class Interpreter {
  
  private static Interpreter referencedInterpreter = null;

  private AddSimpleProblemCommand addSimpleProblem;
  private ViewProblemsCommand viewProblem;
  private AddSimpleProblemSetCommand addSimpleProblemSet;

  private Command commandObject;
  private String[] parameters;
  private String command;

  private DatabaseStoreAPI databaseStore;
  private DatabaseExtractAPI databaseExtract;
  private Connection connection;

  private OutputGen outputGenerator;



  /**
   * Hashtable object that holds all the commands and their respective keys
   */
  private Hashtable<String, Command> commandList =
      new Hashtable<String, Command>();
  
  /**
   * Default constructor
   * @param mode 1 for command line mode, 2 for GUI mode.
   */

  private Interpreter(OutputMode mode) {
    // database stuff
    connection = DatabaseDriverAPI.connectOrCreateDataBase();
    DatabaseDriverAPI.initialize(connection);

    databaseStore = new DatabaseStoreAPI();
    databaseExtract = new DatabaseExtractAPI();

    // initialize output generator depending on application mode
    switch(mode) {
      case COMMAND_LINE:
        outputGenerator = new OutputGenerator();
        break;
      case GUI:
        outputGenerator = new GUIOutputGenerator();
        break;
    }

    // create all command object being used
    addSimpleProblem = new AddSimpleProblemCommand(databaseStore, outputGenerator);
    viewProblem = new ViewProblemsCommand(databaseExtract, outputGenerator);
    addSimpleProblemSet = new AddSimpleProblemSetCommand(databaseStore, outputGenerator);

    // add the commands into an array
    Command[] commands = {addSimpleProblem, viewProblem, addSimpleProblemSet};

    // add the commands to the hashtable
    for (int i = 0; i < commands.length; i++) {
      commandList.put(Integer.toString(i + 1), commands[i]);
    }
  }
  
  public static Interpreter createNewInterpreter(OutputMode mode) {
    if(referencedInterpreter == null) {
      referencedInterpreter = new Interpreter(mode);
    }
    return referencedInterpreter;
  }

  /**
   * Execute the action base on the user input
   * 
   * @param formattedInput
   */
  public void executeAction(String[] formattedInput) {
    // extract the command and parameters from the formattedInput
    command = formattedInput[0];
    parameters = Arrays.copyOfRange(formattedInput, 1, formattedInput.length);
    
    // If the command is valid
    if (commandList.containsKey(command)) {
      // Find the corresponding command and execute it
      commandObject = commandList.get(command);
      commandObject.execute(parameters);
    }
    // TODO: raise error for invalid commands
  }

  public OutputGen getOutputGenerator() {
    return outputGenerator;
  }
}
