package com.johnbryce.project.mainTesting;

import java.sql.SQLException;

import com.johnbryce.project.DB.ConnectionPool;
import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.clr.dbdao.CompaniesDBDAOTesting;
import com.johnbryce.project.clr.dbdao.CouponDBDAOTesting;
import com.johnbryce.project.clr.dbdao.CustomersDBDAOTesting;
import com.johnbryce.project.clr.facade.AdminFacadeTesting;
import com.johnbryce.project.clr.facade.CompanyFacadeTesting;
import com.johnbryce.project.clr.facade.CustomerFacadeTesting;
import com.johnbryce.project.clr.login.LoginManagerTesting;
import com.johnbryce.project.exception.NotExistException;
import com.johnbryce.project.job.DailyTask;

public class TestAllFunc {

	public static void testAll() throws SQLException, NotExistException {
		Thread t1 = new Thread(new DailyTask());
		TestInit.init();
		t1.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			System.out.println(e1.getMessage());
		}
		CouponDBDAOTesting.main(null);
		CustomersDBDAOTesting.main(null);
		CompaniesDBDAOTesting.main(null);
		
		CustomerFacadeTesting.main(null);
		CompanyFacadeTesting.main(null);
		AdminFacadeTesting.main(null);
		
		LoginManagerTesting.main(null);
		t1.stop();
		try {
			ConnectionPool.getInstance().closeAllConnection();
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
