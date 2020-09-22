package com.johnbryce.project.clr.login;

import java.sql.SQLException;

import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.clr.facade.CustomerFacadeTesting;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.facade.AdministratorFacade;
import com.johnbryce.project.facade.CompanyFacade;
import com.johnbryce.project.facade.CustomerFacade;
import com.johnbryce.project.security.ClientType;
import com.johnbryce.project.security.LoginManager;
import com.johnbryce.project.utils.Headers;
import com.johnbryce.project.utils.Ido;

public class LoginManagerTesting {

	public static void main(String[] args) throws SQLException, NotExistException {

		TestInit.init();

		//CustomerFacadeTesting.main(args);
		
		Headers.LogingManagerTesting();

		LoginManager loginManager = LoginManager.getInstance();

		System.out.println("---------Company bad loging---------");
		CompanyFacade companyFacade = (CompanyFacade) loginManager.login("Sahut Tari", "sahut5@gmail.com",
				ClientType.COMPANY);
		if (companyFacade != null) {
			Ido.print(companyFacade.getCompanyDetalis());
		} else {
			System.out.println("Facade is null");
		}
		System.out.println("---------Company good loging---------");
		companyFacade = (CompanyFacade) loginManager.login("sahut5@gmail.com", "1111", ClientType.COMPANY);
		System.out.println("Login successfully - Company usage example");
		if (companyFacade != null) {
			Ido.print(companyFacade.getCompanyDetalis());
		} else {
			System.out.println("Facade is null");
		}
		System.out.println("---------Customer bad loging---------");
		CustomerFacade customerFacade = (CustomerFacade) loginManager.login("daniel@gmail.com", "555",
				ClientType.CUSTOMER);
		if (customerFacade != null) {
			Ido.print(customerFacade.getCustomerDetalis());
		} else {
			System.out.println("Facade is null");
		}
		System.out.println("---------Customer good loging---------");
		customerFacade = (CustomerFacade) loginManager.login("danielram@gmail.com", "danielram", ClientType.CUSTOMER);
		System.out.println("Login successfully - Customer usage example");
		if (customerFacade != null) {
			Ido.print(customerFacade.getCustomerDetalis());
		} else {
			System.out.println("Facade is null");
		}
		System.out.println("---------Admin bad loging---------");
		AdministratorFacade administratorFacade = (AdministratorFacade) loginManager.login("Dana", "1234",
				ClientType.ADMINISTRATOR);
		if (administratorFacade != null) {
			Ido.print(administratorFacade.getCompanies());
			Ido.print(administratorFacade.getCustomers());
		} else {
			System.out.println("Facade is null");
		}
		System.out.println("---------Admin good loging---------");
		administratorFacade = (AdministratorFacade) loginManager.login("admin@admin.com", "admin",
				ClientType.ADMINISTRATOR);
		System.out.println("Login successfully - Admin usage example");
		if (administratorFacade != null) {
			Ido.print(administratorFacade.getCompanies());
			Ido.print(administratorFacade.getCustomers());
		} else {
			System.out.println("Facade is null");
		}
	}
}
