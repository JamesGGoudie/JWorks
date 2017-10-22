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
		
		System.out.println("1. Create a question.\n");
		System.out.println("2. View a question.\n");
		System.out.println("--------------------------");
		System.out.println("Choose a option: ");
		String  choice = userInput.nextLine();
				
		return choice;
		
	}
}  