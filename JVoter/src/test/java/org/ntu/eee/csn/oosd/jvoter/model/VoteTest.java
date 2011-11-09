package org.ntu.eee.csn.oosd.jvoter.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;



/**
 * 
 * @author WangYabin
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
		
		
		Vote tVote = new Vote();
		String voteID = tVote.getUniqueID();
		String name = "test_01";
		String desc = "which unit test do you like?";
		String initiatorIP = "127.0.0.0.1";
		String initiator = "wangyabin";
		Date deadline = new Date();
		boolean isInitiator = true;
		boolean isCanceled = false;
		boolean isReply = false;
		String option1 = "a";
		String option2 = "b";
		String option3 = "c";
		String option4 = "d";
		ArrayList<String> options = new ArrayList<String>();
		options.add(option1);
		options.add(option2);
		options.add(option3);
		options.add(option4);
		
		tVote.setVoteID(voteID);
		tVote.setName(name);
		tVote.setDesc(desc);
		tVote.setInitiatorIP(initiatorIP);
		tVote.setInitiator(initiator);
		tVote.setDeadline(deadline);
		tVote.setIsInitiator(isInitiator);
		tVote.setCanceled(isCanceled);
		tVote.setReply(isReply);
		tVote.setOptions(options);
		
		tVote.add();
		assertEquals(voteID, tVote.select().getVoteID());
		tVote.delete();
		
	}
}
