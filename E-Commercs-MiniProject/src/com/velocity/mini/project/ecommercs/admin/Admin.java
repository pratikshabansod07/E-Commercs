package com.velocity.mini.project.ecommercs.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

import com.velocity.mini.project.ecommercs.connection.ConnectionDetails;

public class Admin {
	
	Connection connection=null;
	PreparedStatement preparedStatement=null;
//public static void main(String[] args) throws Exception

public void AdminDetails()
{
	Admin admin= new Admin();
		
		//User_Details user_Details= new User_Details();
		//user_Details.UserDetails();
		
	System.out.println("___________________________________________________");
	System.out.println("************************************************");
	System.out.println("All products Details");
	System.out.println("************************************************");
		admin.getproductDetails();
		
	//System.out.println("___________________________________________________");	
	System.out.println("************************************************");	
	System.out.println("Users Details ");
	System.out.println("************************************************");
	System.out.println("___________________________________________________");
	
		admin.getuserdetails();
		
	System.out.println("___________________________________________________");	
	System.out.println("************************************************");
	System.out.println("Users History Details");
	System.out.println("************************************************");
	System.out.println("___________________________________________________");
	
	admin.getuserhistory();
		
		System.out.println("__________________xxxxxx________________");	
		
}
	
//method showing products details
private void getproductDetails()
{
	try {
	ConnectionDetails ConnectionDetails= new ConnectionDetails();
	connection=ConnectionDetails.getConnectionDetails();
	 preparedStatement= connection.prepareStatement("select*from ProductDetails ");
	ResultSet rs=preparedStatement.executeQuery();
	System.out.println("___________________________________________________");	
	System.out.println("product Id"+"-----"+"Name"+"-----"+"Price"+"/-");
	while(rs.next())
	{
		int Pid = +rs.getInt(1);
		String Name = rs.getString(2);
		int Price = rs.getInt(5);
		System.out.println(Pid+"-----"+Name+"-----"+Price+"/-");
		System.out.println("___________________________________________________");
		
	}
	
	//closing resourse
	connection.close();
	preparedStatement.close();
	rs.close();
	}
	
	catch (Exception e) {

		e.printStackTrace();
	}
	
}
   
//methods for userDetails
private void getuserdetails()
{
	try {
		ConnectionDetails ConnectionDetails= new ConnectionDetails();
		connection=ConnectionDetails.getConnectionDetails();
		//select query use
	     preparedStatement= connection.prepareStatement("select*from userdetails  ");
	     //query execute
	      ResultSet rs=preparedStatement.executeQuery();
	  System.out.println("FirstName-----LastName-----City-----Email-----ContactNo");
	     
	  while(rs.next())
	  {
		//int id =rs.getInt(1);
		String FirstName = rs.getString(2);
		String LastName = rs.getString(3);
		String City = rs.getString(4);
		String Email = rs.getString(5);
		String ContactNo = rs.getString(6);
		System.out.println(FirstName+"-----"+LastName+"-----"+City+"-----"+Email+"-----"+ContactNo);
		System.out.println("___________________________________________________");
		
	}
	//closing resourse
	connection.close();
	preparedStatement.close();
	rs.close();
	} 
	
	catch (Exception e) {

		e.printStackTrace();
	}
	
}


//user Buying history
	private void getuserhistory()
	{
	try {
		ConnectionDetails ConnectionDetails= new ConnectionDetails();
		connection=ConnectionDetails.getConnectionDetails();
		//using select query
	   preparedStatement= connection.prepareStatement("select * from  practice.userhistory inner join practice.userdetails\r\n" + 
	   		"on practice.userhistory.Email = practice.userdetails.Email  ");
	//query execute
	ResultSet rs=preparedStatement.executeQuery();
	System.out.println("UserHistory_id-----Product_id-----Product_Name-----Description-----Quntity-----Price-----"
			+ "TotalPrice-----FirstName-----LastName-----City-----Email-----ContactNo");
	while(rs.next())
	{
		int Hid = rs.getInt(1);
		int Pid =rs.getInt(2);
		String Name = rs.getString(3);
		String Description = rs.getString(4);
		int Quntity = rs.getInt(5);
	    int Price = rs.getInt(6);
		int TotalPrice = rs.getInt(7);
		//Email = "+rs.getString(8));
		String FirstName = rs.getString(2);
		String LastName = rs.getString(3);
		String City = rs.getString(4);
		String Email = rs.getString(5);
		String ContactNo = rs.getString(6);
		System.out.println(Hid+"-----"+Pid+"-----"+Name+"-----"+Description+"-----"+Quntity+"-----"+Price+"-----"+
		TotalPrice+"-----"+FirstName+"-----"+LastName+"-----"+City+"-----"+Email+"-----"+ContactNo);
		System.out.println("___________________________________________________");
	}
	//closing resourse
	connection.close();
	preparedStatement.close();
	rs.close();
	
	} 
	
	catch (Exception e) {

		e.printStackTrace();
	}
	
}




	

}



