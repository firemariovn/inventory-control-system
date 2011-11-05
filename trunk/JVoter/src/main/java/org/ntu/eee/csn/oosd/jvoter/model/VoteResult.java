package org.ntu.eee.csn.oosd.jvoter.model;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.ntu.eee.csn.oosd.jvoter.util.DBUtil;

/**
 * A VoteResult is used to save final vote results for a vote
 * 
 * @author WangDing
 * 
 */
public class VoteResult {
	String voteId;
	String voterIp;
	int option;
	int rid;
	
	transient DBUtil db = new DBUtil();
	Connection conn = null;
	Statement stmt=null;
	ResultSet rs = null;
	String sql = null;
	PreparedStatement ps = null;
	
	public void setResultId(int rid) {
		this.rid = rid;
	}
	public int getResultId() {
		return rid;
	}
	
	public void setVoteId(String voteId) {
		this.voteId = voteId;
	}

	public String getVoteId() {
		return voteId;
	}
	public void setVoterIp(String voterIp) {
		this.voterIp = voterIp;
	}

	public String getVoterIp() {
		return voterIp;
	}
	public void setOption(int option) {
		this.option = option;
	}

	public int getOption() {
		return option;
	}
	public void add(){
		sql = "insert into voteresult(voteid, voterip, option) values (?,?,?)";
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, this.voteId);
			ps.setString(2, this.voterIp);
			ps.setInt(3, this.option);
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
	public void delete(){
		try {
			sql = "delete from voteresult where rid=?";
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1,this.rid);
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
	public VoteResult select(){
		VoteResult voteResult = null;
		try {
			sql = "select * from voteresult where rid=?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setInt(1, this.rid);
			rs = ps.executeQuery();
			while(rs.next()){
				voteResult = new VoteResult();
				voteResult.setResultId(rs.getInt("rid"));
				voteResult.setOption(rs.getInt("option"));
				voteResult.setVoterIp(rs.getString("voterip"));
				voteResult.setVoteId(rs.getString("voteid"));	
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return voteResult;
	}
	public List getVoteResultByVoteId(String voteId){ 
		List<VoteResult> rlist = new LinkedList<VoteResult>();
		try {
			sql = "select * voteresult where voteid=?";
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, voteId);
			rs = ps.executeQuery();

			while(rs.next()){
				VoteResult tmpVoteResult = new VoteResult();
				tmpVoteResult.setResultId(rs.getInt("rid"));
				tmpVoteResult.setVoterIp(rs.getString("voterip"));
				tmpVoteResult.setOption(rs.getInt("option"));
				tmpVoteResult.setVoteId(rs.getString("voteid"));
				rlist.add(tmpVoteResult);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rlist;
	}
	public int getRId(){
		sql = "select top 1 * from voteresult order by rid desc";
		int rid=0;
		try {
			conn = db.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				rid = rs.getInt("rid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rid;
	}
}
