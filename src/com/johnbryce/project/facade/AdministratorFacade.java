package com.johnbryce.project.facade;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.project.beans.Company;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.beans.Customer;
import com.johnbryce.project.beans.Customer_vs_coupon;
import com.johnbryce.project.exception.AlreadyExistException;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.exception.CanNotChangeExeption;

public class AdministratorFacade extends ClientFacade {

	private static final String EMAIL = "admin@admin.com";
	private static final String PASSWORD = "admin";

	@Override
	public boolean login(String email, String password) throws NotExistException {
		if( email.equals(this.EMAIL) && password.equals(this.PASSWORD)) {
			return true;
		}
		throw new NotExistException("Email or Password");
	}

	public void addCompany(Company company) throws AlreadyExistException, NotExistException {
		List<Company> companys = getCompanies();
		for (Company comp : companys) {
			if (comp.getEmail().equals(company.getEmail())) {
				throw new AlreadyExistException("email");
			}
			if (comp.getName().equals(company.getName())) {
				throw new AlreadyExistException("name");
			}
		}
		this.companiesDAO.addCompany(company);
	}

	public void updateCompany(Company company) throws NotExistException, CanNotChangeExeption {
		List<Company> companies = getCompanies();
		for (Company comp : companies) {
			if (comp.getName().equals(company.getName())) {
				this.companiesDAO.updateCompany(company);
				return;
			}
		}
		throw new CanNotChangeExeption("Company's name");
	}

	public void deleteCompany(int companyId) {
		List<Coupon> couponsCompany = couponsDAO.getCompanyCoupons(companyId);
		for (Coupon coupon : couponsCompany) {
			couponsDAO.deleteCustomerVsCouponsByCouponID(coupon.getId());
			couponsDAO.deleteCoupon(coupon.getId());
		}
		companiesDAO.deleteCompany(companyId);
	}

	public List<Company> getCompanies() {
		List<Company> company = companiesDAO.getAllCompanies();
		for (int i = 0; i < company.size(); i++) {
			company.set(i, getOneCompany(company.get(i).getId()));
		}
		return company;
	}

	public Company getOneCompany(int companyId) {
		List<Coupon> companysCoupon = couponsDAO.getCompanyCoupons(companyId);
		Company company = companiesDAO.getOneCompany(companyId);
		company.setCoupons(companysCoupon);
		return company;
	}

	public void addCustomer(Customer customer) throws AlreadyExistException, NotExistException {
		List<Customer> customers = getCustomers();
		for (Customer cust : customers) {
			if (cust.getEmail().equals(customer.getEmail())) {
				throw new AlreadyExistException("email.");
			}
		}
		this.customerDAO.addCustomer(customer);
	}

	public void updateCustomer(Customer customer) throws NotExistException {
		this.customerDAO.updateCustomer(customer);
	}

	public void deleteCustomer(int customerId) {
		List<Customer_vs_coupon> customerCoupons = couponsDAO.getCustomersCoupons(customerId);
		for (Customer_vs_coupon coupon : customerCoupons) {
			couponsDAO.deleteCouponPurchase(customerId, coupon.getCoupon_id());
			Coupon couponAmount = couponsDAO.getOneCoupon(coupon.getCoupon_id());
			couponAmount.setAmount(couponAmount.getAmount() + 1); // need?
			couponsDAO.updateCoupon(couponAmount);
		}
		customerDAO.deleteCustomer(customerId);
	}

	public List<Customer> getCustomers() {
		List<Customer> customer = customerDAO.getAllCustomers();
		for (int i = 0; i < customer.size(); i++) {
			customer.set(i, getOneCustomer(customer.get(i).getId()));
		}
		return customer;
	}

	public Customer getOneCustomer(int customerId){
		List<Customer_vs_coupon> customerCoupons = couponsDAO.getCustomersCoupons(customerId);
		List<Coupon> coupons = new ArrayList<>();
		for (Customer_vs_coupon cvc : customerCoupons) {
			Coupon coupon = couponsDAO.getOneCoupon(cvc.getCoupon_id());
			coupons.add(coupon);
		}
		Customer customer = customerDAO.getOneCustomer(customerId);
		customer.setCoupons(coupons);
		return customer;
	}
}
