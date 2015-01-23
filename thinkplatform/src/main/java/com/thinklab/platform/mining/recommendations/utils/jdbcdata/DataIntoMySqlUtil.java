package com.thinklab.platform.mining.recommendations.utils.jdbcdata;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataIntoMySqlUtil {
	
	public Connection getConnection(String host,String port,String userName,String passWord,String dbName){
		Connection conn = null;
		try{
			String url = "jdbc:mysql://"+host+":"+port+"/"+dbName;
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(url, userName, passWord);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;
	}
	
	
}
