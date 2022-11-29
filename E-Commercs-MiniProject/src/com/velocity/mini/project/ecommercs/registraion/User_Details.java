package com.velocity.mini.project.ecommercs.registraion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import com.velocity.mini.project.ecommercs.admin.Admin;
import com.velocity.mini.project.ecommercs.connection.ConnectionDetails;



public class User_Details  {
	
	private int Id;
	private String FirstName;
	private String LastName;
	private String City;
	private String Email;
	private String ContactNo;
	private String PassWord;
	
	Connection connection=null;
	PreparedStatement preparedstatment =null;
	
//create method for user detail
		public void UserDetails() throws Exception
	{
	//object create 
		User_Details user_Details=new User_Details();
	//creating scanner class
			Scanner scan= new Scanner(System.in);
			System.out.println(" For Registration Entre - 1");
			System.out.println("  For log_In Entre - 2 ");
			System.out.println("If Admin then Entre - 3");
			//int a=scan.nextInt();
			int i=scan.nextInt();
			if(i==1)
			{
				System.out.println("entre name");
				String FirstName=scan.next();
				System.out.println("entre LastName");
				String LastName=scan.next();
				System.out.println("entre City");
				String City=scan.next();
				System.out.println("entre Email");
				String Email=scan.next();
				System.out.println("entre Contact Number");
				String ContactNo=scan.next();
				System.out.println("Create Password");
				String PassWord= scan.next();
				user_Details.insertUserData( FirstName, LastName,City,Email,ContactNo,PassWord);
					
				System.out.println("user log in");
				user_Details.login();
				//closing scanner
				
				
			}else if(i==2)
			{
				user_Details.login();
			}
			else if(i==3)
			{
				user_Details.login();
				Admin Admin = new Admin();
				Admin.AdminDetails();
			}
				
	}
		
//creating method for inserting data
		private void insertUserData(String FirstName,String LastName,String City,String Email,String ContactNo,String PassWord) throws SQLException
		{
			try {
				//calling connectiondetail class method
				ConnectionDetails connectionDetails=new ConnectionDetails();
				connection=connectionDetails.getConnectionDetails();
		 //writing query or preparedStatment
				preparedstatment= connection.prepareStatement("insert into userdetails(FirstName,LastName,City,Email,ContactNo,PassWord)"
		 		+ "values(?,?,?,?,?,?)");
		//set values 
				preparedstatment.setString(1, FirstName);
				preparedstatment.setString(2, LastName);
				preparedstatment.setString(3, City);
				preparedstatment.setString(4, Email);
				preparedstatment.setString(5, ContactNo);
				preparedstatment.setString(6, PassWord);
			int i=preparedstatment.executeUpdate();
			System.out.println("Sucessfully Registration ="+i);
			System.out.println(".....................................");
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
			finally
			{
				//closing the connection and statment
				connection.close();
				preparedstatment.close();
			}
	   }
	
		
//method for log in 
		public void login() throws Exception
		{
			Scanner scan= new Scanner(System.in);

			//System.out.println("load class");
			ConnectionDetails connectionDetails=new ConnectionDetails();
			connection=connectionDetails.getConnectionDetails();
			Statement stm=connection.createStatement();
			
			System.out.println("entre Username");
			String Email=scan.next();
			
			System.out.println("entre PassWord");
			String PassWord=scan.next();
			 
		//writing query 
			String sql= "select * from userdetails where Email='"+Email+"' and PassWord='"+PassWord+"' ";	
				
		//set values 
				ResultSet rs=stm.executeQuery(sql);
			if(rs.next())
				{
					System.out.println("log in Succefull");
				}
				else
				{
					System.out.println("fail");
					System.out.println("please Re Entre Usename and PassWord for log in");
					ConnectionDetails connectionDetail=new ConnectionDetails();
					connection=connectionDetails.getConnectionDetails();
					
					Statement stmt=connection.createStatement();
					System.out.println("entre Username");
					String Emai=scan.next();
					
					System.out.println("entre PassWord");
					String PassWords=scan.next();
					String sq= "select * from userdetails where Email='"+Emai+"' and PassWord='"+PassWords+"' ";
					ResultSet rss=stm.executeQuery(sq);
					connection.close();
					stmt.close();
					rss.close();
					
				}
					
				connection.close();
				stm.close();
				rs.close();
				
			
		}
		
	
}
