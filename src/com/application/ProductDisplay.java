package com.application;

import java.util.List;

import com.model.Product_Details;
import com.model.User;
import com.repository.EcommerceRepositoryImpl;

public class ProductDisplay {
	private EcommerceRepositoryImpl ecomrepo;

	public ProductDisplay(EcommerceRepositoryImpl ecom) {
		this.ecomrepo = ecom;
	}

	public void ProductDisplay() {
		List<Product_Details> proddetails = ecomrepo.getProductDetails();

		if (proddetails == null) {
			System.out.println("Database fetching problem..");
		} else {

			System.out.printf(
					"----------------------------------------------------------------------------------------------------------%n");
			System.out.printf("                                            PRODUCT DETAILS %n");
			System.out.printf(
					"----------------------------------------------------------------------------------------------------------%n");
			System.out.printf("|%-20s|%-50s|%-12s|%-10s|%n", "ProductName ", "ProductDiscription", "ProductPrice",
					"ProductQuantity");

			for (Product_Details prod : proddetails) {
				String name = prod.getProductname();
				String descrip = prod.getProductdescription();
				int price = prod.getPrice();
				int qty = prod.getQuantity();
				// System.out.println(name+"\t"+descrip+"\t"+price+"\t"+qty);

				System.out.printf(
						"----------------------------------------------------------------------------------------------------------%n");
				System.out.printf("|%-20s|%-50s|%-12d|%-10d|%n ", prod.getProductname(), prod.getProductdescription(),
						prod.getPrice(), prod.getQuantity());

				System.out.printf(
						"---------------------------------------------------------------------------------------------------------%n");

			}
		}

	}
	
	public void UserDisplay() {
		List<User> userdetails=ecomrepo.getUserDetails();
		if (userdetails == null) {
			System.out.println("Database fetching problem..");
		} else {

			System.out.printf(
					"----------------------------------------------------------------------------------------------------------%n");
			System.out.printf("                                            USER DETAILS %n");
			System.out.printf(
					"----------------------------------------------------------------------------------------------------------%n");
			System.out.printf("|%-20s|%-50s|%-12s|%-10s|%n", "User ID ", " User Email", " Username",
					" Password");

			for (User user : userdetails) {
				int id = user.getUser_id();
				String uemail = user.getEmail();
				String uname = user.getUsername();
				String upass = user.getPassword();

				System.out.printf(
						"----------------------------------------------------------------------------------------------------------%n");
				System.out.printf("|%-20d|%-50s|%-12s|%-10s|%n ", id, uemail,uname,upass);

				System.out.printf(
						"---------------------------------------------------------------------------------------------------------%n");
			}
		}

	}
	

}
