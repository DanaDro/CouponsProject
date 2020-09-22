package com.johnbryce.project.clr.dbdao;

import java.sql.SQLException;

import com.johnbryce.project.beans.Customer;
import com.johnbryce.project.clr.TestInit;
import com.johnbryce.project.dbdao.CustomerDBDAO;
import com.johnbryce.project.utils.Headers;
import com.johnbryce.project.utils.Ido;

public class CustomersDBDAOTesting {

	public static void main(String[] args) throws SQLException {
		
		TestInit.init();
		Headers.CustomersDBDAOTesting();
		
		CustomerDBDAO customers = new CustomerDBDAO();
		
		System.out.println("--------add customers--------");
		Customer customer = new Customer("Maya", "Drosvit", "maya@gmail.com", "12345", null);
		customers.addCustomer(customer);
		Ido.print(customers.getAllCustomers());
		
		System.out.println("--------Update customers--------");
		customer = customers.getOneCustomer(5);
		customer.setEmail("mayadrosvit@gmail.com");
		customer.setPassword("123456");
		customers.updateCustomer(customer);
		Ido.print(customers.getOneCustomer(5));
		
		System.out.println("--------Delete customers--------");
		customers.deleteCustomer(5);
		Ido.print(customers.getAllCustomers());
		
		System.out.println("--------One customer--------");
		Ido.print(customers.getOneCustomer(3));
		
		System.out.println("--------All customers--------");
		Ido.print(customers.getAllCustomers());
		
		System.out.println("--------customer exists--------");
		System.out.println(customers.isCustomerExists("danielram@gmail.com", "danielram"));
	}
}
