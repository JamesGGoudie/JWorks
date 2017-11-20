package action;

import static org.junit.Assert.*;

import org.junit.Test;
import org.sqlite.SQLiteException;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseExtractAPI;
import databaseAPI.DatabaseStoreAPI;
import models.Problem;
import models.SingleAnswerProblem;

public class TestViewQuestionAction {
	

	@Test
	public void testGetAllProblems() {
		// add a question
		Problem p = new SingleAnswerProblem("questions","wrong");
		DatabaseAPI store = new DatabaseStoreAPI();
		Action addQuestion = new AddQuestionAction();
		
		addQuestion.execute(p,store);
		// extract
		DatabaseAPI extract = new DatabaseExtractAPI();
		Action viewAll = new ViewQuestionAction();
		
		Object actual = viewAll.execute(extract);
		Object expected = true;
		
		assertEquals(expected, actual);
	}
	
	// expect to return a exception or msg to say no questions or something???
	@Test(expected = SQLiteException.class)
	public void testGetWhenNoProblems() {
		DatabaseAPI extract = new DatabaseExtractAPI();
		Action viewAll = new ViewQuestionAction();
		
		viewAll.execute(extract);
		
	}

}
