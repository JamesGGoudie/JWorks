package databaseAPI;

import database.DatabaseSelector;
import exceptions.DatabaseSelectException;
import io.OutputGenerator;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DatabaseExtractAPI extends DatabaseSelector implements DatabaseAPI{
    @Override
    public void actOnDatabase(int actObj, String[] args) {
        try {
            Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
            // the query result will always be parsed into an Array list
            List<String[]> problems = new ArrayList<String[]>();
            // stores value returned from respective table
            ResultSet results;
            // store metadata for corresponding ResultSet
            ResultSetMetaData rsmd;
            switch (actObj){
                // when a request is made to return all problems
                case 1:
                    results = DatabaseSelector.getAllProblems(connection);
                    rsmd = results.getMetaData();
                    formatResult(results, rsmd, problems);
                    break;
                case 2:
                    results = DatabaseSelector.getProblemSet(Integer.parseInt(args[0]), connection);
                    rsmd = results.getMetaData();
                    formatResult(results, rsmd, problems);
                    break;
            }
            OutputGenerator outTo = new OutputGenerator();
            outTo.problemSetOutput(problems);
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (DatabaseSelectException e) {
            System.out.println(e.getMessage());
        }
    }

    private void formatResult(ResultSet rs, ResultSetMetaData rm, List<String[]> problems)
            throws DatabaseSelectException, SQLException{
        // each problem will be an array, they will be stored into the list of problems
        // parse each result into problem attribute and add it to the list of problems
        String[] row;
        while (rs.next()){
            row = new String[rm.getColumnCount()];
            for(int col = 1; col <= rm.getColumnCount();col++){
                System.out.print(rs.getString(col)+"   ");
                row[col - 1] = rs.getString(col);
            }
            System.out.print("\n");
            problems.add(row);
        }
    }
}
