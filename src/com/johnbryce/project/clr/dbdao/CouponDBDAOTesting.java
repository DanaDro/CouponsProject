package com.johnbryce.project.clr.dbdao;

import java.sql.Date;
import java.sql.SQLException;

import com.johnbryce.project.DAO.CouponsDAO;
import com.johnbryce.project.beans.Category;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.dbdao.CouponDBDAO;
import com.johnbryce.project.utils.Headers;
import com.johnbryce.project.utils.Ido;

public class CouponDBDAOTesting {

	public static void main(String[] args) throws SQLException {
		
		TestInit.init();
		Headers.CouponsDBDAOTesting();
		
		CouponsDAO coupons = new CouponDBDAO();
		
		System.out.println("--------add coupons--------");
		Coupon coupon = new Coupon(1, Category.CLOTH, "A&F", "pretty cloths", Date.valueOf("2020-09-20"), Date.valueOf("2021-09-20"), 100, 250, "shirt");
		coupons.addCoupon(coupon);
		Ido.print(coupons.getAllCoupons());
		
		System.out.println("--------Update coupons--------");
		coupon = coupons.getOneCoupon(5);
		coupon.setPrice(249.9);
		coupon.setAmount(150);
		coupons.updateCoupon(coupon);
		Ido.print(coupons.getOneCoupon(5));
		
		System.out.println("--------Delete coupons--------");
		coupons.deleteCoupon(5);
		Ido.print(coupons.getAllCoupons());
		System.out.println("--------One coupon--------");
		Ido.print(coupons.getOneCoupon(1));
		
		System.out.println("--------All coupons--------");
		Ido.print(coupons.getAllCoupons());
		
		System.out.println("--------Add coupons purchase--------");
		coupons.addCouponPurchase(1, 2);
		coupons.addCouponPurchase(3, 1);;
		Ido.print(coupons.getCustomersCoupons(1));
		Ido.print(coupons.getCustomersCoupons(3));
		
		System.out.println("--------Delete coupons purchase--------");
		coupons.deleteCouponPurchase(1, 2);
		System.out.println(coupons.getCustomersCoupons(1));
		
		System.out.println("--------get coupon purchased--------");
		Ido.print(coupons.getCouponsPurchased(1));
		
		System.out.println("--------get all company's coupons--------");
		Ido.print(coupons.getCompanyCoupons(1));
		
		System.out.println("--------get all customer's coupons--------");
		Ido.print(coupons.getCustomersCoupons(3));
	}
}
