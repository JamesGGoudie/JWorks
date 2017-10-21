package io;

import java.util.Scanner;

public class UI implements UIGen{
	
	private Scanner inputPrompt() {
		Scanner userInput = new Scanner(System.in);
		
		return userInput;
	}
	
	public int userChoicePrompt() {
		
		System.out.println("1. Create a question.\n");
		System.out.println("2. View a question.\n");
		System.out.println("--------------------------");
		System.out.println("Choose a option: ");
		int choice = inputPrompt().nextInt();
		
		inputPrompt().close();
		
		return choice;
		
	}
}  