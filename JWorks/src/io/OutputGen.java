package io;

import models.Problem;
import models.ProblemSet;

import java.util.List;

public interface OutputGen {

	enum OutputMode {
		COMMAND_LINE, GUI
	};

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

	/**
	 * Outputs a general object to the stream.
	 * @param obj the object to output
	 */
	void outputPayload(Object obj);

	/**
	 * Returns the last Object that was sent to this OutputGenerator.
	 * @return the last Object that was sent to this OutputGenerator
	 */
	Object getLastResult();
}
