package io;

import models.Problem;
import models.ProblemSet;

import java.util.List;

public class OutputGenerator implements OutputGen{

	private Object lastOutput;
	
	/**
	 * Output a String out onto the UI
	 * @param out
	 */
	public void output(String out) {
		System.out.println(out);
	}

	/**
	 * Output a Problem object onto the UI
	 *
	 * @param problem the Problem object to output
	 */
	@Override
	public void output(Problem problem) {
		output("Q" + problem.getId() + ":" + problem.getProblem());
		output("A:" + problem.getAnswer());
	}

	/**
	 * Output a Problem Set object onto the UI
	 *
	 * @param problemSet the Problem Set object to output
	 */
	@Override
	public void output(ProblemSet problemSet) {
        output("Problem Set " + problemSet.getId() + ":");
        for (Problem p : problemSet.getQuestions()) {
            output(p);
        }
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

	/**
	 * Returns the last Object that was sent to this OutputGenerator.
	 *
	 * @return the last Object that was sent to this OutputGenerator
	 */
	@Override
	public Object getLastResult() {
		return lastOutput;
	}
}
