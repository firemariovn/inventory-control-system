package org.ntu.eee.csn.oosd.jvoter.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;
/**
 * 
 * @author WangDing G1101030A
 *
 */
public class DBUtil {
	
	/**Get a DB Connection from h2 DB
	 * @author WangDing G1101030A
	 * @return
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/jvoter","sa","");
		System.out.println(conn.toString());
		return conn;		
		
	}
	/**
	 * 
	 * @author WangDing G1101030A
	 */
	public static String generateGUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		getConnection();
	}

}
