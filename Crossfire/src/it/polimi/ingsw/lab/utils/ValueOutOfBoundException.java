package it.polimi.ingsw.lab.utils;

public class ValueOutOfBoundException extends Exception {
	public int original_value;
	
	private static final long serialVersionUID = 1L;

	public ValueOutOfBoundException(int orig) {
		this.original_value = orig;
	}
	
	public ValueOutOfBoundException() {
		super();
	}
}
