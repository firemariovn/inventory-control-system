package org.ntu.eee.csn.oosd.jvoter.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import org.ntu.eee.csn.oosd.jvoter.util.DBUtil;
import org.ntu.eee.csn.oosd.jvoter.util.JVoterProtocol;

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
	
	DBUtil db = new DBUtil();
	Connection conn = null;
	Statement stmt=null;
	ResultSet rs = null;
	String sql = null;

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
	
	public void addVote(String voteID, String desc, String initiator, String dateLine){	
		try {
			sql = "INSERT INTO vote VALUES(?, ?, ?, ?)";
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,voteID);
			ps.setString(2, desc);
			ps.setString(3, initiator);
			ps.setString(4, dateLine);
			ps.execute(sql);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
		
	}
	public void deleteVote(String voteID){
		try {
			sql = "delete vote where voteid=?";
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,voteID);
			ps.execute(sql);
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		}
	}
}
