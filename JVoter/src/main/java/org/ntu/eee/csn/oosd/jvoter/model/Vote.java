package org.ntu.eee.csn.oosd.jvoter.model;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.ntu.eee.csn.oosd.jvoter.util.DBUtil;
import org.ntu.eee.csn.oosd.jvoter.util.JVoterProtocol;
import org.omg.CORBA.TRANSACTION_MODE;

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

	String hostName;
	String hostIp;
	int isInitiator;
	int isValidate;
	
	String desc;

	String initiatorIp;

	Date deadline = new Date();
	
	transient DBUtil db = new DBUtil();
	Connection conn = null;
	Statement stmt=null;
	ResultSet rs = null;
	String sql = null;
	PreparedStatement ps = null;
	
	SimpleDateFormat   format   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");

	public String getUniqueID(){
		return DBUtil.generateGUID();
		
		
	}
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

	public String getInitiatorIp() {
		return initiatorIp;
	}

	public void setInitiatorIp(String initiatorIp) {
		this.initiatorIp = initiatorIp;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	
	public String getHostIp() {
		return hostName;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}
	public int getIsInitiator() {
		return isInitiator;
	}

	public void setIsInitiator(int isInitiator) {
		this.isInitiator = isInitiator;
	}
	public int getIsValidate() {
		return isValidate;
	}

	public void setIsValidate(int isValidate) {
		this.isValidate = isValidate;
	}
	public void add(){
		
		try {
			sql = "INSERT INTO VOTE(VOTEID, HOSTNAME, HOSTIP, DESC, INITIATORIP," +
					"DEADLINE, ISINITIATOR, ISVALIDATE) VALUES(?,?,?,?,?,?,?,?)";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.voteID);
			ps.setString(2, this.hostName);
			ps.setString(3, this.hostIp);
			ps.setString(4, this.desc);
			ps.setString(5, this.initiatorIp);
			ps.setString(6, format.format(deadline));
			ps.setInt(7, this.isInitiator);
			ps.setInt(8, this.isValidate);
			ps.executeUpdate();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void delete(){
		try {
			sql = "delete from vote where voteid=?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1,this.voteID);
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
	public Vote select(){
		Vote vote = null;
		try {
			sql = "select * from vote where VOTEID=?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.voteID);
			rs = ps.executeQuery();

			while(rs.next()){
				vote = new Vote();
				vote.setVoteID(rs.getString("voteid"));
				vote.setDesc(rs.getString("desc"));
				vote.setInitiatorIp(rs.getString("initiatorip"));
				try { 
					vote.setDeadline(format.parse(rs.getString("deadline")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				vote.setHostName(rs.getString("hostname"));
				vote.setHostIp(rs.getString("hostip"));
				vote.setIsInitiator(rs.getInt("isinitiator"));
				vote.setIsValidate(rs.getInt("isvalidate"));
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vote;
	}
	
	public List all(){ 
		List<Vote> vlist = new LinkedList<Vote>();
		try {
			sql = "select * from vote";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();

			while(rs.next()){
				Vote tmpVote = new Vote();
				tmpVote.setVoteID(voteID);
				tmpVote.setDesc(rs.getString("desc"));
				tmpVote.setInitiatorIp(rs.getString("initiatorip"));
				try { 
					tmpVote.setDeadline(format.parse(rs.getString("deadline")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tmpVote.setHostName(rs.getString("hostname"));
				tmpVote.setHostIp(rs.getString("hostip"));
				tmpVote.setIsInitiator(rs.getInt("isinitiator"));
				tmpVote.setIsValidate(rs.getInt("isvalidate"));
				vlist.add(tmpVote);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vlist;
	}
	public List getVoteByIsValidate(int isValidate){ 
		List<Vote> vlist = new LinkedList<Vote>();
		try {
			sql = "select * vote where isvalidate=?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, isValidate);
			rs = ps.executeQuery();

			while(rs.next()){
				Vote tmpVote = new Vote();
				tmpVote.setVoteID(rs.getString("voteid"));
				tmpVote.setDesc(rs.getString("desc"));
				tmpVote.setInitiatorIp(rs.getString("initiatorip"));
				try { 
					tmpVote.setDeadline(format.parse(rs.getString("deadline")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				tmpVote.setHostName(rs.getString("hostname"));
				tmpVote.setHostIp(rs.getString("hostip"));
				tmpVote.setIsInitiator(rs.getInt("isinitiator"));
				tmpVote.setIsValidate(isValidate);
				vlist.add(tmpVote);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vlist;
	}
	
}
