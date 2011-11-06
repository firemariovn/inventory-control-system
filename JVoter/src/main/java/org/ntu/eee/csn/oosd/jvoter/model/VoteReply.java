package org.ntu.eee.csn.oosd.jvoter.model;

import java.io.Serializable;

/**
 * An instance of VoteReply is used to save reply data for a vote when selecting
 * a option
 * 
 * @author WangDing
 * 
 */
public class VoteReply implements Serializable {

	/**
	 * Sytem Generated ID, don't remove!
	 */
	private static final long serialVersionUID = 3532963886054894174L;

	String voteID;

	Integer choice;

	String replierHost;
	
	public VoteReply(String id,int op,String ip)
	{
	  this.voteID=id;
	  this.choice=op;
	  this.replierHost=ip;
			  
	}

	public String getVoteID() {
		return voteID;
	}

	public void setVoteID(String voteID) {
		this.voteID = voteID;
	}

	public Integer getChoice() {
		return choice;
	}

	public void setChoice(Integer choice) {
		this.choice = choice;
	}

	public String getReplierHost() {
		return replierHost;
	}

	public void setReplierHost(String replierHost) {
		this.replierHost = replierHost;
	}
	
	

}
