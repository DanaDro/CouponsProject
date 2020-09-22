package com.johnbryce.project.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.project.DAO.CustomerDAO;
import com.johnbryce.project.DB.ConnectionPool;
import com.johnbryce.project.beans.Customer;

public class CustomerDBDAO implements CustomerDAO {
	private static final String QUERY_INSERT_CUSTOMER = "INSERT INTO `project`.`customers` (`first_name`, `last_name`, `email`, `password`) VALUES (? ,?, ?, ?);";
	private static final String QUERY_UPDATE_CUSTOMER = "UPDATE `project`.`customers` SET `first_name` = ?, `last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
	private static final String QUERY_DELETE_CUSTOMER = "DELETE FROM project.customers WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL_CUSTOMER = "SELECT * FROM project.customers;";
	private static final String QUERY_GET_ONE_CUSTOMER = "SELECT * FROM project.customers WHERE (`id` = ?);";
	private static final String QUERY_IS_CUSTOMER_EXIST = "SELECT * from project.customers WHERE (Email=?) AND (Password=?);";

	@Override
	public boolean isCustomerExists(String email, String password) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_CUSTOMER_EXIST);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return false;
	}

	@Override
	public void addCustomer(Customer customer) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT_CUSTOMER);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void updateCustomer(Customer customer) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE_CUSTOMER);
			statement.setString(1, customer.getFirstName());
			statement.setString(2, customer.getLastName());
			statement.setString(3, customer.getEmail());
			statement.setString(4, customer.getPassword());
			statement.setInt(5, customer.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCustomer(int customerID) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_CUSTOMER);
			statement.setInt(1, customerID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public List<Customer> getAllCustomers() {
		Connection conn = null;
		List<Customer> customers = new ArrayList<>();
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL_CUSTOMER);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Customer customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), null);
				customers.add(customer);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return customers;
	}

	@Override
	public Customer getOneCustomer(int customerID) {
		Connection conn = null;
		Customer customer = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE_CUSTOMER);
			statement.setInt(1, customerID);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				customer = new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getString(5), null);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return customer;
	}

	@Override
	public int getCustomerId(String email, String password) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_CUSTOMER_EXIST);
			statement.setString(1, email);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return 0;
	}
}
