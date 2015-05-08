/**
 * 
 */
package it.polimi.ingsw.lab.utils;

/**
 *
 *
 */
public class NotValidObjectException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public NotValidObjectException() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public NotValidObjectException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 */
	public NotValidObjectException(String arg0) {
		super(arg0);
	
	}

	/**
	 * @param arg0
	 */
	public NotValidObjectException(Throwable arg0) {
		super(arg0);
	
	}

}
