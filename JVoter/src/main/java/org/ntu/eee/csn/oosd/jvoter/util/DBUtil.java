package org.ntu.eee.csn.oosd.jvoter.util;

import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.UUID;

public class DBUtil {
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException{
		Class.forName("org.h2.Driver");
		Connection conn = DriverManager.getConnection("jdbc:h2:./data/jvoter","sa","");
		System.out.println(conn.toString());
		return conn;		
		
	}
	
	public static String generateGUID(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		
	}
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		getConnection();
	}

}
