package com.lti.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.lti.pojo.CustomerClass;

public class CustomerDao {

	public void add(CustomerClass customer) {
		Connection conn = null;
		PreparedStatement stmt = null;

		Properties dbprop = new Properties();
		String sql = "insert into customer values(?,?,?)";
		// p.load(new FileReader("/MyWebApp/src/oracle.info"));
		try {
			dbprop.load(this.getClass().getClassLoader().getResourceAsStream("oracle.info"));
			System.out.println(dbprop.getProperty("driverClassName"));
			Class.forName(dbprop.getProperty("driverClassName"));
			conn = DriverManager.getConnection(dbprop.getProperty("url"), dbprop.getProperty("username"),
					dbprop.getProperty("password"));
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, customer.getId());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getEmail());
			stmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(conn != null || stmt != null)
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
