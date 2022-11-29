package com.velocity.mini.project.ecommercs.main.product;

import java.sql.Statement;
import java.util.Scanner;

import com.velocity.mini.project.ecommercs.connection.ConnectionDetails;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Product_Details {

	Connection connection =null;
	Statement statement = null;
	PreparedStatement preparedStatement=null;
	
	
	public int productDetails() throws Exception
	{
	
		Product_Details product_Details=new Product_Details();
		product_Details.getproductDetails();
		
		Scanner scan= new Scanner (System.in);
		
		System.out.println("please select product id");
		int  Pid=scan.nextInt();
		boolean founds=false;
		while(!founds)
		{
		if(Pid<=10)
		{
		product_Details.userproduct(Pid);
		founds=true;
		}else if(Pid>10)
		{
			System.out.println("please re entre product id");
			Pid=scan.nextInt();
			
		}
		}
		
		System.out.println("please entre Quntity of product");
		int Quntity=scan.nextInt();
		
		System.out.println("for Buying product please entre 1 and Add to Cart entre 2" );
		int B=scan.nextInt();
		
		ConnectionDetails ConnectionDetails= new ConnectionDetails();
		connection=ConnectionDetails.getConnectionDetails();
		 preparedStatement= connection.prepareStatement("select Name,Description,Quentity,Price from ProductDetails where Pid='"+Pid+"' ");
		 ResultSet rs=preparedStatement.executeQuery();
		 while(rs.next())
		 {
		 String Name=rs.getString(1);
		String Description= rs.getString(2);
		int Quentity = rs.getInt(3);
		 Quentity=Quentity-Quntity;
		int Price= rs.getInt(4); 
		 int TotalPrice =0;
		 TotalPrice=Price*Quntity;
		
		if(B==1)//buy details
		{
			System.out.println("for buying insert Email");
			String Email=scan.next();
			product_Details.insertbuydetails(Pid,Name,Description, Quntity,Price,TotalPrice,Email);
			
			System.out.println("data sucuss inter into buy");
			  product_Details.buyDetails(Pid);
			  
			  product_Details.buydetailsdelete();
			  
			  
			  System.out.println("thank you for shopping ");
			
				founds=true;
			break;
		
		
		}
		 
	     else if(B==2)//cart detail
		
			{
	    	 System.out.println("for add to cart insert Email");
				String Email=scan.next();
				product_Details.insertcartdetails(Pid, Name, Description, Quntity, Price, TotalPrice,Email);
				product_Details.cartDetails(Pid);
			
				System.out.println("for by insert 1 and 2 for cart");
				int i=scan.nextInt();
				//while(!founds)
				
				if(i==1)
				{
				 product_Details.buyDetails(Pid);
				
				 System.out.println("thank you for shopping ");
				 product_Details.buydetailsdelete();
				  
					
				 break;
				
				} else if(i==2)
				
				{
					product_Details.getproductDetails();
					System.out.println("please select product id");
					int  Pids=scan.nextInt();
					boolean found=false;
					while(!founds)
					{
					if(Pid<=10)
					{
					product_Details.userproduct(Pid);
					founds=true;
					}else if(Pid>10)
					{
						System.out.println("please re entre product id");
						Pid=scan.nextInt();
						
					}
					}
					
					System.out.println("please entre Quntity of product");
					int Quntitys=scan.nextInt();
					System.out.println("for add to cart insert Email");
					String Emails=scan.next();
					product_Details.insertcartdetails(Pid, Name, Description, Quntity, Price, TotalPrice,Email);
					System.out.println("for by insert 1 and 2 for cart");
					int s=scan.nextInt();
					//while(!founds)
					
					if(s==1)
					{
					 product_Details.buyDetails(Pid);
					 System.out.println("thank you for shopping ");
						
					 product_Details.buydetailsdelete();
					 break;
					
					}
				
					product_Details.cartDetails(Pid);
				}
				
			}
		 }
		return B;
		
		
		 
		
		 
	}
		
		 //delete buys data
	public void buydetailsdelete() throws Exception
	{
		ConnectionDetails ConnectionDetails= new ConnectionDetails();
		connection=ConnectionDetails.getConnectionDetails();
		Statement statement=connection.createStatement();
		String query="DELETE FROM  practice.`buys` ";
		statement.execute(query);
		System.out.println(" delete buy  record into DB");
		
	}
	
	//delete cart data
	public void cartdetailsdelete() throws Exception
	{
		ConnectionDetails ConnectionDetails= new ConnectionDetails();
		connection=ConnectionDetails.getConnectionDetails();
		Statement statement=connection.createStatement();
		String query="TRUNCATE  practice.`cart` ";
		statement.execute(query);
		System.out.println(" delete cart  record into DB");
		
				
	}
	

//buy method
	private void insertbuydetails(int Pid,String Name ,String Description,int Quntity,int Price,int TotalPrice,String Email ) throws Exception
	{
		try {
			
			ConnectionDetails ConnectionDetails= new ConnectionDetails();
			connection=ConnectionDetails.getConnectionDetails();

	 //insert query
			preparedStatement= connection.prepareStatement("insert into buys (Pid,Name,Description,Quntity,Price,TotalPrice,Email)"
	 		+ "values(?,?,?,?,?,?,?)");
					preparedStatement.setInt(1, Pid);
					preparedStatement.setString(2, Name);
					preparedStatement.setString(3, Description);
					preparedStatement.setInt(4, Quntity);
					preparedStatement.setInt(5, Price);
					preparedStatement.setInt(6, TotalPrice);
					preparedStatement.setString(7, Email);
					int i=preparedStatement.executeUpdate();
					System.out.println("inserting data ="+i);
									
		//userhistory				
		preparedStatement= connection.prepareStatement("insert into userhistory (Pid,Name,Description,Quntity,Price,TotalPrice,Email) "
				+ "select Pid,Name,Description,Quntity,Price,TotalPrice,Email from buys");
		int b=preparedStatement.executeUpdate();
		System.out.println("inserting to user history ="+b);
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			//closing connection 
			connection.close();
			preparedStatement.close();
		}
		
	}
	
//buydetails
	public void buyDetails(int Pid) throws Exception
	{
		ConnectionDetails ConnectionDetails= new ConnectionDetails();
		connection=ConnectionDetails.getConnectionDetails();
	 preparedStatement= connection.prepareStatement("select*from buys where Pid='"+Pid+"'  ");
	ResultSet rs=preparedStatement.executeQuery();
	//query execute
	while(rs.next())
	{
		System.out.println("Pid =" +rs.getInt(2));
		System.out.println("Name = "+rs.getString(3));
		System.out.println("Decripstion = "+rs.getString(4));
		System.out.println("Quntity = "+rs.getInt(5));
		System.out.println("Price = "+rs.getInt(6));
		System.out.println("TotalPrice = "+rs.getInt(7));
		
	}
	//closing resourse
	connection.close();
	preparedStatement.close();
	rs.close();
	} 
	
//inerting cart details
	private void insertcartdetails(int Pid,String Name , String Description,int Quntity,int Price,int TotalPrice,String Email) throws Exception
	{
		try {
			ConnectionDetails ConnectionDetails= new ConnectionDetails();
			connection=ConnectionDetails.getConnectionDetails();

	 //insert query
			preparedStatement= connection.prepareStatement("insert into cart (Pid,Name,Description,Quntity,Price,TotalPrice,Email)"
	 		+ "values(?,?,?,?,?,?,?)");
					preparedStatement.setInt(1, Pid);
					preparedStatement.setString(2, Name);
					preparedStatement.setString(3, Description);
					preparedStatement.setInt(4, Quntity);
					preparedStatement.setInt(5, Price);
					preparedStatement.setInt(6, TotalPrice);
					preparedStatement.setString(7, Email);
					int i=preparedStatement.executeUpdate();
					System.out.println("Dta succefully add to cart ="+i);
					
		preparedStatement= connection.prepareStatement("insert into buys (Pid,Name,Description,Quntity,Price,TotalPrice,Email) "
					+ "select Pid,Name,Description,Quntity,Price,TotalPrice,Email from cart");
			int b=preparedStatement.executeUpdate();
			System.out.println("inserting to user history ="+b);
					
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		finally
		{
			//closing the connection and statment
			connection.close();
			preparedStatement.close();
		}
		
	}
	
	//cart details
	public void cartDetails(int Pid) throws Exception
	{
		ConnectionDetails ConnectionDetails= new ConnectionDetails();
		connection=ConnectionDetails.getConnectionDetails();
	 preparedStatement= connection.prepareStatement("select*from cart where Pid='"+Pid+"' ");
	ResultSet rs=preparedStatement.executeQuery();
	while(rs.next())
	{
		System.out.println("Pid =" +rs.getInt(2));
		System.out.println("Name = "+rs.getString(3));
		System.out.println("Decripstion = "+rs.getString(4));
		System.out.println("quentity = "+rs.getInt(5));
		System.out.println("Price = "+rs.getInt(6));
		System.out.println("TotalPrice =" +rs.getInt(7));
	}
	//delete cart details
	
	
	//closing resourse
	connection.close();
	preparedStatement.close();
	rs.close();
	} 
	
	
//method for showing all dtails of products
	private void getproductDetails()
	{
		try {
			ConnectionDetails ConnectionDetails= new ConnectionDetails();
			connection=ConnectionDetails.getConnectionDetails();
		 preparedStatement= connection.prepareStatement("select*from ProductDetails order by Price asc");
		ResultSet rs=preparedStatement.executeQuery();
		System.out.println("product Id"+"-----"+"Name"+"-----"+"Price"+"/-");
		while(rs.next())
		{
			int Pid = +rs.getInt(1);
			String Name = rs.getString(2);
			String Decripstion = rs.getString(3);
			int Price = rs.getInt(5);
			System.out.println(Pid+"-----"+Name+"-----"+Decripstion+"---"+Price+"/-");
			System.out.println("__________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________________");
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
	
	//method product selected by user
	private void userproduct(int Pid) throws Exception
	{
	ConnectionDetails ConnectionDetails= new ConnectionDetails();
	connection=ConnectionDetails.getConnectionDetails();
		
	 preparedStatement= connection.prepareStatement("select*from ProductDetails where Pid='"+Pid+"' ");
	//System.out.println("query execute");
	ResultSet rs=preparedStatement.executeQuery();
	while(rs.next())
	{
		System.out.println("Pid =" +rs.getInt(1));
		System.out.println("Name = "+rs.getString(2));
		System.out.println("Decripstion = "+rs.getString(3));
		System.out.println("Price = "+rs.getInt(5));
		}
	connection.close();
	rs.close();
	preparedStatement.close();
	
	}
		
	
}
