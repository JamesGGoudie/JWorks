package action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;

import models.ProblemSetAttempt;
import models.Problem;
import models.SimpleProblemSet;
import models.SingleAnswerProblem;
import models.Student;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;

import action.AddProblemSetAttemptAction;

import java.time.Instant;
import java.util.Date;


public class TestAddProblemSetAttemptAction {
	
	private Problem problem;
	private SimpleProblemSet problemSet;
	private Student bob;
	
	@Before
	public void setUp() {
		//create problem
		problem = new SingleAnswerProblem("What is cat?","6");
		//create problem set
		problemSet = new SimpleProblemSet();
		problemSet.addProblem(problem);
		//create student
		bob = new Student("Bob", "bob@email.com", "000000", 1);
		
	}

	@Test
	public void testAddAttemptCurrent() {
		
		ProblemSetAttempt attempt = new ProblemSetAttempt(bob, problemSet);
		
		DatabaseAPI store = mock(DatabaseStoreAPI.class);
		
		Action attemptAction = new AddProblemSetAttemptAction();
		
		Object actual = attemptAction.execute(attempt, store);
		Object expected = true;
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void testAddAttemptBefore() {
		Date time;
		time = Date.from(Instant.now());
		
		ProblemSetAttempt attempt = new ProblemSetAttempt(bob, problemSet, time);
		
		DatabaseAPI store = mock(DatabaseStoreAPI.class);
		
		Action attemptAction = new AddProblemSetAttemptAction();
		
		Object actual = attemptAction.execute(attempt, store);
		Object expected = true;
		
		assertEquals(expected, actual);
	}

}
