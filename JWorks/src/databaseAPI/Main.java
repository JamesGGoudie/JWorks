package databaseAPI;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        String[] input = {"Q_1", "A_1"};
        
        Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
        DatabaseDriverAPI.initialize(connection);
        
        DatabaseStoreAPI database = new DatabaseStoreAPI();

        DatabaseExtractAPI select = new DatabaseExtractAPI();
	    //database.actOnDatabase(1, input);
	    select.actOnDatabase(1,input);
    }

}
