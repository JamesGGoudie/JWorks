package io;

import java.util.Scanner;

public class UI implements UIGen{
	
	public int userChoicePrompt() {
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("1. Create a question.\n");
		System.out.println("2. View a question.\n");
		System.out.println("--------------------------");
		System.out.println("Choose a option: \n");
		int choice = userInput.nextInt();
		
		userInput.close();
		
		return choice;
		
	}
	
	public String userQuestionPrompt() {
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Enter the question: \n");
		String question = userInput.next();

		userInput.close();
		
		return question;
	}
	
	public String userAnsPrompt() {
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Enter the answer to the question: \n");
		String ans = userInput.next();
		
		userInput.close();
		
		return ans;
		
	}
	
	 
}  