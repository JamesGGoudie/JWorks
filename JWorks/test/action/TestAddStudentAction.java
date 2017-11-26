package action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import action.Action;
import action.AddStudentAction;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;
import models.Student;

import static org.mockito.Mockito.*;

public class TestAddStudentAction {
	
	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void testAddStudent() {
		Student student = new Student("Bob", "bob@email.com", "000000", 1);
		
		DatabaseAPI databaseAPI = mock(DatabaseStoreAPI.class);
		Action action = new AddStudentAction();
		
		Object actual = action.execute(student, databaseAPI);
		Object expected = true;
		
		assertEquals(expected, actual);
	}
	
	// should throw exceptions InsertException
	@Test
	public void testAddStudentWithNullProperties() {
		exception.expect(DatabaseInsertException.class);
		
		Student student = new Student(null, null, null, 1);
		
		DatabaseAPI databaseAPI = mock(DatabaseStoreAPI.class);
		
		Action action = new AddStudentAction();
		
		action.execute(student, databaseAPI);
		
	}

}
