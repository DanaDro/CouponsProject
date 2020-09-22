package com.johnbryce.project.clr.dbdao;

import java.sql.SQLException;

import com.johnbryce.project.DAO.CompaniesDAO;
import com.johnbryce.project.beans.Company;
import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.dbdao.CompaniesDBDAO;
import com.johnbryce.project.utils.Headers;
import com.johnbryce.project.utils.Ido;

public class CompaniesDBDAOTesting {
	
	public static void main(String[] args) throws SQLException {
		
		TestInit.init();
		Headers.CompaniesDBDAOTesting();
		
		CompaniesDAO companiesDAO = new CompaniesDBDAO();
		
		System.out.println("--------add companies--------");
		Company company = new Company("Pita express", "pita@gmail.com", "5555");
		companiesDAO.addCompany(company);
		Ido.print(companiesDAO.getAllCompanies());
		
		System.out.println("--------Update companies--------");
		company = companiesDAO.getOneCompany(5);
		company.setEmail("pitaexpress@gmail.com");
		companiesDAO.updateCompany(company);
		Ido.print(companiesDAO.getOneCompany(5));
		
		System.out.println("--------Delete companies--------");
		companiesDAO.deleteCompany(5);
		Ido.print(companiesDAO.getAllCompanies());
		
		System.out.println("--------One company companies--------");
		Ido.print(companiesDAO.getOneCompany(1));
		
		System.out.println("--------All companies--------");
		Ido.print(companiesDAO.getAllCompanies());
		
		System.out.println("--------company exists--------");
		System.out.println(companiesDAO.isCompanyExists("fit5@gmail.com", "555"));
	}
}
