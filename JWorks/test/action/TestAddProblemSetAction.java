package action;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;
import models.Problem;
import models.SimpleProblemSet;
import models.SingleAnswerProblem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.mockito.Mockito.*;

public class TestAddProblemSetAction {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testAddProblemSet() {
		Problem problem = new SingleAnswerProblem("212","5");
		String startT = "10/11/2001 12:00 AM";
		String endT = "15/11/2001 12:00 AM";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		
		Date start = null;
		Date end = null;
		
		try {
			start = dateFormat.parse(startT);
			end = dateFormat.parse(endT);
		} catch (ParseException e) {
			fail("Hope not");
		}
		
		SimpleProblemSet pSet = new SimpleProblemSet();
		
		pSet.addProblem(problem);
		pSet.setStartTime(start);
		pSet.setEndTime(end);
		pSet.setMaxAttempts(2);
		
		AddProblemSetAction action = new AddProblemSetAction();
		
		DatabaseAPI api = mock(DatabaseStoreAPI.class);
	    
		Object actual = action.execute(pSet, api);
	    Object expected = pSet;
	    
		assertEquals(expected, actual);
	}
	
	// DatabaseInsertException 
	@Test
	public void testAddEmptyProblemSet() {
		exception.expect(NullPointerException.class); // i dont think this is the kind of exceptions to catch
		
		Problem problem = new SingleAnswerProblem(null, null);
		SimpleProblemSet pSet = new SimpleProblemSet();
		
		pSet.addProblem(problem);
		pSet.setStartTime(null);
		pSet.setEndTime(null);
		pSet.setMaxAttempts(0);
		
		AddProblemSetAction action = new AddProblemSetAction();
		
		DatabaseAPI api = mock(DatabaseStoreAPI.class);
		
		Object actual = action.execute(pSet, api);
	    Object expected = pSet;
		
	    assertEquals(expected, actual);
	}
	
}
