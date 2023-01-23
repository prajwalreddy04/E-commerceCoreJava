package com.repository;

import java.util.List;

import com.model.Product_Details;
import com.model.User;
import com.model.UserPurchase;

public interface EcommerceRepository {

	public boolean isAddNewUser(User user);
	public List<User> validateExistingUser(String uname,String pass);
	public List<Product_Details> getProductDetails();
	public boolean isUpdatedQuantity(int id,int qty);
	public List<User> getUserDetails();
	public boolean setUserPurchase(UserPurchase uperchase);
	
}
