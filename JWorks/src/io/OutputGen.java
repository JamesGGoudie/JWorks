package io;

import java.util.List;

public interface OutputGen {
	
	/**
	 * Output a String out onto the UI
	 * @param out
	 */
	public void output(String out);
	
	/**
	 * Outputs a ArrayList of problems/problem sets.
	 * @param problemSet an ArrayList of problems in a problem set. 
	 */
	public void problemSetOutput(List<String[]> problemSet);
	
}
