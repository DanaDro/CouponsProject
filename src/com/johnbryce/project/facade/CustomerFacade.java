package com.johnbryce.project.facade;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.johnbryce.project.beans.Category;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.beans.Customer_vs_coupon;
import com.johnbryce.project.beans.Customer;
import com.johnbryce.project.exception.AlreadyExistException;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.exception.OperationNotAllowedExeption;

public class CustomerFacade extends ClientFacade {

	private int customerId;

	public CustomerFacade() {
		super();
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	@Override
	public boolean login(String email, String password) throws NotExistException{
		if (customerDAO.isCustomerExists(email, password)) {
			this.customerId = customerDAO.getCustomerId(email, password);
			return true;
		}
		throw new NotExistException("Email or Password");
	}


	public void purchastCoupon(Coupon coupon) throws Exception {
		List<Customer_vs_coupon> coupons = couponsDAO.getCustomersCoupons(this.customerId);
		for (Customer_vs_coupon coup : coupons) {
			if (coup.getCoupon_id() == coupon.getId()) {
				throw new AlreadyExistException("coupon.");
			}
		}
		coupon = couponsDAO.getOneCoupon(coupon.getId());
		if (coupon.getAmount() <= 0) {
			throw new OperationNotAllowedExeption("Purchase coupon with amount 0");
		}
		if (coupon.getEndDate().before(new Date())) {
			throw new OperationNotAllowedExeption("Purchase coupon expired");
		}
		couponsDAO.addCouponPurchase(this.customerId, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);
		couponsDAO.updateCoupon(coupon);
	}

	public List<Coupon> getCustomersCoupons(){
		List<Customer_vs_coupon> customersCoupons = couponsDAO.getCustomersCoupons(this.customerId);
		List<Coupon> results = new ArrayList<>();
		for (Customer_vs_coupon coup : customersCoupons) {
			results.add(couponsDAO.getOneCoupon(coup.getCoupon_id()));
		}
		return results;
	}

	public List<Coupon> getCustomersCouponsByCategory(Category category){
		List<Coupon> coupons = getCustomersCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
				results.add(coupon);
			}
		}
		return results;
	}

	public List<Coupon> getCustoemrCouponsByPrice(double maxPrice){
		List<Coupon> coupons = getCustomersCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				results.add(coupon);
			}
		}
		return results;
	}

	public Customer getCustomerDetalis() {
		List<Coupon> customerCoupon = getCustomersCoupons();
		Customer customer = customerDAO.getOneCustomer(this.customerId);
		customer.setCoupons(customerCoupon);
		
		return customer;
	}
	
	public int getCustomerIdFromDBDAO(String email, String password) {
		return this.customerDAO.getCustomerId(email, password);
	}
}
