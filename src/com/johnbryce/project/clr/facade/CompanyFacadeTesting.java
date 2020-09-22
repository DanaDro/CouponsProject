package com.johnbryce.project.clr.facade;

import com.johnbryce.project.facade.CompanyFacade;
import com.johnbryce.project.utils.Headers;
import com.johnbryce.project.utils.Ido;

import java.sql.Date;
import java.sql.SQLException;

import com.johnbryce.project.beans.Category;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.exception.AlreadyExistException;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.exception.CanNotChangeExeption;

public class CompanyFacadeTesting {

	public static void main(String[] args) throws SQLException {

		TestInit.init();
		Headers.CompanyFacadeTesting();

		CompanyFacade companyFacade = new CompanyFacade();

		System.out.println("----------------bad logging...----------------");
		try {
			System.out.println(companyFacade.login("aaa@gmail.com", "aaa"));
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------------good logging...----------------");
		try {
			System.out.println(companyFacade.login("sahut5@gmail.com", "1111"));
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------------add coupon with existing title should not work----------------");
		try {
			companyFacade.addCoupon(new Coupon(1, Category.FOOD, "The best Vaction", "frozen fruits",
					Date.valueOf("2020-08-30"), Date.valueOf("2021-08-30"), 100, 55.9, "stam"));
		} catch (AlreadyExistException | NotExistException e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("----------------add coupon works----------------");

		try {
			companyFacade.addCoupon(new Coupon(1, Category.FOOD, "Frozen Fruits", "fruits", Date.valueOf("2020-08-30"),
					Date.valueOf("2021-08-30"), 100, 55.9, "stam"));
		} catch (AlreadyExistException | NotExistException e2) {
			System.out.println(e2.getMessage());
		}
		Ido.print(companyFacade.getCompanyCoupons());

		System.out.println("----------------update coupon id should not work----------------");

		if (companyFacade.getCompanyCoupons().size() > 0) {
			Coupon coupon = companyFacade.getCompanyCoupons().get(0);
			try {
				coupon.setId(5);
				companyFacade.updateCoupon(coupon);
			} catch (NotExistException | CanNotChangeExeption e1) {
				System.out.println(e1.getMessage());
			}
		} else {
			System.out.println("No coupons for this company");
		}
		System.out.println("----------------update company id should not work----------------");
		Coupon coupon = companyFacade.getCompanyCoupons().get(0);
		try {
			coupon.setcompanyId(2);
			companyFacade.updateCoupon(coupon);
		} catch (NotExistException | CanNotChangeExeption e1) {
			System.out.println(e1.getMessage());
		}

		System.out.println("----------------update coupon works----------------");
		coupon = companyFacade.getCompanyCoupons().get(0);
		try {
			coupon.setAmount(100);
			companyFacade.updateCoupon(coupon);
		} catch (NotExistException e1) {
			System.out.println(e1.getMessage());
		}
		Ido.print(companyFacade.getCompanyCoupons());
		System.out.println(
				"----------------delete coupon should delete customer vs coupons related coupons----------------");
		companyFacade.deleteCoupon(1);
		System.out.println("----------------get all coupons----------------");

		Ido.print(companyFacade.getCompanyCoupons());

		System.out.println("----------------get all coupons by category----------------");

		Ido.print(companyFacade.getCompanyCouponsByCategory(Category.FOOD));

		System.out.println("----------------get all coupons by max price----------------");

		Ido.print(companyFacade.getCompanyCouponsByPrice(100.0));

		System.out.println("----------------get company's detalis----------------");
		Ido.print(companyFacade.getCompanyDetalis());
	}
}
