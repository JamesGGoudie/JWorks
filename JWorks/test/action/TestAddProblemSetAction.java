package action;

import static org.junit.Assert.*;

import org.junit.Test;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;
import models.Problem;
import models.SimpleProblemSet;
import models.SingleAnswerProblem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestAddProblemSetAction {

	@Test
	public void testAddProblemSet() {
		Problem problem = new SingleAnswerProblem("212","5");
		
		String startT = "Nov 5, 2001 11:30 AM";
		String endT = "Nov 6, 2001 11:30 PM";
		
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		
		Date start = null;
		Date end = null;
		
		try {
			start = dateFormat.parse(startT);
			end = dateFormat.parse(endT);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			fail("Incorrect date format");
		}
		
		SimpleProblemSet pSet = new SimpleProblemSet();
		
		pSet.addProblem(problem);
		pSet.setStartTime(start);
		pSet.setEndTime(end);
		pSet.setMaxAttempts(2);
		
		AddProblemSetAction action = new AddProblemSetAction();
		DatabaseAPI api = new DatabaseStoreAPI();
	    Object actual = action.execute(pSet, api);
		
	    Object expected = pSet;
	    
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAddEmptyProblemSet() {
		
		
		
		
		fail("Not yet implemented");
	}
	
	@Test
	public void testReturnException() {
		
		
		
		
		fail("Not yet");
	}

}
