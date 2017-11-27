package action;

import static org.junit.Assert.*;
import org.junit.Test;

import action.ViewAllAttemptsAction;
import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseExtractAPI;
import exceptions.DatabaseSelectException;

import models.ProblemSetAttempt;
import static org.mockito.Mockito.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestViewAllAttemptsAction {

	@Test
	public void testViewAllAttemptsWithNoAttempts() throws SQLException, DatabaseSelectException {
		List<ProblemSetAttempt> attempts = new ArrayList<>();
		
		Action action = new ViewAllAttemptsAction();
		DatabaseExtractAPI extract = mock(DatabaseExtractAPI.class);
		doReturn(attempts).when(extract).getAllAttempts();
		
		Object actual = action.execute(extract);
		Object expected = attempts;
		
		assertEquals(expected, actual);
	}

}
