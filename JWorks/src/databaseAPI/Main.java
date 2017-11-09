package databaseAPI;

import java.sql.Connection;
import models.*;

public class Main {

    public static void main(String[] args) {
        Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
        DatabaseDriverAPI.initialize(connection);

        Problem p1 = new SingleAnswerProblem("What is 2 + 2", "4");
        Problem p2 = new SingleAnswerProblem("2 plus 2 = 4. Minus 1", "3");
        SimpleProblemSet ps1 = new SimpleProblemSet();
        ps1.addProblem(p1);
        Student sUser = new Student("aaron", "a.rodgers@packers.club", "collar", 12);
        DatabaseStoreAPI insert = new DatabaseStoreAPI();
        DatabaseExtractAPI select = new DatabaseExtractAPI();
        try{
            //System.out.println(insert.actOnDatabase(p1));
            //System.out.println(insert.actOnDatabase(p2));
            System.out.println(insert.actOnDatabse(ps1));
            //System.out.println(insert.actOnDatabse(sUser));
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        String[] out = {"1", "2", "3"};
        //select.actOnDatabase(1, out);
        //select.actOnDatabase(2, out);
	    //select.actOnDatabase(3, out);
    }

}
