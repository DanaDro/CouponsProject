package com.johnbryce.project.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.johnbryce.project.beans.Category;

public class DataBaseManager {

	private static final String URl = "jdbc:mysql://localhost:3306/project?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=Asia/Jerusalem";
	private static final String USER = "root";
	private static final String PASS = "1234";

	private static final String QUERY_CREATE_COMPANIES_TABLE = "CREATE TABLE `project`.`companies` (\r\n" + 
			"  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `email` VARCHAR(45) NOT NULL,\r\n" + 
			"  `password` VARCHAR(45) NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`));";
	private static final String QUERY_DROP_COMPANIES_TABLE = "DROP TABLE `project`.`companies`";
	private static final String QUERY_CREATE_CUSTOMERS_TABLE = "CREATE TABLE `project`.`customers` (\r\n" + 
			"  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `first_name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `last_name` VARCHAR(45) NOT NULL,\r\n" + 
			"  `email` VARCHAR(45) NOT NULL,\r\n" + 
			"  `password` VARCHAR(45) NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`));";
	private static final String QUERY_DROP_CUSTOMERS_TABLE = "DROP TABLE `project`.`customers`";
	private static final String QUERY_CREATE_CATEGORIES_TABLE = "CREATE TABLE `project`.`categories` (\r\n" + 
			"  `id` INT NOT NULL AUTO_INCREMENT,\r\n" + 
			"  `name` VARCHAR(45) NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`));";
	private static final String QUERY_DROP_CATEGORIES_TABLE = "DROP TABLE `project`.`categories`";
	private static final String QUERY_CREATE_COUPONS_TABLE = "CREATE TABLE `project`.`coupons` (\r\n" + 
			"  `id` INT NOT NULL AUTO_INCREMENT, \r\n" + 
			"  `company_id` INT NOT NULL,\r\n" + 
			"  `category_id` INT NOT NULL,\r\n" + 
			"  `title` VARCHAR(45) NOT NULL,\r\n" + 
			"  `description` VARCHAR(45) NOT NULL,\r\n" + 
			"  `start_date` DATE NOT NULL,\r\n" + 
			"  `end_date` DATE NOT NULL,\r\n" + 
			"  `amount` INT NOT NULL,\r\n" + 
			"  `price` DOUBLE NOT NULL,\r\n" + 
			"  `image` VARCHAR(45) NOT NULL,\r\n" + 
			"  PRIMARY KEY (`id`),\r\n" + 
			"  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\r\n" + 
			"  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\r\n" + 
			"  CONSTRAINT `company_id`\r\n" + 
			"    FOREIGN KEY (`company_id`)\r\n" + 
			"    REFERENCES `project`.`companies` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION,\r\n" + 
			"  CONSTRAINT `category_id`\r\n" + 
			"    FOREIGN KEY (`category_id`)\r\n" + 
			"    REFERENCES `project`.`categories` (`id`)\r\n" + 
			"    ON DELETE NO ACTION\r\n" + 
			"    ON UPDATE NO ACTION);";
	private static final String QUERY_DROP_COUPONS_TABLE = "DROP TABLE `project`.`coupons`";
	private static final String QUERY_CREATE_CUTOMERS_VS_COUPONS_TABLE = "CREATE TABLE `project`.`customers_vs_coupons` (\r\n" + 
			"			  `customer_id` INT NOT NULL,\r\n" + 
			"			  `coupon_id` INT NOT NULL,\r\n" + 
			"			  PRIMARY KEY (`customer_id`, `coupon_id`),\r\n" + 
			"			  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\r\n" + 
			"			  CONSTRAINT `customer_id`\r\n" + 
			"			    FOREIGN KEY (`customer_id`)\r\n" + 
			"			    REFERENCES `project`.`customers` (`id`)\r\n" + 
			"			    ON DELETE NO ACTION\r\n" + 
			"			    ON UPDATE NO ACTION,\r\n" + 
			"			  CONSTRAINT `coupon_id`\r\n" + 
			"			    FOREIGN KEY (`coupon_id`)\r\n" + 
			"			    REFERENCES `project`.`coupons` (`id`)\r\n" + 
			"			    ON DELETE NO ACTION\r\n" + 
			"			    ON UPDATE NO ACTION);";
	private static final String QUERY_DROP_CUTOMERS_VS_COUPONS_TABLE = "DROP TABLE `project`.`customers_vs_coupons`";
	private static final String QUERY_INSERT_CATEGORY = "INSERT INTO `project`.`categories` (name) VALUES (?);";
	
	public static void runQuery(String QUERY) {
		Connection conn = null;
		try {

			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY);
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}

	public static void addCategory(Category category) {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			PreparedStatement statement = conn.prepareStatement(QUERY_INSERT_CATEGORY);
			statement.setString(1, category.name());
			statement.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionPool.getInstance().returnConnection(conn);
		}
	}
	
	public static void dropAndCreateTable() throws SQLException {
		runQuery(QUERY_DROP_CUTOMERS_VS_COUPONS_TABLE);
		runQuery(QUERY_DROP_COUPONS_TABLE);
		runQuery(QUERY_DROP_COMPANIES_TABLE);
		runQuery(QUERY_DROP_CUSTOMERS_TABLE);
		runQuery(QUERY_DROP_CATEGORIES_TABLE);
		
		runQuery(QUERY_CREATE_CATEGORIES_TABLE);
		runQuery(QUERY_CREATE_CUSTOMERS_TABLE);
		runQuery(QUERY_CREATE_COMPANIES_TABLE);
		runQuery(QUERY_CREATE_COUPONS_TABLE);
		runQuery(QUERY_CREATE_CUTOMERS_VS_COUPONS_TABLE);
		
		for (Category category : Category.values()) {
			addCategory(category);
		}
	}
	
	public static String getUrl() {
		return URl;
	}

	public static String getUser() {
		return USER;
	}

	public static String getPass() {
		return PASS;
	}

}