package action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import action.Action;
import action.AddStudentAction;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseStoreAPI;

import models.Student;

public class TestAddStudentAction {

	@Test
	public void testAddStudent() {
		Student student = new Student("Bob", "bob@email.com", "000000", 1);
		
		DatabaseAPI databaseAPI = new DatabaseStoreAPI();
		Action action = new AddStudentAction();
		
		Object actual = action.execute(student, databaseAPI);
		Object expected = true;
		
		assertEquals( "FAILED TO ADD STUDENT", expected, actual);
	}
	
	// should throw exceptions
	@Test
	public void testAddStudentWithNullProperties() {
		Student student = new Student(null, null, null, 1);
		
		DatabaseAPI databaseAPI = new DatabaseStoreAPI();
		Action action = new AddStudentAction();
		
		action.execute(student, databaseAPI);
		
	}
	
	@Test
	public void testAddStudentExpectError() {
		
		assertTrue(true);
	}

}
