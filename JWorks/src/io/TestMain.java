package io;

public class TestMain {

	public static void main(String[] args) {
		
		UI test = new UI();
		OutputGenerator test_out = new OutputGenerator();
		
		test.userChoicePrompt();
		
		String test_string = "[554] What is 1+1? Ans: 2";
		test_out.output(test_string);
		
	}

}
