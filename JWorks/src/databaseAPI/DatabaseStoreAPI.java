package databaseAPI;

import database.DatabaseInserter;
import exceptions.DatabaseInsertException;
import models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseStoreAPI extends DatabaseInserter implements DatabaseAPI{
    private Connection connection;
    /**
     * Inserts into the problems table in the JWorks database
     * @param newProblem Problem object that is to be inserted into the database
     * @return the PrimaryKey of the problem in the table
     * @throws DatabaseInsertException
     * @throws SQLException
     */
    public int actOnDatabase(Problem newProblem) throws DatabaseInsertException, SQLException{
        this.actOnDatabase();
        //store the primary key of row inserted
        int result;
        result = DatabaseInserter.insertProblem(1, newProblem.getProblem(), newProblem.getAnswer(), newProblem.getCreatorID(),connection);
        newProblem.setId(result);
        return result;
    }

    /**
     * Inserts into the problem sets table in the JWorks database
     * @param newPSet Problem set objects whose attribute is to be stored into the database
     * @return Primary key of the ProblemSet in the table
     * @throws DatabaseInsertException
     * @throws SQLException
     */
    public int actOnDatabase(ProblemSet newPSet) throws DatabaseInsertException, SQLException{
        this.actOnDatabase();
        //store the primary key of row inserted
        int result;
        int[] pIDs = new int[newPSet.getQuestions().size()];
        for (int i = 0; i < newPSet.getQuestions().size(); i++){
            pIDs[i] = (newPSet.getQuestions().get(i)).getId();
        }
        result = DatabaseInserter.insertProblemSet(newPSet.getMaxAttempts(), pIDs, newPSet.getStartTime(), newPSet.getEndTime(), newPSet.getCreatorID(), connection);
        newPSet.setId(result);
        return result;
    }

    /**
     * Parse info from given student object and call respective database function to store it
     * @param newStudent instance of new Student who is going to be registered into the database
     * @return student number for successful insertion, -1 for failed insertion
     * @throws DatabaseInsertException
     * @throws SQLException
     */
    public int actOnDatabase(Student newStudent) throws DatabaseInsertException, SQLException{
        this.actOnDatabase();
        //store the primary key of row inserted
        int result;
        boolean test = DatabaseInserter.insertStudent(newStudent.getStudentNumber(), newStudent.getName(), newStudent.getEmailAddress(), newStudent.getPassword(), connection);
        if (test){
            result = newStudent.getStudentNumber();
        } else{
            result = -1;
        }
        return result;
    }

    /**
     * Parse info from given Instructor object and call respective database function to store it
     * @param newInstructor instance of new Student who is going to be registered into the database
     * @return instructorID if insertion was successful, -1 for failed insertion
     * @throws DatabaseInsertException
     * @throws SQLException
     */
    public int actOnDatabase(Instructor newInstructor) throws DatabaseInsertException, SQLException{
        this.actOnDatabase();
        int result;
        boolean test = DatabaseInserter.insertInstructor(newInstructor.getInstructorID(), newInstructor.getName(), newInstructor.getEmailAddress(), newInstructor.getPassword(), connection);
        if (test){
            result = newInstructor.getInstructorID();
        } else{
            result = -1;
        }
        return result;
    }

    @Override
    public void actOnDatabase() {
        connection = DatabaseDriverAPI.connectOrCreateDataBase();
    }
    
    /**
     * Given a list of tags, stores the given tags for this problem in the database.
     * @param newTags The tags to be associated with the problem.
     * @param problem The problem object to have the tags appended to. Must have it's ID.
     * @return True if all of the tags were added, false otherwise.
     */
    public boolean actOnDatabase(List<String> newTags, Problem problem) {
        this.actOnDatabase();
        boolean result = true;
        String currentTag;
        Iterator<String> iterator = newTags.iterator();
        int problemID = problem.getId();
        
        
        try {
            while (iterator.hasNext()) {
                currentTag = iterator.next();
                result = DatabaseInserter.insertProblemTag(problemID, currentTag, connection);
            }
        } catch (DatabaseInsertException e) {
            result = false;
        }
        
        return result;
    }
    
    /**
     * Given a list of tags, stores the given tags for the problem set into the database.
     * @param newTags The tags to be associated with the given problem set.
     * @param problemSet The problem set object have the tags appended to. Must have it's ID.
     * @return True if all of the tags were added, false otherwise.
     */
    public boolean actOnDatabase(List<String> newTags, ProblemSet problemSet) {
      boolean result = true;
      String currentTag;
      Iterator<String> iterator = newTags.iterator();
      int problemSetID = problemSet.getId();
      
      try {
          while (iterator.hasNext()) {
              currentTag = iterator.next();
              result = DatabaseInserter.insertProblemSetTag(problemSetID, currentTag,
                      connection);
          }
      } catch (DatabaseInsertException e) {
          result = false;
      }
      
      return result;
    }

    /**
     * Inserts a problem set attempt into the database.
     * @param problemSetAttempt The attempt containing all of the information needed to store into
     *                          the database including the student, problem set, attempt time, and
     *                          the student's answers.
     * @return True if the problem set attempt was added to the database, false otherwise.
     */
    public boolean actOnDatabase(ProblemSetAttempt problemSetAttempt) throws DatabaseInsertException {
        this.actOnDatabase();
        boolean result = false;
        
        int studentNumber = problemSetAttempt.getStudent().getStudentNumber();
        int problemSetKey = problemSetAttempt.getProblemSet().getId();
        long attemptTime = (problemSetAttempt.getTimeAttempted().getTime()) / 1000L;
        
        int problemCount = problemSetAttempt.getProblemSet().getQuestions().size();
        
        int[] problems = new int[problemCount];
        String[] answers = new String[problemCount];

        for (int i = 0; i < problemCount; i++) {
            problems[i] = problemSetAttempt.getProblemSet().getQuestions().get(i).getId();
            answers[i] = problemSetAttempt.getAnswers().get(i);
        }

        result = DatabaseInserter.insertStudentsAttempt(studentNumber, problemSetKey,
                attemptTime, problems, answers, this.connection);
        
        return result;
    }

}
