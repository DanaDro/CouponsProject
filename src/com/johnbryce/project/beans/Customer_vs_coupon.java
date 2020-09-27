package com.johnbryce.project.beans;

public class Customer_vs_coupon {

	private int customer_id;
	private int coupon_id;

	public Customer_vs_coupon(int customer_id, int coupon_id) {
		this.customer_id = customer_id;
		this.coupon_id = coupon_id;
	}

	public Customer_vs_coupon(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getCoupon_id() {
		return coupon_id;
	}

	public void setCoupon_id(int coupon_id) {
		this.coupon_id = coupon_id;
	}

	@Override
	public String toString() {
		return "Coupons_vs_customers [customer_id=" + customer_id + ", coupon_id=" + coupon_id + "]";
	}
}
