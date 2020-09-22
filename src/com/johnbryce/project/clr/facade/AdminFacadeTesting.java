package com.johnbryce.project.clr.facade;

import java.sql.SQLException;

import com.johnbryce.project.beans.Company;
import com.johnbryce.project.beans.Customer;
import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.dbdao.CouponDBDAO;
import com.johnbryce.project.exception.AlreadyExistException;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.exception.CanNotChangeExeption;
import com.johnbryce.project.facade.AdministratorFacade;
import com.johnbryce.project.utils.Headers;
import com.johnbryce.project.utils.Ido;

public class AdminFacadeTesting {

	public static void main(String[] args) throws SQLException {

		TestInit.init();
		Headers.AdminFacadeTesting();
		
		AdministratorFacade administratorFacade = new AdministratorFacade();

		System.out.println("----------------bad logging...----------------");
		try {
			System.out.println(administratorFacade.login("dana5555@gmail.com", "111"));
		} catch (NotExistException e3) {
			System.out.println(e3.getMessage());
		}

		System.out.println("----------------good logging...----------------");
		try {
			System.out.println(administratorFacade.login("admin@admin.com", "admin"));
		} catch (NotExistException e3) {
			System.out.println(e3.getMessage());
		}

		System.out.println("----------------add company works----------------");
		try {
			administratorFacade.addCompany(new Company("CCC", "ccc@gmail.com", "ccc"));
			Ido.print(administratorFacade.getCompanies());
		} catch (AlreadyExistException | NotExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------------add company with the same name should not work----------------");
		try {
			administratorFacade.addCompany(new Company("JB", "jjjB@gmail.com", "*6460"));
		} catch (AlreadyExistException | NotExistException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("----------------add company with the same email address should not work----------------");
		try {
			administratorFacade.addCompany(new Company("Fit Studio", "fit5@gmail.com", "fitfitfit"));
		} catch (AlreadyExistException | NotExistException e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("----------------update company id should not work----------------");
		Company company;
		try {
			company = administratorFacade.getOneCompany(1);
			company.setId(3);
			administratorFacade.updateCompany(company);
		} catch (NotExistException | CanNotChangeExeption e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("----------------update company name should not work----------------");
		try {
			company = administratorFacade.getOneCompany(1);
			company.setName("Sahut Tari");
			administratorFacade.updateCompany(company);
		} catch (NotExistException | CanNotChangeExeption e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("----------------update company works----------------");
		try {
			company = administratorFacade.getOneCompany(1);
			company.setPassword("sahuttari123");
			administratorFacade.updateCompany(company);
			Ido.print(administratorFacade.getOneCompany(1));
		} catch (NotExistException | CanNotChangeExeption e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("----------------delete company works----------------");
		administratorFacade.deleteCompany(2);
		Ido.print(administratorFacade.getCompanies());
		System.out.println("----------------get all companies----------------");
		Ido.print(administratorFacade.getCompanies());
		System.out.println("----------------get one company by id works----------------");
		Ido.print(administratorFacade.getOneCompany(1));
		
		CouponDBDAO couponDBDAO = new CouponDBDAO();
		couponDBDAO.addCouponPurchase(1, 1);
		couponDBDAO.addCouponPurchase(2, 2);
		couponDBDAO.addCouponPurchase(2, 1);
		couponDBDAO.addCouponPurchase(3, 1);
		
		System.out.println("----------------add customer with the same email adress should not work----------------");
		try {
			administratorFacade.addCustomer(new Customer("Dana", "Ram", "dana5@gmail.com", "DanaDana", null));
		} catch (AlreadyExistException | NotExistException e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("----------------add customer works----------------");
		try {
			administratorFacade.addCustomer(
					new Customer("Natalie", "Drosvit", "nataliedrosvit@gmail.com", "nataliedrosvit", null));
			Ido.print(administratorFacade.getCustomers());
		} catch (AlreadyExistException | NotExistException e1) {
			System.out.println(e1.getMessage());
		}
		System.out.println("----------------update customer id should not work----------------");
		Customer customer;

		try {
			customer = administratorFacade.getOneCustomer(2);
			customer.setId(3);
			administratorFacade.updateCustomer(customer);
			Ido.print(administratorFacade.getOneCustomer(2));
		} catch (NotExistException | CanNotChangeExeption e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("----------------update customer works----------------");
		try {
			customer = administratorFacade.getOneCustomer(2);
			customer.setLastName("Zehavi");
			administratorFacade.updateCustomer(customer);
			Ido.print(administratorFacade.getOneCustomer(2));
		} catch (NotExistException e2) {
			System.out.println(e2.getMessage());
		}
		System.out.println("----------------delete customer works----------------");
		administratorFacade.deleteCustomer(1);
		Ido.print(administratorFacade.getCustomers());
		System.out.println("----------------get all customers----------------");
		Ido.print(administratorFacade.getCustomers());
		System.out.println("----------------get one customer by id works----------------");
		Ido.print(administratorFacade.getOneCustomer(2));
	}

}
