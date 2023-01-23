package com.service;

import java.util.List;
import java.util.Scanner;

import com.model.Product_Details;
import com.repository.EcommerceRepositoryImpl;

public class ProductSelection {
	private static int total = 0;
	private EcommerceRepositoryImpl ecomrepo;
	Scanner sc = new Scanner(System.in);

	public ProductSelection(EcommerceRepositoryImpl ecom) {
		this.ecomrepo = ecom;
	}

	public void selection() {
		String choice;int count=0;
		do {
			System.out.println("Want To Buy ?(Yes/No)");
			choice = sc.nextLine();
			if (choice.equalsIgnoreCase("yes")) {

				System.out.println("Select Product from Table >> ");
				String prodName = sc.nextLine();
				List<Product_Details> proddetails = ecomrepo.getProductDetails();

				if (proddetails == null) {
					System.out.println("Database fetching problem..");
				} else {

					for (Product_Details prod : proddetails) {
						if (prod.getProductname().equalsIgnoreCase(prodName)) {
							int remqty = 0;count++;
							System.out.println("Product Name >> " + prod.getProductname());
							System.out.println("Product Discription >> " + prod.getProductdescription());
							System.out.println("Product Price >> " + prod.getPrice());
							System.out.println("Product Quantity >> " + prod.getQuantity());

							System.out.println("\nEnter " + prod.getProductname() + " Quantity >> ");
							int qty = sc.nextInt();// 2
							sc.nextLine();
							if (qty >= 0 && prod.getQuantity() > 0) {// 6
								remqty = prod.getQuantity() - qty;
								total += qty * prod.getPrice();
								System.out.println("Remaining " + prod.getProductname() + "Quantity >>" + remqty);
								System.out.println(
										"Total Amout for " + prod.getProductname() + " is >>" + prod.getPrice() * qty);
							} else {
								System.out.println("out of stock !!");
							}
							boolean qtyresult = ecomrepo.isUpdatedQuantity(prod.getProductid(), remqty);
							if (qtyresult) {
								System.out.println("database updated ..");
							} else {
								System.out.println("database not updated ..");
							}
							break;
						} //else {
						//	System.out.println("problem in result..");
							
						//}
					}
					if(count==0)
						System.out.println("product not in table ..");

				}
			}
		} while (choice.equalsIgnoreCase("yes"));
	}
	public int TotalBill() {
		return total;
	}
}
