package com.johnbryce.project.clr.facade;

import java.sql.Date;
import java.sql.SQLException;

import com.johnbryce.project.beans.Category;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.dbdao.CouponDBDAO;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.facade.CustomerFacade;
import com.johnbryce.project.utils.Headers;
import com.johnbryce.project.utils.Ido;

public class CustomerFacadeTesting {

	public static void main(String[] args) throws SQLException {

		TestInit.init();
		Headers.CustomerFacadeTesting();

		CustomerFacade customerFacade = new CustomerFacade();
		System.out.println("----------------bad logging...----------------");
		try {
			System.out.println(customerFacade.login("dana5555@gmail.com", "111"));
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("----------------good logging...----------------");
		try {
			System.out.println(customerFacade.login("danielram@gmail.com", "danielram"));
		} catch (NotExistException e) {
			System.out.println(e.getMessage());
		}

		System.out.println("----------------add coupon works----------------");
		try {
			Coupon c2 = new Coupon(2, 1, Category.FOOD, "In n Out", "best burger in California",
					Date.valueOf("2020-09-20"), Date.valueOf("2021-09-20"), 1000, 50.5, "Burger");
			customerFacade.purchastCoupon(c2);
			Ido.print(customerFacade.getCustomersCoupons());
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("----------------add coupon with 0 amount should not work----------------");
		try {
			Coupon c4 = new Coupon(4, 1, Category.FOOD, "Segev Restaurant", "Italian food", Date.valueOf("2020-09-09"),
					Date.valueOf("2020-09-30"), 0, 300, "pasta");
			customerFacade.purchastCoupon(c4);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

		System.out.println("----------------add coupon with expired date should not work----------------");
		try {
			Coupon c1 = new Coupon(1, Category.VACATION, "The best Vaction", "Vaction in resort",
					Date.valueOf("2020-08-30"), Date.valueOf("2020-09-08"), 1000, 5555.9, "hotel B");
			c1 = new CouponDBDAO().getAllCoupons().get(0);
			customerFacade.purchastCoupon(c1);
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("----------------add coupon existing should not work----------------");
		try {
			Coupon c2 = new Coupon(2, 1, Category.FOOD, "In n Out", "best burger in California",
					Date.valueOf("2020-09-20"), Date.valueOf("2021-09-20"), 1000, 50.5, "Burger");
			customerFacade.purchastCoupon(c2);
			Ido.print(customerFacade.getCustomersCoupons());
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("----------------get all coupons----------------");
		Ido.print(customerFacade.getCustomersCoupons());
		System.out.println("----------------get all coupons by category----------------");
		Ido.print(customerFacade.getCustomersCouponsByCategory(Category.FOOD));
		System.out.println("----------------get all coupons by max price----------------");
		Ido.print(customerFacade.getCustoemrCouponsByPrice(100.0));

		System.out.println("----------------get customer's detalis----------------");
		Ido.print(customerFacade.getCustomerDetalis());
	}

}
