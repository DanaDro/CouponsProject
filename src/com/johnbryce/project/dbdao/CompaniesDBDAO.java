package com.johnbryce.project.dbdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.johnbryce.project.DAO.CompaniesDAO;
import com.johnbryce.project.DB.ConnectionPool;
import com.johnbryce.project.beans.Company;

public class CompaniesDBDAO implements CompaniesDAO {

	private static final String QUERY_INSERT_COMPANIES = "INSERT INTO `project`.`companies` (`name`, `email`, `password`) VALUES (?, ?, ?);";
	private static final String QUERY_UPDATE_COMPANIES = "UPDATE `project`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?);";
	private static final String QUERY_DELETE_COMPANIES = "DELETE FROM project.companies WHERE (`id` = ?);";
	private static final String QUERY_GET_ALL_COMPANIES = "SELECT * FROM project.companies;";
	private static final String QUERY_GET_ONE_COMPANIES = "SELECT * FROM project.companies WHERE (`id` = ?);";
	private static final String QUERY_IS_COMPANY_EXIST = "SELECT * from project.companies WHERE (Email=?) AND (Password=?);";

	@Override
	public boolean isCompanyExists(String email, String password) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_COMPANY_EXIST);
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
	public void addCompany(Company company) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT_COMPANIES);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void updateCompany(Company company) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_UPDATE_COMPANIES);
			statement.setString(1, company.getName());
			statement.setString(2, company.getEmail());
			statement.setString(3, company.getPassword());
			statement.setInt(4, company.getId());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public void deleteCompany(int companyID) {
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_DELETE_COMPANIES);
			statement.setInt(1, companyID);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	@Override
	public List<Company> getAllCompanies() {
		List<Company> companies = new ArrayList<>();
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ALL_COMPANIES);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				Company company = new Company(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
				companies.add(company);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return companies;
	}

	@Override
	public Company getOneCompany(int companyID) {
		Connection conn = null;
		Company company = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_GET_ONE_COMPANIES);
			statement.setInt(1, companyID);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			company = new Company(resultSet.getInt(1),resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
		return company;
	}

	@Override
	public int getCompanyId(String email, String password) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_IS_COMPANY_EXIST);
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
