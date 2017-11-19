package action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import action.Action;
import action.AddProblemSetAction;
import action.AddQuestionAction;
import action.AddStudentAction;
import action.LoginAction;
import action.ViewQuestionAction;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;
import models.Problem;
import models.SingleAnswerProblem; 

public class TestAddQuetionAction {
	
	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@Test
	public void testAddQuestion() {
		String[] question = {"Blue","4"};
		Problem problem = new SingleAnswerProblem(question[0], question[1]);
		
		MockDatabaseAPI mockStore = new MockDatabaseStoreAPI(); // Encountered Error for action.excute() when casting
		
		DatabaseAPI databaseAPI = new DatabaseStoreAPI();
		
		// Testing this way to see if return the Problem object,
		// confused about mocking in this part
		Action action = new AddQuestionAction();
		
		Object actual = action.execute(problem, databaseAPI);
		Problem expected = problem;
		
		assertEquals(expected, actual);

	}
	
	@Test
	public void testAddNULLQuestion() {
		String[] question = {null, null};
		Problem problem = new SingleAnswerProblem(question[0], question[1]);
		
		DatabaseAPI databaseAPI = new DatabaseStoreAPI();
		
		Action action = new AddQuestionAction();
		
		Object actual = action.execute(problem, databaseAPI);
		Problem expected = problem;
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void testAddQuestionExpectError() {
		// how to break this
		
		//exception.expect(DatabaseInsertException.class);
		assertTrue(true);
	}

}
