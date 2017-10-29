package databaseAPI;

import database.DatabaseSelector;
import exceptions.DatabaseSelectException;

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
            switch (actObj){
                // when a request is made to return all problems
                case 1:
                    ResultSet results = DatabaseSelector.getAllProblems(connection);
                    ResultSetMetaData rsmd = results.getMetaData();
                    String[] a_problem = new String[rsmd.getColumnCount()];
                    // each problem will be an array, they will be stored into the list of problems
                    // parse each result into problem attribute and add it to the list of problems
                    while (results.next()){
                        for(int col = 1; col < rsmd.getColumnCount();col++){
                            System.out.print(results.getString(col));
                            a_problem[col] = results.getString(col);
                        }
                        System.out.print("\n");
                        problems.add(a_problem);
                    }
                    String[] col;
                    for (int aRow = 0; aRow < problems.size(); aRow++){
                        col = problems.get(aRow);
                        System.out.println("Problem PK:"+ " "+ col[1]);
                        for(int aCol = 1; aCol < col.length; aCol++){
                            System.out.print(col[aCol]+"\t");
                        }
                        System.out.print("\n");
                    }
            }
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        } catch (DatabaseSelectException e) {
            System.out.println(e.getMessage());
        }
    }
}
