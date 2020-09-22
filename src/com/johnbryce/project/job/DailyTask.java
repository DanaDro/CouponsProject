package com.johnbryce.project.job;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.johnbryce.project.DAO.CouponsDAO;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.dbdao.CouponDBDAO;

public class DailyTask implements Runnable {

	private CouponsDAO couponsDAO = null;
	private boolean quit = false;

	public DailyTask() {
		this.couponsDAO = new CouponDBDAO();
	}

	@Override
	public void run() {
		while (quit == false) {
			List<Coupon> coupons = couponsDAO.getAllCoupons();
			for (Coupon coupon : coupons) {
				if(coupon.getEndDate().before(Date.valueOf(LocalDate.now()))) {
					couponsDAO.deleteCustomerVsCouponsByCouponID(coupon.getId());
					couponsDAO.deleteCoupon(coupon.getId());
				}
			}
			try {
				Thread.sleep(1000 * 60 * 60 * 24);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void stop() {
		quit= true;
	}
}
