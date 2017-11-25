package databaseAPI;

import database.DatabaseSelector;
import exceptions.DatabaseSelectException;
import models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabaseExtractAPI extends DatabaseSelector implements DatabaseAPI{
    private Connection connection;
    @Override
    public void actOnDatabase(){
        connection = DatabaseDriverAPI.connectOrCreateDataBase();
    }
    
    /**
     * assigns values to problem with the corresponding problem key
     * @param pKey primary key to identify the problem in database
     * @param searchProblem problem that will be populated and returned
     * @return problem with attributes corresponding to its primary key
     * @throws DatabaseSelectException
     * @throws SQLException
     */
    public Problem actOnDatabase(int pKey, Problem searchProblem)throws DatabaseSelectException, SQLException{
        this.actOnDatabase();
        // stores value returned from respective table
        ResultSet results;
        // store metadata for corresponding ResultSet
        ResultSetMetaData rsmd;
        results = DatabaseSelector.getSingleProblem(pKey, connection);
        rsmd = results.getMetaData();
        String[] resultRow = new String[rsmd.getColumnCount()];
        for (int col = 1; col <= rsmd.getColumnCount(); col++){
            // check what's being inserted
            resultRow[col - 1] = results.getString(col);
        }
        
        searchProblem = populateProblem(resultRow);
        searchProblem.setCreatorID(DatabaseSelector.getProblemCreator(pKey, this.connection));
        searchProblem.addTags(this.getProblemTags(pKey));
        return searchProblem;
    }

    /**
     * Use information from array to assign attributes to Problem
     */
    private Problem populateProblem(String[] aRow) {
        Problem newProblem;
        int pid = Integer.parseInt(aRow[0]);
        String question = aRow[2];
        String answer = aRow[3];
        //once instructor model has been created
        //int iid = Integer.getInteger(aRow[3]);
        newProblem = new SingleAnswerProblem(question, answer);
        newProblem.setId(pid);
        return newProblem;
    }

    /**
     * parses info stored in rs into attributes for a student user
     * @param rs result set from requesting student with specific student number
     * @param rm metatata for rs
     * @return String array which has parsed attributes of a student user
     * @throws DatabaseSelectException
     * @throws SQLException
     */
    private String[] formatUser(ResultSet rs, ResultSetMetaData rm)
            throws DatabaseSelectException, SQLException{
        // parse student into a string array
        String[] row = new String[rm.getColumnCount()];
        for(int col = 1; col <= rm.getColumnCount();col++){
            //System.out.print(rs.getString(col)+"   ");
            row[col - 1] = rs.getString(col);
        }
        //System.out.print("\n");
        return row;
    }

    public Instructor actOnDatabase(int iid, Instructor searchInstructor) throws DatabaseSelectException, SQLException {
        this.actOnDatabase();
        // stores value returned from respective table
        ResultSet results;
        // store metadata for corresponding ResultSet
        ResultSetMetaData rsmd;
        results = DatabaseSelector.getInstructor(iid, connection);
        rsmd = results.getMetaData();
        String[] resultRow = new String[rsmd.getColumnCount()];
        resultRow = formatUser(results, rsmd);
        searchInstructor = populateInstructor(searchInstructor, resultRow);
        return searchInstructor;
    }

    private Instructor populateInstructor(Instructor emptyInstructor, String[] instructorArray){
        Integer instructorID = new Integer(instructorArray[0]);
        emptyInstructor.setInstructorID(instructorID);
        emptyInstructor.setName(instructorArray[1]);
        emptyInstructor.setEmailAddress(instructorArray[2]);
        emptyInstructor.setPassword(instructorArray[3]);
        return emptyInstructor;
    }

    /**
     * Gets attributes for Student object with respective student id
     * parse info into student object and returns it
     * @param sid the unique key associated with student object
     * @param searchStudent empty student object
     * @return populated student object
     */
    public Student actOnDatabase(int sid, Student searchStudent) throws DatabaseSelectException, SQLException{
        this.actOnDatabase();
        // stores value returned from respective table
        ResultSet results;
        // store metadata for corresponding ResultSet
        ResultSetMetaData rsmd;
        results = DatabaseSelector.getStudent(sid, connection);
        rsmd = results.getMetaData();
        String[] resultRow = new String[rsmd.getColumnCount()];
        resultRow = formatUser(results, rsmd);
        searchStudent = populateStudent(searchStudent, resultRow);
        return searchStudent;
    }

    /**
     * Use info in array provided to set attribute of student object supplied
     * @param emptyStudent instance of Student user which needs attributes to be assigned
     * @param studentArray result row from database represented as an array
     * @return Student object that has been assigned all its attributes
     */
    private Student populateStudent(Student emptyStudent, String[] studentArray){
        emptyStudent.setStudentNumber(Integer.parseInt(studentArray[0]));
        emptyStudent.setName(studentArray[1]);
        emptyStudent.setEmailAddress(studentArray[2]);
        emptyStudent.setPassword(studentArray[3]);
        return emptyStudent;
    }
    
    /**
     * Gets all of the problems from the database and appends them to the given list. The list
     * given will first be cleared of any problems already present.
     * @problems A list of problems.
     * @throws DatabaseSelectException Thrown if the result set could not be retrieved from the
     *                                 database.
     * @throws SQLException Thrown if there was an attempt to access an inaccessible resultSet.
     */
    public List<Problem> actOnDatabase(List<Problem> allProblems) throws DatabaseSelectException,
            SQLException {
        this.actOnDatabase();
        ResultSet problemsRaw = DatabaseSelector.getAllProblems(this.connection);
        
        allProblems.clear();
        
        // If the databaseSelector failed, then we do not want to continue.
        if (problemsRaw == null) {
            String errorMessage = "Got a null object instead of a resultSet when trying to get";
            errorMessage += " all problems from the database.";
            throw new DatabaseSelectException(errorMessage);
        } else {
            // For every problem in the result set.
            while (problemsRaw.next()) {
                // Get all of the information from the current item in the result set.
                int id = problemsRaw.getInt(1);
                int questionType = problemsRaw.getInt(2);
                String question = problemsRaw.getString(3);
                String answer = problemsRaw.getString(4);
                
                int creatorID = DatabaseSelector.getProblemCreator(id, this.connection);
                
                // If we had more than one question type, this switch statement would be
                // useful.
                switch (questionType) {
                    case (1):
                        // Instantiate the problem with data from the result set.
                        SingleAnswerProblem problem = new SingleAnswerProblem(question,
                            answer);
                        problem.setId(id);
                        problem.setCreatorID(creatorID);
                        problem.addTags(this.getProblemTags(id));
                        // This is just in case in a future build we need to send the problems
                        // together to the output generator.
                        allProblems.add(problem);
                        break;
                    default:
                        break;
                }
            }
            
            // Close the result set to allow for modification of the data.
            problemsRaw.close();
        }
        
        return allProblems;
    }

    /**
     * Modifies the given problem set and adds each problem from that the problem set should
     * contain to the list of problems. Any problems currently stored in the problem set will be
     * lost.
     * @param problemSetKey The unique ID of the problemSet.
     * @param problemSet The problem set object to append the questions to.
     * @return A problem set containing all of the problems that the problem set should contain
     *         based on the given ID.
     * @throws DatabaseSelectException Thrown if a resultSet could not be retrieved from the
     *                                 database.
     * @throws SQLException Thrown if there was an attempt to access an inaccessible resultSet.
     */
    public ProblemSet actOnDatabase(int problemSetKey, ProblemSet problemSet) throws
            DatabaseSelectException, SQLException {
      
        this.actOnDatabase();
        ResultSet problemsRaw = DatabaseSelector.getProblemsInProblemSet(problemSetKey,
            this.connection);
        
        ResultSet problemSetRaw = DatabaseSelector.getProblemSet(problemSetKey, this.connection);
        
        // If the databaseSelector failed, then we do not want to continue.
        if (problemsRaw == null) {
            String errorMessage = "Got a null object instead of a resultSet when trying to get";
            errorMessage += " the problems contained in a problem set from the database.";
            throw new DatabaseSelectException(errorMessage);
        } else if (problemSetRaw == null) {
            String errorMessage = "Got a null object instead of a resultSet when trying to get a";
            errorMessage += " problem set from the database.";
            throw new DatabaseSelectException(errorMessage);
        } else {
            List<Problem> problems = new ArrayList<Problem>();
            
            // For every problem in the problem set.
            while (problemsRaw.next()) {
                // Get the ID of the problem from the result set.
                int id = problemsRaw.getInt(1);
                
                // Get the data for the current problem from the database.
                ResultSet problemRaw = DatabaseSelector.getSingleProblem(id, this.connection);
                
                // Make sure that the problem is not null.
                if (problemRaw == null) {
                  String errorMessage = "Got a null object instead of a resultSet when trying to";
                  errorMessage += " get a problem from the database.";
                  throw new DatabaseSelectException(errorMessage);
                } else {
                  
                  int questionType = problemRaw.getInt(2);
                  String question = problemRaw.getString(3);
                  String answer = problemRaw.getString(4);
                  
                  int creatorID = DatabaseSelector.getProblemCreator(id, this.connection);
                  
                  Problem problem = null;

                  problemRaw.close();
                  // If we had more than one question type, this switch statement would be
                  // useful.
                  switch (questionType) {
                      case (1):
                          // Instantiate the problem with data from the result set.
                          problem = new SingleAnswerProblem(question,
                              answer);
                          problem.setId(id);
                          problem.setCreatorID(creatorID);
                          problem.addTags(this.getProblemTags(id));
                          break;
                      default:
                          break;
                  }
                  
                  // Make sure that we actually got a problem from the database.
                  if (problem != null) {
                      problems.add(problem);
                  }
                }
            }
            
            problemSet = new SimpleProblemSet(problems);
            
            // Get the data from the result set.
            int id = problemSetRaw.getInt(1);
            int maxAttempts = problemSetRaw.getInt(2);
            // These values from the result set need to be multiplied by 1000 because time is
            // stored as seconds in the database, but the Date object requires milliseconds.
            Date startTime = new Date(problemSetRaw.getInt(3) * 1000L);
            Date endTime = new Date(problemSetRaw.getInt(4) * 1000L);


            int creatorID = DatabaseSelector.getProblemSetCreator(problemSetKey, this.connection);
            
            problemSet.setId(id);
            problemSet.setMaxAttempts(maxAttempts);
            problemSet.setStartTime(startTime);
            problemSet.setEndTime(endTime);
            problemSet.setCreatorID(creatorID);
            problemSet.addTags(this.getProblemSetTags(id));
            
            // Close the result sets to allow for modification of the data.
            problemsRaw.close();
            problemSetRaw.close();
        }
        
        return problemSet;
    }
    
    /**
     * Retrieves all of the tags associated with the given problem from the database.
     * @param problemKey The unique ID of the problem.
     * @return A list of strings, each of which is a tag associated with the given problem.
     */
    private List<String> getProblemTags(int problemKey) {
        this.actOnDatabase();
        List<String> tags = new ArrayList<String>();
      
        try {
            ResultSet results = DatabaseSelector.getProblemTags(problemKey, this.connection);
            
            while (results.next()) {
                tags.add(results.getString(1));
            }
        } catch (DatabaseSelectException | SQLException e) {
            tags.clear();
        }
      
        return tags;
    }
    
    /**
     * Retrieves all of the tags associated with the given problem set from the database.
     * @param problemSetKey The unique ID of the problem set.
     * @return A list of strings, each of which is a tag associated with the given problem set.
     */
    private List<String> getProblemSetTags(int problemSetKey) {
        List<String> tags = new ArrayList<String>();
      
        try {
            ResultSet results = DatabaseSelector.getProblemSetTags(problemSetKey, this.connection);
            
            while (results.next()) {
                tags.add(results.getString(1));
            }
        } catch (DatabaseSelectException | SQLException e) {
            tags.clear();
        }
        
        return tags;
    }

    /**
     * Returns a list of all of the problems with the given tag.
     * @param problems A list of problems that will be cleared and filled with all of the problems
     *                 in the database that contain the given tag.
     * @param tag The tag that we want to use to find problems.
     * @return A list of problems with the given tag. An empty list could imply that an error
     *         occurred.
     */
    public List<Problem> actOnDatabase(List<Problem> problems, String tag) {
        this.actOnDatabase();
        problems.clear();
        
        try {
            ResultSet results = DatabaseSelector.getProblemsWithTag(tag, this.connection);
            
            while (results.next()) {
                Problem newProblem = new SingleAnswerProblem();
                
                problems.add(this.actOnDatabase(results.getInt(1), newProblem));
            }
        } catch (DatabaseSelectException | SQLException e) {
            problems.clear();
        }
      
      return problems;
    }
    
    /**
     * Gets all of the problem sets from the database.
     * @param allProblemSets A list that will be cleared and filled with all of the problem sets in
     *                       the database. An empty list could indicate an error occurred.
     * @return A list containing all of the problem sets in the database.
     */
    public List<ProblemSet> actOnDatabase(ArrayList<ProblemSet> allProblemSets) {
        this.actOnDatabase();
      
        allProblemSets.clear();
        
        try {
            ResultSet problemSetResults = DatabaseSelector.getAllProblemSets(this.connection);
            
            while (problemSetResults.next()) {
                ProblemSet problemSet = new SimpleProblemSet();
                allProblemSets.add(this.actOnDatabase(problemSetResults.getInt(1), problemSet));
            }
        } catch (DatabaseSelectException | SQLException e) {
            allProblemSets.clear();
        }
      
        return allProblemSets;
    }
    
    /**
     * Returns a list of all of the problem sets with the given tag.
     * @param tag The tag that we want to use to sort problem sets.
     * @param problemSets A list of problem sets that will be cleared and filled with all of the
     *                    problem sets in that database that contain the given tag.
     * @return A list of problem sets with the given tag. An empty list could imply that an error
     *         occurred.
     */
    public List<ProblemSet> actOnDatabase(String tag, List<ProblemSet> problemSets) {
      this.actOnDatabase();
      problemSets.clear();
      
      try {
          ResultSet results = DatabaseSelector.getProblemSetsWithTag(tag, this.connection);
          
          while (results.next()) {
              ProblemSet newProblemSet = new SimpleProblemSet();
              
              problemSets.add(this.actOnDatabase(results.getInt(1), newProblemSet));
          }
      } catch (DatabaseSelectException | SQLException e) {
          problemSets.clear();
      }
      
      return problemSets;
    }

    /**
     * Gets the problem set attempt from the database.
     * @param problemSetAttempt A problemSetAttempt which will be filled with the data from the
     *                          database. Should come with the problem set ID and attempt number.
     * @return A ProblemSetAttempt object that contains all of the information 
     */
    public ProblemSetAttempt actOnDatabase(ProblemSetAttempt problemSetAttempt) {
        this.actOnDatabase();
        int studentNumber = problemSetAttempt.getStudent().getStudentNumber();
        int problemSet = problemSetAttempt.getProblemSet().getId();
        long attemptTime = (problemSetAttempt.getTimeAttempted().getTime()) / 1000L;
        
        try {
            ResultSet previousAttemptData = DatabaseSelector.getStudentsAttempt(studentNumber,
                    problemSet, attemptTime, connection);
            
            while (previousAttemptData.next()) {
                problemSetAttempt.setAnswer(previousAttemptData.getInt(1),
                        previousAttemptData.getString(2));
            }
        } catch (DatabaseSelectException | SQLException e) {
            e.printStackTrace();
        }
        
        return problemSetAttempt;
    }

    /**
     * 
     * @return A list containing all of the problem set attempts in the database. An empty list
     *         indicates that an error may have occurred.
     */
    public List<ProblemSetAttempt> getAllAttempts() throws SQLException, DatabaseSelectException {
        this.actOnDatabase();
        List<ProblemSetAttempt> allAttempts = new ArrayList<>();

        ResultSet allAttemptData = DatabaseSelector.getAllAttemptIdentifiers(this.connection);

        while(allAttemptData.next()) {
            int studentNumber = allAttemptData.getInt(1);
            int problemSetID = allAttemptData.getInt(2);
            Date attemptTime = new Date(allAttemptData.getInt(3) * 1000);

            Student student = this.actOnDatabase(studentNumber, new Student());
            ProblemSet problemSet = this.actOnDatabase(problemSetID, new SimpleProblemSet());

            ProblemSetAttempt newAttempt = new ProblemSetAttempt(student, problemSet,
                    attemptTime);

            allAttempts.add(this.actOnDatabase(newAttempt));
        }
        
        return allAttempts;
    }
}
