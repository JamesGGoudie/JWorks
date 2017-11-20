package action;

import models.Problem;
import models.ProblemSet;
import models.Student;

public class MockDatabaseStoreAPI implements MockDatabaseAPI{
	
	public int actOnDatabase(Problem newProblem) {
		return 1;
	}
	
	public int actOnDatabase(ProblemSet newSet) {
		return 1;
	}
	
	public int actOnDatabase(Student newStudent) {
		return 1;
	}

	@Override
	public void actOnDatabase(int actObj, String[] args) {
		// TODO Auto-generated method stub
		
	}

}
