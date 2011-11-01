package org.ntu.eee.csn.oosd.jvoter.model;

import java.util.HashMap;

/**
 * A VoteResult is used to save final vote results for a vote
 * 
 * @author WangDing
 * 
 */
public class VoteResult extends Vote {

	/**
	 * For example: for option 1 , there is 10 users to vote, I put this
	 * key-value pair "1:10" to a hashmap. for option 2, there is 20 users to
	 * vote, I put this key-value pair "2:20" to a hashmap.
	 */
	HashMap result = new HashMap();

	public VoteResult() {

		// The below is just test statements
		result.put(1, 10);
		result.put(2, 20);
		result.put(3, 30);
		result.put(4, 40);
	}

	public HashMap getResult() {
		return result;
	}

	public void setResult(HashMap result) {
		this.result = result;
	}
	
	
}
