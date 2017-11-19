package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import exceptions.ConnectionFailedException;

public class DatabaseDriver {
  /**
   * If the database exists, will connect to it. Otherwise, will create the database.
   * @return The connection to the SQLite database.
   */
  protected static Connection connectOrCreateDatabase() {
    Connection connection = null;
    
    Properties properties = new Properties();
    properties.setProperty("PRAGMA foreign_keys", "ON");
    
    try {
      Class.forName("org.sqlite.JDBC");
      connection = DriverManager.getConnection("jdbc:sqlite:jworks.db", properties);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
      System.out.println("The required SQLite class could not be found.");
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("The connection to the database could not be instantiated.");
    } 
    
    return connection;
  }
  
  /**
   * Will add the tables to the database.
   * @param connection The connection to the database which the tables will be written to.
   * @return The connection to the database that was passed in.
   * @throws ConnectionFailedException Thrown if the tables were not added to the database.
   */
  protected static Connection initialize(Connection connection) throws ConnectionFailedException {
    if (!initializeDatabase(connection)) {
      throw new ConnectionFailedException();
    }
    
    return connection;
  }
  
  /**
   * Creates all of the tables to be used in the database.
   * @param connection The connection to the database file.
   * @return True if all tables were created; false otherwise.
   */
  private static boolean initializeDatabase(Connection connection) {
    Statement statement = null;
    boolean result = false;
    
    try {
      statement = connection.createStatement();
      
      String sql = "CREATE TABLE IF NOT EXISTS PROBLEMS " 
          + "(ID INTEGER PRIMARY KEY NOT NULL," 
          + "TYPE INTEGER NOT NULL,"
          + "QUESTION TEXT NOT NULL,"
          + "ANSWER NOT NULL)";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS PROBLEMSETS "
          + "(ID INTEGER PRIMARY KEY NOT NULL,"
          + "MAXATTEMPTS INTEGER NOT NULL,"
          + "STARTTIME INTEGER NOT NULL,"
          + "ENDTIME INTEGER NOT NULL)";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS PROBLEMSETS_PROBLEMS_RELATIONSHIP "
          + "(PROBLEMSET INTEGER NOT NULL,"
          + "PROBLEM INTEGER NOT NULL,"
          + "FOREIGN KEY (PROBLEMSET) REFERENCES PROBLEMSETS(ID),"
          + "FOREIGN KEY (PROBLEM) REFERENCES PROBLEMS(ID))";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS STUDENTS "
          + "(STUDENTNUMBER INTEGER PRIMARY KEY NOT NULL,"
          + "NAME TEXT NOT NULL,"
          + "EMAIL TEXT NOT NULL,"
          + "PASSWORD TEXT NOT NULL)";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS INSTRUCTORS "
          + "(ID INTEGER PRIMARY KEY NOT NULL,"
          + "NAME TEXT NOT NULL,"
          + "EMAIL TEXT NOT NULL,"
          + "PASSWORD TEXT NOT NULL)";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS INSTRUCTORS_PROBLEMS_RELATIONSHIP "
          + "(INSTRUCTOR INTEGER NOT NULL,"
          + "PROBLEM INTEGER PRIMARY KEY NOT NULL,"
          + "FOREIGN KEY (INSTRUCTOR) REFERENCES INSTRUCTORS(ID),"
          + "FOREIGN KEY (PROBLEM) REFERENCES PROBLEMS(ID))";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS INSTRUCTORS_PROBLEMSETS_RELATIONSHIP "
          + "(INSTRUCTOR INTEGER NOT NULL,"
          + "PROBLEMSET INTEGER PRIMARY KEY NOT NULL,"
          + "FOREIGN KEY (INSTRUCTOR) REFERENCES INSTRUCTORS(ID),"
          + "FOREIGN KEY (PROBLEMSET) REFERENCES PROBLEMSETS(ID))";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS ATTEMPTSREMAINING "
          + "(STUDENTNUMBER INTEGER NOT NULL,"
          + "PROBLEMSET INTEGER NOT NULL,"
          + "ATTEMPTSREMAINING INTEGER NOT NULL,"
          + "FOREIGN KEY(STUDENTNUMBER) REFERENCES STUDENTS(STUDENTNUMBER)"
          + "FOREIGN KEY(PROBLEMSET) REFERENCES PROBLEMSETS(ID))";
      statement.executeUpdate(sql);
      
      sql = "CREATE TABLE IF NOT EXISTS PREVIOUSATTEMPTS "
          + "(STUDENTNUMBER INTEGER NOT NULL,"
          + "PROBLEMSET INTEGER NOT NULL,"
          + "PROBLEM INTEGER NOT NULL,"
          + "STUDENTANSWER STRING NOT NULL,"
          + "FOREIGN KEY(STUDENTNUMBER) REFERENCES STUDENTS(STUDENTNUMBER)"
          + "FOREIGN KEY(PROBLEMSET) REFERENCES PROBLEMSETS(ID)"
          + "FOREIGN KEY(PROBLEM) REFERENCES PROBLEMS(ID))";
      statement.executeUpdate(sql);
      
      statement.close();
      result = true;
      
    } catch (SQLException e) {
      e.printStackTrace();
      System.out.println("A problem occurred while trying to create the tables.");
    }
    
    return result;
  }
}
