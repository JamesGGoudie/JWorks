package action;

import static org.junit.Assert.*;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.sqlite.SQLiteException;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseExtractAPI;
import databaseAPI.DatabaseStoreAPI;

import exceptions.DatabaseInsertException;
import exceptions.DatabaseSelectException;

import models.Problem;
import models.SingleAnswerProblem;

import static org.mockito.Mockito.*;

public class TestViewQuestionAction {
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testGetAllProblems() {
		// add a question
		Problem p = new SingleAnswerProblem("questions","wrong");
		DatabaseStoreAPI store = mock(DatabaseStoreAPI.class);
		Action addQuestion = new AddQuestionAction();
		
		addQuestion.execute(p,store);
		// extract
		DatabaseExtractAPI extract =  new DatabaseExtractAPI();
		
		Action viewAll = new ViewQuestionAction();
		
		Object actual = viewAll.execute(extract);
		Object expected = p;
		
		assertEquals(expected, actual);
	}
	
	// expect to return a exception or msg to say no questions or something???
	@Test
	public void testGetWhenNoProblems() throws DatabaseSelectException{
		exception.expect(DatabaseSelectException.class);
		
		DatabaseExtractAPI extract = new DatabaseExtractAPI();
		Action viewAll = new ViewQuestionAction();
		
		viewAll.execute(extract);
		
	}

}
