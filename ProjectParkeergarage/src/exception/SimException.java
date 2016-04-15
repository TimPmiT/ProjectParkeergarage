package exception;

/**
 * This class takes care of creating an exception object when an exception appears.
 * 
 * @author Femke Hoornveld, Koen Gorter
 * @version 1.0 (11-04-2016)
 */
public class SimException extends Exception {
	private static final long serialVersionUID = -5268539085229462286L;

	/**
	 * This is the constructor for the SimException object.
	 * @param message  This is the message that is passed through from the occurring exception.
	 */
	public SimException(String message) {
		super(message);
	}
}
