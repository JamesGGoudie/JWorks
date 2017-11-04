package database;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ConnectionFailedException;
import exceptions.DatabaseInsertException;
import exceptions.DatabaseSelectException;

public class Main {

  public static void main(String[] args) {
    
    File file = new File("jworks.db");
    file.delete();
    
    Connection connection = DatabaseDriver.connectOrCreateDatabase();
    
    try {
      DatabaseDriver.initialize(connection);
      
      int num = 10;
      
      try {
        int problemSetNum = -1;
        int questionNum = -1;

        questionNum = DatabaseInserter.insertProblem(2, "Q1", "A1", connection);
        
        
        int problemIDs[] = {7, 5, 12};

        DatabaseInserter.insertStudent(num, "James", "gmail", connection);
        DatabaseInserter.insertStudent(2 * num, "James", "gmail", connection);
        problemSetNum = DatabaseInserter.insertProblemSet(101, problemIDs, connection);
        problemSetNum = DatabaseInserter.insertProblemSet(102, problemIDs, connection);
        problemSetNum = DatabaseInserter.insertProblemSet(103, problemIDs, connection);
        // DatabaseUpdater.updateAttemptsRemaining(problemSetNum, num, 10, connection);
        
        
        
        
        try {
          System.out.println("Attempts_Remaining: " + DatabaseSelector.getAttemptsRemaining(num, 1, connection));
          System.out.println("Deleted Problem Set: " + DatabaseDeleter.deleteProblemSet(2, connection));
          System.out.println("Attempts_Remaining: " + DatabaseSelector.getAttemptsRemaining(num, 1, connection));
        } catch (DatabaseSelectException e2) {
          // TODO Auto-generated catch block
          e2.printStackTrace();
        }
        
        // System.out.println("ProblemSetNum:" + problemSetNum);
        
        
        try {
          ResultSet results = DatabaseSelector.getStudent(num, connection);
          results.next();
          int outputNum = results.getInt(1);
          // System.out.println(outputNum);

          results.close();
        } catch (DatabaseSelectException e1) {
          // TODO Auto-generated catch block
          e1.printStackTrace();
        } catch (SQLException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        /*
        try {
           DatabaseSelector.getAttemptsRemaining(num, problemSetNum, connection);
           // DatabaseDeleter.deleteProblemSet(problemSetNum, connection);
          // DatabaseDeleter.deleteStudent(num, connection);
           DatabaseSelector.getAttemptsRemaining(num, problemSetNum, connection);
        } catch (DatabaseSelectException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }*/
      } catch (DatabaseInsertException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }

    } catch (ConnectionFailedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
}
