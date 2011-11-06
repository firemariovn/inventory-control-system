package org.ntu.eee.csn.oosd.jvoter.model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A VoteResult is used to save final vote results for a vote
 * 
 * @author WangDing
 * 
 */
public class VoteResult extends Vote {

	/**
	 * For example: for option 1 , there is 10 users to vote, I put a number "10"
	 * to the ArrayList. 
	 */
	ArrayList<Integer> result = new ArrayList<Integer>();


	public VoteResult() {

		// The below is just test statements
	
	}
	public ArrayList<Integer> getResult() {
		return result;
	}

	public void setResult(ArrayList<Integer> result) {
		this.result = result;
	}




	
}