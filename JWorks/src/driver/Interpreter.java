package driver;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Hashtable;

import command.Command;
import command.AddSimpleProblemCommand;
import command.LoginCommand;
import command.ViewProblemsCommand;

import databaseAPI.DatabaseDriverAPI;
import databaseAPI.DatabaseExtractAPI;
import databaseAPI.DatabaseStoreAPI;
import io.OutputGen;
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
  private LoginCommand login;

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
   */
  private Interpreter() {
    // database stuff
    connection = DatabaseDriverAPI.connectOrCreateDataBase();
    DatabaseDriverAPI.initialize(connection);

    databaseStore = new DatabaseStoreAPI();
    databaseExtract = new DatabaseExtractAPI();

    // initialize output generator
    outputGenerator = new OutputGenerator();

    // create all command object being used
    addSimpleProblem = new AddSimpleProblemCommand(databaseStore, outputGenerator);
    viewProblem = new ViewProblemsCommand(databaseExtract, outputGenerator);
    login = new LoginCommand(databaseExtract, outputGenerator);

    // add the commands into an array
    Command[] commands = {addSimpleProblem, viewProblem, login};

    // add the commands to the hashtable
    for (int i = 0; i < commands.length; i++) {
      commandList.put(Integer.toString(i + 1), commands[i]);
    }
  }
  
  public static Interpreter createNewInterpreter() {
    if(referencedInterpreter == null) {
      referencedInterpreter = new Interpreter();
    }
    return referencedInterpreter;
  }

  /**
   * Execute the action base on the user input
   * 
   * @param formattedInput
   * @return Whether or not the command successfully executed
   */
  public boolean executeAction(String[] formattedInput) {
    // extract the command and parameters from the formattedInput
    command = formattedInput[0];
    parameters = Arrays.copyOfRange(formattedInput, 1, formattedInput.length);
    
    // If the command is valid
    if (commandList.containsKey(command)) {
      // Find the corresponding command and execute it
      commandObject = commandList.get(command);
      return commandObject.execute(parameters);
    }
    // TODO: raise error for invalid commands
    return false;
  }
}
