package action;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import action.Action;
import action.AddQuestionAction;


import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;
import models.Problem;
import models.SingleAnswerProblem; 

import static org.mockito.Mockito.*;

public class TestAddQuetionAction {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testAddMock() {
		String[] question = {"Blue","4"};
		Problem problem = new SingleAnswerProblem(question[0], question[1]);
		
		DatabaseAPI databaseAPI = mock(DatabaseStoreAPI.class);
		
		Action action = new AddQuestionAction();
		
		assertEquals(action.execute(problem, databaseAPI), problem);
		

	}
	
	// not catching the error
	@Test
	public void testAddNULLQuestion(){
		exception.expect(DatabaseInsertException.class);
		
		String[] question = {null, null};
		Problem problem = new SingleAnswerProblem(question[0], question[1]);
		
		DatabaseAPI databaseAPI = mock(DatabaseAPI.class); // mockito cant use to cast- error
		
		Action action = new AddQuestionAction();
		
		action.execute(problem, databaseAPI);

	}
	

}
