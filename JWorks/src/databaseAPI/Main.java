package databaseAPI;

import java.sql.Connection;
import models.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
        DatabaseDriverAPI.initialize(connection);

        Problem p1 = new SingleAnswerProblem("What is 2 + 2", "4");
        Problem p2 = new SingleAnswerProblem("2 plus 2 = 4. Minus 1", "3");
        SimpleProblemSet ps1 = new SimpleProblemSet();
        ps1.addProblem(p1);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ps1.setEndTime(date);
        ps1.setStartTime(date);
        Student sUser = new Student("aaron", "a.rodgers@packers.club", "collar", 12);
        DatabaseStoreAPI insert = new DatabaseStoreAPI();
        DatabaseExtractAPI select = new DatabaseExtractAPI();
        ps1.setMaxAttempts(5);
        ps1.setCreatorID(15);
        try{
            int problemNum = insert.actOnDatabase(p1);
            int problemSetNum = insert.actOnDatabase(ps1);
            //System.out.println(insert.actOnDatabase(p1));
            //System.out.println(insert.actOnDatabase(p2));
            //System.out.println(insert.actOnDatabase(ps1));
            //System.out.println(insert.actOnDatabase(sUser));
            Problem sp = select.actOnDatabase(problemNum, new SingleAnswerProblem("", ""));
            System.out.println("id: "+sp.getId()+"q: "+sp.getProblem()+"a: "+sp.getAnswer());
            ProblemSet psR = select.actOnDatabase(problemSetNum, ps1);
            System.out.println("id: " + psR.getId() + " creatorID: " + psR.getCreatorID() + " MaxAttempts: " + psR.getMaxAttempts() + " StartTime: " + psR.getStartTime().getTime() + " EndTime: " + psR.getEndTime().getTime());
            System.out.println("questionID1: " + psR.getQuestions().get(0).getId());
        } catch (Exception e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        String[] out = {"1", "2", "3"};
        //select.actOnDatabase(1, out);
        //select.actOnDatabase(2, out);
	    //select.actOnDatabase(3, out);
    }

}
