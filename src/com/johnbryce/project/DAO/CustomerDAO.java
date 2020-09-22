package com.johnbryce.project.DAO;

import java.util.List;

import com.johnbryce.project.beans.Customer;

public interface CustomerDAO {
	boolean isCustomerExists (String email, String password);
	void addCustomer(Customer company);
	void updateCustomer(Customer customer);
	void deleteCustomer(int customerID);
	List<Customer> getAllCustomers();
	Customer getOneCustomer(int customerID);
	int getCustomerId(String email, String password);
}
