package com.velocity.mini.project.ecommercs.main.method;

import com.velocity.mini.project.ecommercs.main.product.Product_Details;
import com.velocity.mini.project.ecommercs.registraion.User_Details;

public class Main {

	

		public static void main(String[] args) throws Exception {
			
			User_Details user_Details= new User_Details();
			user_Details.UserDetails();
			
			System.out.println("__________________________________________________________________________________________");
			
			Product_Details product_Details=new Product_Details();
			product_Details.productDetails();
			
			
			
			System.out.println("_______________________________________");


	}

}
