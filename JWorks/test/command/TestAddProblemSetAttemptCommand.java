package command;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import models.ProblemSetAttempt;
import models.SimpleProblemSet;
import models.SingleAnswerProblem;
import models.Student;

import action.Action;
import action.AddProblemSetAttemptAction;
import databaseAPI.DatabaseStoreAPI;
import exceptions.DatabaseInsertException;

import static org.mockito.Mockito.*;

public class TestAddProblemSetAttemptCommand {
	
	private SimpleProblemSet sps;
	private ProblemSetAttempt psa;
	private SingleAnswerProblem p;
	private Student cat;
	
	@Before
	public void setUp() {
		// create cat student
		cat = new Student("cat", "noemail","pass", 1234);
		// create a problem
		p = new SingleAnswerProblem("hhhh","92");
		// create a problem set
		sps = new SimpleProblemSet();
		sps.addProblem(p);
		//
		psa = new ProblemSetAttempt(cat, sps);
	}

	@Test
	public void testExecute() throws DatabaseInsertException {
		// make the execute
		Action action = mock(AddProblemSetAttemptAction.class);
		DatabaseStoreAPI store = mock(DatabaseStoreAPI.class);
		doReturn(true).when(action).execute(psa, store);
		doReturn(true).when(store).actOnDatabase(psa);
		// psa array
		String[] psaList = {psa.serialize()};
		
		AddProblemSetAttemptCommand command = new AddProblemSetAttemptCommand(store, null);
		Object actual = command.execute(psaList);
		Object expected = false;
		assertEquals(expected, actual);
	}

}
