package org.ntu.eee.csn.oosd.jvoter.exception;

/**
 * Exception during JVoter communication
 * @author WangDing
 *
 */
public class JVoterCommunicationException extends Exception{

	/**
	 * Sytem Generated ID, don't remove!
	 */
	private static final long serialVersionUID = 4114381874742115492L;

	/**
	 * Constructor
	 */
	public JVoterCommunicationException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public JVoterCommunicationException(String msg) {
		super(msg);
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 * @param t
	 *            Stack trace
	 */
	public JVoterCommunicationException(String msg, Throwable t) {
		super(msg, t);
	}

}
