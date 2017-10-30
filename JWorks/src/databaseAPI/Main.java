package databaseAPI;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        String[] problem = {"Q_1", "A_1"};
        String[] pSet = {"2", "1", "2", "3"};
        String[] sSet = {"400", "Carson Wenz", "c.wenz@email.xyz"};
        Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
        DatabaseDriverAPI.initialize(connection);
        
        DatabaseStoreAPI insert = new DatabaseStoreAPI();

        DatabaseExtractAPI select = new DatabaseExtractAPI();
	    //insert.actOnDatabase(2, problem);
        //insert.actOnDatabase(2, pSet);
        //insert.actOnDatabase(3, sSet);
	    select.actOnDatabase(3,sSet);
    }

}
