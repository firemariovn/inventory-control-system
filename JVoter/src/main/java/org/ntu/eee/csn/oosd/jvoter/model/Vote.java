package org.ntu.eee.csn.oosd.jvoter.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * An instance of vote is used to save the vote content when initiating a vote
 * 
 * @author WangDing
 * 
 */
public class Vote implements Serializable {

	/**
	 * Sytem Generated ID, don't remove!
	 */
	private static final long serialVersionUID = -7039854193326114361L;

	String voteID;

	// Note : by default, there is only no more than 4 options for a vote
	ArrayList<String> options = new ArrayList<String>();

	String desc;

	String initiator;

	Date deadline = new Date();

	public String getVoteID() {
		return voteID;
	}

	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}

	public ArrayList<String> getOptions() {
		return options;
	}

	public void setOptions(ArrayList<String> options) {
		this.options = options;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getInitiator() {
		return initiator;
	}

	public void setInitiator(String initiator) {
		this.initiator = initiator;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	
	
}
