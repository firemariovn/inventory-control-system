package org.ntu.eee.csn.oosd.jvoter.model;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;



/**
 * 
 * @author WangDing
 *
 */
public class VoteTest extends TestCase {
	private static Logger LOGGER = Logger.getLogger(VoteTest.class);

	
	/**
	 * Constructor
	 */
	public VoteTest() {
		super();
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("instantiate");
	}

	/**
	 * Constructor
	 * 
	 * @param name
	 *            test name
	 */
	public VoteTest(String name) {
		super(name);
		if (LOGGER.isDebugEnabled())
			LOGGER.debug("instantiate");
	}

	/**
	 * Declares the test suite.
	 * 
	 * @return the test suite.
	 */
	public static TestSuite suite() {
		TestSuite testSuite = new TestSuite(VoteTest.class);
		return testSuite;
	}

	public void testVoteTable(){
		
		LOGGER.info("testVoteTable");
	}
}
