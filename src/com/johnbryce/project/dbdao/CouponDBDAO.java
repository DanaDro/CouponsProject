package com.johnbryce.project.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.project.DAO.CouponsDAO;
import com.johnbryce.project.DB.ConnectionPool;
import com.johnbryce.project.beans.Category;
import com.johnbryce.project.beans.Coupon;
import com.johnbryce.project.beans.Customer_vs_coupon;

public class CouponDBDAO implements CouponsDAO {
	private static final String QUERY_INSERT_COUPON = "INSERT INTO `project`.`coupons` (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
	private static final String QUERY_UPDATE_COUPON = "UPDATE `project`.`coupons` SET `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?);";
	private static final String QUERY_DELETE_COUPON = "DELETE FROM project.coupons WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL_COUPON = "SELECT * FROM project.coupons;";
	private static final String QUERY_GET_ONE_COUPON = "SELECT * FROM project.coupons WHERE (`id` = ?);";
	private static final String QUERY_ADD_COUPON_PURCHASE = "INSERT INTO `project`.`customers_vs_coupons`(`customer_id`, `coupon_id`) VALUES(?,?) ;";
	private static final String QUERY_DELETE_COUPON_PURCHASE = "DELETE FROM project.customers_vs_coupons WHERE (`customer_id` = ? AND `coupon_id` = ?);";
	private static final String QUERY_GET_COUPONS_VS_CUTOMERS_BY_COUPON_ID = "SELECT * FROM project.customers_vs_coupons WHERE (`coupon_id` = ?);";
	private static final String QUERY_GET_ALL_COMPANYS_COUPON = "SELECT * FROM project.coupons WHERE (`company_id` = ?);";
	private static final String QUERY_DELETE_COUPONS_VS_CUTOMERS_BY_COUPON_ID = "DELETE FROM project.customers_vs_coupons WHERE (`coupon_id` = ?);";
	private static final String QUERY_GET_CUSTOMERS_COUPONS = "SELECT * FROM project.customers_vs_coupons WHERE (`customer_id` = ?);";
	
	@Override
	public void addCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT_COUPON);
			statement.setInt(1, coupon.getCompanyId());
			statement.setInt(2, coupon.getCategory().ordinal() + 1);
			statement.setString(3, coupon.getTitle());
			statement.setString(4, coupon.getDescription());
			statement.setDate(5, coupon.getStartDate());
			statement.setDate(6, coupon.getEndDate());
			statement.setInt(7, coupon.getAmount());
			statement.setDouble(8, coupon.getPrice());
			statement.setString(9, coupon.getImage());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE_COUPON);
			statement.setString(1, coupon.getTitle());
			statement.setString(2, coupon.getDescription());
			statement.setDate(3, coupon.getStartDate());
			statement.setDate(4, coupon.getEndDate());
			statement.setInt(5, coupon.getAmount());
			statement.setDouble(6, coupon.getPrice());
			statement.setString(7, coupon.getImage());
			statement.setInt(8, coupon.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCoupon(int couponId) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_COUPON);
			statement.setInt(1, couponId);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public List<Coupon> getAllCoupons() {
		Connection conn = null;
		List<Coupon> coupons = new ArrayList<>();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL_COUPON);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Coupon coupon = new Coupon(resultSet.getInt(1), resultSet.getInt(2),
						Category.values()[resultSet.getInt(3) - 1], resultSet.getString(4), resultSet.getString(5),
						resultSet.getDate(6), resultSet.getDate(7), resultSet.getInt(8), resultSet.getDouble(9),
						resultSet.getString(10));
				coupons.add(coupon);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupons;
	}

	@Override
	public Coupon getOneCoupon(int couponId) {
		Connection conn = null;
		Coupon coupon = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE_COUPON);
			statement.setInt(1, couponId);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				coupon = new Coupon(resultSet.getInt(1), resultSet.getInt(2),
						Category.values()[resultSet.getInt(3) - 1], resultSet.getString(4), resultSet.getString(5),
						resultSet.getDate(6), resultSet.getDate(7), resultSet.getInt(8), resultSet.getDouble(9),
						resultSet.getString(10));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupon;
	}

	@Override
	public void addCouponPurchase(int customerId, int couponId) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_ADD_COUPON_PURCHASE);
			statement.setInt(1, customerId);
			statement.setInt(2, couponId);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCouponPurchase(int customerId, int couponId) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_COUPON_PURCHASE);
			statement.setInt(1, customerId);
			statement.setInt(2, couponId);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public List<Customer_vs_coupon> getCouponsPurchased(int couponId) {
		Connection conn = null;
		List<Customer_vs_coupon> coupons_vs_customers = new ArrayList<>();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_COUPONS_VS_CUTOMERS_BY_COUPON_ID);
			statement.setInt(1, couponId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Customer_vs_coupon coupon = new Customer_vs_coupon(resultSet.getInt(1), resultSet.getInt(2));
				coupons_vs_customers.add(coupon);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupons_vs_customers;
	}

	@Override
	public List<Coupon> getCompanyCoupons(int companyId) {
		Connection conn = null;
		List<Coupon> coupons = new ArrayList<>();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL_COMPANYS_COUPON);
			statement.setInt(1, companyId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Coupon coupon = new Coupon(resultSet.getInt(1), resultSet.getInt(2),
						Category.values()[resultSet.getInt(3) - 1], resultSet.getString(4), resultSet.getString(5),
						resultSet.getDate(6), resultSet.getDate(7), resultSet.getInt(8), resultSet.getDouble(9),
						resultSet.getString(10));
				coupons.add(coupon);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupons;
	}

	@Override
	public void deleteCustomerVsCouponsByCouponID(int couponId) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_COUPONS_VS_CUTOMERS_BY_COUPON_ID);
			statement.setInt(1, couponId);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public List<Customer_vs_coupon> getCustomersCoupons(int customerId) {
		Connection conn = null;
		List<Customer_vs_coupon> coupons_vs_customers = new ArrayList<>();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_CUSTOMERS_COUPONS);
			statement.setInt(1, customerId);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Customer_vs_coupon coupon = new Customer_vs_coupon(resultSet.getInt(1), resultSet.getInt(2));
				coupons_vs_customers.add(coupon);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return coupons_vs_customers;
	}

}
