package javaBeanTest;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;


public class DBUtil {
	boolean inited = false;

	// load the driver
	public void initJDBC() throws ClassNotFoundException {
		// load mysql jdbc driver
		Class.forName("com.mysql.jdbc.Driver");
		inited = true;
		System.out.println("Success loading Mysql Driver");
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		if (!inited) {
			initJDBC();
		}
		// RUL:JDBC:mysql//server address/database name
		Connection conn = DriverManager.getConnection(
				"jdbc:mysql://3.112.204.243/user?useUnicode=true&characterEncoding=utf-8", "root", "xusong");
		return conn;
	}

public boolean loginSuccess(String name,String password) {
	boolean returnValue = false;
	String sql = "SELECT * FROM user";
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	
	try {
		conn = getConnection();
		stmt = (Statement) conn.createStatement();
		rs = stmt.executeQuery(sql);
		while(rs.next()){
			String userNameInDB = rs.getString("name");
			String passwordInDB = rs.getString("password");
			
			if(userNameInDB.equals(name)&&passwordInDB.equals(password) ) {
				returnValue = true;
				break;
			}
		}
	}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	return returnValue;
	}
}

