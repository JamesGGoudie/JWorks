package databaseAPI;

import java.io.File;
import java.sql.Connection;
import models.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        File db = new File("jworks.db");
        db.delete();
        
        int creatorID = 2008;
        int maxAttempts = 5;
        
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
        Instructor iUser = new Instructor("mike", "m.mccarthy@packers.club", "jk", creatorID);
        p1.setCreatorID(creatorID);
        p2.setCreatorID(creatorID);
        ps1.setCreatorID(creatorID);
        ps1.setMaxAttempts(maxAttempts);
        DatabaseStoreAPI insert = new DatabaseStoreAPI();
        DatabaseExtractAPI select = new DatabaseExtractAPI();
        try{
            System.out.println(insert.actOnDatabase(p1));
            System.out.println(insert.actOnDatabase(p2));
            System.out.println(insert.actOnDatabase(ps1));
            ps1.addProblem(p2);
            System.out.println(insert.actOnDatabase(ps1));
            System.out.println(insert.actOnDatabase(sUser));
            System.out.println(insert.actOnDatabase(iUser));
            System.out.println();
            Problem sp = select.actOnDatabase(p1.getId(), new SingleAnswerProblem());
            ProblemSet sps = select.actOnDatabase(ps1.getId(), new SimpleProblemSet());
            Instructor si = select.actOnDatabase(2008, new Instructor());
            Student ss = select.actOnDatabase(12, new Student());
            printProblem(sp);
            printpSet(sps);
            printUser(si);
            printUser(ss);
            ArrayList<ProblemSet> derp = new ArrayList<ProblemSet>();
            
            List<ProblemSet> allProblemSets = select.actOnDatabase(derp);
            
            for (ProblemSet problemSet : allProblemSets) {
              printpSet(problemSet);
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("FAIL!!!");
        }
        String[] out = {"1", "2", "3"};
        //select.actOnDatabase(1, out);
        //select.actOnDatabase(2, out);
      //select.actOnDatabase(3, out);
    }

    static void printDBObject(DatabaseObject obj){
        System.out.println("id: "+obj.getId());
    }

    static void printProblem(Problem p){
        printDBObject(p);
        System.out.println("question: "+p.getProblem());
        System.out.println("answer: "+p.getAnswer());
        System.out.println("created by: "+p.getCreatorID());
        System.out.println();
    }

    static void printpSet(ProblemSet ps){
        printDBObject(ps);
        System.out.println("released: "+ps.getStartTime());
        System.out.println("submit by: "+ps.getEndTime());
        System.out.println("max attempts: "+ps.getMaxAttempts());
        System.out.println("created by: "+ps.getCreatorID());
        System.out.println();
        List<Problem> pList = ps.getQuestions();
        for (Problem p: pList
                ) {printProblem(p);
            System.out.println("");

        }
    }

    static void printUser(User u){
        printDBObject(u);
        System.out.println("name: "+u.getName());
        System.out.println("email: "+u.getEmailAddress());
        System.out.println("password: "+u.getPassword());
        System.out.println();
    }

}