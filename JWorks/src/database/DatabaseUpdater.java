package database;

import java.sql.Connection;

public interface DatabaseUpdater {

  /**
   * Updates the text body of a problem already in the database.
   * @param newQuestion The new text body for a pre-existing problem.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the question was updated, false if otherwise.
   */
  static boolean updateProblemBody(String newQuestion, int problemKey, Connection connection) {
    return false;
  }
  
  /**
   * Updates the answer of a problem already in the database.
   * @param newAnswer The new answer for a pre-existing problem.
   * @param problemKey The unique key of the problem.
   * @param connection The connection to the database.
   * @return True if the answer was updated, false if otherwise.
   */
  static boolean updateProblemAnswer(String newAnswer, int problemKey, Connection connection) {
    return false;
  }
}
