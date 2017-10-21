package database;

import java.sql.Connection;

import exceptions.DatabaseInsertException;

public interface DatabaseInserter {

  /**
   * 
   * @param type An integer used to represent the questions type.
   * @param question The text that would be prompted to the student.
   * @param answer The answer to the question.
   * @param connection The connection to the database.
   * @return The unique key of the problem.
   * @throws DatabaseInsertException Thrown if the question could not be added to the database.
   */
  static int insertProblem(int type, String question, String answer, Connection connection)
      throws DatabaseInsertException {
    return -1;
  }
}
