package com.johnbryce.project.security;

import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.facade.AdministratorFacade;
import com.johnbryce.project.facade.ClientFacade;
import com.johnbryce.project.facade.CompanyFacade;
import com.johnbryce.project.facade.CustomerFacade;

public class LoginManager {

	private static LoginManager instance = null;

	private LoginManager() {
		
	}

	public static LoginManager getInstance() {
		if (instance == null) {
			synchronized (LoginManager.class) {
				if (instance == null) {
					instance = new LoginManager();
				}
			}
		}
		return instance;
	}

	public ClientFacade login(String email, String password, ClientType clientType) {
		switch (clientType) {
		case ADMINISTRATOR:
			AdministratorFacade administratorFacade = new AdministratorFacade();
			try {
				if(administratorFacade.login(email, password)) {
					return administratorFacade;
				}
			} catch (NotExistException e1) {
				System.out.println(e1.getMessage());
			}
			break;
		case COMPANY:
				CompanyFacade companyFacade = new CompanyFacade();
			try {
				if(companyFacade.login(email, password)) {
					return companyFacade;
				}
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
			break;
		case CUSTOMER:
			CustomerFacade customerFacade = new CustomerFacade();
			try {
				if(customerFacade.login(email, password)) {
					return customerFacade;
				}
			} catch (NotExistException e) {
				System.out.println(e.getMessage());
			}
			break;

		default:
			break;
		}
		return null;
	}

}
