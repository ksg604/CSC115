/** 
 *  Name       : Kevin San Gabriel
 *  Student ID : V00853258
 */

/**
 *An exception thrown when a stack is empty.
 */

public class StackEmptyException extends RuntimeException {


	/**Creates an exception
	 * @param msg The message to the calling program
	 */
	public StackEmptyException(String msg) {
		super(msg);
	}
	
	/** 
	 * Creates an exception without a message
	 */	
	public StackEmptyException() {
		super();
	}

}