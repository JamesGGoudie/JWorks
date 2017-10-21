package database;

import java.sql.Connection;

public interface DatabaseDeleter {
  
  /**
   * Removes a problem from the database.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the problem was deleted, false otherwise.
   */
  static boolean deleteProblem(int problemKey, Connection connection) {
    return false;
  }
}
