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
	
	SimpleDateFormat   format   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm:ss");

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
	
	public void add(){
		
		try {
			sql = "INSERT INTO VOTE(VOTEID, DESC, INITIATOR, DEADLINE) VALUES(?,?,?,?)";
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, voteID);
			ps.setString(2, desc);
			ps.setString(3, initiator);
			ps.setString(4, format.format(deadline));
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
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1,voteID);
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
	public Vote select(String voteID){
		Vote vote = null;
		try {
			sql = "select DESC, INITIATOR, DEADLINE from vote where VOTEID=?";
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, voteID);
			rs = ps.executeQuery();

			while(rs.next()){
				vote = new Vote();
				vote.setVoteID(voteID);
				vote.setDesc(rs.getString("desc"));
				vote.setInitiator(rs.getString("initiator"));
				try { 
					vote.setDeadline(format.parse(rs.getString("deadline")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
		List<Vote> list = new LinkedList<Vote>();
		try {
			sql = "select * from vote";
			conn = db.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			rs = ps.executeQuery(sql);

			while(rs.next()){
				Vote tmpVote = new Vote();
				tmpVote.setVoteID(rs.getString("voteid"));
				tmpVote.setDesc(rs.getString("desc"));
				tmpVote.setInitiator(rs.getString("initiator"));
				try {
					tmpVote.setDeadline(format.parse(rs.getString("deadline")));
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				list.add(tmpVote);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
}
