package org.ntu.eee.csn.oosd.jvoter.exception;

/**
 * Exception when accessing database
 * @author WangDing
 *
 */
public class DBException extends Exception {

	/**
	 * Sytem Generated ID, don't remove!
	 */
	private static final long serialVersionUID = 4114381874742115492L;

	/**
	 * Constructor
	 */
	public DBException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 */
	public DBException(String msg) {
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
	public DBException(String msg, Throwable t) {
		super(msg, t);
	}

}