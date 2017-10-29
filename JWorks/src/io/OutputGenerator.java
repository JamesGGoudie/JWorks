package io;

import java.util.List;

public class OutputGenerator {
	
	/**
	 * Output a String out onto the UI
	 * @param out
	 */
	public void output(String out) {
		System.out.println(out);
	}
	
	/**
	 * Outputs a ArrayList of problems/problem sets.
	 * @param problemSet an ArrayList of problems in a problem set. 
	 */
	public void problemSetOutput(List<String[]> problemSet) {
		// is the current problem in the problemSet list
		String[] currentProblem;
		// outputs every problem in problemSet
		for(int id = 0; id <problemSet.size();id++) {
			// tracks current problem
			currentProblem = problemSet.get(id);
			System.out.println("Question Number: " + currentProblem[0]);
			// at current question now get info about the question
			for (int currentQuestion = 1; currentQuestion < currentProblem.length; currentQuestion++) {
				System.out.println(currentProblem[currentQuestion] + "\t");
			}
			System.out.println("\n");
		}
	}
}
