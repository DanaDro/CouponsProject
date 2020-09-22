package com.johnbryce.project.facade;

import com.johnbryce.project.DAO.CompaniesDAO;
import com.johnbryce.project.DAO.CouponsDAO;
import com.johnbryce.project.DAO.CustomerDAO;
import com.johnbryce.project.dbdao.CompaniesDBDAO;
import com.johnbryce.project.dbdao.CustomerDBDAO;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.dbdao.CouponDBDAO;

public abstract class ClientFacade {
	
	 protected CompaniesDAO companiesDAO;
	 protected CustomerDAO customerDAO;
	 protected CouponsDAO couponsDAO;
	 
	 public ClientFacade() {
		this.companiesDAO = new CompaniesDBDAO();
		this.customerDAO = new CustomerDBDAO();
		this.couponsDAO = new CouponDBDAO();
	}

	public abstract boolean login(String email, String password) throws NotExistException;
}
