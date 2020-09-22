package com.johnbryce.project.DAO;

import java.util.List;

import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.beans.Customer_vs_coupon;

public interface CouponsDAO {
	void addCoupon(Coupon coupons);
	void updateCoupon(Coupon coupons);
	void deleteCoupon(int couponsID);
	List<Coupon> getAllCoupons();
	Coupon getOneCoupon(int couponsID);
	void addCouponPurchase(int customerID, int couponID);
	void deleteCouponPurchase(int customerID, int couponID);
	List<Customer_vs_coupon> getCouponsPurchased(int couponId);
	void deleteCustomerVsCouponsByCouponID(int couponsId);
	List<Coupon> getCompanyCoupons(int companyId);
	List<Customer_vs_coupon> getCustomersCoupons(int customerId);
}
