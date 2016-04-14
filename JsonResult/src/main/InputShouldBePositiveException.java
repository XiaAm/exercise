package main;

public class InputShouldBePositiveException extends Exception {
	
	private int input;
	public InputShouldBePositiveException(int n){
		this.input = n;
	}
}