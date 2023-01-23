package com.application;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import com.model.User;
import com.model.UserPurchase;
import com.repository.EcommerceRepositoryImpl;
import com.service.ProductSelection;

public class EcommerceApplication {
	private static LocalDate ldate=LocalDate.now();
	private static LocalTime ltime=LocalTime.now();

	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Scanner sc = new Scanner(System.in);
		System.out.println("-----Ecommerce Application-----");
		System.out.println(
				"Existing User in database or New User !!! if Existing User type 1 , if not Existing User type 2");
		int userexist = sc.nextInt();
		sc.nextLine();
		EcommerceRepositoryImpl ecomrepo = new EcommerceRepositoryImpl();
		UserPurchase uperchase=new UserPurchase();
		int count=0;String username=null,password=null;
		if (userexist == 1) {
			System.out.println("Enter username >>  ");
			     username = sc.nextLine();
			System.out.println("Enter password >>  ");
			     password = sc.nextLine();
			List<User> userlist = ecomrepo.validateExistingUser(username, password);
			if (userlist == null) {
				System.out.println("Invalid Username and Password !");
			} else {
				count++;int id;
				for (User us : userlist) {
					id = us.getUser_id();
					String email = us.getEmail();
					String uname = us.getUsername();
					String pass = us.getPassword();
					// System.out.println(id + "\t" + email + "\t" + uname + "\t" + pass);
				}
			}
		} else if (userexist == 2) {
   
			User user = new User();
			System.out.println("Adding new user ..");
			System.out.println("Enter Email >> ");
			String email = sc.nextLine();
			user.setEmail(email);
			System.out.println("Enter Username >> ");
			 username = sc.nextLine();
			user.setUsername(username);
			System.out.println("Enter Password >> ");
			 password = sc.nextLine();
			user.setPassword(password);

			boolean useradd = ecomrepo.isAddNewUser(user);
			if (useradd) {
				System.out.println("user added ..");
				count++;
				
			} else {
				System.out.println("user not added ..");
			}
		}
		System.out.println();
		if(count>0) {
		
		
		
		ProductDisplay proddisplay=new ProductDisplay(ecomrepo);
		proddisplay.ProductDisplay();
		System.out.println();
		ProductSelection pselection=new ProductSelection(ecomrepo);
		pselection.selection();
		System.out.println("Gross Total Purchase >>"+ pselection.TotalBill());
		if(pselection.TotalBill()>0) {
			List<User> usobj=ecomrepo.validateExistingUser(username, password);
			
			for(User u: usobj)
			 uperchase.setUser_id(u.getUser_id());
			uperchase.setPurchased_amt(pselection.TotalBill());
			uperchase.setPurchased_date(ldate);
			uperchase.setPurchased_time(ltime);
			boolean result=ecomrepo.setUserPurchase(uperchase);
			if(result) {
				System.out.println(result);
			}
			else
			{
				System.out.println(result);
			}
		}
		
		System.out.println("Want To see User Details >> ");
		if(sc.nextLine().equalsIgnoreCase("yes")) {
			proddisplay.UserDisplay();
		}
		 
		
		}
	}

}
