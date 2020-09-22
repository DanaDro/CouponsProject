package com.johnbryce.project.facade;

import java.util.ArrayList;
import java.util.List;

import com.johnbryce.project.beans.Category;
import com.johnbryce.project.beans.Company;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.exception.AlreadyExistException;
import com.johnbryce.project.exception.NotExistException;

public class CompanyFacade extends ClientFacade {

	private int companyId;

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public CompanyFacade() {
		super();
	}

	@Override
	public boolean login(String email, String password) throws NotExistException {
		if (companiesDAO.isCompanyExists(email, password)) {
			this.companyId = companiesDAO.getCompanyId(email, password);
			return true;
		}
		throw new NotExistException("Email or Password");
	}

	public void addCoupon(Coupon coupon) throws AlreadyExistException, NotExistException {
		List<Coupon> companyCoupons = getCompanyCoupons();
		for (Coupon coup : companyCoupons) {
			if (coup.getTitle().equals(coupon.getTitle())) {
				throw new AlreadyExistException("Coupon Title.");
			}
		}
		this.couponsDAO.addCoupon(coupon);
	}

	public void updateCoupon(Coupon coupon) throws NotExistException {
		this.couponsDAO.updateCoupon(coupon);
	}

	public void deleteCoupon(int couponId) {
		if (this.companyId == couponsDAO.getOneCoupon(couponId).getCompanyId()) {
			couponsDAO.deleteCustomerVsCouponsByCouponID(couponId);
			couponsDAO.deleteCoupon(couponId);
		}
	}

	public List<Coupon> getCompanyCoupons() {
		List<Coupon> coupons = couponsDAO.getAllCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coup : coupons) {
			if (coup.getCompanyId() == this.companyId) {
				results.add(coup);
			}
		}
		return results;
	}

	public List<Coupon> getCompanyCouponsByCategory(Category category) {
		List<Coupon> coupons = getCompanyCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getCategory().equals(category)) {
				results.add(coupon);
			}
		}
		return results;
	}

	public List<Coupon> getCompanyCouponsByPrice(double maxPrice) {
		List<Coupon> coupons = getCompanyCoupons();
		List<Coupon> results = new ArrayList<>();
		for (Coupon coupon : coupons) {
			if (coupon.getPrice() <= maxPrice) {
				results.add(coupon);
			}
		}
		return results;
	}

	public Company getCompanyDetalis() {
		List<Coupon> companysCoupon = getCompanyCoupons();
		Company company = companiesDAO.getOneCompany(this.companyId);
		company.setCoupons(companysCoupon);
		return company;
	}

}
