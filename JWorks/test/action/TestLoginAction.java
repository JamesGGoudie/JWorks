package action;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import databaseAPI.DatabaseAPI;
import databaseAPI.DatabaseExtractAPI;
import databaseAPI.DatabaseStoreAPI;
import models.Student;

public class TestLoginAction {
	
	private DatabaseAPI storeAPI;
	private Student bob;
	private Action addStudent;
	
	@Before
	public void setUp() {
		// Create a Student before any tests are run
		storeAPI = new DatabaseStoreAPI();
		bob = new Student("bob", "bob@email.com", "555", 1312);
		addStudent = new AddStudentAction();
		addStudent.execute(bob, storeAPI);
		
	}
	

	@Test
	public void testLoginWithExistingUser() {
		
		// check login 
		Action login = new LoginAction();
		DatabaseAPI extract = new DatabaseExtractAPI();
		
		Object actual = login.execute("1312","555", extract);
		Object expected = bob;
		assertEquals(expected, actual);
	}
	
	@Test
	public void testLoginWithNoneExistingUser() {
		Action login = new LoginAction();
		DatabaseAPI extract = new DatabaseExtractAPI();
		
		login.execute("1235","notreal", extract);
		
		
		fail("Not yet implemented");
	}
	
	// throws exceptions
	@Test
	public void testInvalidInputs() {
		Action login = new LoginAction();
		DatabaseAPI extract = new DatabaseExtractAPI();
		
		login.execute("1235HiCHAR","notreal", extract);
		
	}
	
	@Test
	public void testCatchExceptions() {
		fail("Not yet implemented");
	}

}
