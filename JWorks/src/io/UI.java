package io;

import java.util.Scanner;

public class UI implements UIGen{
	
	private Scanner userInput = new Scanner(System.in);
	
	public int userChoicePrompt() {
		
		System.out.println("1. Create a question.\n");
		System.out.println("2. View a question.\n");
		System.out.println("--------------------------");
		System.out.println("Choose a option: \n");
		int choice = userInput.nextInt();
		
		userInput.close();
		
		return choice;
		
	}
}  