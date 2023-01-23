package com.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.model.Product_Details;
import com.model.User;
import com.model.UserPurchase;

public class EcommerceRepositoryImpl implements EcommerceRepository {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	List<User> userlist;
	List<Product_Details> proddetails;

	public EcommerceRepositoryImpl() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce_database", "root", "Prajwal@123");
			System.out.println("connected to database ...");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error is " + e);
		}
	}

	@Override
	public boolean isAddNewUser(User user) {
		// TODO Auto-generated method stub
		try {
			pstmt = conn.prepareStatement("insert into user values('0',?,?,?)");
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			int value = pstmt.executeUpdate();
			if (value > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error is " + e);
			return false;
		}
	}

	@Override
	public List<User> validateExistingUser(String uname, String pass) {
		// TODO Auto-generated method stub
		try {
			userlist = new ArrayList<User>();
			pstmt = conn.prepareStatement("select * from user where username=? and password=?");
			pstmt.setString(1, uname);
			pstmt.setString(2, pass);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));
				userlist.add(user);
			} else {
				return userlist = null;
			}
		} catch (Exception e) {
			System.out.println("Error is" + e);
		}
		return userlist;
	}

	@Override
	public List<Product_Details> getProductDetails() {
		// TODO Auto-generated method stub
		try {
			proddetails = new ArrayList<Product_Details>();
			pstmt = conn.prepareStatement("select * from product_details order by productname");
			rs = pstmt.executeQuery();
			while(rs.next()) {
				Product_Details pdetails = new Product_Details();
				pdetails.setProductid(rs.getInt(1));
				pdetails.setProductdescription(rs.getString(2));
				pdetails.setPrice(rs.getInt(3));
				pdetails.setProductname(rs.getString(4));
				pdetails.setQuantity(rs.getInt(5));
				proddetails.add(pdetails);
			} 
		} catch (Exception e) {
			System.out.println("Error is" + e);
			return proddetails=null;
		}
		return proddetails;
	}

	@Override
	public boolean isUpdatedQuantity(int id, int qty) {
		// TODO Auto-generated method stub
		try {
			pstmt = conn.prepareStatement("update product_details set quantity=? where productid=?");
			pstmt.setInt(1, qty);
			pstmt.setInt(2, id);
			int res=pstmt.executeUpdate();
			if(res>0) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(Exception e) {
			System.out.println("Error is "+ e);
			return false;
		}
	}

	@Override
	public List<User> getUserDetails() {
		// TODO Auto-generated method stub
		try {
			userlist = new ArrayList<User>();
			pstmt = conn.prepareStatement("select * from user");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUser_id(rs.getInt(1));
				user.setEmail(rs.getString(2));
				user.setUsername(rs.getString(3));
				user.setPassword(rs.getString(4));
				userlist.add(user);
			} 
		} catch (Exception e) {
			System.out.println("Error is" + e);
		}
		return userlist;
	}

	@Override
	public boolean setUserPurchase(UserPurchase uperchase) {
		// TODO Auto-generated method stub
		try {
			pstmt = conn.prepareStatement("insert into userpurchase values('0',?,?,?,?)");
			pstmt.setInt(1, uperchase.getUser_id());
			pstmt.setInt(2, uperchase.getPurchased_amt());
			java.sql.Date date = java.sql.Date.valueOf(uperchase.getPurchased_date());
			java.sql.Time time = java.sql.Time.valueOf(uperchase.getPurchased_time());
			pstmt.setDate(3, date);
			pstmt.setTime(4, time);
			int value = pstmt.executeUpdate();
			if (value > 0) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error is " + e);
			return false;
		}
	
	}

}
