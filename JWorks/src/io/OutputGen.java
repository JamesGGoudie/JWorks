package io;

import models.Problem;
import models.ProblemSet;

import java.util.List;

public interface OutputGen {
	
	/**
	 * Output a String out onto the UI
	 * @param out
	 */
	void output(String out);

    /**
     * Output a Problem object onto the UI
     * @param problem the Problem object to output
     */
    void output(Problem problem);

    /**
     * Output a Problem Set object onto the UI
     * @param problemSet the Problem Set object to output
     */
    void output(ProblemSet problemSet);
	
	/**
	 * Outputs a ArrayList of problems/problem sets.
	 * @param problemSet an ArrayList of problems in a problem set. 
	 */
	void problemSetOutput(List<String[]> problemSet);


	
}
