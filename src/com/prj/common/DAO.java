package com.prj.common;

import java.sql.*;

/*
* 데이터베이스의 연결정보를 활용해서 세션획득
* url, id, pass
*/
public class DAO {
	String url = "jdbc:oracle:thin:@localhost:1521:xe";
	String id = "scott";
	String pass = "tiger";
	Connection conn;
	Statement stmt;
	ResultSet rs;
	PreparedStatement psmt;

	// Connenction 생성하는 메소드,
	public void getConnect() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(url, id, pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	} // end of getConnect.

	public void disconnect() {
		try {
			if (conn != null)
				conn.close();
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
