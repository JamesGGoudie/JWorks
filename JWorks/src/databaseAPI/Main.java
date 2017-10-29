package databaseAPI;

import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        String[] input = {"Q_1", "A_1"};
        
        Connection connection = DatabaseDriverAPI.connectOrCreateDataBase();
        DatabaseDriverAPI.initialize(connection);
        
        DatabaseStoreAPI database = new DatabaseStoreAPI();

        DatabaseExtractAPI store = new DatabaseExtractAPI();
	    database.actOnDatabase(1, input);
	    store.actOnDatabase(1,input);
    }

}
