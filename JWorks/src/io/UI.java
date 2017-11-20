package io;

import java.util.Scanner;

public class UI implements UIGen{
	
	//Scanner declaration
	private Scanner userInput = new Scanner(System.in);
	
	/**
	 * Prompts the user for a choice
	 * @return returns a String of the prompt
	 */
	public String userChoicePrompt() {
		System.out.println("AddSimpleProblemCommand - Create a question.\n");
		System.out.println("ViewProblemsCommand - View a question.\n");
		System.out.println("AddStudentCommand - Add a student.\n");
		System.out.println("------------------------------------------------");
		System.out.println("Type a command or type \"exit\" to exit: ");
		String  choice = userInput.nextLine();
				
		return choice;
		
	}
}  