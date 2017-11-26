package action;

import static org.junit.Assert.*;
import org.junit.Test;

import static org.mockito.Mockito.*;

import action.ViewAllProblemSetsAction;
import databaseAPI.DatabaseExtractAPI;
import databaseAPI.DatabaseStoreAPI;
import models.Problem;
import models.ProblemSet;
import models.SimpleProblemSet;
import models.SingleAnswerProblem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestViewAllProblemSetsAction {
	
	private ProblemSet createProblemSet() {
		// create a problem
		Problem p = new SingleAnswerProblem("hi", "5");
		// date initialization
		SimpleDateFormat dateFormat = new SimpleDateFormat();
		
		String sTime = "11/11/2011 12:00 AM";
		String eTime = "19/11/2011 12:00 AM";
		
		Date start = null;
		Date end = null;
		try {
			start = dateFormat.parse(sTime);
			end = dateFormat.parse(eTime);
		} catch (ParseException e) {
			fail("Hope not");
		}
		// create problem set
		SimpleProblemSet ps = new SimpleProblemSet();
		ps.addProblem(p);
		ps.setStartTime(start);
		ps.setEndTime(end);
		ps.setMaxAttempts(5);
		// store the problem set
		Action storeAction = new AddProblemSetAction();
		DatabaseStoreAPI store = new DatabaseStoreAPI();
		storeAction.execute(ps, store);
		
		return ps;
	}
	
	@Test
	public void testViewExistingProblemSets() {
		//create problem set
		ProblemSet problemSet = createProblemSet();
		// return problem set 
		ArrayList<ProblemSet> ups = new ArrayList<>(); // use ps
		
		ArrayList<ProblemSet> ps = new ArrayList<>();
		ps.add(problemSet);
		// actions
		Action action = new ViewAllProblemSetsAction();
		DatabaseExtractAPI extract = mock(DatabaseExtractAPI.class);
		doReturn(ps).when(extract).actOnDatabase(ups);
		
		Object actual = action.execute(extract);
		Object expected = ps;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testViewEmptyProblemSets() {
		ArrayList<ProblemSet> p = new ArrayList<>();
		
		DatabaseExtractAPI extract = mock(DatabaseExtractAPI.class);
		Action action = new ViewAllProblemSetsAction();
		
		Object actual = action.execute(extract);
		Object expected = p;
		
		assertEquals(expected, actual);
	}
}
