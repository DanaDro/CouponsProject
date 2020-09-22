package com.johnbryce.project.clr;

import java.sql.Date;
import java.sql.SQLException;

import com.johnbryce.project.DAO.CompaniesDAO;
import com.johnbryce.project.DAO.CouponsDAO;
import com.johnbryce.project.DB.DataBaseManager;
import com.johnbryce.project.beans.Category;
import com.johnbryce.project.beans.Company;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.beans.Customer;
import com.johnbryce.project.dbdao.CompaniesDBDAO;
import com.johnbryce.project.dbdao.CouponDBDAO;
import com.johnbryce.project.dbdao.CustomerDBDAO;

public class TestInit {
	
	public static void init() throws SQLException {
		DataBaseManager.dropAndCreateTable();
		
		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		Company c1 = new Company("Sahut", "sahut5@gmail.com", "1111");
		Company c2 = new Company("Fitness", "fit5@gmail.com", "555");
		Company c3 = new Company("Bareket", "bareket@gmail.com", "bareket1234");
		Company c4 = new Company("JB", "JB@gmail.com", "*6460");
		companiesDAO.addCompany(c1);
		companiesDAO.addCompany(c2);
		companiesDAO.addCompany(c3);
		companiesDAO.addCompany(c4);
		
		CustomerDBDAO customers = new CustomerDBDAO();
		Customer customer1 = new Customer("Dana", "Drosvit", "dana5@gmail.com", "111", null);
		Customer customer2 = new Customer("Ido", "Zahavy", "ido5@gmail.com", "555", null);
		Customer customer3 = new Customer("Daniel", "Ram", "danielram@gmail.com", "danielram", null);
		Customer customer4 = new Customer("Ido", "Shay", "idoshay@gmail.com", "343434", null);
		customers.addCustomer(customer1);
		customers.addCustomer(customer2);
		customers.addCustomer(customer3);
		customers.addCustomer(customer4);
		
		CouponsDAO coupons = new CouponDBDAO();
	
		Coupon coupon1 = new Coupon(1, Category.VACATION ,"The best Vaction", "Vaction in resort", Date.valueOf("2020-08-30"), Date.valueOf("2020-09-08"), 1000, 5555.9, "hotel B"); // expired date - for testing
		Coupon coupon2 = new Coupon(1, Category.FOOD ,"In n Out", "best burger in California", Date.valueOf("2020-09-20"), Date.valueOf("2021-09-20"), 1000, 50.5, "Burger");
		Coupon coupon3 = new Coupon(1, Category.ELECTRICITY, "Best Buy", "best computer", Date.valueOf("2020-08-30"), Date.valueOf("2020-09-30"), 100, 4000, "13''");
		Coupon coupon4 = new Coupon(1, Category.FOOD, "Segev Restaurant", "Italian food", Date.valueOf("2020-09-09"), Date.valueOf("2020-09-30"), 0, 300, "pasta"); // amount - 0 for testing
		Coupon coupon5 = new Coupon(2, Category.GAMES, "Snake", "90s game", Date.valueOf("2020-09-01"), Date.valueOf("2021-09-30"), 500, 10.99, "snake");
		Coupon coupon6 = new Coupon(3, Category.CLOTH, "Adict", "Fasion Cloth", Date.valueOf("2020-08-01"), Date.valueOf("2021-08-30"), 1000, 300, "shirts");
		Coupon coupon7 = new Coupon(4, Category.RESTAURANT, "Chin Chin", "Asian food for the family", Date.valueOf("2020-12-03"), Date.valueOf("2021-03-21"), 200, 200, "Sushi");
		
		coupons.addCoupon(coupon1);
		coupons.addCoupon(coupon2);
		coupons.addCoupon(coupon3);
		coupons.addCoupon(coupon4);
		coupons.addCoupon(coupon5);
		coupons.addCoupon(coupon6);
		coupons.addCoupon(coupon7);
	}
}
