package org.ntu.eee.csn.oosd.jvoter.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ntu.eee.csn.oosd.jvoter.util.DBUtil;

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
	
	transient DBUtil db = new DBUtil();
	Connection conn = null;
	Statement stmt=null;
	ResultSet rs = null;
	String sql = null;
	PreparedStatement ps = null;
	
	SimpleDateFormat   format   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");
	
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
	public void add(){
		sql = "INSERT INTO VOTE(VOTEID, CHOICE, REPLIERHOST)VALUES(?,?,?)";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.voteID);
			ps.setInt(2, this.choice);
			ps.setString(3, this.replierHost);
			ps.executeUpdate();
			conn.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
