package com.velocity.mini.project.ecommercs.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionDetails {
	
	Connection connection = null ;
	public Connection getConnectionDetails()
	{
		try {
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//creating connection
		  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root", "root");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return connection;
	}
	
	// by
	public Connection getBuyConnectionDetails()
	{
		try {
			//Loading the Driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			//creating connection
		  connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice", "root", "root");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return connection;
	}

}
