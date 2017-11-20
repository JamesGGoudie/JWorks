package databaseAPI;

import database.DatabaseSelector;
import exceptions.DatabaseInsertException;
import exceptions.DatabaseSelectException;
import io.GUIOutputGenerator;
import io.OutputGenerator;
import models.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DatabaseExtractAPI extends DatabaseSelector implements DatabaseAPI{
    private Connection connection;
    @Override
    public void actOnDatabase(){
        connection = DatabaseDriverAPI.connectOrCreateDataBase();
    }

    /**
     * Gets result from query run by DatabaseSelector class, parses it and sends it to output generator
     * @param actObj 1 for problem, 2 for problem set, 3 for Student User
     * @param args {Primary key for user}
     */
    public void actOnDatabase(int actObj, String[] args) {
        this.actOnDatabase();
        //to send message to UI
        GUIOutputGenerator outTo = new GUIOutputGenerator();
        try {
            this.actOnDatabase();
            // stores value returned from respective table
            ResultSet results;
            // store metadata for corresponding ResultSet
            ResultSetMetaData rsmd;
            switch (actObj){
                // when a request is made to return all problems
                case 1:
                    outputAllProblems();
                    break;
                case 2:
                    outputProblemSet(Integer.parseInt(args[0]));
                    break;
                case 3:
                    results = DatabaseSelector.getStudent(Integer.parseInt(args[0]), connection);
                    rsmd = results.getMetaData();
                    if (results.isClosed()){
                        throw new DatabaseSelectException("student with student number: "+args[0]+" wasn't be found");
                    }
                    else{
                        String[] studentRow = formatStudent(results, rsmd);
                        outTo.output("User has student number: "+studentRow[0]+" name: "+studentRow[1]+" and email: "+studentRow[2]);
                    }
                    break;
            }
            connection.close();
        }catch (SQLException e){
            e.printStackTrace();
            outTo.output(e.getMessage());
        } catch (DatabaseSelectException e) {
            outTo.output(e.getMessage());
        }
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
        while(results.next()){
            for (int col = 1; col <= rsmd.getColumnCount(); col++){
                // check what's being inserted
                resultRow[col] = results.getString(col);
            }
        }
        searchProblem = populateProblem(resultRow);
        return searchProblem;
    }

    /**
     * Gets all the problems in the database are organizes them into a list of problems
     * @param pList list of problem objects that will populated using info from database
     * @return list with all the problems in currently being stored in the database
     * @throws DatabaseSelectException
     * @throws SQLException
     */
    public List<Problem> actOnDatabase(List<Problem> pList) throws DatabaseSelectException, SQLException{
        this.actOnDatabase();
        // the query result will always be parsed into an Array list
        List<String[]> problems = new ArrayList<String[]>();
        // stores value returned from respective table
        ResultSet results;
        // store metadata for corresponding ResultSet
        ResultSetMetaData rsmd;
        results = DatabaseSelector.getAllProblems(connection);
        rsmd = results.getMetaData();
        formatResult(results, rsmd, problems);
        for (int problemIndex = 0; problemIndex < problems.size(); problemIndex++){
            pList.add(populateProblem(problems.get(problemIndex)));
        }
        return pList;
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
     * populates problems list with info stored in rs
     * @param rs the result set from requesting all problems
     * @param rm metadata for rs
     * @param problems list of string arrays each representing a row on problems table
     * @throws DatabaseSelectException
     * @throws SQLException
     */
    private void formatResult(ResultSet rs, ResultSetMetaData rm, List<String[]> problems)
            throws DatabaseSelectException, SQLException{
        // each problem will be an array, they will be stored into the list of problems
        // parse each result into problem attribute and add it to the list of problems
        String[] row;
        while (rs.next()){
            row = new String[rm.getColumnCount()];
            for(int col = 1; col <= rm.getColumnCount();col++){
                //System.out.print(rs.getString(col)+"   ");
                row[col - 1] = rs.getString(col);
            }
            //System.out.print("\n");
            problems.add(row);
        }
    }

    /**
     *
     * @param sid
     * @param searchStudent
     * @return
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
        resultRow = formatStudent(results, rsmd);
        searchStudent = populateStudent(searchStudent, resultRow);
        return searchStudent;
    }

    /**
     * parses info stored in rs into attributes for a student user
     * @param rs result set from requesting student with specific student number
     * @param rm metatata for rs
     * @return String array which has parsed attributes of a student user
     * @throws DatabaseSelectException
     * @throws SQLException
     */
    private String[] formatStudent(ResultSet rs, ResultSetMetaData rm)
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

    /**
     * Use info in array provided to set attribute of student object supplied
     * @param emptyStudent instance of Student user which needs attributes to be assigned
     * @param studentArray result row from database represented as an array
     * @return Student object that has been assigned all its attributes
     */
    private Student populateStudent(Student emptyStudent, String[] studentArray){
        emptyStudent.setStudentNumber(Integer.getInteger(studentArray[0]));
        emptyStudent.setName(studentArray[1]);
        emptyStudent.setEmailAddress(studentArray[2]);
        emptyStudent.setPassword(studentArray[3]);
        return emptyStudent;
    }
    
    /**
     * Gets all of the problems from the database as a ResultSet and then passes each problem
     * individually to the GUI output generator.
     * @throws DatabaseSelectException Thrown if the result set could not be retrieved from the
     *                                 database.
     * @throws SQLException Thrown if there was an attempt to access an inaccessible resultSet.
     */
    private void outputAllProblems() throws DatabaseSelectException, SQLException {
        GUIOutputGenerator outTo = new GUIOutputGenerator();
        List<Problem> allProblems = new ArrayList<Problem>();
        ResultSet problemsRaw = DatabaseSelector.getAllProblems(this.connection);
        
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
                
                // If we had more than one question type, this switch statement would be
                // useful.
                switch (questionType) {
                    case (1):
                        // Instantiate the problem with data from the result set.
                        SingleAnswerProblem problem = new SingleAnswerProblem(question,
                            answer);
                        problem.setId(id);
                        
                        // This is just in case in a future build we need to send the problems
                        // together to the output generator.
                        allProblems.add(problem);
                        
                        // Pass the problem to the GUI output generator.
                        outTo.output(problem);
                        break;
                    default:
                        break;
                }
            }
            
            // Close the result set to allow for modification of the data.
            problemsRaw.close();
        }
    }

    /**
     * 
     * @param problemSetKey The unique ID of the problem set.
     * @throws DatabaseSelectException Thrown if a resultSet could not be retrieved from the
     *                                 database.
     * @throws SQLException Thrown if there was an attempt to access an inaccessible resultSet.
     */
    private void outputProblemSet(int problemSetKey) throws DatabaseSelectException, SQLException {
        GUIOutputGenerator outTo = new GUIOutputGenerator();
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
                  
                  Problem problem = null;
                  
                  // If we had more than one question type, this switch statement would be
                  // useful.
                  switch (questionType) {
                      case (1):
                          // Instantiate the problem with data from the result set.
                          problem = new SingleAnswerProblem(question,
                              answer);
                          problem.setId(id);
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
            
            ProblemSet problemSet = new SimpleProblemSet(problems);
            
            // Get the data from the result set.
            int id = problemSetRaw.getInt(1);
            int maxAttempts = problemSetRaw.getInt(2);
            // These values from the result set need to be multiplied by 1000 because time is
            // stored as seconds in the database, but the Date object requires milliseconds.
            Date startTime = new Date(problemSetRaw.getInt(3) * 1000L);
            Date endTime = new Date(problemSetRaw.getInt(4) * 1000L);
            
            problemSet.setId(id);
            problemSet.setMaxAttempts(maxAttempts);
            problemSet.setStartTime(startTime);
            problemSet.setEndTime(endTime);
            
            outTo.output(problemSet);

            // Close the result sets to allow for modification of the data.
            problemsRaw.close();
            problemSetRaw.close();
        }
    }
}
