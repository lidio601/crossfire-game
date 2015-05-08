package it.polimi.ingsw.lab.utils;

public class NotValidParameterException extends Exception {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NotValidParameterException() {
		super();

	}

	public NotValidParameterException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	
	}

	public NotValidParameterException(String arg0) {
		super(arg0);
		
	}

	public NotValidParameterException(Throwable arg0) {
		super(arg0);
		
	}

}
