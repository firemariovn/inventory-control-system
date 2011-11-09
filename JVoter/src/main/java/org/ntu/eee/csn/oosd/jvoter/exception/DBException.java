package org.ntu.eee.csn.oosd.jvoter.exception;

/**
 * Exception when accessing database
 * @author WangDing G1101030A
 *
 */
public class DBException extends Exception {

	/**
	 * Sytem Generated ID, don't remove!
	 */
	private static final long serialVersionUID = 4114381874742115492L;

	/**
	 * Constructor
	 * @author WangDing G1101030A
	 */
	public DBException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param msg
	 *            Error message
	 * @author WangDing G1101030A           
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
	 * @author WangDing G1101030A           
	 */
	public DBException(String msg, Throwable t) {
		super(msg, t);
	}

}
