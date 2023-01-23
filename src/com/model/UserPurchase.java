package com.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class UserPurchase {

	private int order_id;
	private int user_id;
	private int purchased_amt;
	private LocalDate purchased_date;
	private LocalTime purchased_time;

	public LocalTime getPurchased_time() {
		return purchased_time;
	}

	public void setPurchased_time(LocalTime purchased_time) {
		this.purchased_time = purchased_time;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getPurchased_amt() {
		return purchased_amt;
	}

	public void setPurchased_amt(int purchased_amt) {
		this.purchased_amt = purchased_amt;
	}

	public LocalDate getPurchased_date() {
		return purchased_date;
	}

	public void setPurchased_date(LocalDate purchased_date) {
		this.purchased_date = purchased_date;
	}

}
