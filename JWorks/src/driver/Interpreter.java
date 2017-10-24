package driver;

import java.sql.Connection;
import java.util.Arrays;
import java.util.Hashtable;

import command.ICommand;
import command.AddSimpleProblemCommand;
import command.ViewProblemsCommand;

import databaseAPI.DatabaseDriverAPI;
import databaseAPI.DatabaseStoreAPI;

/**
 * Interprets the user input and execute the execute the action
 * 
 * @author Sin
 *
 */
public class Interpreter {

  private AddSimpleProblemCommand addSimpleProblem;
  private ViewProblemsCommand viewProblem;

  private ICommand commandObject;
  private String[] parameters;
  private String command;

  private DatabaseStoreAPI database;
  private Connection connection;



  /**
   * Hashtable object that holds all the commands and their respective keys
   */
  private Hashtable<String, ICommand> commandList =
      new Hashtable<String, ICommand>();

  /**
   * Default constructor
   */
  public Interpreter() {
    // create all command object being used
    addSimpleProblem = new AddSimpleProblemCommand();
    viewProblem = new ViewProblemsCommand();

    // add the commands into an array
    ICommand[] commands = {addSimpleProblem, viewProblem};

    // add the commands to the hashtable
    for (int i = 0; i < commands.length; i++) {
      commandList.put(Integer.toString(i + 1), commands[i]);
    }

    // database stuff
    connection = DatabaseDriverAPI.connectOrCreateDataBase();
    DatabaseDriverAPI.initialize(connection);

    database = new DatabaseStoreAPI();
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
}
